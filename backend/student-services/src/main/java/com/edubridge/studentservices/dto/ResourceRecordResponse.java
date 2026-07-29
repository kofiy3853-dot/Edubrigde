package com.edubridge.studentservices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceRecordResponse {
    private String id;
    private String supportProfileId;
    private String studentId;
    private String resourceType;
    private String resourceName;
    private String resourceStatus;
    private String description;
    private String provider;
    private LocalDateTime allocatedDate;
    private LocalDateTime expiryDate;
    private Integer utilizationRate;
    private Integer satisfactionScore;
    private String notes;
    private Boolean isActive;
}
