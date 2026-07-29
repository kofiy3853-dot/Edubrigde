package com.edubridge.studentservices.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "resource_records", schema = "student_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceRecord extends AuditableEntity {

    @Column(name = "support_profile_id", nullable = false)
    private String supportProfileId;

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "resource_type", nullable = false, length = 50)
    private String resourceType; // ACADEMIC, WELLBEING, FINANCIAL, TECHNOLOGY, ACCESSIBILITY

    @Column(name = "resource_name", nullable = false, length = 255)
    private String resourceName;

    @Column(name = "resource_status", nullable = false, length = 50)
    private String resourceStatus; // REQUESTED, APPROVED, ALLOCATED, ACTIVE, RETURNED, CANCELLED

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "provider", length = 255)
    private String provider;

    @Column(name = "allocated_date")
    private LocalDateTime allocatedDate;

    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;

    @Column(name = "utilization_rate")
    private Integer utilizationRate;

    @Column(name = "satisfaction_score")
    private Integer satisfactionScore;

    @Column(name = "notes", length = 2000)
    private String notes;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
