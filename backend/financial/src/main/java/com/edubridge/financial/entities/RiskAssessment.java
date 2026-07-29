package com.edubridge.financial.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "risk_assessments", schema = "financial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RiskAssessment extends AuditableEntity {

    @Column(name = "budget_profile_id", nullable = false)
    private String budgetProfileId;

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "university_id", nullable = false)
    private String universityId;

    @Column(name = "country_code", nullable = false, length = 3)
    private String countryCode;

    @Column(name = "risk_type", nullable = false, length = 50)
    private String riskType; // BUDGET, COST, COUNTRY, ACADEMIC, FINANCIAL

    @Column(name = "risk_level", nullable = false, length = 20)
    private String riskLevel; // LOW, MEDIUM, HIGH, CRITICAL

    @Column(name = "risk_score", precision = 5, scale = 2)
    private BigDecimal riskScore;

    @Column(name = "budget_risk", precision = 15, scale = 2)
    private BigDecimal budgetRisk;

    @Column(name = "cost_risk", precision = 15, scale = 2)
    private BigDecimal costRisk;

    @Column(name = "country_risk", precision = 15, scale = 2)
    private BigDecimal countryRisk;

    @Column(name = "academic_risk", precision = 15, scale = 2)
    private BigDecimal academicRisk;

    @Column(name = "financial_risk", precision = 15, scale = 2)
    private BigDecimal financialRisk;

    @Column(name = "risk_notes", length = 2000)
    private String riskNotes;

    @Column(name = "assessed_by", length = 255)
    private String assessedBy;

    @Column(name = "assessed_at")
    private LocalDateTime assessedAt;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
