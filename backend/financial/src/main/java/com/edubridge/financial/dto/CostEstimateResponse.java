package com.edubridge.financial.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CostEstimateResponse {
    private String id;
    private String studentId;
    private String universityId;
    private String programId;
    private String countryCode;
    private String currencyCode;
    private Integer academicYear;
    private BigDecimal totalTuition;
    private BigDecimal totalAccommodation;
    private BigDecimal totalLivingExpenses;
    private BigDecimal totalOtherCosts;
    private BigDecimal grandTotal;
    private BigDecimal availableFunds;
    private BigDecimal financialGap;
    private BigDecimal affordabilityScore;
    private String riskLevel;
    private Boolean isFinalized;
    private LocalDate finalizedAt;
    private Boolean isActive;
}
