package com.edubridge.financial.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryProfileResponse {
    private String id;
    private String countryCode;
    private String countryName;
    private String currencyCode;
    private String currencyName;
    private BigDecimal exchangeRateToUsd;
    private BigDecimal avgMonthlyLivingCost;
    private BigDecimal avgMonthlyAccommodation;
    private BigDecimal avgMonthlyFood;
    private BigDecimal avgMonthlyTransportation;
    private BigDecimal avgMonthlyHealthcare;
    private BigDecimal avgAnnualTuitionPublic;
    private BigDecimal avgAnnualTuitionPrivate;
    private BigDecimal studentVisaCost;
    private Boolean healthInsuranceRequired;
    private BigDecimal healthInsuranceCost;
    private Integer workHoursAllowedPerWeek;
    private BigDecimal minimumBankStatement;
    private String notes;
    private Boolean isActive;
    private Instant createdAt;
    private Instant updatedAt;
}
