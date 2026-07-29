package com.edubridge.financial.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "financial_policies", schema = "financial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinancialPolicy extends AuditableEntity {

    @Column(name = "university_id")
    private String universityId;

    @Column(name = "country_code", length = 3)
    private String countryCode;

    @Column(name = "policy_type", nullable = false, length = 50)
    private String policyType; // BUDGET, COST, VALIDATION, ELIGIBILITY, INTEGRATION, SCHOLARSHIP

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "code", nullable = false, length = 50)
    private String code;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "policy_rule", nullable = false, length = 10000)
    private String policyRule; // JSON policy definition

    @Column(name = "effect", nullable = false, length = 10)
    private String effect; // ALLOW, DENY, RESTRICT

    @Column(name = "priority", nullable = false)
    @Builder.Default
    private Integer priority = 0;

    @Column(name = "is_mandatory", nullable = false)
    @Builder.Default
    private Boolean isMandatory = true;

    @Column(name = "effective_date")
    private Instant effectiveDate;

    @Column(name = "expiration_date")
    private Instant expirationDate;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;

    @Column(name = "requires_review", nullable = false)
    @Builder.Default
    private Boolean requiresReview = false;

    @Column(name = "last_reviewed_date")
    private Instant lastReviewedDate;
}
