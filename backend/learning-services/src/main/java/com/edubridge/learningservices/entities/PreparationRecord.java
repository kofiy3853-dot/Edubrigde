package com.edubridge.learningservices.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "preparation_records", schema = "learning_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PreparationRecord extends AuditableEntity {

    @Column(name = "course_profile_id", nullable = false)
    private String courseProfileId;

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "preparation_type", nullable = false, length = 50)
    private String preparationType; // UNIVERSITY_READINESS, ACADEMIC_PREPARATION, EDUCATIONAL_SUCCESS, INTERNATIONAL, DIGITAL_SKILLS

    @Column(name = "preparation_status", nullable = false, length = 50)
    private String preparationStatus; // PLANNING, IN_PROGRESS, COMPLETED, NEEDS_ATTENTION

    @Column(name = "target_institution", length = 255)
    private String targetInstitution;

    @Column(name = "target_program", length = 255)
    private String targetProgram;

    @Column(name = "readiness_score")
    private Integer readinessScore;

    @Column(name = "requirements_met", length = 2000)
    private String requirementsMet;

    @Column(name = "requirements_pending", length = 2000)
    private String requirementsPending;

    @Column(name = "target_date")
    private LocalDateTime targetDate;

    @Column(name = "notes", length = 2000)
    private String notes;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
