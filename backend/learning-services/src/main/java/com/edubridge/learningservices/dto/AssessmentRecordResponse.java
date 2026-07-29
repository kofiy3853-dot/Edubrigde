package com.edubridge.learningservices.dto;

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
public class AssessmentRecordResponse {
    private String id;
    private String courseProfileId;
    private String studentId;
    private String assessmentType;
    private String assessmentStatus;
    private BigDecimal score;
    private BigDecimal maxScore;
    private Integer percentage;
    private String grade;
    private String assessmentNotes;
    private String recommendations;
    private LocalDateTime assessedAt;
    private Boolean isActive;
}
