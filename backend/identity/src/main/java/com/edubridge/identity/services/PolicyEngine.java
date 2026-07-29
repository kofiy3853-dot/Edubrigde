package com.edubridge.identity.services;

import com.edubridge.identity.entities.*;
import com.edubridge.identity.repositories.AccessLogRepository;
import com.edubridge.identity.repositories.PolicyRepository;
import com.edubridge.identity.repositories.UserRepository;
import com.edubridge.shared.exceptions.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PolicyEngine {

    private final PolicyRepository policyRepository;
    private final UserRepository userRepository;
    private final AccessLogRepository accessLogRepository;

    @Transactional(readOnly = true)
    public AuthorizationResult evaluate(AuthorizationContext context) {
        User user = userRepository.findByIdWithRoles(context.getUserId())
            .orElseThrow(() -> new UnauthorizedException("User not found"));

        Set<String> userRoles = user.getRoles().stream()
            .filter(Role::getIsActive)
            .map(Role::getName)
            .collect(Collectors.toSet());

        Set<String> userPermissions = user.getRoles().stream()
            .filter(Role::getIsActive)
            .flatMap(role -> role.getPermissions().stream())
            .filter(Permission::getIsActive)
            .map(Permission::getName)
            .collect(Collectors.toSet());

        List<UUID> roleIds = user.getRoles().stream()
            .filter(Role::getIsActive)
            .map(Role::getId)
            .collect(Collectors.toList());

        List<Policy> policies = policyRepository.findActivePoliciesForRoles(
            roleIds, context.getResource(), context.getAction()
        );

        boolean accessGranted = false;
        String matchedPolicy = null;

        for (Policy policy : policies) {
            if (evaluateCondition(policy, user, userRoles, userPermissions, context)) {
                if (Policy.Effect.DENY.name().equals(policy.getEffect())) {
                    logAccess(context, false, policy.getName(), "DENY policy matched");
                    return AuthorizationResult.denied(policy.getName(), "DENY policy matched");
                }
                accessGranted = true;
                matchedPolicy = policy.getName();
                break;
            }
        }

        if (!accessGranted) {
            logAccess(context, false, null, "No matching ALLOW policy");
            return AuthorizationResult.denied(null, "No matching ALLOW policy");
        }

        logAccess(context, true, matchedPolicy, null);
        return AuthorizationResult.granted(matchedPolicy);
    }

    @Transactional(readOnly = true)
    public boolean hasPermission(UUID userId, String permissionName) {
        User user = userRepository.findByIdWithRoles(userId)
            .orElseThrow(() -> new UnauthorizedException("User not found"));

        return user.getRoles().stream()
            .filter(Role::getIsActive)
            .flatMap(role -> role.getPermissions().stream())
            .filter(Permission::getIsActive)
            .anyMatch(permission -> permission.getName().equals(permissionName));
    }

    @Transactional(readOnly = true)
    public boolean hasRole(UUID userId, String roleName) {
        User user = userRepository.findByIdWithRoles(userId)
            .orElseThrow(() -> new UnauthorizedException("User not found"));

        return user.getRoles().stream()
            .anyMatch(role -> role.getName().equals(roleName) && role.getIsActive());
    }

    @Transactional(readOnly = true)
    public Set<String> getUserPermissions(UUID userId) {
        User user = userRepository.findByIdWithRoles(userId)
            .orElseThrow(() -> new UnauthorizedException("User not found"));

        return user.getRoles().stream()
            .filter(Role::getIsActive)
            .flatMap(role -> role.getPermissions().stream())
            .filter(Permission::getIsActive)
            .map(Permission::getName)
            .collect(Collectors.toSet());
    }

    @Transactional(readOnly = true)
    public Set<String> getUserRoles(UUID userId) {
        User user = userRepository.findByIdWithRoles(userId)
            .orElseThrow(() -> new UnauthorizedException("User not found"));

        return user.getRoles().stream()
            .filter(Role::getIsActive)
            .map(Role::getName)
            .collect(Collectors.toSet());
    }

    public void checkPermission(UUID userId, String permissionName) {
        if (!hasPermission(userId, permissionName)) {
            throw new UnauthorizedException("Missing permission: " + permissionName);
        }
    }

    public void checkRole(UUID userId, String roleName) {
        if (!hasRole(userId, roleName)) {
            throw new UnauthorizedException("Missing role: " + roleName);
        }
    }

    private boolean evaluateCondition(Policy policy, User user, Set<String> roles,
                                      Set<String> permissions, AuthorizationContext context) {
        if (policy.getConditionExpression() == null || policy.getConditionExpression().isEmpty()) {
            return true;
        }

        String condition = policy.getConditionExpression().toLowerCase();

        if (condition.contains("own_resource")) {
            return context.getResourceOwnerId() != null &&
                   context.getResourceOwnerId().equals(user.getId());
        }

        if (condition.contains("any_resource")) {
            return true;
        }

        return true;
    }

    private void logAccess(AuthorizationContext context, boolean granted, String policyName, String denialReason) {
        try {
            AccessLog log = AccessLog.builder()
                .userId(context.getUserId())
                .resource(context.getResource())
                .action(context.getAction())
                .accessGranted(granted)
                .policyName(policyName)
                .denialReason(denialReason)
                .ipAddress(context.getIpAddress())
                .userAgent(context.getUserAgent())
                .requestId(context.getRequestId())
                .build();
            accessLogRepository.save(log);
        } catch (Exception e) {
            log.error("Failed to log access: {}", e.getMessage());
        }
    }

    public record AuthorizationResult(boolean granted, String policyName, String denialReason) {
        public static AuthorizationResult granted(String policyName) {
            return new AuthorizationResult(true, policyName, null);
        }

        public static AuthorizationResult denied(String policyName, String denialReason) {
            return new AuthorizationResult(false, policyName, denialReason);
        }
    }
}
