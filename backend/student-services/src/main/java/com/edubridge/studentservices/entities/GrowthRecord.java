package com.edubridge.studentservices.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "growth_records", schema = "student_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GrowthRecord extends AuditableEntity {

    @Column(name = "mentorship_id", nullable = false)
    private String mentorshipId;

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "growth_type", nullable = false, length = 50)
    private String growthType; // SKILL, KNOWLEDGE, BEHAVIOR, NETWORK, CAREER

    @Column(name = "growth_status", nullable = false, length = 50)
    private String growthStatus; // TRACKING, ACHIEVED, PARTIAL, NOT_ACHIEVED

    @Column(name = "milestone_name", nullable = false, length = 255)
    private String milestoneName;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "target_date")
    private LocalDateTime targetDate;

    @Column(name = "achieved_date")
    private LocalDateTime achievedDate;

    @Column(name = "progress_score", precision = 5, scale = 2)
    private BigDecimal progressScore;

    @Column(name = "evidence", length = 2000)
    private String evidence;

    @Column(name = "mentor_notes", length = 2000)
    private String mentorNotes;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
