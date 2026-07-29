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
public class WellbeingRecordResponse {
    private String id;
    private String supportProfileId;
    private String studentId;
    private String wellbeingType;
    private String wellbeingStatus;
    private String severityLevel;
    private String description;
    private String counselorId;
    private LocalDateTime sessionDate;
    private LocalDateTime nextSessionDate;
    private String treatmentPlan;
    private String progressNotes;
    private Boolean isActive;
}
