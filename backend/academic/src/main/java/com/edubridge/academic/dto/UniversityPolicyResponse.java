package com.edubridge.academic.dto;

import lombok.*;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UniversityPolicyResponse {
    private String id;
    private String universityId;
    private String policyType;
    private String name;
    private String code;
    private String description;
    private String policyRule;
    private String effect;
    private Integer priority;
    private Boolean isMandatory;
    private Instant effectiveDate;
    private Instant expirationDate;
    private Boolean isActive;
    private Boolean requiresReview;
    private Instant lastReviewedDate;
    private Instant createdAt;
    private Instant updatedAt;
}
