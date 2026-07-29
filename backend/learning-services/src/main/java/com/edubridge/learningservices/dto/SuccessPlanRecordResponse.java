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
public class SuccessPlanRecordResponse {
    private String id;
    private String studentId;
    private String growthProfileId;
    private String planType;
    private String planName;
    private String planStatus;
    private String planDescription;
    private LocalDateTime targetCompletionDate;
    private LocalDateTime actualCompletionDate;
    private String priorityLevel;
    private Integer totalGoals;
    private Integer completedGoals;
    private Double goalCompletionPercentage;
    private String successMetrics;
    private String actionItems;
    private String responsibleAdvisorId;
    private LocalDateTime lastReviewDate;
    private LocalDateTime nextReviewDate;
    private String notes;
    private Boolean isActive;
}
