package com.edubridge.financial.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "cost_records", schema = "financial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CostRecord extends AuditableEntity {

    @Column(name = "university_id")
    private String universityId;

    @Column(name = "program_id")
    private String programId;

    @Column(name = "country_code", nullable = false, length = 3)
    private String countryCode;

    @Column(name = "cost_type", nullable = false, length = 50)
    private String costType; // TUITION, ACCOMMODATION, TRANSPORTATION, FOOD, HEALTHCARE, ACADEMIC, OTHER

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(name = "currency_code", nullable = false, length = 3)
    private String currencyCode;

    @Column(name = "frequency", nullable = false, length = 20)
    private String frequency; // ONE_TIME, MONTHLY, QUARTERLY, ANNUALLY

    @Column(name = "academic_year")
    private Integer academicYear;

    @Column(name = "is_mandatory", nullable = false)
    @Builder.Default
    private Boolean isMandatory = true;

    @Column(name = "is_estimated", nullable = false)
    @Builder.Default
    private Boolean isEstimated = true;

    @Column(name = "source", length = 255)
    private String source; // Source of cost information

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
