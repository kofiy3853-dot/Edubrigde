package com.edubridge.learningservices.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "academic_learning_progress_records", schema = "learning_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LearningProgressRecord extends AuditableEntity {

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "growth_profile_id", nullable = false)
    private String growthProfileId;

    @Column(name = "subject_area", nullable = false, length = 255)
    private String subjectArea;

    @Column(name = "subject_code", length = 50)
    private String subjectCode;

    @Column(name = "progress_type", nullable = false, length = 50)
    private String progressType; // COURSEWORK, RESEARCH, PROJECT, INTERNSHIP, SELF_STUDY, CAPSTONE

    @Column(name = "progress_status", nullable = false, length = 50)
    private String progressStatus; // NOT_STARTED, IN_PROGRESS, COMPLETED, ON_HOLD, WITHDRAWN

    @Column(name = "competency_level", length = 50)
    private String competencyLevel; // NOVICE, BEGINNER, INTERMEDIATE, ADVANCED, EXPERT, MASTERY

    @Column(name = "proficiency_score")
    private Double proficiencyScore;

    @Column(name = "target_competency_level", length = 50)
    private String targetCompetencyLevel;

    @Column(name = "milestones_achieved")
    @Builder.Default
    private Integer milestonesAchieved = 0;

    @Column(name = "milestones_total")
    @Builder.Default
    private Integer milestonesTotal = 0;

    @Column(name = "learning_hours_logged")
    @Builder.Default
    private Double learningHoursLogged = 0.0;

    @Column(name = "mastery_threshold")
    @Builder.Default
    private Double masteryThreshold = 80.0;

    @Column(name = "is_mastery_achieved")
    @Builder.Default
    private Boolean isMasteryAchieved = false;

    @Column(name = "mastery_achieved_at")
    private LocalDateTime masteryAchievedAt;

    @Column(name = "next_milestone_name", length = 255)
    private String nextMilestoneName;

    @Column(name = "next_milestone_deadline")
    private LocalDateTime nextMilestoneDeadline;

    @Column(name = "notes", length = 2000)
    private String notes;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
