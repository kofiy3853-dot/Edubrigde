package com.edubridge.financial.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "scholarship_catalog", schema = "financial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScholarshipCatalog extends AuditableEntity {

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "code", nullable = false, unique = true, length = 50)
    private String code;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "scholarship_type", nullable = false, length = 50)
    private String scholarshipType; // INSTITUTION, GOVERNMENT, PRIVATE, INTERNATIONAL, MERIT, NEED_BASED

    @Column(name = "funding_organization", nullable = false, length = 255)
    private String fundingOrganization;

    @Column(name = "country_code", nullable = false, length = 3)
    private String countryCode;

    @Column(name = "currency_code", nullable = false, length = 3)
    private String currencyCode;

    @Column(name = "min_amount", precision = 15, scale = 2)
    private BigDecimal minAmount;

    @Column(name = "max_amount", precision = 15, scale = 2)
    private BigDecimal maxAmount;

    @Column(name = "coverage_type", nullable = false, length = 50)
    private String coverageType; // TUITION, LIVING_EXPENSES, ACADEMIC_EXPENSES, INSURANCE, FULL, PARTIAL

    @Column(name = "academic_requirement", length = 2000)
    private String academicRequirement;

    @Column(name = "financial_requirement", length = 2000)
    private String financialRequirement;

    @Column(name = "application_deadline")
    private LocalDate applicationDeadline;

    @Column(name = "academic_year")
    private Integer academicYear;

    @Column(name = "is_renewable", nullable = false)
    @Builder.Default
    private Boolean isRenewable = false;

    @Column(name = "renewal_criteria", length = 2000)
    private String renewalCriteria;

    @Column(name = "max_recipients")
    private Integer maxRecipients;

    @Column(name = "current_recipients", nullable = false)
    @Builder.Default
    private Integer currentRecipients = 0;

    @Column(name = "eligible_countries", length = 2000)
    private String eligibleCountries;

    @Column(name = "eligible_programs", length = 2000)
    private String eligiblePrograms;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
