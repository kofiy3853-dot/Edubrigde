package com.edubridge.learningservices.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "career_profiles", schema = "learning_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CareerProfile extends AuditableEntity {

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "career_status", nullable = false, length = 50)
    private String careerStatus; // EXPLORING, DEVELOPING, ADVANCING, SPECIALIZING, TRANSITIONING, COMPLETED

    @Column(name = "career_field", length = 255)
    private String careerField;

    @Column(name = "career_subfield", length = 255)
    private String careerSubfield;

    @Column(name = "career_goal", length = 2000)
    private String careerGoal;

    @Column(name = "career_vision", length = 2000)
    private String careerVision;

    @Column(name = "industry_preference", length = 255)
    private String industryPreference;

    @Column(name = "work_environment_preference", length = 255)
    private String workEnvironmentPreference; // CORPORATE, STARTUP, ACADEMIC, GOVERNMENT, NGO, FREELANCE, REMOTE

    @Column(name = "geographic_preference", length = 255)
    private String geographicPreference;

    @Column(name = "career_readiness_score")
    @Builder.Default
    private Double careerReadinessScore = 0.0;

    @Column(name = "professional_maturity_score")
    @Builder.Default
    private Double professionalMaturityScore = 0.0;

    @Column(name = "target_career_entry_date")
    private LocalDateTime targetCareerEntryDate;

    @Column(name = "actual_career_entry_date")
    private LocalDateTime actualCareerEntryDate;

    @Column(name = "last_career_review_date")
    private LocalDateTime lastCareerReviewDate;

    @Column(name = "notes", length = 2000)
    private String notes;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
