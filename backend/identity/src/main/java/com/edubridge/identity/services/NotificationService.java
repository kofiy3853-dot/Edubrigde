package com.edubridge.identity.services;

import com.edubridge.identity.dto.NotificationResponse;
import com.edubridge.identity.dto.NotificationStatsResponse;
import com.edubridge.identity.entities.Notification;
import com.edubridge.identity.entities.User;
import com.edubridge.identity.repositories.NotificationRepository;
import com.edubridge.identity.repositories.UserRepository;
import com.edubridge.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Transactional
    public void sendVerificationEmail(UUID userId, String email, String token) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Notification notification = Notification.builder()
            .user(user)
            .type(Notification.Type.EMAIL_VERIFICATION.name())
            .channel(Notification.Channel.EMAIL.name())
            .subject("Verify your email address")
            .body(buildVerificationEmailBody(user.getFirstName(), token))
            .status(Notification.Status.PENDING.name())
            .eventType("VerificationRequestedEvent")
            .build();

        notificationRepository.save(notification);
        log.info("Verification email queued: userId={}, email={}", userId, email);
    }

    @Transactional
    public void sendAccountCreatedNotification(UUID userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Notification notification = Notification.builder()
            .user(user)
            .type(Notification.Type.ACCOUNT_CREATED.name())
            .channel(Notification.Channel.EMAIL.name())
            .subject("Welcome to EduBridge OS")
            .body(buildAccountCreatedBody(user.getFirstName()))
            .status(Notification.Status.PENDING.name())
            .eventType("UserCreatedEvent")
            .build();

        notificationRepository.save(notification);
        log.info("Account created notification queued: userId={}", userId);
    }

    @Transactional
    public void sendPasswordChangedNotification(UUID userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Notification notification = Notification.builder()
            .user(user)
            .type(Notification.Type.PASSWORD_CHANGED.name())
            .channel(Notification.Channel.EMAIL.name())
            .subject("Your password has been changed")
            .body(buildPasswordChangedBody(user.getFirstName()))
            .status(Notification.Status.PENDING.name())
            .eventType("PasswordChangedEvent")
            .build();

        notificationRepository.save(notification);
        log.info("Password changed notification queued: userId={}", userId);
    }

    @Transactional
    public void sendSessionRevokedNotification(UUID userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Notification notification = Notification.builder()
            .user(user)
            .type(Notification.Type.SESSION_REVOKED.name())
            .channel(Notification.Channel.EMAIL.name())
            .subject("A session has been revoked")
            .body(buildSessionRevokedBody(user.getFirstName()))
            .status(Notification.Status.PENDING.name())
            .eventType("SessionRevokedEvent")
            .build();

        notificationRepository.save(notification);
        log.info("Session revoked notification queued: userId={}", userId);
    }

    @Transactional
    public void sendAccountLockedNotification(UUID userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Notification notification = Notification.builder()
            .user(user)
            .type(Notification.Type.ACCOUNT_LOCKED.name())
            .channel(Notification.Channel.EMAIL.name())
            .subject("Your account has been locked")
            .body(buildAccountLockedBody(user.getFirstName()))
            .status(Notification.Status.PENDING.name())
            .eventType("AccountLockedEvent")
            .build();

        notificationRepository.save(notification);
        log.info("Account locked notification queued: userId={}", userId);
    }

    @Transactional(readOnly = true)
    public List<NotificationResponse> getUserNotifications(UUID userId) {
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(userId).stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<NotificationResponse> getUnreadNotifications(UUID userId) {
        return notificationRepository.findUnreadByUserId(userId).stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public NotificationStatsResponse getNotificationStats(UUID userId) {
        NotificationStatsResponse stats = new NotificationStatsResponse();
        stats.setTotalNotifications(notificationRepository.count());
        stats.setUnreadCount(notificationRepository.countByUserIdAndStatus(userId, Notification.Status.DELIVERED.name()));
        stats.setSentCount(notificationRepository.countByUserIdAndStatus(userId, Notification.Status.SENT.name()));
        stats.setDeliveredCount(notificationRepository.countByUserIdAndStatus(userId, Notification.Status.DELIVERED.name()));
        stats.setFailedCount(notificationRepository.countByUserIdAndStatus(userId, Notification.Status.FAILED.name()));
        return stats;
    }

    @Transactional
    public void markAsRead(UUID notificationId, UUID userId) {
        Notification notification = notificationRepository.findById(notificationId)
            .orElseThrow(() -> new ResourceNotFoundException("Notification not found"));

        if (!notification.getUser().getId().equals(userId)) {
            throw new ResourceNotFoundException("Notification not found");
        }

        notification.markAsRead();
        notificationRepository.save(notification);
        log.info("Notification marked as read: notificationId={}", notificationId);
    }

    private NotificationResponse mapToResponse(Notification notification) {
        NotificationResponse response = new NotificationResponse();
        response.setId(notification.getId());
        response.setType(notification.getType());
        response.setChannel(notification.getChannel());
        response.setSubject(notification.getSubject());
        response.setBody(notification.getBody());
        response.setStatus(notification.getStatus());
        response.setSentAt(notification.getSentAt());
        response.setDeliveredAt(notification.getDeliveredAt());
        response.setReadAt(notification.getReadAt());
        response.setCreatedAt(notification.getCreatedAt());
        return response;
    }

    private String buildVerificationEmailBody(String firstName, String token) {
        return String.format(
            "Hello %s,\n\nPlease verify your email address by clicking the link below:\n\n" +
            "https://edubridge.com/verify?token=%s\n\n" +
            "This link will expire in 24 hours.\n\nBest regards,\nEduBridge OS Team",
            firstName, token
        );
    }

    private String buildAccountCreatedBody(String firstName) {
        return String.format(
            "Hello %s,\n\nWelcome to EduBridge OS! Your account has been created successfully.\n\n" +
            "Please verify your email address to activate your account.\n\nBest regards,\nEduBridge OS Team",
            firstName
        );
    }

    private String buildPasswordChangedBody(String firstName) {
        return String.format(
            "Hello %s,\n\nYour password has been changed successfully.\n\n" +
            "If you did not make this change, please contact support immediately.\n\nBest regards,\nEduBridge OS Team",
            firstName
        );
    }

    private String buildSessionRevokedBody(String firstName) {
        return String.format(
            "Hello %s,\n\nA session has been revoked from your account.\n\n" +
            "If you did not perform this action, please change your password immediately.\n\nBest regards,\nEduBridge OS Team",
            firstName
        );
    }

    private String buildAccountLockedBody(String firstName) {
        return String.format(
            "Hello %s,\n\nYour account has been locked due to multiple failed login attempts.\n\n" +
            "Please contact support to unlock your account.\n\nBest regards,\nEduBridge OS Team",
            firstName
        );
    }
}
