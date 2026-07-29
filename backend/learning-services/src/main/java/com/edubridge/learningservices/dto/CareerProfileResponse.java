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
public class CareerProfileResponse {
    private String id;
    private String studentId;
    private String careerStatus;
    private String careerField;
    private String careerSubfield;
    private String careerGoal;
    private String careerVision;
    private String industryPreference;
    private String workEnvironmentPreference;
    private String geographicPreference;
    private Double careerReadinessScore;
    private Double professionalMaturityScore;
    private LocalDateTime targetCareerEntryDate;
    private LocalDateTime actualCareerEntryDate;
    private LocalDateTime lastCareerReviewDate;
    private String notes;
    private Boolean isActive;
}
