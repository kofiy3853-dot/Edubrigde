package com.edubridge.studentservices.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "policy_records", schema = "student_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PolicyRecord extends AuditableEntity {

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "policy_type", nullable = false, length = 50)
    private String policyType; // SCHEDULING, VALIDATION, SESSION, INTEGRATION, ASSESSMENT

    @Column(name = "policy_rule", nullable = false, length = 2000)
    private String policyRule; // JSON rule definition

    @Column(name = "effect", nullable = false, length = 20)
    private String effect; // ALLOW, DENY

    @Column(name = "priority", nullable = false)
    @Builder.Default
    private Integer priority = 0;

    @Column(name = "conditions", length = 2000)
    private String conditions;

    @Column(name = "effective_from")
    private LocalDate effectiveFrom;

    @Column(name = "effective_to")
    private LocalDate effectiveTo;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
