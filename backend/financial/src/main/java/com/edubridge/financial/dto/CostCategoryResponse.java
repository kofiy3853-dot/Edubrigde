package com.edubridge.financial.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CostCategoryResponse {
    private String id;
    private String name;
    private String code;
    private String description;
    private String parentCode;
    private Integer sortOrder;
    private Boolean isActive;
}
