package com.edubridge.identity.dto;

import lombok.Data;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Data
public class UserResponse {

    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String avatarUrl;
    private Boolean emailVerified;
    private Boolean phoneVerified;
    private String status;
    private Set<RoleResponse> roles;
    private Instant lastLoginAt;
    private Instant createdAt;
}
