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
public class CourseProfileResponse {
    private String id;
    private String studentId;
    private String courseName;
    private String courseType;
    private String courseStatus;
    private String difficultyLevel;
    private String description;
    private String learningObjectives;
    private String prerequisites;
    private Integer estimatedDurationHours;
    private Integer completionPercentage;
    private LocalDateTime enrolledAt;
    private LocalDateTime completedAt;
    private Boolean isActive;
}
