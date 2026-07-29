package com.edubridge.identity.services;

import com.edubridge.identity.dto.CreateUserRequest;
import com.edubridge.identity.dto.UserResponse;
import com.edubridge.identity.entities.User;
import com.edubridge.identity.repositories.UserRepository;
import com.edubridge.shared.exceptions.BadRequestException;
import com.edubridge.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse createUser(CreateUserRequest request) {
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

        User savedUser = userRepository.save(user);
        log.info("User created: id={}, email={}", savedUser.getId(), savedUser.getEmail());

        return mapToResponse(savedUser);
    }

    @Transactional(readOnly = true)
    public UserResponse getUserById(UUID id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));
        return mapToResponse(user);
    }

    @Transactional(readOnly = true)
    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("User not found: " + email));
        return mapToResponse(user);
    }

    @Transactional(readOnly = true)
    public UserResponse getUserByEmailWithRoles(String email) {
        User user = userRepository.findByEmailWithRoles(email)
            .orElseThrow(() -> new ResourceNotFoundException("User not found: " + email));
        return mapToResponse(user);
    }

    @Transactional
    public void incrementFailedLoginAttempts(User user) {
        user.setFailedLoginAttempts(user.getFailedLoginAttempts() + 1);
        if (user.getFailedLoginAttempts() >= 5) {
            user.setAccountLocked(true);
            log.warn("Account locked due to failed login attempts: email={}", user.getEmail());
        }
        userRepository.save(user);
    }

    @Transactional
    public void resetFailedLoginAttempts(User user) {
        user.setFailedLoginAttempts(0);
        user.setAccountLocked(false);
        user.setAccountLockedUntil(null);
        userRepository.save(user);
    }

    private UserResponse mapToResponse(User user) {
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
