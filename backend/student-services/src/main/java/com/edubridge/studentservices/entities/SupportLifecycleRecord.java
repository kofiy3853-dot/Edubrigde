package com.edubridge.studentservices.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "support_lifecycle_records", schema = "student_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupportLifecycleRecord extends AuditableEntity {

    @Column(name = "support_profile_id", nullable = false)
    private String supportProfileId;

    @Column(name = "student_id", nullable = false)
    private String studentId;

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
