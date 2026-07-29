package com.edubridge.learningservices.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "academic_growth_profiles", schema = "learning_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GrowthProfile extends AuditableEntity {

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "academic_level", nullable = false, length = 50)
    private String academicLevel; // HIGH_SCHOOL, UNDERGRADUATE, POSTGRADUATE, DOCTORAL, PROFESSIONAL

    @Column(name = "growth_status", nullable = false, length = 50)
    private String growthStatus; // EXPLORING, DEVELOPING, ADVANCING, ACHIEVING, EXCELLING, COMPLETED

    @Column(name = "current_gpa")
    private Double currentGpa;

    @Column(name = "cumulative_gpa")
    private Double cumulativeGpa;

    @Column(name = "total_credits_earned")
    @Builder.Default
    private Integer totalCreditsEarned = 0;

    @Column(name = "total_credits_attempted")
    @Builder.Default
    private Integer totalCreditsAttempted = 0;

    @Column(name = "credit_completion_rate")
    @Builder.Default
    private Double creditCompletionRate = 0.0;

    @Column(name = "academic_standing", length = 50)
    private String academicStanding; // GOOD_STANDING, PROBATION, PROVISIONAL, DEAN_LIST, HONORS

    @Column(name = "target_graduation_date")
    private LocalDateTime targetGraduationDate;

    @Column(name = "actual_graduation_date")
    private LocalDateTime actualGraduationDate;

    @Column(name = "academic_interests", length = 2000)
    private String academicInterests;

    @Column(name = "strengths", length = 2000)
    private String strengths;

    @Column(name = "areas_for_improvement", length = 2000)
    private String areasForImprovement;

    @Column(name = "career_alignment_score")
    @Builder.Default
    private Double careerAlignmentScore = 0.0;

    @Column(name = "readiness_score")
    @Builder.Default
    private Double readinessScore = 0.0;

    @Column(name = "last_academic_review_date")
    private LocalDateTime lastAcademicReviewDate;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
