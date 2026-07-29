package com.edubridge.learningservices.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "academic_growth_lifecycle_records", schema = "learning_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GrowthLifecycleRecord extends AuditableEntity {

    @Column(name = "growth_profile_id", nullable = false)
    private String growthProfileId;

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "entity_type", nullable = false, length = 50)
    private String entityType; // GROWTH_PROFILE, LEARNING_PROGRESS, PERFORMANCE_RECORD, SUCCESS_PLAN, ASSESSMENT

    @Column(name = "entity_id", nullable = false)
    private String entityId;

    @Column(name = "previous_status", length = 50)
    private String previousStatus;

    @Column(name = "new_status", nullable = false, length = 50)
    private String newStatus;

    @Column(name = "reason", length = 2000)
    private String reason;

    @Column(name = "changed_by", nullable = false)
    private String changedBy;

    @Column(name = "changed_at", nullable = false)
    private LocalDateTime changedAt;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
