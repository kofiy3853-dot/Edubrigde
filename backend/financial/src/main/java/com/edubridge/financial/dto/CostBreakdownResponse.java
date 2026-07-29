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
public class CostBreakdownResponse {
    private String id;
    private String universityId;
    private String programId;
    private String countryCode;
    private String name;
    private String description;
    private Integer academicYear;
    private BigDecimal totalTuition;
    private BigDecimal totalAccommodation;
    private BigDecimal totalLivingExpenses;
    private BigDecimal totalOtherCosts;
    private BigDecimal grandTotal;
    private String currencyCode;
    private Boolean isEstimated;
    private String source;
    private LocalDate validFrom;
    private LocalDate validTo;
    private Boolean isActive;
}
