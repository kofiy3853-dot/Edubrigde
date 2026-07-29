package com.edubridge.learningservices.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "digital_education_assessment_records", schema = "learning_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssessmentRecord extends AuditableEntity {

    @Column(name = "course_profile_id", nullable = false)
    private String courseProfileId;

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "assessment_type", nullable = false, length = 50)
    private String assessmentType; // PROGRESS, SKILL, READINESS, COMPREHENSION, PERFORMANCE

    @Column(name = "assessment_status", nullable = false, length = 50)
    private String assessmentStatus; // PENDING, IN_PROGRESS, COMPLETED, ARCHIVED

    @Column(name = "score", precision = 5, scale = 2)
    private BigDecimal score;

    @Column(name = "max_score", precision = 5, scale = 2)
    private BigDecimal maxScore;

    @Column(name = "percentage")
    private Integer percentage;

    @Column(name = "grade", length = 10)
    private String grade;

    @Column(name = "assessment_notes", length = 2000)
    private String assessmentNotes;

    @Column(name = "recommendations", length = 2000)
    private String recommendations;

    @Column(name = "assessed_at")
    private LocalDateTime assessedAt;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
