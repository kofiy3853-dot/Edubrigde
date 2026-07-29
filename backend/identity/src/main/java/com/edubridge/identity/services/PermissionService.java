package com.edubridge.identity.services;

import com.edubridge.identity.dto.PermissionResponse;
import com.edubridge.identity.entities.Permission;
import com.edubridge.identity.repositories.PermissionRepository;
import com.edubridge.shared.exceptions.BadRequestException;
import com.edubridge.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PermissionService {

    private final PermissionRepository permissionRepository;

    @Transactional(readOnly = true)
    public List<PermissionResponse> getAllPermissions() {
        return permissionRepository.findAll().stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PermissionResponse getPermissionById(UUID id) {
        Permission permission = permissionRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Permission not found: " + id));
        return mapToResponse(permission);
    }

    @Transactional(readOnly = true)
    public PermissionResponse getPermissionByName(String name) {
        Permission permission = permissionRepository.findByName(name)
            .orElseThrow(() -> new ResourceNotFoundException("Permission not found: " + name));
        return mapToResponse(permission);
    }

    @Transactional
    public Permission createPermission(String name, String description, String resource, String action) {
        if (permissionRepository.existsByName(name)) {
            throw new BadRequestException("Permission already exists: " + name);
        }

        Permission permission = Permission.builder()
            .name(name)
            .description(description)
            .resource(resource)
            .action(action)
            .isActive(true)
            .build();

        Permission savedPermission = permissionRepository.save(permission);
        log.info("Permission created: id={}, name={}", savedPermission.getId(), savedPermission.getName());
        return savedPermission;
    }

    private PermissionResponse mapToResponse(Permission permission) {
        PermissionResponse response = new PermissionResponse();
        response.setId(permission.getId());
        response.setName(permission.getName());
        response.setDescription(permission.getDescription());
        response.setResource(permission.getResource());
        response.setAction(permission.getAction());
        response.setIsActive(permission.getIsActive());
        return response;
    }
}
