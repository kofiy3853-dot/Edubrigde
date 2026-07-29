package com.edubridge.studentservices.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "consultation_profiles", schema = "student_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultationProfile extends AuditableEntity {

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "advisor_id", nullable = false)
    private String advisorId;

    @Column(name = "consultation_type", nullable = false, length = 50)
    private String consultationType; // ACADEMIC, UNIVERSITY, FINANCIAL, SCHOLARSHIP, SUCCESS, INTERNATIONAL

    @Column(name = "consultation_status", nullable = false, length = 50)
    private String consultationStatus; // PENDING, VALIDATING, SCHEDULED, ACTIVE, COMPLETED, RESTRICTED, CANCELLED, ARCHIVED

    @Column(name = "scheduled_date", nullable = false)
    private LocalDateTime scheduledDate;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Column(name = "topic", nullable = false, length = 255)
    private String topic;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "priority", nullable = false, length = 20)
    private String priority; // LOW, MEDIUM, HIGH, URGENT

    @Column(name = "is_virtual", nullable = false)
    @Builder.Default
    private Boolean isVirtual = true;

    @Column(name = "meeting_link", length = 500)
    private String meetingLink;

    @Column(name = "notes", length = 2000)
    private String notes;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
