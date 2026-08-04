package com.edubridge.identity.services;

import com.edubridge.identity.dto.CreateUserRequest;
import com.edubridge.identity.dto.UserResponse;
import com.edubridge.identity.entities.User;
import com.edubridge.identity.repositories.UserRepository;
import com.edubridge.shared.exceptions.BadRequestException;
import com.edubridge.shared.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private User user;
    private CreateUserRequest createUserRequest;

    @BeforeEach
    void setUp() {
        user = User.builder()
            .id(UUID.randomUUID())
            .email("test@example.com")
            .passwordHash("encodedPassword")
            .firstName("John")
            .lastName("Doe")
            .phone("+1234567890")
            .emailVerified(false)
            .phoneVerified(false)
            .accountLocked(false)
            .failedLoginAttempts(0)
            .status(User.Status.PENDING_VERIFICATION.name())
            .createdAt(LocalDateTime.now())
            .build();

        createUserRequest = new CreateUserRequest();
        createUserRequest.setEmail("test@example.com");
        createUserRequest.setPassword("password123");
        createUserRequest.setFirstName("John");
        createUserRequest.setLastName("Doe");
        createUserRequest.setPhone("+1234567890");
    }

    @Test
    void createUser_Success() {
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserResponse response = userService.createUser(createUserRequest);

        assertNotNull(response);
        assertEquals("test@example.com", response.getEmail());
        assertEquals("John", response.getFirstName());
        assertEquals("Doe", response.getLastName());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void createUser_DuplicateEmail_ThrowsException() {
        when(userRepository.existsByEmail(anyString())).thenReturn(true);

        assertThrows(BadRequestException.class, () -> userService.createUser(createUserRequest));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void getUserById_Success() {
        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.of(user));

        UserResponse response = userService.getUserById(user.getId());

        assertNotNull(response);
        assertEquals(user.getId(), response.getId());
        assertEquals("test@example.com", response.getEmail());
    }

    @Test
    void getUserById_NotFound_ThrowsException() {
        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(UUID.randomUUID()));
    }

    @Test
    void getUserByEmail_Success() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));

        UserResponse response = userService.getUserByEmail("test@example.com");

        assertNotNull(response);
        assertEquals("test@example.com", response.getEmail());
    }

    @Test
    void getUserByEmail_NotFound_ThrowsException() {
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.getUserByEmail("notfound@example.com"));
    }

    @Test
    void incrementFailedLoginAttempts_BelowThreshold() {
        user.setFailedLoginAttempts(2);
        when(userRepository.save(any(User.class))).thenReturn(user);

        userService.incrementFailedLoginAttempts(user);

        assertEquals(3, user.getFailedLoginAttempts());
        assertFalse(user.getAccountLocked());
    }

    @Test
    void incrementFailedLoginAttempts_ExceedsThreshold_LocksAccount() {
        user.setFailedLoginAttempts(4);
        when(userRepository.save(any(User.class))).thenReturn(user);

        userService.incrementFailedLoginAttempts(user);

        assertEquals(5, user.getFailedLoginAttempts());
        assertTrue(user.getAccountLocked());
    }

    @Test
    void resetFailedLoginAttempts_Success() {
        user.setFailedLoginAttempts(3);
        user.setAccountLocked(true);
        when(userRepository.save(any(User.class))).thenReturn(user);

        userService.resetFailedLoginAttempts(user);

        assertEquals(0, user.getFailedLoginAttempts());
        assertFalse(user.getAccountLocked());
        assertNull(user.getAccountLockedUntil());
    }
}
