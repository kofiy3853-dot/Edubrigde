package com.edubridge.financial.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BudgetPolicyResponse {
    private String id;
    private String universityId;
    private String countryCode;
    private String name;
    private String description;
    private String policyType;
    private String policyRule;
    private String effect;
    private Integer priority;
    private String conditions;
    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;
    private Boolean isActive;
}
