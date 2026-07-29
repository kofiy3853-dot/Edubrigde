package com.edubridge.studentservices.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "competency_records", schema = "student_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompetencyRecord extends AuditableEntity {

    @Column(name = "assessment_profile_id", nullable = false)
    private String assessmentProfileId;

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "competency_name", nullable = false, length = 255)
    private String competencyName;

    @Column(name = "competency_category", length = 50)
    private String competencyCategory; // TECHNICAL, SOFT_SKILL, LEADERSHIP, DOMAIN, DIGITAL

    @Column(name = "proficiency_level", nullable = false, length = 50)
    private String proficiencyLevel; // BEGINNER, INTERMEDIATE, ADVANCED, EXPERT

    @Column(name = "proficiency_score", precision = 5, scale = 2)
    private BigDecimal proficiencyScore;

    @Column(name = "target_level", length = 50)
    private String targetLevel;

    @Column(name = "evidence", length = 2000)
    private String evidence;

    @Column(name = "last_assessed_date")
    private LocalDateTime lastAssessedDate;

    @Column(name = "next_assessment_date")
    private LocalDateTime nextAssessmentDate;

    @Column(name = "notes", length = 2000)
    private String notes;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
