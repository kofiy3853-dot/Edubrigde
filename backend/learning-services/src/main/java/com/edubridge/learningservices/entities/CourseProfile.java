package com.edubridge.learningservices.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "course_profiles", schema = "learning_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseProfile extends AuditableEntity {

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "course_name", nullable = false, length = 255)
    private String courseName;

    @Column(name = "course_type", nullable = false, length = 50)
    private String courseType; // UNIVERSITY_PREPARATION, STUDY_SKILLS, DIGITAL_LITERACY, CAREER_PREPARATION, INTERNATIONAL

    @Column(name = "course_status", nullable = false, length = 50)
    private String courseStatus; // ENROLLED, IN_PROGRESS, COMPLETED, PAUSED, CANCELLED

    @Column(name = "difficulty_level", length = 50)
    private String difficultyLevel; // BEGINNER, INTERMEDIATE, ADVANCED, EXPERT

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "learning_objectives", length = 2000)
    private String learningObjectives;

    @Column(name = "prerequisites", length = 1000)
    private String prerequisites;

    @Column(name = "estimated_duration_hours")
    private Integer estimatedDurationHours;

    @Column(name = "completion_percentage")
    @Builder.Default
    private Integer completionPercentage = 0;

    @Column(name = "enrolled_at")
    private LocalDateTime enrolledAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
