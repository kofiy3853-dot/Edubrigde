package com.edubridge.identity.entities;

import com.edubridge.shared.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "verification_tokens", schema = "identity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerificationToken extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "token_hash", nullable = false, unique = true)
    private String tokenHash;

    @Column(name = "type", nullable = false, length = 30)
    private String type;

    @Column(name = "expires_at", nullable = false)
    private Instant expiresAt;

    @Column(name = "used", nullable = false)
    @Builder.Default
    private Boolean used = false;

    @Column(name = "used_at")
    private Instant usedAt;

    public enum Type {
        EMAIL_VERIFICATION, PASSWORD_RESET
    }

    public boolean isExpired() {
        return Instant.now().isAfter(expiresAt);
    }

    public void markAsUsed() {
        this.used = true;
        this.usedAt = Instant.now();
    }
}
