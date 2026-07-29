package com.edubridge.studentservices.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "assessment_profiles", schema = "student_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssessmentProfile extends AuditableEntity {

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "assessment_type", nullable = false, length = 50)
    private String assessmentType; // ACADEMIC, COMPETENCY, BEHAVIORAL, SKILL, CAREER

    @Column(name = "assessment_status", nullable = false, length = 50)
    private String assessmentStatus; // PLANNING, IN_PROGRESS, COMPLETED, ARCHIVED

    @Column(name = "assessment_period", length = 50)
    private String assessmentPeriod; // WEEKLY, MONTHLY, QUARTERLY, SEMESTER, ANNUAL

    @Column(name = "target_score")
    private Integer targetScore;

    @Column(name = "current_score")
    private Integer currentScore;

    @Column(name = "assessment_criteria", length = 2000)
    private String assessmentCriteria;

    @Column(name = "assessment_notes", length = 2000)
    private String assessmentNotes;

    @Column(name = "next_assessment_date")
    private LocalDateTime nextAssessmentDate;

    @Column(name = "last_assessment_date")
    private LocalDateTime lastAssessmentDate;

    @Column(name = "assessor_id")
    private String assessorId;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
