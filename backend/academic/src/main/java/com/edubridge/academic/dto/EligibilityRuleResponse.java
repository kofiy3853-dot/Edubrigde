package com.edubridge.academic.dto;

import lombok.*;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EligibilityRuleResponse {
    private String id;
    private String universityId;
    private String programId;
    private String ruleType;
    private String name;
    private String description;
    private String ruleExpression;
    private String effect;
    private String conditions;
    private Integer priority;
    private Boolean isMandatory;
    private Boolean isActive;
    private Instant createdAt;
    private Instant updatedAt;
}
