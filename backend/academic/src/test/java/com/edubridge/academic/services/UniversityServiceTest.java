package com.edubridge.academic.services;

import com.edubridge.academic.entities.University;
import com.edubridge.academic.repositories.UniversityRepository;
import com.edubridge.shared.exceptions.ConflictException;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UniversityServiceTest {

    @Mock
    private UniversityRepository universityRepository;

    @InjectMocks
    private UniversityService universityService;

    private University university;

    @BeforeEach
    void setUp() {
        university = University.builder()
            .id("uni-001")
            .code("MIT")
            .name("Massachusetts Institute of Technology")
            .countryCode("US")
            .city("Cambridge")
            .institutionType("RESEARCH_UNIVERSITY")
            .accreditationStatus("ACCREDITED")
            .isActive(true)
            .isPartner(false)
            .build();
    }

    @Test
    void createUniversity_Success() {
        when(universityRepository.existsByCode(anyString())).thenReturn(false);
        when(universityRepository.existsByName(anyString())).thenReturn(false);
        when(universityRepository.save(any(University.class))).thenReturn(university);

        University saved = universityService.createUniversity(university);

        assertNotNull(saved);
        assertEquals("MIT", saved.getCode());
        assertEquals("Massachusetts Institute of Technology", saved.getName());
        verify(universityRepository, times(1)).save(any(University.class));
    }

    @Test
    void createUniversity_DuplicateCode_ThrowsException() {
        when(universityRepository.existsByCode(anyString())).thenReturn(true);

        assertThrows(ConflictException.class, () -> universityService.createUniversity(university));
        verify(universityRepository, never()).save(any(University.class));
    }

    @Test
    void createUniversity_DuplicateName_ThrowsException() {
        when(universityRepository.existsByCode(anyString())).thenReturn(false);
        when(universityRepository.existsByName(anyString())).thenReturn(true);

        assertThrows(ConflictException.class, () -> universityService.createUniversity(university));
        verify(universityRepository, never()).save(any(University.class));
    }

    @Test
    void getUniversityById_Success() {
        when(universityRepository.findById(anyString())).thenReturn(Optional.of(university));

        University found = universityService.getUniversityById("uni-001");

        assertNotNull(found);
        assertEquals("MIT", found.getCode());
    }

    @Test
    void getUniversityById_NotFound_ThrowsException() {
        when(universityRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> universityService.getUniversityById("nonexistent"));
    }

    @Test
    void getUniversityByCode_Success() {
        when(universityRepository.findByCode(anyString())).thenReturn(Optional.of(university));

        University found = universityService.getUniversityByCode("MIT");

        assertNotNull(found);
        assertEquals("MIT", found.getCode());
    }

    @Test
    void getUniversityByCode_NotFound_ThrowsException() {
        when(universityRepository.findByCode(anyString())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> universityService.getUniversityByCode("NONEXISTENT"));
    }

    @Test
    void getAllUniversities_Success() {
        University university2 = University.builder()
            .id("uni-002")
            .code("STANFORD")
            .name("Stanford University")
            .build();

        when(universityRepository.findAll()).thenReturn(Arrays.asList(university, university2));

        List<University> universities = universityService.getAllUniversities();

        assertNotNull(universities);
        assertEquals(2, universities.size());
    }

    @Test
    void updateUniversity_Success() {
        when(universityRepository.findById(anyString())).thenReturn(Optional.of(university));
        when(universityRepository.save(any(University.class))).thenReturn(university);

        University updateData = University.builder()
            .name("MIT - Updated")
            .build();

        University updated = universityService.updateUniversity("uni-001", updateData);

        assertNotNull(updated);
        verify(universityRepository, times(1)).save(any(University.class));
    }

    @Test
    void deactivateUniversity_Success() {
        when(universityRepository.findById(anyString())).thenReturn(Optional.of(university));
        when(universityRepository.save(any(University.class))).thenReturn(university);

        University deactivated = universityService.deactivateUniversity("uni-001");

        assertNotNull(deactivated);
        assertFalse(deactivated.getIsActive());
    }

    @Test
    void activateUniversity_Success() {
        university.setIsActive(false);
        when(universityRepository.findById(anyString())).thenReturn(Optional.of(university));
        when(universityRepository.save(any(University.class))).thenReturn(university);

        University activated = universityService.activateUniversity("uni-001");

        assertNotNull(activated);
        assertTrue(activated.getIsActive());
    }

    @Test
    void searchUniversities_Success() {
        when(universityRepository.findAll()).thenReturn(Arrays.asList(university));

        List<University> results = universityService.searchUniversities("MIT");

        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals("MIT", results.get(0).getCode());
    }

    @Test
    void searchUniversities_NoResults() {
        when(universityRepository.findAll()).thenReturn(Arrays.asList(university));

        List<University> results = universityService.searchUniversities("NONEXISTENT");

        assertNotNull(results);
        assertTrue(results.isEmpty());
    }
}
