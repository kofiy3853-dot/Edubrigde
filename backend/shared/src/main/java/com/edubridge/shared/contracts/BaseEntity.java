package com.edubridge.shared.contracts;

import java.time.Instant;
import java.util.UUID;

public interface BaseEntity {
    UUID getId();
    Instant getCreatedAt();
    Instant getUpdatedAt();
}
