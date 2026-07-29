package com.edubridge.academic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProgramResponse {
    private String id;
    private String universityId;
    private String name;
    private String code;
    private String level;
    private String fieldOfStudy;
    private Integer durationYears;
    private Integer durationMonths;
    private Integer creditsRequired;
    private String description;
    private String requirements;
    private String languageOfInstruction;
    private BigDecimal tuitionFeeAmount;
    private String tuitionFeeCurrency;
    private Boolean isActive;
    private Boolean isOnline;
    private Instant createdAt;
    private Instant updatedAt;
}
