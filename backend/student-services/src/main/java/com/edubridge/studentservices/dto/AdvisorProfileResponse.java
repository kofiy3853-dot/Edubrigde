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
public class AdvisorProfileResponse {
    private String id;
    private String userId;
    private String advisorType;
    private String specialization;
    private String qualifications;
    private Integer experienceYears;
    private String languages;
    private String availabilityStatus;
    private Integer maxConsultationsPerDay;
    private Integer currentConsultations;
    private BigDecimal rating;
    private Integer totalReviews;
    private Boolean isActive;
}
