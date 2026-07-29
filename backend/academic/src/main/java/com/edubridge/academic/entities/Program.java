package com.edubridge.academic.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "programs", schema = "academic")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Program extends AuditableEntity {

    @Column(name = "university_id", nullable = false)
    private String universityId;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "code", nullable = false, unique = true, length = 50)
    private String code;

    @Column(name = "level", nullable = false, length = 50)
    private String level; // UNDERGRADUATE, DIPLOMA, CERTIFICATE, FOUNDATION, POSTGRADUATE

    @Column(name = "field_of_study", nullable = false, length = 255)
    private String fieldOfStudy;

    @Column(name = "duration_years", nullable = false)
    private Integer durationYears;

    @Column(name = "duration_months", nullable = false)
    @Builder.Default
    private Integer durationMonths = 0;

    @Column(name = "credits_required")
    private Integer creditsRequired;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "requirements", length = 5000)
    private String requirements; // JSON string for program-specific requirements

    @Column(name = "language_of_instruction", length = 50)
    private String languageOfInstruction; // EN, FR, ES, DE, etc.

    @Column(name = "tuition_fee_amount")
    private java.math.BigDecimal tuitionFeeAmount;

    @Column(name = "tuition_fee_currency", length = 3)
    @Builder.Default
    private String tuitionFeeCurrency = "USD";

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;

    @Column(name = "is_online", nullable = false)
    @Builder.Default
    private Boolean isOnline = false;
}
