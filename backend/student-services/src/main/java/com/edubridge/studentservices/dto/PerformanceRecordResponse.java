package com.edubridge.studentservices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerformanceRecordResponse {
    private String id;
    private String assessmentProfileId;
    private String studentId;
    private String performanceType;
    private String performanceStatus;
    private String subjectArea;
    private BigDecimal score;
    private BigDecimal maxScore;
    private Integer percentage;
    private String grade;
    private Integer rank;
    private LocalDateTime recordedDate;
    private String notes;
    private Boolean isActive;
}
