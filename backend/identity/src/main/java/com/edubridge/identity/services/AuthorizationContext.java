package com.edubridge.identity.services;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AuthorizationContext {

    private UUID userId;
    private String resource;
    private String action;
    private UUID resourceOwnerId;
    private String ipAddress;
    private String userAgent;
    private String requestId;
}
