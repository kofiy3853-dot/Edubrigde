package com.edubridge.identity.entities;

import com.edubridge.shared.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "audit_logs", schema = "identity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditLog extends BaseEntity {

    @Column(name = "audit_id", nullable = false, unique = true)
    @Builder.Default
    private UUID auditId = UUID.randomUUID();

    @Column(name = "trace_id", length = 100)
    private String traceId;

    @Column(name = "request_id", length = 100)
    private String requestId;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "service_name", nullable = false, length = 100)
    private String serviceName;

    @Column(name = "operation", nullable = false, length = 100)
    private String operation;

    @Column(name = "resource_type", length = 50)
    private String resourceType;

    @Column(name = "resource_id", length = 100)
    private String resourceId;

    @Column(name = "action", nullable = false, length = 50)
    private String action;

    @Column(name = "result", nullable = false, length = 20)
    private String result;

    @Column(name = "ip_address", length = 45)
    private String ipAddress;

    @Column(name = "user_agent", length = 500)
    private String userAgent;

    @Column(name = "duration_ms")
    private Long durationMs;

    @Column(name = "error_message", length = 1000)
    private String errorMessage;

    @Column(name = "metadata_json", length = 5000)
    private String metadataJson;

    @Column(name = "timestamp", nullable = false)
    @Builder.Default
    private Instant timestamp = Instant.now();

    public enum Result {
        SUCCESS, FAILURE, DENIED, ERROR
    }

    public enum Action {
        CREATE, READ, UPDATE, DELETE, LOGIN, LOGOUT, REFRESH,
        VERIFY, RESET_PASSWORD, ASSIGN_ROLE, REVOKE_ROLE,
        LOCK_ACCOUNT, UNLOCK_ACCOUNT, SEND_NOTIFICATION
    }
}
