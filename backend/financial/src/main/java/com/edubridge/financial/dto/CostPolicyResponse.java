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
public class CostPolicyResponse {
    private String id;
    private String universityId;
    private String countryCode;
    private String categoryCode;
    private String name;
    private String description;
    private String policyType;
    private String policyRule;
    private BigDecimal discountPercentage;
    private BigDecimal discountAmount;
    private BigDecimal minAmount;
    private BigDecimal maxDiscount;
    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;
    private Boolean isActive;
    private Boolean requiresApproval;
}
