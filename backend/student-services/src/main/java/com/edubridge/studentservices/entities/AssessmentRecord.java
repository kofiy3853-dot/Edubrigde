package com.edubridge.studentservices.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "assessment_records", schema = "student_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssessmentRecord extends AuditableEntity {

    @Column(name = "consultation_id", nullable = false)
    private String consultationId;

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "advisor_id", nullable = false)
    private String advisorId;

    @Column(name = "assessment_type", nullable = false, length = 50)
    private String assessmentType; // CONSULTATION, SESSION, ADVISOR, STUDENT

    @Column(name = "assessment_status", nullable = false, length = 50)
    private String assessmentStatus; // PENDING, VALIDATING, COMPLETED, ARCHIVED

    @Column(name = "assessment_score", precision = 5, scale = 2)
    private BigDecimal assessmentScore;

    @Column(name = "satisfaction_score", precision = 5, scale = 2)
    private BigDecimal satisfactionScore;

    @Column(name = "effectiveness_score", precision = 5, scale = 2)
    private BigDecimal effectivenessScore;

    @Column(name = "assessment_notes", length = 2000)
    private String assessmentNotes;

    @Column(name = "recommendations", length = 2000)
    private String recommendations;

    @Column(name = "assessed_at")
    private LocalDateTime assessedAt;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
