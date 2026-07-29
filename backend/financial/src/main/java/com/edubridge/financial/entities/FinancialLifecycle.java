package com.edubridge.financial.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "financial_lifecycle", schema = "financial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinancialLifecycle extends AuditableEntity {

    @Column(name = "assessment_id", nullable = false)
    private String assessmentId;

    @Column(name = "status", nullable = false, length = 50)
    private String status; // PENDING, VALIDATING, ESTIMATING, VALIDATED, RESTRICTED, INACTIVE, ARCHIVED

    @Column(name = "previous_status", length = 50)
    private String previousStatus;

    @Column(name = "reason", length = 2000)
    private String reason;

    @Column(name = "changed_by", length = 255)
    private String changedBy;

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
