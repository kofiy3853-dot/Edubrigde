package com.edubridge.studentservices.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "academic_support_records", schema = "student_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AcademicSupportRecord extends AuditableEntity {

    @Column(name = "support_profile_id", nullable = false)
    private String supportProfileId;

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "support_type", nullable = false, length = 50)
    private String supportType; // TUTORING, STUDY_GROUP, ACCOMMODATION, LEARNING_SUPPORT, PEER_SUPPORT

    @Column(name = "support_status", nullable = false, length = 50)
    private String supportStatus; // REQUESTED, APPROVED, IN_PROGRESS, COMPLETED, CANCELLED

    @Column(name = "subject_area", length = 255)
    private String subjectArea;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "scheduled_date")
    private LocalDateTime scheduledDate;

    @Column(name = "completed_date")
    private LocalDateTime completedDate;

    @Column(name = "frequency", length = 50)
    private String frequency; // ONE_TIME, WEEKLY, BI_WEEKLY, MONTHLY

    @Column(name = "duration_weeks")
    private Integer durationWeeks;

    @Column(name = "outcome_notes", length = 2000)
    private String outcomeNotes;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
