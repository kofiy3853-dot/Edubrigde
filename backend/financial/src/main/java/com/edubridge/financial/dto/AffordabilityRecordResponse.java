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
public class AffordabilityRecordResponse {
    private String id;
    private String budgetProfileId;
    private String studentId;
    private String universityId;
    private String countryCode;
    private String affordabilityType;
    private String affordabilityStatus;
    private BigDecimal affordabilityScore;
    private BigDecimal educationalAffordability;
    private BigDecimal livingAffordability;
    private BigDecimal emergencyAffordability;
    private BigDecimal totalAffordability;
    private String affordabilityNotes;
    private String assessedBy;
    private LocalDateTime assessedAt;
    private Boolean isActive;
}
