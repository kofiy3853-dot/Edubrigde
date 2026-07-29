package com.edubridge.learningservices.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "career_professional_records", schema = "learning_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfessionalRecord extends AuditableEntity {

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "career_profile_id", nullable = false)
    private String careerProfileId;

    @Column(name = "professional_type", nullable = false, length = 50)
    private String professionalType; // SKILL_DEVELOPMENT, INDUSTRY_READINESS, COMMUNICATION, WORKPLACE_PREP, DIGITAL_SKILLS

    @Column(name = "professional_name", nullable = false, length = 255)
    private String professionalName;

    @Column(name = "professional_status", nullable = false, length = 50)
    private String professionalStatus; // PLANNED, IN_PROGRESS, COMPLETED, EXPIRED, CANCELLED

    @Column(name = "skill_area", length = 255)
    private String skillArea;

    @Column(name = "skill_level", length = 50)
    private String skillLevel; // BEGINNER, INTERMEDIATE, ADVANCED, EXPERT, MASTERY

    @Column(name = "proficiency_score")
    private Double proficiencyScore;

    @Column(name = "target_proficiency_score")
    private Double targetProficiencyScore;

    @Column(name = "hours_logged")
    @Builder.Default
    private Double hoursLogged = 0.0;

    @Column(name = "target_hours")
    private Double targetHours;

    @Column(name = "certification_name", length = 255)
    private String certificationName;

    @Column(name = "certification_date")
    private LocalDateTime certificationDate;

    @Column(name = "certification_expiry")
    private LocalDateTime certificationExpiry;

    @Column(name = "industry_recognized")
    @Builder.Default
    private Boolean industryRecognized = false;

    @Column(name = "notes", length = 2000)
    private String notes;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
