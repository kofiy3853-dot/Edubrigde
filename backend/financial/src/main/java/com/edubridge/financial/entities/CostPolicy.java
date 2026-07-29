package com.edubridge.financial.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "cost_policies", schema = "financial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CostPolicy extends AuditableEntity {

    @Column(name = "university_id")
    private String universityId;

    @Column(name = "country_code", length = 3)
    private String countryCode;

    @Column(name = "category_code", length = 50)
    private String categoryCode;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "policy_type", nullable = false, length = 50)
    private String policyType; // DISCOUNT, SCHOLARSHIP, REFUND, PAYMENT_PLAN, LATE_FEE, WAIVER

    @Column(name = "policy_rule", nullable = false, length = 2000)
    private String policyRule; // JSON rule definition

    @Column(name = "discount_percentage", precision = 5, scale = 2)
    private BigDecimal discountPercentage;

    @Column(name = "discount_amount", precision = 15, scale = 2)
    private BigDecimal discountAmount;

    @Column(name = "min_amount", precision = 15, scale = 2)
    private BigDecimal minAmount;

    @Column(name = "max_discount", precision = 15, scale = 2)
    private BigDecimal maxDiscount;

    @Column(name = "effective_from")
    private java.time.LocalDate effectiveFrom;

    @Column(name = "effective_to")
    private java.time.LocalDate effectiveTo;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;

    @Column(name = "requires_approval", nullable = false)
    @Builder.Default
    private Boolean requiresApproval = false;
}
