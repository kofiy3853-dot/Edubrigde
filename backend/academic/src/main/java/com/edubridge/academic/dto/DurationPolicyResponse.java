package com.edubridge.academic.dto;

import lombok.*;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DurationPolicyResponse {
    private String id;
    private String programId;
    private String durationType;
    private String name;
    private String description;
    private Integer minDurationYears;
    private Integer maxDurationYears;
    private Integer minDurationMonths;
    private Integer maxDurationMonths;
    private Integer defaultDurationYears;
    private Integer defaultDurationMonths;
    private Integer creditsPerYear;
    private Integer maxCreditsPerSemester;
    private Integer maxExtensions;
    private Integer extensionDurationMonths;
    private Instant effectiveDate;
    private Instant expirationDate;
    private Boolean isActive;
    private Instant createdAt;
    private Instant updatedAt;
}
