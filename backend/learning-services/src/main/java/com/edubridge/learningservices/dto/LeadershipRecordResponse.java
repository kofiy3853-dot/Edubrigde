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
public class LeadershipRecordResponse {
    private String id;
    private String studentId;
    private String careerProfileId;
    private String leadershipType;
    private String leadershipName;
    private String leadershipStatus;
    private String leadershipLevel;
    private String leadershipArea;
    private Double leadershipScore;
    private Double targetLeadershipScore;
    private Integer teamSizeManaged;
    private Integer projectCount;
    private Double innovationScore;
    private Double entrepreneurshipScore;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String notes;
    private Boolean isActive;
}
