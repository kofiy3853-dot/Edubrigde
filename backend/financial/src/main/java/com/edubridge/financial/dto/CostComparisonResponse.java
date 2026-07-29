package com.edubridge.financial.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CostComparisonResponse {
    private String id;
    private String studentId;
    private String name;
    private String description;
    private String countryCode;
    private Integer academicYear;
    private String currencyCode;
    private String university1Id;
    private String university1ProgramId;
    private BigDecimal university1TotalCost;
    private String university2Id;
    private String university2ProgramId;
    private BigDecimal university2TotalCost;
    private String university3Id;
    private String university3ProgramId;
    private BigDecimal university3TotalCost;
    private String comparisonResult;
    private String recommendedUniversityId;
    private Boolean isCompleted;
    private Boolean isActive;
}
