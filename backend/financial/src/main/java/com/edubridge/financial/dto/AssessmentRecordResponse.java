package com.edubridge.financial.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssessmentRecordResponse {
    private String id;
    private String studentId;
    private String scholarshipId;
    private String assessmentStatus;
    private BigDecimal academicScore;
    private BigDecimal financialScore;
    private BigDecimal eligibilityScore;
    private BigDecimal overallScore;
    private String riskLevel;
    private String assessmentNotes;
    private String assessedBy;
    private LocalDateTime assessedAt;
    private LocalDate validUntil;
    private Boolean isActive;
}
