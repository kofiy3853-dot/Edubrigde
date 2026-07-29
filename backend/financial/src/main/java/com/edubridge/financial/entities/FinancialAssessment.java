package com.edubridge.financial.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "financial_assessments", schema = "financial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinancialAssessment extends AuditableEntity {

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "university_id")
    private String universityId;

    @Column(name = "program_id")
    private String programId;

    @Column(name = "country_code", nullable = false, length = 3)
    private String countryCode;

    @Column(name = "status", nullable = false, length = 50)
    private String status; // PENDING, VALIDATING, ESTIMATING, VALIDATED, RESTRICTED, INACTIVE, ARCHIVED

    @Column(name = "assessment_type", nullable = false, length = 50)
    private String assessmentType; // STANDARD, DETAILED, QUICK, COMPREHENSIVE

    @Column(name = "total_estimated_cost", precision = 15, scale = 2)
    private BigDecimal totalEstimatedCost;

    @Column(name = "total_available_funds", precision = 15, scale = 2)
    private BigDecimal totalAvailableFunds;

    @Column(name = "financial_gap", precision = 15, scale = 2)
    private BigDecimal financialGap;

    @Column(name = "affordability_score")
    private Integer affordabilityScore;

    @Column(name = "risk_level", length = 20)
    private String riskLevel;

    @Column(name = "assessment_results", length = 10000)
    private String assessmentResults; // JSON assessment results

    @Column(name = "validation_results", length = 10000)
    private String validationResults; // JSON validation results

    @Column(name = "policy_results", length = 10000)
    private String policyResults; // JSON policy results

    @Column(name = "notes", length = 2000)
    private String notes;

    @Column(name = "assessed_by", length = 255)
    private String assessedBy;

    @Column(name = "assessed_at")
    private Instant assessedAt;

    @Column(name = "expiration_date")
    private Instant expirationDate;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
