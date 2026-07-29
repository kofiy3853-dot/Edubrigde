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
public class AssessmentRecordResponse {
    private String id;
    private String consultationId;
    private String studentId;
    private String advisorId;
    private String assessmentType;
    private String assessmentStatus;
    private BigDecimal assessmentScore;
    private BigDecimal satisfactionScore;
    private BigDecimal effectivenessScore;
    private String assessmentNotes;
    private String recommendations;
    private LocalDateTime assessedAt;
    private Boolean isActive;
}
