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
public class LearningProgressRecordResponse {
    private String id;
    private String studentId;
    private String growthProfileId;
    private String subjectArea;
    private String subjectCode;
    private String progressType;
    private String progressStatus;
    private String competencyLevel;
    private Double proficiencyScore;
    private String targetCompetencyLevel;
    private Integer milestonesAchieved;
    private Integer milestonesTotal;
    private Double learningHoursLogged;
    private Double masteryThreshold;
    private Boolean isMasteryAchieved;
    private LocalDateTime masteryAchievedAt;
    private String nextMilestoneName;
    private LocalDateTime nextMilestoneDeadline;
    private String notes;
    private Boolean isActive;
}
