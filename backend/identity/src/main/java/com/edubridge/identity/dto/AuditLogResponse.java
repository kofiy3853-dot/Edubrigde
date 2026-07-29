package com.edubridge.identity.dto;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class AuditLogResponse {

    private UUID id;
    private UUID auditId;
    private String traceId;
    private String requestId;
    private UUID userId;
    private String serviceName;
    private String operation;
    private String resourceType;
    private String resourceId;
    private String action;
    private String result;
    private String ipAddress;
    private Long durationMs;
    private String errorMessage;
    private Instant timestamp;
}
