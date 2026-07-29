package com.edubridge.academic.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "admission_lifecycle", schema = "academic")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdmissionLifecycle extends AuditableEntity {

    @Column(name = "assessment_id", nullable = false)
    private String assessmentId;

    @Column(name = "status", nullable = false, length = 50)
    private String status; // PENDING, VALIDATING, ELIGIBLE, CONDITIONALLY_ELIGIBLE, RESTRICTED, INELIGIBLE, ARCHIVED

    @Column(name = "previous_status", length = 50)
    private String previousStatus;

    @Column(name = "reason", length = 2000)
    private String reason;

    @Column(name = "changed_by", length = 255)
    private String changedBy; // User ID or system

    @Column(name = "effective_date", nullable = false)
    private Instant effectiveDate;

    @Column(name = "expiration_date")
    private Instant expirationDate;

    @Column(name = "review_date")
    private Instant reviewDate;

    @Column(name = "notes", length = 2000)
    private String notes;

    @Column(name = "is_current", nullable = false)
    @Builder.Default
    private Boolean isCurrent = true;
}
