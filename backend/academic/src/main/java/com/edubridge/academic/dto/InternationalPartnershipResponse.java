package com.edubridge.academic.dto;

import lombok.*;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InternationalPartnershipResponse {
    private String id;
    private String partnerUniversityId;
    private String partnerInstitutionName;
    private String partnerInstitutionCountry;
    private String partnerInstitutionCode;
    private String partnershipType;
    private String status;
    private String agreementReference;
    private Instant startDate;
    private Instant endDate;
    private Instant renewalDate;
    private String contactPerson;
    private String contactEmail;
    private String contactPhone;
    private String notes;
    private Boolean isActive;
    private Boolean requiresApproval;
    private Instant createdAt;
    private Instant updatedAt;
}
