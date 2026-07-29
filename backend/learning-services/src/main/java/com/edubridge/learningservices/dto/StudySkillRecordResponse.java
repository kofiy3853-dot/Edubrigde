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
public class StudySkillRecordResponse {
    private String id;
    private String courseProfileId;
    private String studentId;
    private String skillName;
    private String skillCategory;
    private String skillStatus;
    private String proficiencyLevel;
    private BigDecimal proficiencyScore;
    private String description;
    private Integer practiceHours;
    private LocalDateTime lastPracticedAt;
    private Boolean isActive;
}
