package com.edubridge.financial.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SustainabilityRecordResponse {
    private String id;
    private String budgetProfileId;
    private String studentId;
    private String universityId;
    private String countryCode;
    private String sustainabilityType;
    private String sustainabilityStatus;
    private BigDecimal sustainabilityScore;
    private BigDecimal monthlySustainability;
    private BigDecimal academicSustainability;
    private BigDecimal emergencySustainability;
    private BigDecimal longtermSustainability;
    private String sustainabilityNotes;
    private String assessedBy;
    private LocalDateTime assessedAt;
    private Boolean isActive;
}
