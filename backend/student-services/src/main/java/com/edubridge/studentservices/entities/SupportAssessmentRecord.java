package com.edubridge.studentservices.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "support_assessment_records", schema = "student_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssessmentRecord extends AuditableEntity {

    @Column(name = "support_profile_id", nullable = false)
    private String supportProfileId;

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "assessment_type", nullable = false, length = 50)
    private String assessmentType; // INITIAL, PROGRESS, OUTCOME, RISK, SATISFACTION

    @Column(name = "assessment_status", nullable = false, length = 50)
    private String assessmentStatus; // PENDING, IN_PROGRESS, COMPLETED, ARCHIVED

    @Column(name = "risk_level", length = 50)
    private String riskLevel; // LOW, MODERATE, HIGH, CRITICAL

    @Column(name = "risk_score", precision = 5, scale = 2)
    private BigDecimal riskScore;

    @Column(name = "assessment_notes", length = 2000)
    private String assessmentNotes;

    @Column(name = "recommendations", length = 2000)
    private String recommendations;

    @Column(name = "assessed_by")
    private String assessedBy;

    @Column(name = "assessed_at")
    private LocalDateTime assessedAt;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
