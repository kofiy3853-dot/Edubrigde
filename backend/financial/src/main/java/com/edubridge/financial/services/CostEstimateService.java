package com.edubridge.financial.services;

import com.edubridge.financial.entities.CostEstimate;
import com.edubridge.financial.repositories.CostEstimateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CostEstimateService {

    private final CostEstimateRepository costEstimateRepository;

    @Transactional
    public CostEstimate createCostEstimate(CostEstimate estimate) {
        log.info("Creating cost estimate for student: {} university: {} program: {}",
                estimate.getStudentId(), estimate.getUniversityId(), estimate.getProgramId());

        BigDecimal financialGap = estimate.getGrandTotal().subtract(estimate.getAvailableFunds());
        estimate.setFinancialGap(financialGap);

        if (estimate.getGrandTotal().compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal score = estimate.getAvailableFunds()
                    .multiply(BigDecimal.valueOf(100))
                    .divide(estimate.getGrandTotal(), 2, BigDecimal.ROUND_HALF_UP);
            estimate.setAffordabilityScore(score);
        }

        if (estimate.getAffordabilityScore() != null) {
            if (estimate.getAffordabilityScore().compareTo(BigDecimal.valueOf(80)) >= 0) {
                estimate.setRiskLevel("LOW");
            } else if (estimate.getAffordabilityScore().compareTo(BigDecimal.valueOf(50)) >= 0) {
                estimate.setRiskLevel("MEDIUM");
            } else if (estimate.getAffordabilityScore().compareTo(BigDecimal.valueOf(20)) >= 0) {
                estimate.setRiskLevel("HIGH");
            } else {
                estimate.setRiskLevel("CRITICAL");
            }
        }

        return costEstimateRepository.save(estimate);
    }

    @Transactional(readOnly = true)
    public CostEstimate getCostEstimateById(String id) {
        return costEstimateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cost estimate not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<CostEstimate> getCostEstimatesByStudent(String studentId) {
        return costEstimateRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<CostEstimate> getDraftCostEstimatesByStudent(String studentId) {
        return costEstimateRepository.findDraftByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<CostEstimate> getFinalizedCostEstimatesByStudent(String studentId) {
        return costEstimateRepository.findFinalizedByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public CostEstimate getCostEstimateByStudentAndUniversityAndProgramAndYear(
            String studentId, String universityId, String programId, Integer academicYear) {
        return costEstimateRepository
                .findByStudentIdAndUniversityIdAndProgramIdAndAcademicYearAndIsActiveTrue(
                        studentId, universityId, programId, academicYear)
                .orElseThrow(() -> new RuntimeException("Cost estimate not found"));
    }

    @Transactional(readOnly = true)
    public List<CostEstimate> getCostEstimatesByRiskLevel(String riskLevel) {
        return costEstimateRepository.findByRiskLevel(riskLevel);
    }

    @Transactional
    public CostEstimate updateCostEstimate(String id, CostEstimate updatedEstimate) {
        CostEstimate existing = getCostEstimateById(id);
        existing.setTotalTuition(updatedEstimate.getTotalTuition());
        existing.setTotalAccommodation(updatedEstimate.getTotalAccommodation());
        existing.setTotalLivingExpenses(updatedEstimate.getTotalLivingExpenses());
        existing.setTotalOtherCosts(updatedEstimate.getTotalOtherCosts());
        existing.setGrandTotal(updatedEstimate.getGrandTotal());
        existing.setAvailableFunds(updatedEstimate.getAvailableFunds());

        BigDecimal financialGap = existing.getGrandTotal().subtract(existing.getAvailableFunds());
        existing.setFinancialGap(financialGap);

        if (existing.getGrandTotal().compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal score = existing.getAvailableFunds()
                    .multiply(BigDecimal.valueOf(100))
                    .divide(existing.getGrandTotal(), 2, BigDecimal.ROUND_HALF_UP);
            existing.setAffordabilityScore(score);
        }

        if (existing.getAffordabilityScore() != null) {
            if (existing.getAffordabilityScore().compareTo(BigDecimal.valueOf(80)) >= 0) {
                existing.setRiskLevel("LOW");
            } else if (existing.getAffordabilityScore().compareTo(BigDecimal.valueOf(50)) >= 0) {
                existing.setRiskLevel("MEDIUM");
            } else if (existing.getAffordabilityScore().compareTo(BigDecimal.valueOf(20)) >= 0) {
                existing.setRiskLevel("HIGH");
            } else {
                existing.setRiskLevel("CRITICAL");
            }
        }

        return costEstimateRepository.save(existing);
    }

    @Transactional
    public CostEstimate finalizeCostEstimate(String id) {
        CostEstimate existing = getCostEstimateById(id);
        existing.setFinalized(true);
        existing.setFinalizedAt(LocalDate.now());
        return costEstimateRepository.save(existing);
    }

    @Transactional
    public void deactivateCostEstimate(String id) {
        CostEstimate existing = getCostEstimateById(id);
        existing.setActive(false);
        costEstimateRepository.save(existing);
    }
}
