package com.edubridge.academic.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "institution_classifications", schema = "academic")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstitutionClassification extends AuditableEntity {

    @Column(name = "university_id", nullable = false)
    private String universityId;

    @Column(name = "classification_type", nullable = false, length = 50)
    private String classificationType; // SIZE, RESEARCH, FOCUS, REGION, NETWORK, RANKING

    @Column(name = "classification_value", nullable = false, length = 100)
    private String classificationValue; // e.g., LARGE, RESEARCH_INTENSIVE, STEM_FOCUSED

    @Column(name = "classification_code", nullable = false, length = 50)
    private String classificationCode; // Standardized code

    @Column(name = "classification_body", length = 255)
    private String classificationBody; // Organization that assigned classification

    @Column(name = "effective_date")
    private Instant effectiveDate;

    @Column(name = "expiration_date")
    private Instant expirationDate;

    @Column(name = "notes", length = 2000)
    private String notes;

    @Column(name = "is_primary", nullable = false)
    @Builder.Default
    private Boolean isPrimary = false;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
