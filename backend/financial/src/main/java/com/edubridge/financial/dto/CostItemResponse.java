package com.edubridge.financial.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CostItemResponse {
    private String id;
    private String universityId;
    private String programId;
    private String categoryCode;
    private String name;
    private String description;
    private BigDecimal amount;
    private String currencyCode;
    private String frequency;
    private Integer academicYear;
    private Boolean isMandatory;
    private Boolean isEstimated;
    private Boolean isRefundable;
    private String refundPolicy;
    private String source;
    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;
    private Boolean isActive;
}
