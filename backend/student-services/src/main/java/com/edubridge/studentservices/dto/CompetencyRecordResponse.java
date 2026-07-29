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
public class CompetencyRecordResponse {
    private String id;
    private String assessmentProfileId;
    private String studentId;
    private String competencyName;
    private String competencyCategory;
    private String proficiencyLevel;
    private BigDecimal proficiencyScore;
    private String targetLevel;
    private String evidence;
    private LocalDateTime lastAssessedDate;
    private LocalDateTime nextAssessmentDate;
    private String notes;
    private Boolean isActive;
}
