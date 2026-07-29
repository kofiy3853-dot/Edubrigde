package com.edubridge.academic.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "eligibility_rules", schema = "academic")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EligibilityRule extends AuditableEntity {

    @Column(name = "university_id", nullable = false)
    private String universityId;

    @Column(name = "program_id")
    private String programId; // null means university-wide

    @Column(name = "rule_type", nullable = false, length = 50)
    private String ruleType; // ACADEMIC, LANGUAGE, AGE, COUNTRY, PROGRAM, COMBINED

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "rule_expression", nullable = false, length = 10000)
    private String ruleExpression; // JSON rule expression

    @Column(name = "effect", nullable = false, length = 10)
    private String effect; // ALLOW, DENY, RESTRICT

    @Column(name = "conditions", nullable = false, length = 10000)
    private String conditions; // JSON conditions

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
