package com.edubridge.learningservices.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "educational_resource_records", schema = "learning_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceRecord extends AuditableEntity {

    @Column(name = "course_profile_id", nullable = false)
    private String courseProfileId;

    @Column(name = "student_id", nullable = false)
    private String studentId;

    @Column(name = "resource_type", nullable = false, length = 50)
    private String resourceType; // ACADEMIC, DIGITAL_LEARNING, CAREER, GUIDANCE, INTERNATIONAL

    @Column(name = "resource_name", nullable = false, length = 255)
    private String resourceName;

    @Column(name = "resource_status", nullable = false, length = 50)
    private String resourceStatus; // AVAILABLE, ACCESSIBLE, COMPLETED, RESTRICTED

    @Column(name = "description", length = 2000)
    private String description;

    @Column(name = "resource_url", length = 500)
    private String resourceUrl;

    @Column(name = "format_type", length = 50)
    private String formatType; // VIDEO, DOCUMENT, INTERACTIVE, AUDIO, IMAGE

    @Column(name = "access_count")
    @Builder.Default
    private Integer accessCount = 0;

    @Column(name = "last_accessed_at")
    private LocalDateTime lastAccessedAt;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;
}
