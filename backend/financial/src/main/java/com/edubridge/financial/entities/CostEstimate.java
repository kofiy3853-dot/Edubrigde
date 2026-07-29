package com.edubridge.financial.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cost_estimates", schema = "financial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CostEstimate extends AuditableEntity {

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "university_id", nullable = false)
    private String universityId;

    @Column(name = "program_id", nullable = false)
    private String programId;

    @Column(name = "country_code", nullable = false, length = 3)
    private String countryCode;

    @Column(name = "currency_code", nullable = false, length = 3)
    private String currencyCode;

    @Column(name = "academic_year", nullable = false)
    private Integer academicYear;

    @Column(name = "total_tuition", nullable = false, precision = 15, scale = 2)
    private BigDecimal totalTuition;

    @Column(name = "total_accommodation", nullable = false, precision = 15, scale = 2)
    @Builder.Default
    private BigDecimal totalAccommodation = BigDecimal.ZERO;

    @Column(name = "total_living_expenses", nullable = false, precision = 15, scale = 2)
    @Builder.Default
    private BigDecimal totalLivingExpenses = BigDecimal.ZERO;

    @Column(name = "total_other_costs", nullable = false, precision = 15, scale = 2)
    @Builder.Default
    private BigDecimal totalOtherCosts = BigDecimal.ZERO;

    @Column(name = "grand_total", nullable = false, precision = 15, scale = 2)
    private BigDecimal grandTotal;

    @Column(name = "available_funds", nullable = false, precision = 15, scale = 2)
    @Builder.Default
    private BigDecimal availableFunds = BigDecimal.ZERO;

    @Column(name = "financial_gap", nullable = false, precision = 15, scale = 2)
    private BigDecimal financialGap;

    @Column(name = "affordability_score", precision = 5, scale = 2)
    private BigDecimal affordabilityScore;

    @Column(name = "risk_level", length = 20)
    private String riskLevel; // LOW, MEDIUM, HIGH, CRITICAL

    @Column(name = "is_finalized", nullable = false)
    @Builder.Default
    private Boolean isFinalized = false;

    @Column(name = "finalized_at")
    private LocalDate finalizedAt;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
