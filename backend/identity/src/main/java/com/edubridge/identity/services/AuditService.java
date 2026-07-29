package com.edubridge.identity.services;

import com.edubridge.identity.dto.AuditLogResponse;
import com.edubridge.identity.dto.AuditStatsResponse;
import com.edubridge.identity.entities.AuditLog;
import com.edubridge.identity.repositories.AuditLogRepository;
import com.edubridge.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuditService {

    private final AuditLogRepository auditLogRepository;

    @Transactional
    public AuditLog log(AuditLog.AuditLogBuilder builder) {
        AuditLog auditLog = builder.build();
        auditLogRepository.save(auditLog);
        log.debug("Audit log created: auditId={}, operation={}", auditLog.getAuditId(), auditLog.getOperation());
        return auditLog;
    }

    @Transactional
    public void logAuthentication(UUID userId, String operation, AuditLog.Result result,
                                   String ipAddress, String userAgent, Long durationMs) {
        AuditLog auditLog = AuditLog.builder()
            .userId(userId)
            .serviceName("identity-authentication")
            .operation(operation)
            .resourceType("USER")
            .action(operation)
            .result(result.name())
            .ipAddress(ipAddress)
            .userAgent(userAgent)
            .durationMs(durationMs)
            .build();
        auditLogRepository.save(auditLog);
    }

    @Transactional
    public void logAuthorization(UUID userId, String operation, String resourceType,
                                  String resourceId, AuditLog.Result result) {
        AuditLog auditLog = AuditLog.builder()
            .userId(userId)
            .serviceName("identity-authorization")
            .operation(operation)
            .resourceType(resourceType)
            .resourceId(resourceId)
            .action(operation)
            .result(result.name())
            .build();
        auditLogRepository.save(auditLog);
    }

    @Transactional
    public void logUserManagement(UUID userId, String operation, String resourceType,
                                   String resourceId, AuditLog.Result result) {
        AuditLog auditLog = AuditLog.builder()
            .userId(userId)
            .serviceName("identity-user-management")
            .operation(operation)
            .resourceType(resourceType)
            .resourceId(resourceId)
            .action(operation)
            .result(result.name())
            .build();
        auditLogRepository.save(auditLog);
    }

    @Transactional(readOnly = true)
    public Page<AuditLogResponse> getUserAuditLogs(UUID userId, Pageable pageable) {
        return auditLogRepository.findByUserIdOrderByTimestampDesc(userId, pageable)
            .map(this::mapToResponse);
    }

    @Transactional(readOnly = true)
    public List<AuditLogResponse> getUserAuditLogsByAction(UUID userId, String action) {
        return auditLogRepository.findByUserIdAndActionOrderByTimestampDesc(userId, action).stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<AuditLogResponse> getAuditLogsByDateRange(Instant start, Instant end, Pageable pageable) {
        return auditLogRepository.findByDateRange(start, end, pageable)
            .map(this::mapToResponse);
    }

    @Transactional(readOnly = true)
    public AuditStatsResponse getAuditStats(UUID userId) {
        AuditStatsResponse stats = new AuditStatsResponse();
        stats.setTotalLogs(auditLogRepository.count());
        stats.setSuccessCount(auditLogRepository.countByUserIdAndResult(userId, AuditLog.Result.SUCCESS.name()));
        stats.setFailureCount(auditLogRepository.countByUserIdAndResult(userId, AuditLog.Result.FAILURE.name()));
        stats.setDeniedCount(auditLogRepository.countByUserIdAndResult(userId, AuditLog.Result.DENIED.name()));
        stats.setErrorCount(auditLogRepository.countByUserIdAndResult(userId, AuditLog.Result.ERROR.name()));
        return stats;
    }

    private AuditLogResponse mapToResponse(AuditLog auditLog) {
        AuditLogResponse response = new AuditLogResponse();
        response.setId(auditLog.getId());
        response.setAuditId(auditLog.getAuditId());
        response.setTraceId(auditLog.getTraceId());
        response.setRequestId(auditLog.getRequestId());
        response.setUserId(auditLog.getUserId());
        response.setServiceName(auditLog.getServiceName());
        response.setOperation(auditLog.getOperation());
        response.setResourceType(auditLog.getResourceType());
        response.setResourceId(auditLog.getResourceId());
        response.setAction(auditLog.getAction());
        response.setResult(auditLog.getResult());
        response.setIpAddress(auditLog.getIpAddress());
        response.setDurationMs(auditLog.getDurationMs());
        response.setErrorMessage(auditLog.getErrorMessage());
        response.setTimestamp(auditLog.getTimestamp());
        return response;
    }
}
