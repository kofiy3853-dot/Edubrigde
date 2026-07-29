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
public class EvaluationRecordResponse {
    private String id;
    private String assessmentProfileId;
    private String studentId;
    private String evaluationType;
    private String evaluationStatus;
    private BigDecimal overallScore;
    private String evaluationSummary;
    private String strengths;
    private String areasForImprovement;
    private String recommendations;
    private String evaluatorId;
    private LocalDateTime evaluatedAt;
    private Boolean isActive;
}
