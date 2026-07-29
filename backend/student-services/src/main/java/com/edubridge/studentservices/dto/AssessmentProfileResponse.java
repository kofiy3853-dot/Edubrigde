package com.edubridge.studentservices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssessmentProfileResponse {
    private String id;
    private String studentId;
    private String assessmentType;
    private String assessmentStatus;
    private String assessmentPeriod;
    private Integer targetScore;
    private Integer currentScore;
    private String assessmentCriteria;
    private String assessmentNotes;
    private LocalDateTime nextAssessmentDate;
    private LocalDateTime lastAssessmentDate;
    private String assessorId;
    private Boolean isActive;
}
