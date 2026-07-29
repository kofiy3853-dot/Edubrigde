package com.edubridge.academic.dto;

import lombok.*;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccreditationRecordResponse {
    private String id;
    private String universityId;
    private String accreditationType;
    private String status;
    private String accreditationBody;
    private String accreditationReference;
    private String accreditationLevel;
    private Instant grantedDate;
    private Instant expirationDate;
    private Instant renewalDate;
    private String verificationUrl;
    private String documentationUrl;
    private String notes;
    private Boolean isActive;
    private Boolean requiresRenewal;
    private Instant createdAt;
    private Instant updatedAt;
}
