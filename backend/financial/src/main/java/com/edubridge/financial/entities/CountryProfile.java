package com.edubridge.financial.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "country_profiles", schema = "financial")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryProfile extends AuditableEntity {

    @Column(name = "country_code", nullable = false, unique = true, length = 3)
    private String countryCode; // ISO 3166-1 alpha-3

    @Column(name = "country_name", nullable = false, length = 100)
    private String countryName;

    @Column(name = "currency_code", nullable = false, length = 3)
    private String currencyCode; // ISO 4217

    @Column(name = "currency_name", nullable = false, length = 50)
    private String currencyName;

    @Column(name = "exchange_rate_to_usd", precision = 15, scale = 6)
    private BigDecimal exchangeRateToUsd;

    @Column(name = "avg_monthly_living_cost", precision = 15, scale = 2)
    private BigDecimal avgMonthlyLivingCost;

    @Column(name = "avg_monthly_accommodation", precision = 15, scale = 2)
    private BigDecimal avgMonthlyAccommodation;

    @Column(name = "avg_monthly_food", precision = 15, scale = 2)
    private BigDecimal avgMonthlyFood;

    @Column(name = "avg_monthly_transportation", precision = 15, scale = 2)
    private BigDecimal avgMonthlyTransportation;

    @Column(name = "avg_monthly_healthcare", precision = 15, scale = 2)
    private BigDecimal avgMonthlyHealthcare;

    @Column(name = "avg_annual_tuition_public", precision = 15, scale = 2)
    private BigDecimal avgAnnualTuitionPublic;

    @Column(name = "avg_annual_tuition_private", precision = 15, scale = 2)
    private BigDecimal avgAnnualTuitionPrivate;

    @Column(name = "student_visa_cost", precision = 15, scale = 2)
    private BigDecimal studentVisaCost;

    @Column(name = "health_insurance_required", nullable = false)
    @Builder.Default
    private Boolean healthInsuranceRequired = true;

    @Column(name = "health_insurance_cost", precision = 15, scale = 2)
    private BigDecimal healthInsuranceCost;

    @Column(name = "work_hours_allowed_per_week")
    private Integer workHoursAllowedPerWeek;

    @Column(name = "minimum_bank_statement", precision = 15, scale = 2)
    private BigDecimal minimumBankStatement;

    @Column(name = "notes", length = 2000)
    private String notes;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
