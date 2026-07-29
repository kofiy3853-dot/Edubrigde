package com.edubridge.academic.dto;

import lombok.*;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcademicValidationResponse {
    private String id;
    private String universityId;
    private String programId;
    private String validationType;
    private String name;
    private String description;
    private String validationRule;
    private String minValue;
    private String maxValue;
    private String allowedValues;
    private Boolean isMandatory;
    private Integer weight;
    private Boolean isActive;
    private Instant createdAt;
    private Instant updatedAt;
}
