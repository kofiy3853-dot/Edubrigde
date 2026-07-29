package com.edubridge.shared.sessions;

import java.time.Instant;
import java.util.UUID;

public record Session(
    UUID id,
    UUID userId,
    String tokenHash,
    String ipAddress,
    String userAgent,
    Instant expiresAt,
    Instant createdAt
) {
    public boolean isExpired() {
        return Instant.now().isAfter(expiresAt);
    }
}
