package com.edubridge.financial.services;

import com.edubridge.financial.entities.CostComparison;
import com.edubridge.financial.repositories.CostComparisonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CostComparisonService {

    private final CostComparisonRepository costComparisonRepository;

    @Transactional
    public CostComparison createCostComparison(CostComparison comparison) {
        log.info("Creating cost comparison: {} for student: {}", comparison.getName(), comparison.getStudentId());
        return costComparisonRepository.save(comparison);
    }

    @Transactional(readOnly = true)
    public CostComparison getCostComparisonById(String id) {
        return costComparisonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cost comparison not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<CostComparison> getCostComparisonsByStudent(String studentId) {
        return costComparisonRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<CostComparison> getCompletedComparisonsByStudent(String studentId) {
        return costComparisonRepository.findByStudentIdAndIsCompletedAndIsActiveTrue(studentId, true);
    }

    @Transactional(readOnly = true)
    public List<CostComparison> getIncompleteComparisonsByStudent(String studentId) {
        return costComparisonRepository.findIncompleteByStudent(studentId);
    }

    @Transactional
    public CostComparison updateCostComparison(String id, CostComparison updatedComparison) {
        CostComparison existing = getCostComparisonById(id);
        existing.setName(updatedComparison.getName());
        existing.setDescription(updatedComparison.getDescription());
        existing.setCountryCode(updatedComparison.getCountryCode());
        existing.setAcademicYear(updatedComparison.getAcademicYear());
        existing.setCurrencyCode(updatedComparison.getCurrencyCode());
        existing.setUniversity1Id(updatedComparison.getUniversity1Id());
        existing.setUniversity1ProgramId(updatedComparison.getUniversity1ProgramId());
        existing.setUniversity1TotalCost(updatedComparison.getUniversity1TotalCost());
        existing.setUniversity2Id(updatedComparison.getUniversity2Id());
        existing.setUniversity2ProgramId(updatedComparison.getUniversity2ProgramId());
        existing.setUniversity2TotalCost(updatedComparison.getUniversity2TotalCost());
        existing.setUniversity3Id(updatedComparison.getUniversity3Id());
        existing.setUniversity3ProgramId(updatedComparison.getUniversity3ProgramId());
        existing.setUniversity3TotalCost(updatedComparison.getUniversity3TotalCost());
        existing.setComparisonResult(updatedComparison.getComparisonResult());
        existing.setRecommendedUniversityId(updatedComparison.getRecommendedUniversityId());
        existing.setCompleted(updatedComparison.getIsCompleted());
        existing.setActive(updatedComparison.getIsActive());
        return costComparisonRepository.save(existing);
    }

    @Transactional
    public CostComparison completeCostComparison(String id, String comparisonResult, String recommendedUniversityId) {
        CostComparison existing = getCostComparisonById(id);
        existing.setCompleted(true);
        existing.setComparisonResult(comparisonResult);
        existing.setRecommendedUniversityId(recommendedUniversityId);
        return costComparisonRepository.save(existing);
    }

    @Transactional
    public void deactivateCostComparison(String id) {
        CostComparison existing = getCostComparisonById(id);
        existing.setActive(false);
        costComparisonRepository.save(existing);
    }
}
