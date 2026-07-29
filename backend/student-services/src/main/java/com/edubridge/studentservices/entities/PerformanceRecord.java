package com.edubridge.studentservices.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "performance_records", schema = "student_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerformanceRecord extends AuditableEntity {

    @Column(name = "assessment_profile_id", nullable = false)
    private String assessmentProfileId;

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "performance_type", nullable = false, length = 50)
    private String performanceType; // GRADE, SCORE, RANK, PROGRESS, ACHIEVEMENT

    @Column(name = "performance_status", nullable = false, length = 50)
    private String performanceStatus; // RECORDED, VERIFIED, PUBLISHED, APPEALED

    @Column(name = "subject_area", length = 255)
    private String subjectArea;

    @Column(name = "score", precision = 5, scale = 2)
    private BigDecimal score;

    @Column(name = "max_score", precision = 5, scale = 2)
    private BigDecimal maxScore;

    @Column(name = "percentage")
    private Integer percentage;

    @Column(name = "grade", length = 10)
    private String grade;

    @Column(name = "rank")
    private Integer rank;

    @Column(name = "recorded_date")
    private LocalDateTime recordedDate;

    @Column(name = "notes", length = 2000)
    private String notes;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
