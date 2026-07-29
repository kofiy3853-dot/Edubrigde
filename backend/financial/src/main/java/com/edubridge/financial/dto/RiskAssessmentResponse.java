package com.edubridge.financial.dto;

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
public class RiskAssessmentResponse {
    private String id;
    private String budgetProfileId;
    private String studentId;
    private String universityId;
    private String countryCode;
    private String riskType;
    private String riskLevel;
    private BigDecimal riskScore;
    private BigDecimal budgetRisk;
    private BigDecimal costRisk;
    private BigDecimal countryRisk;
    private BigDecimal academicRisk;
    private BigDecimal financialRisk;
    private String riskNotes;
    private String assessedBy;
    private LocalDateTime assessedAt;
    private Boolean isActive;
}
