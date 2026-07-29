package com.edubridge.financial.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinancialAssessmentResponse {
    private String id;
    private String studentId;
    private String universityId;
    private String programId;
    private String countryCode;
    private String status;
    private String assessmentType;
    private BigDecimal totalEstimatedCost;
    private BigDecimal totalAvailableFunds;
    private BigDecimal financialGap;
    private Integer affordabilityScore;
    private String riskLevel;
    private String assessmentResults;
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
