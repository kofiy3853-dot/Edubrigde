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
public class ConsultationProfileResponse {
    private String id;
    private String studentId;
    private String advisorId;
    private String consultationType;
    private String consultationStatus;
    private LocalDateTime scheduledDate;
    private Integer duration;
    private String topic;
    private String description;
    private String priority;
    private Boolean isVirtual;
    private String meetingLink;
    private String notes;
    private Boolean isActive;
}
