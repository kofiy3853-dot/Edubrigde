package com.edubridge.learningservices.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "academic_performance_records", schema = "learning_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerformanceRecord extends AuditableEntity {

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "growth_profile_id", nullable = false)
    private String growthProfileId;

    @Column(name = "performance_type", nullable = false, length = 50)
    private String performanceType; // EXAM, ASSIGNMENT, PROJECT, PRESENTATION, PRACTICAL, THESIS

    @Column(name = "performance_name", nullable = false, length = 255)
    private String performanceName;

    @Column(name = "performance_status", nullable = false, length = 50)
    private String performanceStatus; // SCHEDULED, IN_PROGRESS, SUBMITTED, GRADED, COMPLETED, CANCELLED

    @Column(name = "subject_area", nullable = false, length = 255)
    private String subjectArea;

    @Column(name = "score_earned")
    private Double scoreEarned;

    @Column(name = "score_possible")
    private Double scorePossible;

    @Column(name = "percentage_score")
    private Double percentageScore;

    @Column(name = "grade", length = 10)
    private String grade;

    @Column(name = "grade_point")
    private Double gradePoint;

    @Column(name = "academic_period", length = 50)
    private String academicPeriod;

    @Column(name = "academic_year", length = 20)
    private String academicYear;

    @Column(name = "weight_percentage")
    @Builder.Default
    private Double weightPercentage = 1.0;

    @Column(name = "is_weighted")
    @Builder.Default
    private Boolean isWeighted = false;

    @Column(name = "performance_feedback", length = 2000)
    private String performanceFeedback;

    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;

    @Column(name = "graded_at")
    private LocalDateTime gradedAt;

    @Column(name = "graded_by")
    private String gradedBy;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
