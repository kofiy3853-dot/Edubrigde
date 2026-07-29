package com.edubridge.identity.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "notifications", schema = "identity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification extends AuditableEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "type", nullable = false, length = 30)
    private String type;

    @Column(name = "channel", nullable = false, length = 20)
    private String channel;

    @Column(name = "subject", nullable = false, length = 255)
    private String subject;

    @Column(name = "body", nullable = false, length = 5000)
    private String body;

    @Column(name = "status", nullable = false, length = 20)
    @Builder.Default
    private String status = "PENDING";

    @Column(name = "sent_at")
    private Instant sentAt;

    @Column(name = "delivered_at")
    private Instant deliveredAt;

    @Column(name = "read_at")
    private Instant readAt;

    @Column(name = "error_message", length = 1000)
    private String errorMessage;

    @Column(name = "retry_count", nullable = false)
    @Builder.Default
    private Integer retryCount = 0;

    @Column(name = "max_retries", nullable = false)
    @Builder.Default
    private Integer maxRetries = 3;

    @Column(name = "event_type", length = 100)
    private String eventType;

    @Column(name = "event_id", length = 100)
    private String eventId;

    public enum Type {
        EMAIL_VERIFICATION, ACCOUNT_CREATED, ACCOUNT_ACTIVATED,
        PASSWORD_CHANGED, SESSION_REVOKED, ACCOUNT_LOCKED,
        ACCOUNT_SUSPENDED, PROFILE_UPDATED, SECURITY_ALERT
    }

    public enum Channel {
        EMAIL, SMS, PUSH, IN_APP
    }

    public enum Status {
        PENDING, SENT, DELIVERED, FAILED, RETRYING, EXPIRED
    }

    public void markAsSent() {
        this.status = Status.SENT.name();
        this.sentAt = Instant.now();
    }

    public void markAsDelivered() {
        this.status = Status.DELIVERED.name();
        this.deliveredAt = Instant.now();
    }

    public void markAsFailed(String error) {
        this.status = Status.FAILED.name();
        this.errorMessage = error;
    }

    public void markAsRead() {
        this.readAt = Instant.now();
    }
}
