package com.edubridge.financial.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EligibilityRuleResponse {
    private String id;
    private String scholarshipId;
    private String name;
    private String description;
    private String ruleType;
    private String ruleCategory;
    private String ruleCondition;
    private String ruleValue;
    private String operator;
    private Integer priority;
    private Boolean isMandatory;
    private Boolean isActive;
}
