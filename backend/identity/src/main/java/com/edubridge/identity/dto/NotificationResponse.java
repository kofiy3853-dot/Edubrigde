package com.edubridge.identity.dto;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class NotificationResponse {

    private UUID id;
    private String type;
    private String channel;
    private String subject;
    private String body;
    private String status;
    private Instant sentAt;
    private Instant deliveredAt;
    private Instant readAt;
    private Instant createdAt;
}
