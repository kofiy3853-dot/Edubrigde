package com.edubridge.shared.events;

import java.time.Instant;
import java.util.UUID;

public record EventEnvelope(
    UUID eventId,
    String eventType,
    String version,
    Instant timestamp,
    String source,
    Object payload
) {
    public static <T> EventEnvelope of(String eventType, String version, String source, T payload) {
        return new EventEnvelope(
            UUID.randomUUID(),
            eventType,
            version,
            Instant.now(),
            source,
            payload
        );
    }
}
