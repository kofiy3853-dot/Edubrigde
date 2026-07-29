package com.edubridge.identity.controllers;

import com.edubridge.identity.dto.AuditLogResponse;
import com.edubridge.identity.dto.AuditStatsResponse;
import com.edubridge.identity.services.AuditService;
import com.edubridge.shared.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/identity/audit")
@RequiredArgsConstructor
public class AuditController {

    private final AuditService auditService;

    @GetMapping("/logs")
    public ResponseEntity<ApiResponse<Page<AuditLogResponse>>> getAuditLogs(Pageable pageable) {
        UUID userId = getCurrentUserId();
        Page<AuditLogResponse> logs = auditService.getUserAuditLogs(userId, pageable);
        return ResponseEntity.ok(ApiResponse.success(logs));
    }

    @GetMapping("/logs/action/{action}")
    public ResponseEntity<ApiResponse<java.util.List<AuditLogResponse>>> getAuditLogsByAction(
            @PathVariable String action) {
        UUID userId = getCurrentUserId();
        java.util.List<AuditLogResponse> logs = auditService.getUserAuditLogsByAction(userId, action);
        return ResponseEntity.ok(ApiResponse.success(logs));
    }

    @GetMapping("/logs/date-range")
    public ResponseEntity<ApiResponse<Page<AuditLogResponse>>> getAuditLogsByDateRange(
            @RequestParam Instant start,
            @RequestParam Instant end,
            Pageable pageable) {
        Page<AuditLogResponse> logs = auditService.getAuditLogsByDateRange(start, end, pageable);
        return ResponseEntity.ok(ApiResponse.success(logs));
    }

    @GetMapping("/stats")
    public ResponseEntity<ApiResponse<AuditStatsResponse>> getAuditStats() {
        UUID userId = getCurrentUserId();
        AuditStatsResponse stats = auditService.getAuditStats(userId);
        return ResponseEntity.ok(ApiResponse.success(stats));
    }

    private UUID getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UUID) {
            return (UUID) authentication.getPrincipal();
        }
        throw new RuntimeException("User not authenticated");
    }
}
