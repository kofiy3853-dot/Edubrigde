package com.edubridge.financial.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "award_policies", schema = "financial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AwardPolicy extends AuditableEntity {

    @Column(name = "scholarship_id", nullable = false)
    private String scholarshipId;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "policy_type", nullable = false, length = 50)
    private String policyType; // FULL_FUNDING, PARTIAL_FUNDING, RENEWAL, ACADEMIC_PERFORMANCE, VALIDATION

    @Column(name = "coverage_type", nullable = false, length = 50)
    private String coverageType; // TUITION, LIVING_EXPENSES, ACADEMIC_EXPENSES, INSURANCE, FULL, PARTIAL

    @Column(name = "amount", precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(name = "percentage", precision = 5, scale = 2)
    private BigDecimal percentage;

    @Column(name = "max_duration")
    private Integer maxDuration;

    @Column(name = "renewal_required", nullable = false)
    @Builder.Default
    private Boolean renewalRequired = false;

    @Column(name = "min_gpa", precision = 5, scale = 2)
    private BigDecimal minGpa;

    @Column(name = "min_credits")
    private Integer minCredits;

    @Column(name = "conditions", length = 2000)
    private String conditions;

    @Column(name = "effective_from")
    private LocalDate effectiveFrom;

    @Column(name = "effective_to")
    private LocalDate effectiveTo;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
