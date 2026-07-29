package com.edubridge.studentservices.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "student_support_profiles", schema = "student_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentSupportProfile extends AuditableEntity {

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "support_type", nullable = false, length = 50)
    private String supportType; // ACADEMIC, WELLBEING, SOCIAL, FINANCIAL, CAREER

    @Column(name = "support_status", nullable = false, length = 50)
    private String supportStatus; // IDENTIFIED, ASSESSED, ACTIVE, MONITORING, COMPLETED, CLOSED

    @Column(name = "support_level", length = 50)
    private String supportLevel; // LOW, MEDIUM, HIGH, CRITICAL

    @Column(name = "primary_concern", length = 2000)
    private String primaryConcern;

    @Column(name = "secondary_concerns", length = 2000)
    private String secondaryConcerns;

    @Column(name = "support_preferences", length = 2000)
    private String supportPreferences;

    @Column(name = "intervention_history", length = 2000)
    private String interventionHistory;

    @Column(name = "assigned_counselor_id")
    private String assignedCounselorId;

    @Column(name = "last_contact_date")
    private LocalDateTime lastContactDate;

    @Column(name = "next_follow_up")
    private LocalDateTime nextFollowUp;

    @Column(name = "notes", length = 2000)
    private String notes;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
