package com.edubridge.financial.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cost_comparisons", schema = "financial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CostComparison extends AuditableEntity {

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "country_code", nullable = false, length = 3)
    private String countryCode;

    @Column(name = "academic_year", nullable = false)
    private Integer academicYear;

    @Column(name = "currency_code", nullable = false, length = 3)
    private String currencyCode;

    @Column(name = "university1_id", nullable = false)
    private String university1Id;

    @Column(name = "university1_program_id", nullable = false)
    private String university1ProgramId;

    @Column(name = "university1_total_cost", nullable = false, precision = 15, scale = 2)
    private BigDecimal university1TotalCost;

    @Column(name = "university2_id")
    private String university2Id;

    @Column(name = "university2_program_id")
    private String university2ProgramId;

    @Column(name = "university2_total_cost", precision = 15, scale = 2)
    private BigDecimal university2TotalCost;

    @Column(name = "university3_id")
    private String university3Id;

    @Column(name = "university3_program_id")
    private String university3ProgramId;

    @Column(name = "university3_total_cost", precision = 15, scale = 2)
    private BigDecimal university3TotalCost;

    @Column(name = "comparison_result", length = 2000)
    private String comparisonResult;

    @Column(name = "recommended_university_id")
    private String recommendedUniversityId;

    @Column(name = "is_completed", nullable = false)
    @Builder.Default
    private Boolean isCompleted = false;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
