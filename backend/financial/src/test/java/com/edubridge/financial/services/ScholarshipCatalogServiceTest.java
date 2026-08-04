package com.edubridge.financial.services;

import com.edubridge.financial.entities.ScholarshipCatalog;
import com.edubridge.financial.repositories.ScholarshipCatalogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScholarshipCatalogServiceTest {

    @Mock
    private ScholarshipCatalogRepository scholarshipCatalogRepository;

    @InjectMocks
    private ScholarshipCatalogService scholarshipCatalogService;

    private ScholarshipCatalog scholarship;

    @BeforeEach
    void setUp() {
        scholarship = ScholarshipCatalog.builder()
            .id("sch-001")
            .code("MERIT-2024")
            .name("Merit Scholarship 2024")
            .description("Merit-based scholarship for outstanding students")
            .scholarshipType("MERIT")
            .fundingOrganization("EduBridge Foundation")
            .countryCode("US")
            .currencyCode("USD")
            .minAmount(new BigDecimal("1000"))
            .maxAmount(new BigDecimal("10000"))
            .coverageType("TUITION")
            .isActive(true)
            .isRenewable(true)
            .maxRecipients(100)
            .currentRecipients(25)
            .applicationDeadline(Instant.now().plusSeconds(86400 * 30))
            .build();
    }

    @Test
    void createScholarship_Success() {
        when(scholarshipCatalogRepository.existsByCode(anyString())).thenReturn(false);
        when(scholarshipCatalogRepository.save(any(ScholarshipCatalog.class))).thenReturn(scholarship);

        ScholarshipCatalog created = scholarshipCatalogService.createScholarship(scholarship);

        assertNotNull(created);
        assertEquals("MERIT-2024", created.getCode());
        assertEquals("Merit Scholarship 2024", created.getName());
        verify(scholarshipCatalogRepository, times(1)).save(any(ScholarshipCatalog.class));
    }

    @Test
    void createScholarship_DuplicateCode_ThrowsException() {
        when(scholarshipCatalogRepository.existsByCode(anyString())).thenReturn(true);

        assertThrows(RuntimeException.class, () -> scholarshipCatalogService.createScholarship(scholarship));
        verify(scholarshipCatalogRepository, never()).save(any(ScholarshipCatalog.class));
    }

    @Test
    void getScholarshipById_Success() {
        when(scholarshipCatalogRepository.findById(anyString())).thenReturn(Optional.of(scholarship));

        ScholarshipCatalog found = scholarshipCatalogService.getScholarshipById("sch-001");

        assertNotNull(found);
        assertEquals("MERIT-2024", found.getCode());
    }

    @Test
    void getScholarshipById_NotFound_ThrowsException() {
        when(scholarshipCatalogRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> scholarshipCatalogService.getScholarshipById("nonexistent"));
    }

    @Test
    void getScholarshipByCode_Success() {
        when(scholarshipCatalogRepository.findByCode(anyString())).thenReturn(Optional.of(scholarship));

        ScholarshipCatalog found = scholarshipCatalogService.getScholarshipByCode("MERIT-2024");

        assertNotNull(found);
        assertEquals("MERIT-2024", found.getCode());
    }

    @Test
    void getScholarshipByCode_NotFound_ThrowsException() {
        when(scholarshipCatalogRepository.findByCode(anyString())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> scholarshipCatalogService.getScholarshipByCode("NONEXISTENT"));
    }

    @Test
    void getAllActiveScholarships_Success() {
        when(scholarshipCatalogRepository.findByIsActiveTrueOrderByName()).thenReturn(Arrays.asList(scholarship));

        List<ScholarshipCatalog> scholarships = scholarshipCatalogService.getAllActiveScholarships();

        assertNotNull(scholarships);
        assertEquals(1, scholarships.size());
    }

    @Test
    void updateScholarship_Success() {
        when(scholarshipCatalogRepository.findById(anyString())).thenReturn(Optional.of(scholarship));
        when(scholarshipCatalogRepository.save(any(ScholarshipCatalog.class))).thenReturn(scholarship);

        ScholarshipCatalog updateData = ScholarshipCatalog.builder()
            .name("Merit Scholarship 2024 Updated")
            .build();

        ScholarshipCatalog updated = scholarshipCatalogService.updateScholarship("sch-001", updateData);

        assertNotNull(updated);
        verify(scholarshipCatalogRepository, times(1)).save(any(ScholarshipCatalog.class));
    }

    @Test
    void deactivateScholarship_Success() {
        when(scholarshipCatalogRepository.findById(anyString())).thenReturn(Optional.of(scholarship));
        when(scholarshipCatalogRepository.save(any(ScholarshipCatalog.class))).thenReturn(scholarship);

        scholarshipCatalogService.deactivateScholarship("sch-001");

        assertFalse(scholarship.getIsActive());
        verify(scholarshipCatalogRepository, times(1)).save(any(ScholarshipCatalog.class));
    }
}
