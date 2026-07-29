package com.edubridge.studentservices.dto;

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
public class GrowthRecordResponse {
    private String id;
    private String mentorshipId;
    private String studentId;
    private String growthType;
    private String growthStatus;
    private String milestoneName;
    private String description;
    private LocalDateTime targetDate;
    private LocalDateTime achievedDate;
    private BigDecimal progressScore;
    private String evidence;
    private String mentorNotes;
    private Boolean isActive;
}
