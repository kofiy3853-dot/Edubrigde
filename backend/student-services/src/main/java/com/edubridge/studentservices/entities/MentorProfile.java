package com.edubridge.studentservices.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "mentor_profiles", schema = "student_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MentorProfile extends AuditableEntity {

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "mentor_type", nullable = false, length = 50)
    private String mentorType; // ACADEMIC, INDUSTRY, ALUMNI, PEER, RESEARCH

    @Column(name = "expertise_areas", length = 1000)
    private String expertiseAreas;

    @Column(name = "qualifications", length = 2000)
    private String qualifications;

    @Column(name = "experience_years")
    private Integer experienceYears;

    @Column(name = "languages", length = 500)
    private String languages;

    @Column(name = "availability_status", length = 50)
    private String availabilityStatus; // AVAILABLE, BUSY, UNAVAILABLE, ON_LEAVE

    @Column(name = "max_mentees", nullable = false)
    @Builder.Default
    private Integer maxMentees = 5;

    @Column(name = "current_mentees", nullable = false)
    @Builder.Default
    private Integer currentMentees = 0;

    @Column(name = "rating", precision = 3, scale = 2)
    private BigDecimal rating;

    @Column(name = "total_reviews")
    @Builder.Default
    private Integer totalReviews = 0;

    @Column(name = "bio", length = 2000)
    private String bio;

    @Column(name = "linkedin_url", length = 500)
    private String linkedinUrl;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
