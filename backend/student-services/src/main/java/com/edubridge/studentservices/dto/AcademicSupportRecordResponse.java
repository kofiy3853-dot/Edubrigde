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
public class AcademicSupportRecordResponse {
    private String id;
    private String supportProfileId;
    private String studentId;
    private String supportType;
    private String supportStatus;
    private String subjectArea;
    private String description;
    private String providerId;
    private LocalDateTime scheduledDate;
    private LocalDateTime completedDate;
    private String frequency;
    private Integer durationWeeks;
    private String outcomeNotes;
    private Boolean isActive;
}
