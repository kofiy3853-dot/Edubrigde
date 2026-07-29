package com.edubridge.financial.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "cost_items", schema = "financial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CostItem extends AuditableEntity {

    @Column(name = "university_id", nullable = false)
    private String universityId;

    @Column(name = "program_id")
    private String programId;

    @Column(name = "category_code", nullable = false, length = 50)
    private String categoryCode; // References CostCategory.code

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(name = "currency_code", nullable = false, length = 3)
    private String currencyCode;

    @Column(name = "frequency", nullable = false, length = 20)
    private String frequency; // ONE_TIME, MONTHLY, QUARTERLY, ANNUALLY, SEMESTER

    @Column(name = "academic_year", nullable = false)
    private Integer academicYear;

    @Column(name = "is_mandatory", nullable = false)
    @Builder.Default
    private Boolean isMandatory = true;

    @Column(name = "is_estimated", nullable = false)
    @Builder.Default
    private Boolean isEstimated = false;

    @Column(name = "is_refundable", nullable = false)
    @Builder.Default
    private Boolean isRefundable = false;

    @Column(name = "refund_policy", length = 1000)
    private String refundPolicy;

    @Column(name = "source", length = 255)
    private String source;

    @Column(name = "effective_from")
    private java.time.LocalDate effectiveFrom;

    @Column(name = "effective_to")
    private java.time.LocalDate effectiveTo;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
