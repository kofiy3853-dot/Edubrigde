package com.edubridge.studentservices.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "guidance_records", schema = "student_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuidanceRecord extends AuditableEntity {

    @Column(name = "mentorship_id", nullable = false)
    private String mentorshipId;

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "mentor_id", nullable = false)
    private String mentorId;

    @Column(name = "guidance_type", nullable = false, length = 50)
    private String guidanceType; // SESSION, WORKSHOP, RESOURCE, ASSIGNMENT, CHECK_IN

    @Column(name = "guidance_status", nullable = false, length = 50)
    private String guidanceStatus; // SCHEDULED, IN_PROGRESS, COMPLETED, CANCELLED

    @Column(name = "topic", nullable = false, length = 255)
    private String topic;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "scheduled_at")
    private LocalDateTime scheduledAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @Column(name = "duration_minutes")
    private Integer durationMinutes;

    @Column(name = "session_notes", length = 2000)
    private String sessionNotes;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
