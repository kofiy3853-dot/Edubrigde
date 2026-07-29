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
public class FeedbackRecordResponse {
    private String id;
    private String assessmentProfileId;
    private String studentId;
    private String feedbackType;
    private String feedbackStatus;
    private String feedbackCategory;
    private BigDecimal rating;
    private String feedbackContent;
    private String actionItems;
    private String feedbackFromId;
    private LocalDateTime submittedAt;
    private Boolean isActive;
}
