package com.edubridge.identity.entities;

import com.edubridge.shared.entities.AuditableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "permissions", schema = "identity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Permission extends AuditableEntity {

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "resource", nullable = false, length = 100)
    private String resource;

    @Column(name = "action", nullable = false, length = 50)
    private String action;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;

    @ManyToMany(mappedBy = "permissions", fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Role> roles = new HashSet<>();

    public enum Action {
        CREATE, READ, UPDATE, DELETE, MANAGE, VIEW, EXPORT, IMPORT
    }

    public enum Resource {
        USER, ROLE, PERMISSION, SESSION, COURSE, ENROLLMENT,
        PAYMENT, SCHOLARSHIP, APPLICATION, INSTITUTION,
        REPORT, NOTIFICATION, MESSAGE, AI_SERVICE
    }
}
