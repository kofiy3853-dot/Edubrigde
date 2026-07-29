package com.edubridge.studentservices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MentorProfileResponse {
    private String id;
    private String userId;
    private String mentorType;
    private String expertiseAreas;
    private String qualifications;
    private Integer experienceYears;
    private String languages;
    private String availabilityStatus;
    private Integer maxMentees;
    private Integer currentMentees;
    private BigDecimal rating;
    private Integer totalReviews;
    private String bio;
    private String linkedinUrl;
    private Boolean isActive;
}
