package com.edubridge.identity.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "user_profiles", schema = "identity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile extends AuditableEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "date_of_birth")
    private Instant dateOfBirth;

    @Column(name = "gender", length = 20)
    private String gender;

    @Column(name = "nationality", length = 50)
    private String nationality;

    @Column(name = "country", length = 50)
    private String country;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "address", length = 500)
    private String address;

    @Column(name = "postal_code", length = 20)
    private String postalCode;

    @Column(name = "bio", length = 1000)
    private String bio;

    @Column(name = "website", length = 255)
    private String website;

    @Column(name = "linkedin_url", length = 255)
    private String linkedinUrl;

    @Column(name = "timezone", length = 50)
    @Builder.Default
    private String timezone = "UTC";

    @Column(name = "language", length = 10)
    @Builder.Default
    private String language = "en";

    @Column(name = "currency", length = 3)
    @Builder.Default
    private String currency = "USD";
}
