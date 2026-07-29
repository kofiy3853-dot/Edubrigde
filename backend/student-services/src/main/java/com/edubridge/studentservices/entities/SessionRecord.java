package com.edubridge.studentservices.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "session_records", schema = "student_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessionRecord extends AuditableEntity {

    @Column(name = "consultation_id", nullable = false)
    private String consultationId;

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "advisor_id", nullable = false)
    private String advisorId;

    @Column(name = "session_type", nullable = false, length = 50)
    private String sessionType; // ACADEMIC, UNIVERSITY, FINANCIAL, SCHOLARSHIP, SUCCESS, INTERNATIONAL

    @Column(name = "session_status", nullable = false, length = 50)
    private String sessionStatus; // PENDING, VALIDATING, SCHEDULED, ACTIVE, COMPLETED, RESTRICTED, CANCELLED, ARCHIVED

    @Column(name = "scheduled_start", nullable = false)
    private LocalDateTime scheduledStart;

    @Column(name = "scheduled_end", nullable = false)
    private LocalDateTime scheduledEnd;

    @Column(name = "actual_start")
    private LocalDateTime actualStart;

    @Column(name = "actual_end")
    private LocalDateTime actualEnd;

    @Column(name = "session_notes", length = 2000)
    private String sessionNotes;

    @Column(name = "action_items", length = 2000)
    private String actionItems;

    @Column(name = "follow_up_required", nullable = false)
    @Builder.Default
    private Boolean followUpRequired = false;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
