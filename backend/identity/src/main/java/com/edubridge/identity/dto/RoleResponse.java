package com.edubridge.identity.dto;

import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class RoleResponse {

    private UUID id;
    private String name;
    private String description;
    private Boolean isSystem;
    private Boolean isActive;
    private Set<PermissionResponse> permissions;
}
