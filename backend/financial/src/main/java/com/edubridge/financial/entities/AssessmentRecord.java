package com.edubridge.financial.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "assessment_records", schema = "financial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssessmentRecord extends AuditableEntity {

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "scholarship_id", nullable = false)
    private String scholarshipId;

    @Column(name = "assessment_status", nullable = false, length = 50)
    private String assessmentStatus; // PENDING, VALIDATING, ELIGIBLE, CONDITIONALLY_ELIGIBLE, RESTRICTED, INACTIVE, ARCHIVED

    @Column(name = "academic_score", precision = 5, scale = 2)
    private BigDecimal academicScore;

    @Column(name = "financial_score", precision = 5, scale = 2)
    private BigDecimal financialScore;

    @Column(name = "eligibility_score", precision = 5, scale = 2)
    private BigDecimal eligibilityScore;

    @Column(name = "overall_score", precision = 5, scale = 2)
    private BigDecimal overallScore;

    @Column(name = "risk_level", length = 20)
    private String riskLevel; // LOW, MEDIUM, HIGH, CRITICAL

    @Column(name = "assessment_notes", length = 2000)
    private String assessmentNotes;

    @Column(name = "assessed_by", length = 255)
    private String assessedBy;

    @Column(name = "assessed_at")
    private LocalDateTime assessedAt;

    @Column(name = "valid_until")
    private LocalDate validUntil;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
