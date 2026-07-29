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
public class SessionRecordResponse {
    private String id;
    private String consultationId;
    private String studentId;
    private String advisorId;
    private String sessionType;
    private String sessionStatus;
    private LocalDateTime scheduledStart;
    private LocalDateTime scheduledEnd;
    private LocalDateTime actualStart;
    private LocalDateTime actualEnd;
    private String sessionNotes;
    private String actionItems;
    private Boolean followUpRequired;
    private Boolean isActive;
}
