package com.edubridge.financial.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "sustainability_records", schema = "financial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SustainabilityRecord extends AuditableEntity {

    @Column(name = "budget_profile_id", nullable = false)
    private String budgetProfileId;

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "university_id", nullable = false)
    private String universityId;

    @Column(name = "country_code", nullable = false, length = 3)
    private String countryCode;

    @Column(name = "sustainability_type", nullable = false, length = 50)
    private String sustainabilityType; // EDUCATIONAL, MONTHLY, EMERGENCY, ACADEMIC, COUNTRY

    @Column(name = "sustainability_status", nullable = false, length = 50)
    private String sustainabilityStatus; // SUSTAINABLE, CONDITIONALLY_SUSTAINABLE, NOT_SUSTAINABLE, AT_RISK, CRITICAL

    @Column(name = "sustainability_score", precision = 5, scale = 2)
    private BigDecimal sustainabilityScore;

    @Column(name = "monthly_sustainability", precision = 15, scale = 2)
    private BigDecimal monthlySustainability;

    @Column(name = "academic_sustainability", precision = 15, scale = 2)
    private BigDecimal academicSustainability;

    @Column(name = "emergency_sustainability", precision = 15, scale = 2)
    private BigDecimal emergencySustainability;

    @Column(name = "longterm_sustainability", precision = 15, scale = 2)
    private BigDecimal longtermSustainability;

    @Column(name = "sustainability_notes", length = 2000)
    private String sustainabilityNotes;

    @Column(name = "assessed_by", length = 255)
    private String assessedBy;

    @Column(name = "assessed_at")
    private LocalDateTime assessedAt;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
