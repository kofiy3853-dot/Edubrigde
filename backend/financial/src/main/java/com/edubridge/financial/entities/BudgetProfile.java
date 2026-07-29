package com.edubridge.financial.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "budget_profiles", schema = "financial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BudgetProfile extends AuditableEntity {

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

    @Column(name = "academic_duration", nullable = false)
    private Integer academicDuration;

    @Column(name = "monthly_budget", nullable = false, precision = 15, scale = 2)
    private BigDecimal monthlyBudget;

    @Column(name = "annual_budget", nullable = false, precision = 15, scale = 2)
    private BigDecimal annualBudget;

    @Column(name = "total_educational_cost", nullable = false, precision = 15, scale = 2)
    private BigDecimal totalEducationalCost;

    @Column(name = "total_living_expense", nullable = false, precision = 15, scale = 2)
    private BigDecimal totalLivingExpense;

    @Column(name = "total_emergency_reserve", nullable = false, precision = 15, scale = 2)
    @Builder.Default
    private BigDecimal totalEmergencyReserve = BigDecimal.ZERO;

    @Column(name = "available_funds", nullable = false, precision = 15, scale = 2)
    @Builder.Default
    private BigDecimal availableFunds = BigDecimal.ZERO;

    @Column(name = "financial_gap", nullable = false, precision = 15, scale = 2)
    private BigDecimal financialGap;

    @Column(name = "affordability_status", nullable = false, length = 50)
    private String affordabilityStatus; // FULLY_AFFORDABLE, PARTIALLY_AFFORDABLE, CONDITIONALLY_AFFORDABLE, NOT_AFFORDABLE, SCHOLARSHIP_DEPENDENT

    @Column(name = "risk_level", nullable = false, length = 20)
    private String riskLevel; // LOW, MEDIUM, HIGH, CRITICAL

    @Column(name = "is_finalized", nullable = false)
    @Builder.Default
    private Boolean isFinalized = false;

    @Column(name = "finalized_at")
    private LocalDateTime finalizedAt;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
