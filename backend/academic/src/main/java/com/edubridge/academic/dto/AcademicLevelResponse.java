package com.edubridge.academic.dto;

import lombok.*;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcademicLevelResponse {
    private String id;
    private String name;
    private String code;
    private String description;
    private Integer minDurationYears;
    private Integer maxDurationYears;
    private Integer minDurationMonths;
    private Integer maxDurationMonths;
    private Integer typicalDurationYears;
    private Integer typicalDurationMonths;
    private Integer minCredits;
    private Integer maxCredits;
    private Integer typicalCredits;
    private Boolean requiresThesis;
    private Boolean requiresInternship;
    private Boolean requiresResearch;
    private Integer sortOrder;
    private Boolean isActive;
    private Instant createdAt;
    private Instant updatedAt;
}
