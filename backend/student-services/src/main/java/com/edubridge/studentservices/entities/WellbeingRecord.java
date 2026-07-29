package com.edubridge.studentservices.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "wellbeing_records", schema = "student_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WellbeingRecord extends AuditableEntity {

    @Column(name = "support_profile_id", nullable = false)
    private String supportProfileId;

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "wellbeing_type", nullable = false, length = 50)
    private String wellbeingType; // MENTAL_HEALTH, COUNSELING, WELLNESS, CRISIS, STRESS_MANAGEMENT

    @Column(name = "wellbeing_status", nullable = false, length = 50)
    private String wellbeingStatus; // IDENTIFIED, ASSESSED, IN_TREATMENT, MONITORING, RESOLVED

    @Column(name = "severity_level", length = 50)
    private String severityLevel; // LOW, MODERATE, HIGH, CRITICAL

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "counselor_id")
    private String counselorId;

    @Column(name = "session_date")
    private LocalDateTime sessionDate;

    @Column(name = "next_session_date")
    private LocalDateTime nextSessionDate;

    @Column(name = "treatment_plan", length = 2000)
    private String treatmentPlan;

    @Column(name = "progress_notes", length = 2000)
    private String progressNotes;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
