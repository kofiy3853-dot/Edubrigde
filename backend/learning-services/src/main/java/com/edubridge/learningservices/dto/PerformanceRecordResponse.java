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
public class PerformanceRecordResponse {
    private String id;
    private String studentId;
    private String growthProfileId;
    private String performanceType;
    private String performanceName;
    private String performanceStatus;
    private String subjectArea;
    private Double scoreEarned;
    private Double scorePossible;
    private Double percentageScore;
    private String grade;
    private Double gradePoint;
    private String academicPeriod;
    private String academicYear;
    private Double weightPercentage;
    private Boolean isWeighted;
    private String performanceFeedback;
    private LocalDateTime submittedAt;
    private LocalDateTime gradedAt;
    private String gradedBy;
    private Boolean isActive;
}
