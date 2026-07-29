package com.edubridge.academic.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "universities", schema = "academic")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class University extends AuditableEntity {

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "code", nullable = false, unique = true, length = 50)
    private String code;

    @Column(name = "country_code", nullable = false, length = 3)
    private String countryCode;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "website", length = 255)
    private String website;

    @Column(name = "institution_type", nullable = false, length = 50)
    private String institutionType; // PUBLIC, PRIVATE, TECHNICAL, INTERNATIONAL, PARTNER

    @Column(name = "accreditation_status", nullable = false, length = 50)
    private String accreditationStatus; // ACCREDITED, PENDING, NOT_ACCREDITED

    @Column(name = "accreditation_body", length = 255)
    private String accreditationBody;

    @Column(name = "accreditation_date")
    private Instant accreditationDate;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "established_date")
    private Instant establishedDate;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "address", length = 500)
    private String address;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;

    @Column(name = "is_partner", nullable = false)
    @Builder.Default
    private Boolean isPartner = false;
}
