package com.edubridge.financial.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cost_categories", schema = "financial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CostCategory extends AuditableEntity {

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "code", nullable = false, unique = true, length = 50)
    private String code; // TUITION, ACCOMMODATION, TRANSPORTATION, FOOD, HEALTHCARE, ACADEMIC, VISA, OTHER

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "parent_code", length = 50)
    private String parentCode; // For hierarchical categories

    @Column(name = "sort_order", nullable = false)
    @Builder.Default
    private Integer sortOrder = 0;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
