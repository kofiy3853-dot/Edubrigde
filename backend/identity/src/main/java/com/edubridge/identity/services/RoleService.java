package com.edubridge.identity.services;

import com.edubridge.identity.dto.RoleResponse;
import com.edubridge.identity.entities.Role;
import com.edubridge.identity.repositories.RoleRepository;
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
public class RoleService {

    private final RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public List<RoleResponse> getAllRoles() {
        return roleRepository.findAll().stream()
            .map(this::mapToResponse)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RoleResponse getRoleById(UUID id) {
        Role role = roleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Role not found: " + id));
        return mapToResponse(role);
    }

    @Transactional(readOnly = true)
    public RoleResponse getRoleByName(String name) {
        Role role = roleRepository.findByName(name)
            .orElseThrow(() -> new ResourceNotFoundException("Role not found: " + name));
        return mapToResponse(role);
    }

    @Transactional
    public Role createRole(String name, String description, Boolean isSystem) {
        if (roleRepository.existsByName(name)) {
            throw new BadRequestException("Role already exists: " + name);
        }

        Role role = Role.builder()
            .name(name)
            .description(description)
            .isSystem(isSystem)
            .isActive(true)
            .build();

        Role savedRole = roleRepository.save(role);
        log.info("Role created: id={}, name={}", savedRole.getId(), savedRole.getName());
        return savedRole;
    }

    private RoleResponse mapToResponse(Role role) {
        RoleResponse response = new RoleResponse();
        response.setId(role.getId());
        response.setName(role.getName());
        response.setDescription(role.getDescription());
        response.setIsSystem(role.getIsSystem());
        response.setIsActive(role.getIsActive());
        return response;
    }
}
