package com.edubridge.learningservices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GrowthLifecycleRecordResponse {
    private String id;
    private String growthProfileId;
    private String studentId;
    private String entityType;
    private String entityId;
    private String previousStatus;
    private String newStatus;
    private String reason;
    private String changedBy;
    private LocalDateTime changedAt;
    private Boolean isActive;
}
