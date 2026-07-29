package com.edubridge.financial.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "affordability_records", schema = "financial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AffordabilityRecord extends AuditableEntity {

    @Column(name = "budget_profile_id", nullable = false)
    private String budgetProfileId;

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "university_id", nullable = false)
    private String universityId;

    @Column(name = "country_code", nullable = false, length = 3)
    private String countryCode;

    @Column(name = "affordability_type", nullable = false, length = 50)
    private String affordabilityType; // EDUCATIONAL, UNIVERSITY, COUNTRY, LIVING_EXPENSE, EMERGENCY_FUND

    @Column(name = "affordability_status", nullable = false, length = 50)
    private String affordabilityStatus; // FULLY_AFFORDABLE, PARTIALLY_AFFORDABLE, CONDITIONALLY_AFFORDABLE, NOT_AFFORDABLE, SCHOLARSHIP_DEPENDENT

    @Column(name = "affordability_score", precision = 5, scale = 2)
    private BigDecimal affordabilityScore;

    @Column(name = "educational_affordability", precision = 15, scale = 2)
    private BigDecimal educationalAffordability;

    @Column(name = "living_affordability", precision = 15, scale = 2)
    private BigDecimal livingAffordability;

    @Column(name = "emergency_affordability", precision = 15, scale = 2)
    private BigDecimal emergencyAffordability;

    @Column(name = "total_affordability", precision = 15, scale = 2)
    private BigDecimal totalAffordability;

    @Column(name = "affordability_notes", length = 2000)
    private String affordabilityNotes;

    @Column(name = "assessed_by", length = 255)
    private String assessedBy;

    @Column(name = "assessed_at")
    private LocalDateTime assessedAt;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
