package com.edubridge.financial.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_records", schema = "financial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditRecord extends AuditableEntity {

    @Column(name = "scholarship_id", nullable = false)
    private String scholarshipId;

    @Column(name = "student_id")
    private String studentId;

    @Column(name = "action", nullable = false, length = 50)
    private String action; // CREATE, UPDATE, DELETE, ASSESS, AWARD, REVOKE

    @Column(name = "entity_type", nullable = false, length = 50)
    private String entityType; // SCHOLARSHIP, ELIGIBILITY, AWARD, ASSESSMENT, LIFECYCLE

    @Column(name = "entity_id", nullable = false)
    private String entityId;

    @Column(name = "old_value", length = 4000)
    private String oldValue;

    @Column(name = "new_value", length = 4000)
    private String newValue;

    @Column(name = "performed_by", nullable = false, length = 255)
    private String performedBy;

    @Column(name = "performed_at", nullable = false)
    private LocalDateTime performedAt;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
