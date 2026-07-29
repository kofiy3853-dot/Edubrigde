package com.edubridge.identity.services;

import com.edubridge.identity.dto.*;
import com.edubridge.identity.entities.Session;
import com.edubridge.identity.entities.User;
import com.edubridge.identity.entities.VerificationToken;
import com.edubridge.identity.repositories.SessionRepository;
import com.edubridge.identity.repositories.UserRepository;
import com.edubridge.identity.repositories.VerificationTokenRepository;
import com.edubridge.identity.security.JWTConfigProperties;
import com.edubridge.identity.security.JWTTokenProvider;
import com.edubridge.shared.exceptions.BadRequestException;
import com.edubridge.shared.exceptions.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTTokenProvider tokenProvider;
    private final JWTConfigProperties jwtConfig;

    @Transactional
    public LoginResponse login(LoginRequest request, String ipAddress, String userAgent) {
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new UnauthorizedException("Invalid credentials"));

        if (!user.isAccountNonLocked()) {
            throw new UnauthorizedException("Account is locked");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            userRepository.incrementFailedLoginAttempts(user);
            throw new UnauthorizedException("Invalid credentials");
        }

        userRepository.resetFailedLoginAttempts(user);
        user.setLastLoginAt(Instant.now());
        userRepository.save(user);

        String accessToken = tokenProvider.generateAccessToken(
            user.getId(), user.getEmail(), Map.of("roles", user.getRoles())
        );
        String refreshToken = tokenProvider.generateRefreshToken(user.getId());

        Session session = Session.builder()
            .user(user)
            .tokenHash(tokenProvider.hashToken(accessToken))
            .refreshTokenHash(tokenProvider.hashToken(refreshToken))
            .ipAddress(ipAddress)
            .userAgent(userAgent)
            .expiresAt(Instant.now().plus(jwtConfig.getAccessExpiration(), ChronoUnit.SECONDS))
            .refreshExpiresAt(Instant.now().plus(jwtConfig.getRefreshExpiration(), ChronoUnit.SECONDS))
            .lastActivityAt(Instant.now())
            .isActive(true)
            .build();

        sessionRepository.save(session);
        log.info("User logged in: email={}, userId={}", user.getEmail(), user.getId());

        UserResponse userResponse = mapToUserResponse(user);

        return LoginResponse.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .tokenType("Bearer")
            .expiresIn(jwtConfig.getAccessExpiration())
            .user(userResponse)
            .build();
    }

    @Transactional
    public LoginResponse refresh(RefreshTokenRequest request, String ipAddress, String userAgent) {
        String refreshToken = request.getRefreshToken();

        if (!tokenProvider.isRefreshToken(refreshToken)) {
            throw new UnauthorizedException("Invalid refresh token");
        }

        UUID userId = tokenProvider.getUserIdFromToken(refreshToken);
        String tokenHash = tokenProvider.hashToken(refreshToken);

        Session session = sessionRepository.findByTokenHash(tokenHash)
            .orElseThrow(() -> new UnauthorizedException("Invalid refresh token"));

        if (!session.getIsActive() || session.isRefreshExpired()) {
            throw new UnauthorizedException("Refresh token expired or revoked");
        }

        session.revoke("TOKEN_ROTATION");
        sessionRepository.save(session);

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UnauthorizedException("User not found"));

        String newAccessToken = tokenProvider.generateAccessToken(
            user.getId(), user.getEmail(), Map.of("roles", user.getRoles())
        );
        String newRefreshToken = tokenProvider.generateRefreshToken(user.getId());

        Session newSession = Session.builder()
            .user(user)
            .tokenHash(tokenProvider.hashToken(newAccessToken))
            .refreshTokenHash(tokenProvider.hashToken(newRefreshToken))
            .ipAddress(ipAddress)
            .userAgent(userAgent)
            .expiresAt(Instant.now().plus(jwtConfig.getAccessExpiration(), ChronoUnit.SECONDS))
            .refreshExpiresAt(Instant.now().plus(jwtConfig.getRefreshExpiration(), ChronoUnit.SECONDS))
            .lastActivityAt(Instant.now())
            .isActive(true)
            .build();

        sessionRepository.save(newSession);
        log.info("Token refreshed: userId={}", userId);

        UserResponse userResponse = mapToUserResponse(user);

        return LoginResponse.builder()
            .accessToken(newAccessToken)
            .refreshToken(newRefreshToken)
            .tokenType("Bearer")
            .expiresIn(jwtConfig.getAccessExpiration())
            .user(userResponse)
            .build();
    }

    @Transactional
    public void logout(String tokenHash) {
        sessionRepository.findByTokenHash(tokenHash).ifPresent(session -> {
            session.revoke("USER_LOGOUT");
            sessionRepository.save(session);
            log.info("Session revoked: userId={}", session.getUser().getId());
        });
    }

    @Transactional
    public void logoutAll(UUID userId) {
        sessionRepository.findByUserIdAndIsActiveTrue(userId).forEach(session -> {
            session.revoke("USER_LOGOUT_ALL");
            sessionRepository.save(session);
        });
        log.info("All sessions revoked: userId={}", userId);
    }

    @Transactional
    public void register(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already exists: " + request.getEmail());
        }

        User user = User.builder()
            .email(request.getEmail())
            .passwordHash(passwordEncoder.encode(request.getPassword()))
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .phone(request.getPhone())
            .emailVerified(false)
            .phoneVerified(false)
            .accountLocked(false)
            .failedLoginAttempts(0)
            .status(User.Status.PENDING_VERIFICATION.name())
            .build();

        userRepository.save(user);

        String verificationToken = tokenProvider.generateAccessToken(
            user.getId(), user.getEmail(), Map.of("type", "email_verification")
        );

        VerificationToken token = VerificationToken.builder()
            .user(user)
            .tokenHash(tokenProvider.hashToken(verificationToken))
            .type(VerificationToken.Type.EMAIL_VERIFICATION.name())
            .expiresAt(Instant.now().plus(24, ChronoUnit.HOURS))
            .build();

        verificationTokenRepository.save(token);
        log.info("User registered: email={}, verification token generated", user.getEmail());

        // TODO: Send verification email (Step 23)
    }

    @Transactional
    public void verifyAccount(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findValidToken(
            tokenProvider.hashToken(token)
        ).orElseThrow(() -> new BadRequestException("Invalid or expired verification token"));

        User user = verificationToken.getUser();
        user.setEmailVerified(true);
        user.setStatus(User.Status.ACTIVE.name());
        userRepository.save(user);

        verificationToken.markAsUsed();
        verificationTokenRepository.save(verificationToken);

        log.info("Account verified: userId={}", user.getId());
    }

    @Transactional
    public void forgotPassword(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
            () -> new BadRequestException("If the email exists, a reset link has been sent")
        );

        String resetToken = tokenProvider.generateAccessToken(
            user.getId(), user.getEmail(), Map.of("type", "password_reset")
        );

        VerificationToken token = VerificationToken.builder()
            .user(user)
            .tokenHash(tokenProvider.hashToken(resetToken))
            .type(VerificationToken.Type.PASSWORD_RESET.name())
            .expiresAt(Instant.now().plus(1, ChronoUnit.HOURS))
            .build();

        verificationTokenRepository.save(token);
        log.info("Password reset requested: email={}", email);

        // TODO: Send password reset email (Step 23)
    }

    @Transactional
    public void resetPassword(String token, String newPassword) {
        VerificationToken resetToken = verificationTokenRepository.findValidToken(
            tokenProvider.hashToken(token)
        ).orElseThrow(() -> new BadRequestException("Invalid or expired reset token"));

        User user = resetToken.getUser();
        user.setPasswordHash(passwordEncoder.encode(newPassword));
        user.setPasswordChangedAt(Instant.now());
        userRepository.save(user);

        resetToken.markAsUsed();
        verificationTokenRepository.save(resetToken);

        logoutAll(user.getId());
        log.info("Password reset completed: userId={}", user.getId());
    }

    private UserResponse mapToUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setPhone(user.getPhone());
        response.setAvatarUrl(user.getAvatarUrl());
        response.setEmailVerified(user.getEmailVerified());
        response.setPhoneVerified(user.getPhoneVerified());
        response.setStatus(user.getStatus());
        response.setLastLoginAt(user.getLastLoginAt());
        response.setCreatedAt(user.getCreatedAt());
        return response;
    }
}
