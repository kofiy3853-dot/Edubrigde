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
public class StudentSupportProfileResponse {
    private String id;
    private String studentId;
    private String supportType;
    private String supportStatus;
    private String supportLevel;
    private String primaryConcern;
    private String secondaryConcerns;
    private String supportPreferences;
    private String interventionHistory;
    private String assignedCounselorId;
    private LocalDateTime lastContactDate;
    private LocalDateTime nextFollowUp;
    private String notes;
    private Boolean isActive;
}
