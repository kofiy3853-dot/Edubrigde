package com.edubridge.identity.services;

import com.edubridge.identity.dto.RoleResponse;
import com.edubridge.identity.entities.Role;
import com.edubridge.identity.repositories.RoleRepository;
import com.edubridge.shared.exceptions.BadRequestException;
import com.edubridge.shared.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    private Role role;

    @BeforeEach
    void setUp() {
        role = Role.builder()
            .id(UUID.randomUUID())
            .name("ADMIN")
            .description("Administrator role")
            .isSystem(true)
            .isActive(true)
            .build();
    }

    @Test
    void getAllRoles_Success() {
        Role role2 = Role.builder()
            .id(UUID.randomUUID())
            .name("USER")
            .description("Regular user role")
            .isSystem(false)
            .isActive(true)
            .build();

        when(roleRepository.findAll()).thenReturn(Arrays.asList(role, role2));

        List<RoleResponse> responses = roleService.getAllRoles();

        assertNotNull(responses);
        assertEquals(2, responses.size());
        verify(roleRepository, times(1)).findAll();
    }

    @Test
    void getRoleById_Success() {
        when(roleRepository.findById(any(UUID.class))).thenReturn(Optional.of(role));

        RoleResponse response = roleService.getRoleById(role.getId());

        assertNotNull(response);
        assertEquals("ADMIN", response.getName());
        assertEquals("Administrator role", response.getDescription());
    }

    @Test
    void getRoleById_NotFound_ThrowsException() {
        when(roleRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> roleService.getRoleById(UUID.randomUUID()));
    }

    @Test
    void getRoleByName_Success() {
        when(roleRepository.findByName(anyString())).thenReturn(Optional.of(role));

        RoleResponse response = roleService.getRoleByName("ADMIN");

        assertNotNull(response);
        assertEquals("ADMIN", response.getName());
    }

    @Test
    void getRoleByName_NotFound_ThrowsException() {
        when(roleRepository.findByName(anyString())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> roleService.getRoleByName("NONEXISTENT"));
    }

    @Test
    void createRole_Success() {
        when(roleRepository.existsByName(anyString())).thenReturn(false);
        when(roleRepository.save(any(Role.class))).thenReturn(role);

        Role savedRole = roleService.createRole("ADMIN", "Administrator role", true);

        assertNotNull(savedRole);
        assertEquals("ADMIN", savedRole.getName());
        verify(roleRepository, times(1)).save(any(Role.class));
    }

    @Test
    void createRole_DuplicateName_ThrowsException() {
        when(roleRepository.existsByName(anyString())).thenReturn(true);

        assertThrows(BadRequestException.class, () -> roleService.createRole("ADMIN", "Administrator role", true));
        verify(roleRepository, never()).save(any(Role.class));
    }
}
