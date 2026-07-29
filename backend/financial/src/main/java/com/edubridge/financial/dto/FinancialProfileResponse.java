package com.edubridge.financial.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinancialProfileResponse {
    private String id;
    private String studentId;
    private String universityId;
    private String programId;
    private String countryCode;
    private String currencyCode;
    private BigDecimal estimatedTuition;
    private BigDecimal estimatedLivingExpenses;
    private BigDecimal estimatedAccommodation;
    private BigDecimal estimatedTransportation;
    private BigDecimal estimatedFood;
    private BigDecimal estimatedHealthcare;
    private BigDecimal estimatedAcademicExpenses;
    private BigDecimal totalEstimatedCost;
    private BigDecimal availableFunds;
    private BigDecimal expectedScholarships;
    private BigDecimal expectedLoans;
    private BigDecimal financialGap;
    private Integer affordabilityScore;
    private String riskLevel;
    private String notes;
    private Boolean isActive;
    private Instant createdAt;
    private Instant updatedAt;
}
