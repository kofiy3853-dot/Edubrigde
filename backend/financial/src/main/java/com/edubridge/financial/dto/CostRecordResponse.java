package com.edubridge.financial.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CostRecordResponse {
    private String id;
    private String universityId;
    private String programId;
    private String countryCode;
    private String costType;
    private String name;
    private String description;
    private BigDecimal amount;
    private String currencyCode;
    private String frequency;
    private Integer academicYear;
    private Boolean isMandatory;
    private Boolean isEstimated;
    private String source;
    private Boolean isActive;
    private Instant createdAt;
    private Instant updatedAt;
}
