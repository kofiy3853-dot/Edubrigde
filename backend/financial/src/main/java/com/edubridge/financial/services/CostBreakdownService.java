package com.edubridge.financial.services;

import com.edubridge.financial.entities.CostBreakdown;
import com.edubridge.financial.repositories.CostBreakdownRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CostBreakdownService {

    private final CostBreakdownRepository costBreakdownRepository;

    @Transactional
    public CostBreakdown createCostBreakdown(CostBreakdown breakdown) {
        log.info("Creating cost breakdown: {} for university: {}", breakdown.getName(), breakdown.getUniversityId());
        return costBreakdownRepository.save(breakdown);
    }

    @Transactional(readOnly = true)
    public CostBreakdown getCostBreakdownById(String id) {
        return costBreakdownRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cost breakdown not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<CostBreakdown> getCostBreakdownsByUniversity(String universityId) {
        return costBreakdownRepository.findByUniversityIdAndIsActiveTrue(universityId);
    }

    @Transactional(readOnly = true)
    public List<CostBreakdown> getCostBreakdownsByProgram(String programId) {
        return costBreakdownRepository.findByProgramIdAndIsActiveTrue(programId);
    }

    @Transactional(readOnly = true)
    public List<CostBreakdown> getCostBreakdownsByCountry(String countryCode) {
        return costBreakdownRepository.findByCountryCodeAndIsActiveTrue(countryCode);
    }

    @Transactional(readOnly = true)
    public CostBreakdown getCostBreakdownByUniversityAndProgramAndYear(String universityId, String programId, Integer academicYear) {
        return costBreakdownRepository.findByUniversityIdAndProgramIdAndAcademicYearAndIsActiveTrue(universityId, programId, academicYear)
                .orElseThrow(() -> new RuntimeException("Cost breakdown not found for university: " + universityId + ", program: " + programId + ", year: " + academicYear));
    }

    @Transactional(readOnly = true)
    public List<CostBreakdown> getCostBreakdownsByUniversityAndProgram(String universityId, String programId) {
        return costBreakdownRepository.findByUniversityAndProgramOrdered(universityId, programId);
    }

    @Transactional(readOnly = true)
    public List<CostBreakdown> getCostBreakdownsByCountryAndYear(String countryCode, Integer academicYear) {
        return costBreakdownRepository.findByCountryAndYearOrdered(countryCode, academicYear);
    }

    @Transactional
    public CostBreakdown updateCostBreakdown(String id, CostBreakdown updatedBreakdown) {
        CostBreakdown existing = getCostBreakdownById(id);
        existing.setName(updatedBreakdown.getName());
        existing.setDescription(updatedBreakdown.getDescription());
        existing.setTotalTuition(updatedBreakdown.getTotalTuition());
        existing.setTotalAccommodation(updatedBreakdown.getTotalAccommodation());
        existing.setTotalLivingExpenses(updatedBreakdown.getTotalLivingExpenses());
        existing.setTotalOtherCosts(updatedBreakdown.getTotalOtherCosts());
        existing.setGrandTotal(updatedBreakdown.getGrandTotal());
        existing.setCurrencyCode(updatedBreakdown.getCurrencyCode());
        existing.setEstimated(updatedBreakdown.getIsEstimated());
        existing.setSource(updatedBreakdown.getSource());
        existing.setValidFrom(updatedBreakdown.getValidFrom());
        existing.setValidTo(updatedBreakdown.getValidTo());
        existing.setActive(updatedBreakdown.getIsActive());
        return costBreakdownRepository.save(existing);
    }

    @Transactional
    public void deactivateCostBreakdown(String id) {
        CostBreakdown existing = getCostBreakdownById(id);
        existing.setActive(false);
        costBreakdownRepository.save(existing);
    }
}
