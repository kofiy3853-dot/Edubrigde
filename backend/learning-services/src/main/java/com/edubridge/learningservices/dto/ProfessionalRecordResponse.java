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
public class ProfessionalRecordResponse {
    private String id;
    private String studentId;
    private String careerProfileId;
    private String professionalType;
    private String professionalName;
    private String professionalStatus;
    private String skillArea;
    private String skillLevel;
    private Double proficiencyScore;
    private Double targetProficiencyScore;
    private Double hoursLogged;
    private Double targetHours;
    private String certificationName;
    private LocalDateTime certificationDate;
    private LocalDateTime certificationExpiry;
    private Boolean industryRecognized;
    private String notes;
    private Boolean isActive;
}
