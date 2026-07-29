package com.edubridge.learningservices.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "study_skill_records", schema = "learning_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudySkillRecord extends AuditableEntity {

    @Column(name = "course_profile_id", nullable = false)
    private String courseProfileId;

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "skill_name", nullable = false, length = 255)
    private String skillName;

    @Column(name = "skill_category", nullable = false, length = 50)
    private String skillCategory; // STUDY_PLANNING, TIME_MANAGEMENT, RESEARCH, ACADEMIC_WRITING, EXAM_PREPARATION

    @Column(name = "skill_status", nullable = false, length = 50)
    private String skillStatus; // LEARNING, PRACTICING, MASTERED, NEEDS_REVIEW

    @Column(name = "proficiency_level", length = 50)
    private String proficiencyLevel; // BEGINNER, INTERMEDIATE, ADVANCED, EXPERT

    @Column(name = "proficiency_score", precision = 5, scale = 2)
    private BigDecimal proficiencyScore;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "practice_hours")
    private Integer practiceHours;

    @Column(name = "last_practiced_at")
    private LocalDateTime lastPracticedAt;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
