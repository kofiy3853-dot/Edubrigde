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
public class ScholarshipCatalogResponse {
    private String id;
    private String name;
    private String code;
    private String description;
    private String scholarshipType;
    private String fundingOrganization;
    private String countryCode;
    private String currencyCode;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private String coverageType;
    private String academicRequirement;
    private String financialRequirement;
    private LocalDate applicationDeadline;
    private Integer academicYear;
    private Boolean isRenewable;
    private String renewalCriteria;
    private Integer maxRecipients;
    private Integer currentRecipients;
    private String eligibleCountries;
    private String eligiblePrograms;
    private Boolean isActive;
}
