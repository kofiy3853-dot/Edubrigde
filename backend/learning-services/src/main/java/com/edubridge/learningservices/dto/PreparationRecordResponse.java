package com.edubridge.learningservices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PreparationRecordResponse {
    private String id;
    private String courseProfileId;
    private String studentId;
    private String preparationType;
    private String preparationStatus;
    private String targetInstitution;
    private String targetProgram;
    private Integer readinessScore;
    private String requirementsMet;
    private String requirementsPending;
    private LocalDateTime targetDate;
    private String notes;
    private Boolean isActive;
}
