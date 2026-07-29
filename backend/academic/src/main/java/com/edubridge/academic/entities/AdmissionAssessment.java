package com.edubridge.academic.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "admission_assessments", schema = "academic")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdmissionAssessment extends AuditableEntity {

    @Column(name = "university_id", nullable = false)
    private String universityId;

    @Column(name = "program_id", nullable = false)
    private String programId;

    @Column(name = "student_id")
    private String studentId; // Reference to Identity Domain

    @Column(name = "status", nullable = false, length = 50)
    private String status; // PENDING, VALIDATING, ELIGIBLE, CONDITIONALLY_ELIGIBLE, RESTRICTED, INELIGIBLE

    @Column(name = "assessment_type", nullable = false, length = 50)
    private String assessmentType; // STANDARD, EXPEDITED, CONDITIONAL, FULL

    @Column(name = "academic_score")
    private java.math.BigDecimal academicScore;

    @Column(name = "language_score")
    private java.math.BigDecimal languageScore;

    @Column(name = "overall_score")
    private java.math.BigDecimal overallScore;

    @Column(name = "eligibility_result", length = 5000)
    private String eligibilityResult; // JSON eligibility result

    @Column(name = "validation_results", length = 10000)
    private String validationResults; // JSON validation results

    @Column(name = "policy_results", length = 10000)
    private String policyResults; // JSON policy results

    @Column(name = "notes", length = 2000)
    private String notes;

    @Column(name = "assessed_by", length = 255)
    private String assessedBy; // User ID or system

    @Column(name = "assessed_at")
    private Instant assessedAt;

    @Column(name = "expiration_date")
    private Instant expirationDate;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
