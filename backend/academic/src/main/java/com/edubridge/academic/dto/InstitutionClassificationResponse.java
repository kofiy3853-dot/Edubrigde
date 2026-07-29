package com.edubridge.academic.dto;

import lombok.*;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InstitutionClassificationResponse {
    private String id;
    private String universityId;
    private String classificationType;
    private String classificationValue;
    private String classificationCode;
    private String classificationBody;
    private Instant effectiveDate;
    private Instant expirationDate;
    private String notes;
    private Boolean isPrimary;
    private Boolean isActive;
    private Instant createdAt;
    private Instant updatedAt;
}
