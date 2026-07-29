package com.edubridge.academic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdmissionRequirementResponse {
    private String id;
    private String universityId;
    private String programId;
    private String requirementType;
    private String name;
    private String description;
    private Boolean isMandatory;
    private String validationRule;
    private String minValue;
    private String maxValue;
    private String allowedValues;
    private Boolean isActive;
    private Integer priority;
    private Instant createdAt;
    private Instant updatedAt;
}
