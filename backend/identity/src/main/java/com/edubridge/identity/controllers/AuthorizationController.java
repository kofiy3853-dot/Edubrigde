package com.edubridge.identity.controllers;

import com.edubridge.identity.services.AuthorizationContext;
import com.edubridge.identity.services.PolicyEngine;
import com.edubridge.shared.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/identity/authorization")
@RequiredArgsConstructor
public class AuthorizationController {

    private final PolicyEngine policyEngine;

    @GetMapping("/check-permission")
    public ResponseEntity<ApiResponse<Boolean>> checkPermission(@RequestParam String permission) {
        UUID userId = getCurrentUserId();
        boolean hasPermission = policyEngine.hasPermission(userId, permission);
        return ResponseEntity.ok(ApiResponse.success(hasPermission));
    }

    @GetMapping("/check-role")
    public ResponseEntity<ApiResponse<Boolean>> checkRole(@RequestParam String role) {
        UUID userId = getCurrentUserId();
        boolean hasRole = policyEngine.hasRole(userId, role);
        return ResponseEntity.ok(ApiResponse.success(hasRole));
    }

    @GetMapping("/permissions")
    public ResponseEntity<ApiResponse<Set<String>>> getUserPermissions() {
        UUID userId = getCurrentUserId();
        Set<String> permissions = policyEngine.getUserPermissions(userId);
        return ResponseEntity.ok(ApiResponse.success(permissions));
    }

    @GetMapping("/roles")
    public ResponseEntity<ApiResponse<Set<String>>> getUserRoles() {
        UUID userId = getCurrentUserId();
        Set<String> roles = policyEngine.getUserRoles(userId);
        return ResponseEntity.ok(ApiResponse.success(roles));
    }

    @PostMapping("/evaluate")
    public ResponseEntity<ApiResponse<PolicyEngine.AuthorizationResult>> evaluate(
            @RequestParam String resource,
            @RequestParam String action) {
        UUID userId = getCurrentUserId();

        AuthorizationContext context = AuthorizationContext.builder()
            .userId(userId)
            .resource(resource)
            .action(action)
            .build();

        PolicyEngine.AuthorizationResult result = policyEngine.evaluate(context);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    private UUID getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UUID) {
            return (UUID) authentication.getPrincipal();
        }
        throw new RuntimeException("User not authenticated");
    }
}
