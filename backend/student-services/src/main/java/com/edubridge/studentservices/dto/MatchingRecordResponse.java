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
public class MatchingRecordResponse {
    private String id;
    private String mentorshipId;
    private String studentId;
    private String mentorId;
    private String matchingStatus;
    private BigDecimal compatibilityScore;
    private String matchingCriteria;
    private String studentFeedback;
    private String mentorFeedback;
    private LocalDateTime matchedAt;
    private Boolean isActive;
}
