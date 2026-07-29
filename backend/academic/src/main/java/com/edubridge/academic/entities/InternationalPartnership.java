package com.edubridge.academic.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "international_partnerships", schema = "academic")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InternationalPartnership extends AuditableEntity {

    @Column(name = "partner_university_id", nullable = false)
    private String partnerUniversityId; // Our university

    @Column(name = "partner_institution_name", nullable = false, length = 255)
    private String partnerInstitutionName; // External institution name

    @Column(name = "partner_institution_country", nullable = false, length = 3)
    private String partnerInstitutionCountry;

    @Column(name = "partner_institution_code", length = 100)
    private String partnerInstitutionCode; // External institution code

    @Column(name = "partnership_type", nullable = false, length = 50)
    private String partnershipType; // EXCHANGE, STRATEGIC, ACADEMIC, RESEARCH, DUAL_DEGREE

    @Column(name = "status", nullable = false, length = 50)
    private String status; // ACTIVE, PENDING, SUSPENDED, TERMINATED, EXPIRED

    @Column(name = "agreement_reference", length = 100)
    private String agreementReference;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "end_date")
    private Instant endDate;

    @Column(name = "renewal_date")
    private Instant renewalDate;

    @Column(name = "contact_person", length = 255)
    private String contactPerson;

    @Column(name = "contact_email", length = 255)
    private String contactEmail;

    @Column(name = "contact_phone", length = 20)
    private String contactPhone;

    @Column(name = "notes", length = 2000)
    private String notes;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;

    @Column(name = "requires_approval", nullable = false)
    @Builder.Default
    private Boolean requiresApproval = true;
}
