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
public class EmployabilityRecordResponse {
    private String id;
    private String studentId;
    private String careerProfileId;
    private String employabilityType;
    private String employabilityName;
    private String employabilityStatus;
    private String competencyArea;
    private String competencyLevel;
    private Double competencyScore;
    private Double targetCompetencyScore;
    private String industryStandard;
    private Boolean globalRecognized;
    private LocalDateTime validFrom;
    private LocalDateTime validUntil;
    private String evidenceUrl;
    private String notes;
    private Boolean isActive;
}
