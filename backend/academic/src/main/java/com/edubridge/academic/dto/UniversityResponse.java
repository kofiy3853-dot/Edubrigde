package com.edubridge.academic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UniversityResponse {
    private String id;
    private String name;
    private String code;
    private String countryCode;
    private String city;
    private String website;
    private String institutionType;
    private String accreditationStatus;
    private String accreditationBody;
    private Instant accreditationDate;
    private String logoUrl;
    private String description;
    private Instant establishedDate;
    private String phone;
    private String email;
    private String address;
    private Boolean isActive;
    private Boolean isPartner;
    private Instant createdAt;
    private Instant updatedAt;
}
