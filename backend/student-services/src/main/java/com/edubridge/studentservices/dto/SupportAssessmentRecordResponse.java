package com.edubridge.studentservices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupportAssessmentRecordResponse {
    private String id;
    private String supportProfileId;
    private String studentId;
    private String assessmentType;
    private String assessmentStatus;
    private String riskLevel;
    private BigDecimal riskScore;
    private String assessmentNotes;
    private String recommendations;
    private String assessedBy;
    private LocalDateTime assessedAt;
    private Boolean isActive;
}
