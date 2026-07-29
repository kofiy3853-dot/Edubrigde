package com.edubridge.identity.controllers;

import com.edubridge.identity.dto.*;
import com.edubridge.identity.services.UserManagementService;
import com.edubridge.shared.dto.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/identity/users")
@RequiredArgsConstructor
public class UserController {

    private final UserManagementService userManagementService;

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<UserProfileResponse>> getProfile() {
        UUID userId = getCurrentUserId();
        UserProfileResponse response = userManagementService.getProfile(userId);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @PutMapping("/profile")
    public ResponseEntity<ApiResponse<UserProfileResponse>> updateProfile(
            @Valid @RequestBody UpdateProfileRequest request) {
        UUID userId = getCurrentUserId();
        UserProfileResponse response = userManagementService.updateProfile(userId, request);
        return ResponseEntity.ok(ApiResponse.success(response, "Profile updated successfully"));
    }

    @GetMapping("/preferences")
    public ResponseEntity<ApiResponse<UserPreferencesResponse>> getPreferences() {
        UUID userId = getCurrentUserId();
        UserPreferencesResponse response = userManagementService.getPreferences(userId);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @PutMapping("/preferences")
    public ResponseEntity<ApiResponse<UserPreferencesResponse>> updatePreferences(
            @Valid @RequestBody UpdatePreferencesRequest request) {
        UUID userId = getCurrentUserId();
        UserPreferencesResponse response = userManagementService.updatePreferences(userId, request);
        return ResponseEntity.ok(ApiResponse.success(response, "Preferences updated successfully"));
    }

    @PutMapping("/status")
    public ResponseEntity<ApiResponse<Void>> updateAccountStatus(@RequestParam String status) {
        UUID userId = getCurrentUserId();
        userManagementService.updateAccountStatus(userId, status);
        return ResponseEntity.ok(ApiResponse.success(null, "Account status updated"));
    }

    private UUID getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UUID) {
            return (UUID) authentication.getPrincipal();
        }
        throw new RuntimeException("User not authenticated");
    }
}
