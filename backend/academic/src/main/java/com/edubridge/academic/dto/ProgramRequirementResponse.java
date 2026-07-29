package com.edubridge.academic.dto;

import lombok.*;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProgramRequirementResponse {
    private String id;
    private String programId;
    private String requirementType;
    private String name;
    private String description;
    private Boolean isMandatory;
    private String validationRule;
    private String minValue;
    private String maxValue;
    private String allowedValues;
    private Integer weight;
    private Boolean isActive;
    private Integer sortOrder;
    private Instant createdAt;
    private Instant updatedAt;
}
