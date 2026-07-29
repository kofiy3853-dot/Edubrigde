package com.edubridge.academic.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "program_requirements", schema = "academic")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProgramRequirement extends AuditableEntity {

    @Column(name = "program_id", nullable = false)
    private String programId;

    @Column(name = "requirement_type", nullable = false, length = 50)
    private String requirementType; // ACADEMIC, LANGUAGE, AGE, COUNTRY, DOCUMENT, EXPERIENCE

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "is_mandatory", nullable = false)
    @Builder.Default
    private Boolean isMandatory = true;

    @Column(name = "validation_rule", length = 5000)
    private String validationRule; // JSON validation rule

    @Column(name = "min_value", length = 100)
    private String minValue;

    @Column(name = "max_value", length = 100)
    private String maxValue;

    @Column(name = "allowed_values", length = 5000)
    private String allowedValues; // JSON array of allowed values

    @Column(name = "weight", nullable = false)
    @Builder.Default
    private Integer weight = 0;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;

    @Column(name = "sort_order", nullable = false)
    @Builder.Default
    private Integer sortOrder = 0;
}
