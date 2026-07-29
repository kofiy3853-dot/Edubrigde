package com.edubridge.learningservices.dto;

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
    private String courseProfileId;
    private String studentId;
    private String resourceType;
    private String resourceName;
    private String resourceStatus;
    private String description;
    private String resourceUrl;
    private String formatType;
    private Integer accessCount;
    private LocalDateTime lastAccessedAt;
    private Boolean isActive;
}
