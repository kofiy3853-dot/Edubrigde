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
public class GrowthAssessmentRecordResponse {
    private String id;
    private String studentId;
    private String growthProfileId;
    private String assessmentType;
    private String assessmentName;
    private String assessmentStatus;
    private String assessmentDomain;
    private Double scoreEarned;
    private Double scorePossible;
    private Double percentageScore;
    private String grade;
    private String competencyLevel;
    private Integer assessmentDurationMinutes;
    private Double passingScore;
    private Boolean isPassed;
    private String feedback;
    private String assessedBy;
    private LocalDateTime assessedAt;
    private LocalDateTime validUntil;
    private Boolean isActive;
}
