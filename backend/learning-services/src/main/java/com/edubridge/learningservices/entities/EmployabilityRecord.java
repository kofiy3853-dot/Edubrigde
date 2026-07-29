package com.edubridge.learningservices.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "career_employability_records", schema = "learning_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployabilityRecord extends AuditableEntity {

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "career_profile_id", nullable = false)
    private String careerProfileId;

    @Column(name = "employability_type", nullable = false, length = 50)
    private String employabilityType; // PLANNING, COMPETENCY, CAREER_GROWTH, WORKFORCE, EMPLOYMENT_READINESS

    @Column(name = "employability_name", nullable = false, length = 255)
    private String employabilityName;

    @Column(name = "employability_status", nullable = false, length = 50)
    private String employabilityStatus; // NOT_STARTED, IN_PROGRESS, ACHIEVED, EXPIRED, CANCELLED

    @Column(name = "competency_area", length = 255)
    private String competencyArea;

    @Column(name = "competency_level", length = 50)
    private String competencyLevel; // NOVICE, BEGINNER, INTERMEDIATE, ADVANCED, EXPERT

    @Column(name = "competency_score")
    private Double competencyScore;

    @Column(name = "target_competency_score")
    private Double targetCompetencyScore;

    @Column(name = "industry_standard", length = 255)
    private String industryStandard;

    @Column(name = "global_recognized")
    @Builder.Default
    private Boolean globalRecognized = false;

    @Column(name = "valid_from")
    private LocalDateTime validFrom;

    @Column(name = "valid_until")
    private LocalDateTime validUntil;

    @Column(name = "evidence_url", length = 1000)
    private String evidenceUrl;

    @Column(name = "notes", length = 2000)
    private String notes;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
