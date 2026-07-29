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
public class BudgetProfileResponse {
    private String id;
    private String studentId;
    private String universityId;
    private String programId;
    private String countryCode;
    private String currencyCode;
    private Integer academicYear;
    private Integer academicDuration;
    private BigDecimal monthlyBudget;
    private BigDecimal annualBudget;
    private BigDecimal totalEducationalCost;
    private BigDecimal totalLivingExpense;
    private BigDecimal totalEmergencyReserve;
    private BigDecimal availableFunds;
    private BigDecimal financialGap;
    private String affordabilityStatus;
    private String riskLevel;
    private Boolean isFinalized;
    private LocalDateTime finalizedAt;
    private Boolean isActive;
}
