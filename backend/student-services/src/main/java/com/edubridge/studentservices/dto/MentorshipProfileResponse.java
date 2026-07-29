package com.edubridge.studentservices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MentorshipProfileResponse {
    private String id;
    private String studentId;
    private String mentorshipType;
    private String mentorshipStatus;
    private String interests;
    private String goals;
    private String experienceLevel;
    private String preferredMentorGender;
    private String preferredLanguage;
    private Integer maxWeeklyHours;
    private String notes;
    private Boolean isActive;
}
