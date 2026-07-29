package com.edubridge.studentservices.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "feedback_records", schema = "student_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeedbackRecord extends AuditableEntity {

    @Column(name = "assessment_profile_id", nullable = false)
    private String assessmentProfileId;

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "feedback_type", nullable = false, length = 50)
    private String feedbackType; // INSTRUCTOR, PEER, SELF, MENTOR, EXTERNAL

    @Column(name = "feedback_status", nullable = false, length = 50)
    private String feedbackStatus; // SUBMITTED, REVIEWED, ACTIONED, ARCHIVED

    @Column(name = "feedback_category", length = 50)
    private String feedbackCategory; // ACADEMIC, BEHAVIORAL, SKILL, COMMUNICATION, TEAMWORK

    @Column(name = "rating", precision = 3, scale = 2)
    private BigDecimal rating;

    @Column(name = "feedback_content", length = 2000)
    private String feedbackContent;

    @Column(name = "action_items", length = 2000)
    private String actionItems;

    @Column(name = "feedback_from_id")
    private String feedbackFromId;

    @Column(name = "submitted_at")
    private LocalDateTime submittedAt;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
