package com.edubridge.academic.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "academic_validations", schema = "academic")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AcademicValidation extends AuditableEntity {

    @Column(name = "university_id", nullable = false)
    private String universityId;

    @Column(name = "program_id")
    private String programId;

    @Column(name = "validation_type", nullable = false, length = 50)
    private String validationType; // GRADE, TRANSCRIPT, QUALIFICATION, CREDENTIAL, EXPERIENCE

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "validation_rule", nullable = false, length = 10000)
    private String validationRule; // JSON validation rule

    @Column(name = "min_value", length = 100)
    private String minValue;

    @Column(name = "max_value", length = 100)
    private String maxValue;

    @Column(name = "allowed_values", length = 5000)
    private String allowedValues; // JSON array

    @Column(name = "is_mandatory", nullable = false)
    @Builder.Default
    private Boolean isMandatory = true;

    @Column(name = "weight", nullable = false)
    @Builder.Default
    private Integer weight = 0;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
