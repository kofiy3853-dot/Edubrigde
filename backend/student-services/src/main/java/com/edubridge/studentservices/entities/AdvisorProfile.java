package com.edubridge.studentservices.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "advisor_profiles", schema = "student_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdvisorProfile extends AuditableEntity {

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "advisor_type", nullable = false, length = 50)
    private String advisorType; // ACADEMIC, FINANCIAL, SUCCESS, INTERNATIONAL, GENERAL

    @Column(name = "specialization", length = 255)
    private String specialization;

    @Column(name = "qualifications", length = 2000)
    private String qualifications;

    @Column(name = "experience_years")
    private Integer experienceYears;

    @Column(name = "languages", length = 500)
    private String languages;

    @Column(name = "availability_status", nullable = false, length = 50)
    private String availabilityStatus; // AVAILABLE, BUSY, UNAVAILABLE, ON_LEAVE

    @Column(name = "max_consultations_per_day", nullable = false)
    @Builder.Default
    private Integer maxConsultationsPerDay = 10;

    @Column(name = "current_consultations", nullable = false)
    @Builder.Default
    private Integer currentConsultations = 0;

    @Column(name = "rating", precision = 5, scale = 2)
    private BigDecimal rating;

    @Column(name = "total_reviews", nullable = false)
    @Builder.Default
    private Integer totalReviews = 0;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
