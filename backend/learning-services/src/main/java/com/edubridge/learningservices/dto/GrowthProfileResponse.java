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
public class GrowthProfileResponse {
    private String id;
    private String studentId;
    private String academicLevel;
    private String growthStatus;
    private Double currentGpa;
    private Double cumulativeGpa;
    private Integer totalCreditsEarned;
    private Integer totalCreditsAttempted;
    private Double creditCompletionRate;
    private String academicStanding;
    private LocalDateTime targetGraduationDate;
    private LocalDateTime actualGraduationDate;
    private String academicInterests;
    private String strengths;
    private String areasForImprovement;
    private Double careerAlignmentScore;
    private Double readinessScore;
    private LocalDateTime lastAcademicReviewDate;
    private Boolean isActive;
}
