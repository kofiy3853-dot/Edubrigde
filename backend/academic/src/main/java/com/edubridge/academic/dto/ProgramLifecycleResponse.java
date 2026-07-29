package com.edubridge.academic.dto;

import lombok.*;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProgramLifecycleResponse {
    private String id;
    private String programId;
    private String status;
    private String previousStatus;
    private String reason;
    private String changedBy;
    private Instant effectiveDate;
    private Instant expirationDate;
    private Instant reviewDate;
    private String notes;
    private Boolean isCurrent;
    private Boolean isActive;
    private Instant createdAt;
    private Instant updatedAt;
}
