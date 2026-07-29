package com.edubridge.academic.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdmissionAssessmentResponse {
    private String id;
    private String universityId;
    private String programId;
    private String studentId;
    private String status;
    private String assessmentType;
    private BigDecimal academicScore;
    private BigDecimal languageScore;
    private BigDecimal overallScore;
    private String eligibilityResult;
    private String validationResults;
    private String policyResults;
    private String notes;
    private String assessedBy;
    private Instant assessedAt;
    private Instant expirationDate;
    private Boolean isActive;
    private Instant createdAt;
    private Instant updatedAt;
}
