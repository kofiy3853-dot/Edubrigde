package com.edubridge.academic.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "accreditation_records", schema = "academic")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccreditationRecord extends AuditableEntity {

    @Column(name = "university_id", nullable = false)
    private String universityId;

    @Column(name = "accreditation_type", nullable = false, length = 50)
    private String accreditationType; // INSTITUTIONAL, ACADEMIC, COUNTRY, PROGRAM, PARTNERSHIP

    @Column(name = "status", nullable = false, length = 50)
    private String status; // ACCREDITED, PENDING, EXPIRED, REVOKED, SUSPENDED

    @Column(name = "accreditation_body", nullable = false, length = 255)
    private String accreditationBody; // Organization that granted accreditation

    @Column(name = "accreditation_reference", length = 100)
    private String accreditationReference; // Reference number

    @Column(name = "accreditation_level", length = 50)
    private String accreditationLevel; // NATIONAL, REGIONAL, INTERNATIONAL

    @Column(name = "granted_date")
    private Instant grantedDate;

    @Column(name = "expiration_date")
    private Instant expirationDate;

    @Column(name = "renewal_date")
    private Instant renewalDate;

    @Column(name = "verification_url", length = 500)
    private String verificationUrl;

    @Column(name = "documentation_url", length = 500)
    private String documentationUrl;

    @Column(name = "notes", length = 2000)
    private String notes;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;

    @Column(name = "requires_renewal", nullable = false)
    @Builder.Default
    private Boolean requiresRenewal = true;
}
