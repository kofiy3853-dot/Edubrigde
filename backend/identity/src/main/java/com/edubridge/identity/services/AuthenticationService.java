package com.edubridge.identity.services;

import com.edubridge.identity.dto.LoginRequest;
import com.edubridge.identity.dto.LoginResponse;
import com.edubridge.identity.dto.RefreshTokenRequest;
import com.edubridge.identity.dto.UserResponse;
import com.edubridge.identity.entities.Session;
import com.edubridge.identity.entities.User;
import com.edubridge.identity.repositories.SessionRepository;
import com.edubridge.identity.repositories.UserRepository;
import com.edubridge.shared.exceptions.BadRequestException;
import com.edubridge.shared.exceptions.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.expiration:3600}")
    private long jwtExpiration;

    @Value("${jwt.refresh-expiration:604800}")
    private long refreshExpiration;

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

        String accessToken = generateAccessToken(user);
        String refreshToken = generateRefreshToken(user);

        Session session = Session.builder()
            .user(user)
            .tokenHash(hashToken(accessToken))
            .refreshTokenHash(hashToken(refreshToken))
            .ipAddress(ipAddress)
            .userAgent(userAgent)
            .expiresAt(Instant.now().plus(jwtExpiration, ChronoUnit.SECONDS))
            .refreshExpiresAt(Instant.now().plus(refreshExpiration, ChronoUnit.SECONDS))
            .lastActivityAt(Instant.now())
            .isActive(true)
            .build();

        sessionRepository.save(session);
        log.info("User logged in: email={}", user.getEmail());

        UserResponse userResponse = mapToUserResponse(user);

        return LoginResponse.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .tokenType("Bearer")
            .expiresIn(jwtExpiration)
            .user(userResponse)
            .build();
    }

    @Transactional
    public void logout(String tokenHash) {
        sessionRepository.findByTokenHash(tokenHash).ifPresent(session -> {
            session.revoke("USER_LOGOUT");
            sessionRepository.save(session);
        });
    }

    @Transactional
    public void logoutAll(UUID userId) {
        sessionRepository.findByUserIdAndIsActiveTrue(userId).forEach(session -> {
            session.revoke("USER_LOGOUT_ALL");
            sessionRepository.save(session);
        });
    }

    private String generateAccessToken(User user) {
        // Placeholder - will be implemented with JWT in Step 22
        return UUID.randomUUID().toString();
    }

    private String generateRefreshToken(User user) {
        // Placeholder - will be implemented with JWT in Step 22
        return UUID.randomUUID().toString();
    }

    private String hashToken(String token) {
        // Placeholder - will be implemented with proper hashing in Step 22
        return token;
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
