package com.edubridge.financial.services;

import com.edubridge.financial.entities.ScholarshipCatalog;
import com.edubridge.financial.repositories.ScholarshipCatalogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScholarshipCatalogService {

    private final ScholarshipCatalogRepository scholarshipCatalogRepository;

    @Transactional
    public ScholarshipCatalog createScholarship(ScholarshipCatalog scholarship) {
        log.info("Creating scholarship: {} code: {}", scholarship.getName(), scholarship.getCode());
        if (scholarshipCatalogRepository.existsByCode(scholarship.getCode())) {
            throw new RuntimeException("Scholarship with code " + scholarship.getCode() + " already exists");
        }
        return scholarshipCatalogRepository.save(scholarship);
    }

    @Transactional(readOnly = true)
    public ScholarshipCatalog getScholarshipById(String id) {
        return scholarshipCatalogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Scholarship not found: " + id));
    }

    @Transactional(readOnly = true)
    public ScholarshipCatalog getScholarshipByCode(String code) {
        return scholarshipCatalogRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Scholarship not found: " + code));
    }

    @Transactional(readOnly = true)
    public List<ScholarshipCatalog> getAllActiveScholarships() {
        return scholarshipCatalogRepository.findByIsActiveTrueOrderByName();
    }

    @Transactional(readOnly = true)
    public List<ScholarshipCatalog> getScholarshipsByType(String scholarshipType) {
        return scholarshipCatalogRepository.findByScholarshipTypeAndIsActiveTrue(scholarshipType);
    }

    @Transactional(readOnly = true)
    public List<ScholarshipCatalog> getScholarshipsByCountry(String countryCode) {
        return scholarshipCatalogRepository.findByCountryCodeAndIsActiveTrue(countryCode);
    }

    @Transactional(readOnly = true)
    public List<ScholarshipCatalog> getScholarshipsByOrganization(String fundingOrganization) {
        return scholarshipCatalogRepository.findByFundingOrganizationAndIsActiveTrue(fundingOrganization);
    }

    @Transactional(readOnly = true)
    public List<ScholarshipCatalog> getRenewableScholarships() {
        return scholarshipCatalogRepository.findByIsRenewableAndIsActiveTrue(true);
    }

    @Transactional(readOnly = true)
    public List<ScholarshipCatalog> getAvailableScholarships() {
        return scholarshipCatalogRepository.findAvailableScholarships();
    }

    @Transactional(readOnly = true)
    public List<ScholarshipCatalog> getScholarshipsByCountryAndType(String countryCode, String type) {
        return scholarshipCatalogRepository.findByCountryAndType(countryCode, type);
    }

    @Transactional(readOnly = true)
    public List<ScholarshipCatalog> getOpenDeadlineScholarships() {
        return scholarshipCatalogRepository.findOpenDeadlines();
    }

    @Transactional
    public ScholarshipCatalog updateScholarship(String id, ScholarshipCatalog updatedScholarship) {
        ScholarshipCatalog existing = getScholarshipById(id);
        existing.setName(updatedScholarship.getName());
        existing.setDescription(updatedScholarship.getDescription());
        existing.setScholarshipType(updatedScholarship.getScholarshipType());
        existing.setFundingOrganization(updatedScholarship.getFundingOrganization());
        existing.setCountryCode(updatedScholarship.getCountryCode());
        existing.setCurrencyCode(updatedScholarship.getCurrencyCode());
        existing.setMinAmount(updatedScholarship.getMinAmount());
        existing.setMaxAmount(updatedScholarship.getMaxAmount());
        existing.setCoverageType(updatedScholarship.getCoverageType());
        existing.setAcademicRequirement(updatedScholarship.getAcademicRequirement());
        existing.setFinancialRequirement(updatedScholarship.getFinancialRequirement());
        existing.setApplicationDeadline(updatedScholarship.getApplicationDeadline());
        existing.setAcademicYear(updatedScholarship.getAcademicYear());
        existing.setRenewable(updatedScholarship.getIsRenewable());
        existing.setRenewalCriteria(updatedScholarship.getRenewalCriteria());
        existing.setMaxRecipients(updatedScholarship.getMaxRecipients());
        existing.setCurrentRecipients(updatedScholarship.getCurrentRecipients());
        existing.setEligibleCountries(updatedScholarship.getEligibleCountries());
        existing.setEligiblePrograms(updatedScholarship.getEligiblePrograms());
        existing.setActive(updatedScholarship.getIsActive());
        return scholarshipCatalogRepository.save(existing);
    }

    @Transactional
    public void deactivateScholarship(String id) {
        ScholarshipCatalog existing = getScholarshipById(id);
        existing.setActive(false);
        scholarshipCatalogRepository.save(existing);
    }
}
