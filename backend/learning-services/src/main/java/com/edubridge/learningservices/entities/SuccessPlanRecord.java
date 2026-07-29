package com.edubridge.learningservices.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "academic_success_planning_records", schema = "learning_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SuccessPlanRecord extends AuditableEntity {

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "growth_profile_id", nullable = false)
    private String growthProfileId;

    @Column(name = "plan_type", nullable = false, length = 50)
    private String planType; // STUDENT_SUCCESS, UNIVERSITY_SUCCESS, INTERNATIONAL_EDUCATION, ACADEMIC_ACHIEVEMENT, LEADERSHIP_DEVELOPMENT

    @Column(name = "plan_name", nullable = false, length = 255)
    private String planName;

    @Column(name = "plan_status", nullable = false, length = 50)
    private String planStatus; // DRAFT, ACTIVE, IN_PROGRESS, COMPLETED, ON_HOLD, CANCELLED

    @Column(name = "plan_description", length = 2000)
    private String planDescription;

    @Column(name = "target_completion_date")
    private LocalDateTime targetCompletionDate;

    @Column(name = "actual_completion_date")
    private LocalDateTime actualCompletionDate;

    @Column(name = "priority_level", length = 50)
    private String priorityLevel; // LOW, MEDIUM, HIGH, CRITICAL

    @Column(name = "total_goals")
    @Builder.Default
    private Integer totalGoals = 0;

    @Column(name = "completed_goals")
    @Builder.Default
    private Integer completedGoals = 0;

    @Column(name = "goal_completion_percentage")
    @Builder.Default
    private Double goalCompletionPercentage = 0.0;

    @Column(name = "success_metrics", length = 2000)
    private String successMetrics;

    @Column(name = "action_items", length = 2000)
    private String actionItems;

    @Column(name = "responsible_advisor_id")
    private String responsibleAdvisorId;

    @Column(name = "last_review_date")
    private LocalDateTime lastReviewDate;

    @Column(name = "next_review_date")
    private LocalDateTime nextReviewDate;

    @Column(name = "notes", length = 2000)
    private String notes;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
