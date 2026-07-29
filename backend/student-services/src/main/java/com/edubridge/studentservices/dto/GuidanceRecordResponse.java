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
public class GuidanceRecordResponse {
    private String id;
    private String mentorshipId;
    private String studentId;
    private String mentorId;
    private String guidanceType;
    private String guidanceStatus;
    private String topic;
    private String description;
    private LocalDateTime scheduledAt;
    private LocalDateTime completedAt;
    private Integer durationMinutes;
    private String sessionNotes;
    private Boolean isActive;
}
