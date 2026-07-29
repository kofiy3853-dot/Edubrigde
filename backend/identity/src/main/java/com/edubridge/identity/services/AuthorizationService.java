package com.edubridge.identity.services;

import com.edubridge.identity.entities.Permission;
import com.edubridge.identity.entities.Role;
import com.edubridge.identity.entities.User;
import com.edubridge.identity.repositories.UserRepository;
import com.edubridge.shared.exceptions.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorizationService {

    private final UserRepository userRepository;

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
}
