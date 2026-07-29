package com.edubridge.financial.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "cost_breakdowns", schema = "financial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CostBreakdown extends AuditableEntity {

    @Column(name = "university_id", nullable = false)
    private String universityId;

    @Column(name = "program_id", nullable = false)
    private String programId;

    @Column(name = "country_code", nullable = false, length = 3)
    private String countryCode;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

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

    @Column(name = "currency_code", nullable = false, length = 3)
    private String currencyCode;

    @Column(name = "is_estimated", nullable = false)
    @Builder.Default
    private Boolean isEstimated = true;

    @Column(name = "source", length = 255)
    private String source;

    @Column(name = "valid_from", nullable = false)
    private LocalDate validFrom;

    @Column(name = "valid_to")
    private LocalDate validTo;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
