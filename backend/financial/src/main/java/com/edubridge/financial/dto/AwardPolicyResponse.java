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
public class AwardPolicyResponse {
    private String id;
    private String scholarshipId;
    private String name;
    private String description;
    private String policyType;
    private String coverageType;
    private BigDecimal amount;
    private BigDecimal percentage;
    private Integer maxDuration;
    private Boolean renewalRequired;
    private BigDecimal minGpa;
    private Integer minCredits;
    private String conditions;
    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;
    private Boolean isActive;
}
