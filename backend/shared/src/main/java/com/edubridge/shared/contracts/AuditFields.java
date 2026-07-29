package com.edubridge.shared.contracts;

import java.time.Instant;
import java.util.UUID;

public record AuditFields(
    UUID id,
    Instant createdAt,
    Instant updatedAt
) {}
