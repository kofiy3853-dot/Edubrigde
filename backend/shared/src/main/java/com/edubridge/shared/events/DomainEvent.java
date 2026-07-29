package com.edubridge.shared.events;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public abstract class DomainEvent {
    private final UUID eventId;
    private final String eventType;
    private final String version;
    private final Instant timestamp;
    private final String source;
    private final Map<String, String> metadata;

    protected DomainEvent(String eventType, String version, String source) {
        this.eventId = UUID.randomUUID();
        this.eventType = eventType;
        this.version = version;
        this.timestamp = Instant.now();
        this.source = source;
        this.metadata = Map.of();
    }

    protected DomainEvent(String eventType, String version, String source, Map<String, String> metadata) {
        this.eventId = UUID.randomUUID();
        this.eventType = eventType;
        this.version = version;
        this.timestamp = Instant.now();
        this.source = source;
        this.metadata = metadata;
    }

    public UUID getEventId() { return eventId; }
    public String getEventType() { return eventType; }
    public String getVersion() { return version; }
    public Instant getTimestamp() { return timestamp; }
    public String getSource() { return source; }
    public Map<String, String> getMetadata() { return metadata; }
}
