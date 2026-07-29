package com.edubridge.financial.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "eligibility_rules", schema = "financial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EligibilityRule extends AuditableEntity {

    @Column(name = "scholarship_id", nullable = false)
    private String scholarshipId;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "rule_type", nullable = false, length = 50)
    private String ruleType; // ACADEMIC, FINANCIAL, INSTITUTION, COUNTRY, PROGRAM

    @Column(name = "rule_category", nullable = false, length = 50)
    private String ruleCategory; // MIN_GPA, MIN_CREDITS, MAX_INCOME, COUNTRY_RESIDENCY, PROGRAM_ENROLLMENT

    @Column(name = "rule_condition", nullable = false, length = 2000)
    private String ruleCondition; // JSON condition definition

    @Column(name = "rule_value", nullable = false, length = 2000)
    private String ruleValue; // Expected value

    @Column(name = "operator", nullable = false, length = 50)
    private String operator; // GTE, LTE, EQ, NEQ, IN, NOT_IN, CONTAINS

    @Column(name = "priority", nullable = false)
    @Builder.Default
    private Integer priority = 0;

    @Column(name = "is_mandatory", nullable = false)
    @Builder.Default
    private Boolean isMandatory = true;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
