package com.edubridge.studentservices.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "evaluation_records", schema = "student_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationRecord extends AuditableEntity {

    @Column(name = "assessment_profile_id", nullable = false)
    private String assessmentProfileId;

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "evaluation_type", nullable = false, length = 50)
    private String evaluationType; // SELF, PEER, INSTRUCTOR, COMPREHENSIVE, 360

    @Column(name = "evaluation_status", nullable = false, length = 50)
    private String evaluationStatus; // PENDING, IN_PROGRESS, COMPLETED, ARCHIVED

    @Column(name = "overall_score", precision = 5, scale = 2)
    private BigDecimal overallScore;

    @Column(name = "evaluation_summary", length = 2000)
    private String evaluationSummary;

    @Column(name = "strengths", length = 2000)
    private String strengths;

    @Column(name = "areas_for_improvement", length = 2000)
    private String areasForImprovement;

    @Column(name = "recommendations", length = 2000)
    private String recommendations;

    @Column(name = "evaluator_id")
    private String evaluatorId;

    @Column(name = "evaluated_at")
    private LocalDateTime evaluatedAt;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
