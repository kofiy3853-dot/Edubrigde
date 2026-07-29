package com.edubridge.studentservices.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "mentorship_profiles", schema = "student_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MentorshipProfile extends AuditableEntity {

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "mentorship_type", nullable = false, length = 50)
    private String mentorshipType; // ACADEMIC, CAREER, PERSONAL, INDUSTRY, RESEARCH

    @Column(name = "mentorship_status", nullable = false, length = 50)
    private String mentorshipStatus; // PENDING, MATCHING, ACTIVE, COMPLETED, PAUSED, CANCELLED

    @Column(name = "interests", length = 1000)
    private String interests;

    @Column(name = "goals", length = 2000)
    private String goals;

    @Column(name = "experience_level", length = 50)
    private String experienceLevel; // BEGINNER, INTERMEDIATE, ADVANCED, EXPERT

    @Column(name = "preferred_mentor_gender", length = 50)
    private String preferredMentorGender;

    @Column(name = "preferred_language", length = 50)
    private String preferredLanguage;

    @Column(name = "max_weekly_hours", nullable = false)
    @Builder.Default
    private Integer maxWeeklyHours = 2;

    @Column(name = "notes", length = 2000)
    private String notes;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
