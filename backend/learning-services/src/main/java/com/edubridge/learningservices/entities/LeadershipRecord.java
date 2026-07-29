package com.edubridge.learningservices.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "career_leadership_records", schema = "learning_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeadershipRecord extends AuditableEntity {

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "career_profile_id", nullable = false)
    private String careerProfileId;

    @Column(name = "leadership_type", nullable = false, length = 50)
    private String leadershipType; // STUDENT_LEADERSHIP, PROFESSIONAL_LEADERSHIP, INNOVATION, ENTREPRENEURSHIP, COMMUNITY

    @Column(name = "leadership_name", nullable = false, length = 255)
    private String leadershipName;

    @Column(name = "leadership_status", nullable = false, length = 50)
    private String leadershipStatus; // PLANNING, DEVELOPING, ACHIEVED, MENTORING, CANCELLED

    @Column(name = "leadership_level", length = 50)
    private String leadershipLevel; // EMERGING, DEVELOPING, PROFICIENT, ADVANCED, TRANSFORMATIONAL

    @Column(name = "leadership_area", length = 255)
    private String leadershipArea;

    @Column(name = "leadership_score")
    private Double leadershipScore;

    @Column(name = "target_leadership_score")
    private Double targetLeadershipScore;

    @Column(name = "team_size_managed")
    @Builder.Default
    private Integer teamSizeManaged = 0;

    @Column(name = "project_count")
    @Builder.Default
    private Integer projectCount = 0;

    @Column(name = "innovation_score")
    private Double innovationScore;

    @Column(name = "entrepreneurship_score")
    private Double entrepreneurshipScore;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "notes", length = 2000)
    private String notes;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
