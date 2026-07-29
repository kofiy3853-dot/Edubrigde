package com.edubridge.learningservices.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "academic_growth_assessment_records", schema = "learning_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GrowthAssessmentRecord extends AuditableEntity {

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "growth_profile_id", nullable = false)
    private String growthProfileId;

    @Column(name = "assessment_type", nullable = false, length = 50)
    private String assessmentType; // DIAGNOSTIC, FORMATIVE, SUMMATIVE, PEER, SELF, PORTFOLIO

    @Column(name = "assessment_name", nullable = false, length = 255)
    private String assessmentName;

    @Column(name = "assessment_status", nullable = false, length = 50)
    private String assessmentStatus; // SCHEDULED, IN_PROGRESS, COMPLETED, GRADED, ARCHIVED

    @Column(name = "assessment_domain", nullable = false, length = 50)
    private String assessmentDomain; // ACADEMIC, CAREER, RESEARCH, PERSONAL, LEADERSHIP, CULTURAL

    @Column(name = "score_earned")
    private Double scoreEarned;

    @Column(name = "score_possible")
    private Double scorePossible;

    @Column(name = "percentage_score")
    private Double percentageScore;

    @Column(name = "grade", length = 10)
    private String grade;

    @Column(name = "competency_level", length = 50)
    private String competencyLevel; // NOVICE, BEGINNER, INTERMEDIATE, ADVANCED, EXPERT, MASTERY

    @Column(name = "assessment_duration_minutes")
    private Integer assessmentDurationMinutes;

    @Column(name = "passing_score")
    @Builder.Default
    private Double passingScore = 60.0;

    @Column(name = "is_passed")
    @Builder.Default
    private Boolean isPassed = false;

    @Column(name = "feedback", length = 2000)
    private String feedback;

    @Column(name = "assessed_by")
    private String assessedBy;

    @Column(name = "assessed_at")
    private LocalDateTime assessedAt;

    @Column(name = "valid_until")
    private LocalDateTime validUntil;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
