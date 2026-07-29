package com.edubridge.identity.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class PermissionResponse {

    private UUID id;
    private String name;
    private String description;
    private String resource;
    private String action;
    private Boolean isActive;
}
