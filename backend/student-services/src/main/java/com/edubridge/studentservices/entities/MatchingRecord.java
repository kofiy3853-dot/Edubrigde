package com.edubridge.studentservices.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "matching_records", schema = "student_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchingRecord extends AuditableEntity {

    @Column(name = "mentorship_id", nullable = false)
    private String mentorshipId;

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "mentor_id", nullable = false)
    private String mentorId;

    @Column(name = "matching_status", nullable = false, length = 50)
    private String matchingStatus; // PROPOSED, ACCEPTED, REJECTED, COMPLETED, CANCELLED

    @Column(name = "compatibility_score", precision = 5, scale = 2)
    private BigDecimal compatibilityScore;

    @Column(name = "matching_criteria", length = 2000)
    private String matchingCriteria;

    @Column(name = "student_feedback", length = 2000)
    private String studentFeedback;

    @Column(name = "mentor_feedback", length = 2000)
    private String mentorFeedback;

    @Column(name = "matched_at")
    private LocalDateTime matchedAt;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
