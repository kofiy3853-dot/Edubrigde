package com.edubridge.academic.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "academic_levels", schema = "academic")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AcademicLevel extends AuditableEntity {

    @Column(name = "name", nullable = false, length = 100)
    private String name; // Foundation, Certificate, Diploma, Bachelor, Master, Doctoral

    @Column(name = "code", nullable = false, unique = true, length = 20)
    private String code; // FOUNDATION, CERT, DIPLOMA, BACHELOR, MASTER, DOCTORAL

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

    @Column(name = "typical_duration_years")
    private Integer typicalDurationYears;

    @Column(name = "typical_duration_months")
    @Builder.Default
    private Integer typicalDurationMonths = 0;

    @Column(name = "min_credits")
    private Integer minCredits;

    @Column(name = "max_credits")
    private Integer maxCredits;

    @Column(name = "typical_credits")
    private Integer typicalCredits;

    @Column(name = "requires_thesis", nullable = false)
    @Builder.Default
    private Boolean requiresThesis = false;

    @Column(name = "requires_internship", nullable = false)
    @Builder.Default
    private Boolean requiresInternship = false;

    @Column(name = "requires_research", nullable = false)
    @Builder.Default
    private Boolean requiresResearch = false;

    @Column(name = "sort_order", nullable = false)
    @Builder.Default
    private Integer sortOrder = 0;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
