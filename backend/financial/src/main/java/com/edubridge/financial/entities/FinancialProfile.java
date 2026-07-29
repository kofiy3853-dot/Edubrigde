package com.edubridge.financial.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "financial_profiles", schema = "financial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinancialProfile extends AuditableEntity {

    @Column(name = "student_id", nullable = false)
    private String studentId; // Reference to Identity Domain

    @Column(name = "university_id")
    private String universityId; // Reference to Academic Domain

    @Column(name = "program_id")
    private String programId; // Reference to Academic Domain

    @Column(name = "country_code", nullable = false, length = 3)
    private String countryCode;

    @Column(name = "currency_code", nullable = false, length = 3)
    private String currencyCode;

    @Column(name = "estimated_tuition", precision = 15, scale = 2)
    private BigDecimal estimatedTuition;

    @Column(name = "estimated_living_expenses", precision = 15, scale = 2)
    private BigDecimal estimatedLivingExpenses;

    @Column(name = "estimated_accommodation", precision = 15, scale = 2)
    private BigDecimal estimatedAccommodation;

    @Column(name = "estimated_transportation", precision = 15, scale = 2)
    private BigDecimal estimatedTransportation;

    @Column(name = "estimated_food", precision = 15, scale = 2)
    private BigDecimal estimatedFood;

    @Column(name = "estimated_healthcare", precision = 15, scale = 2)
    private BigDecimal estimatedHealthcare;

    @Column(name = "estimated_academic_expenses", precision = 15, scale = 2)
    private BigDecimal estimatedAcademicExpenses;

    @Column(name = "total_estimated_cost", precision = 15, scale = 2)
    private BigDecimal totalEstimatedCost;

    @Column(name = "available_funds", precision = 15, scale = 2)
    private BigDecimal availableFunds;

    @Column(name = "expected_scholarships", precision = 15, scale = 2)
    private BigDecimal expectedScholarships;

    @Column(name = "expected_loans", precision = 15, scale = 2)
    private BigDecimal expectedLoans;

    @Column(name = "financial_gap", precision = 15, scale = 2)
    private BigDecimal financialGap;

    @Column(name = "affordability_score")
    private Integer affordabilityScore; // 0-100

    @Column(name = "risk_level", length = 20)
    private String riskLevel; // LOW, MEDIUM, HIGH, CRITICAL

    @Column(name = "notes", length = 2000)
    private String notes;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
