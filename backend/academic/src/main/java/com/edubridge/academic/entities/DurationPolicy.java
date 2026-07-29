package com.edubridge.academic.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "duration_policies", schema = "academic")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DurationPolicy extends AuditableEntity {

    @Column(name = "program_id", nullable = false)
    private String programId;

    @Column(name = "duration_type", nullable = false, length = 50)
    private String durationType; // FULL_TIME, PART_TIME, ACCELERATED, FLEXIBLE

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "min_duration_years", nullable = false)
    private Integer minDurationYears;

    @Column(name = "max_duration_years", nullable = false)
    private Integer maxDurationYears;

    @Column(name = "min_duration_months", nullable = false)
    @Builder.Default
    private Integer minDurationMonths = 0;

    @Column(name = "max_duration_months", nullable = false)
    @Builder.Default
    private Integer maxDurationMonths = 0;

    @Column(name = "default_duration_years")
    private Integer defaultDurationYears;

    @Column(name = "default_duration_months")
    @Builder.Default
    private Integer defaultDurationMonths = 0;

    @Column(name = "credits_per_year")
    private Integer creditsPerYear;

    @Column(name = "max_credits_per_semester")
    private Integer maxCreditsPerSemester;

    @Column(name = "max_extensions", nullable = false)
    @Builder.Default
    private Integer maxExtensions = 0;

    @Column(name = "extension_duration_months", nullable = false)
    @Builder.Default
    private Integer extensionDurationMonths = 0;

    @Column(name = "effective_date")
    private Instant effectiveDate;

    @Column(name = "expiration_date")
    private Instant expirationDate;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
