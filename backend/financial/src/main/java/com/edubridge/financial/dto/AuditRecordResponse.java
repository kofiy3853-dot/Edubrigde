package com.edubridge.financial.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditRecordResponse {
    private String id;
    private String scholarshipId;
    private String studentId;
    private String action;
    private String entityType;
    private String entityId;
    private String oldValue;
    private String newValue;
    private String performedBy;
    private LocalDateTime performedAt;
    private Boolean isActive;
}
