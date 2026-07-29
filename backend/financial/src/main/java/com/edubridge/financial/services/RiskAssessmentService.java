package com.edubridge.financial.services;

import com.edubridge.financial.entities.RiskAssessment;
import com.edubridge.financial.repositories.RiskAssessmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiskAssessmentService {

    private final RiskAssessmentRepository riskAssessmentRepository;

    @Transactional
    public RiskAssessment createRiskAssessment(RiskAssessment assessment) {
        log.info("Creating risk assessment for budget: {} type: {}", assessment.getBudgetProfileId(), assessment.getRiskType());
        return riskAssessmentRepository.save(assessment);
    }

    @Transactional(readOnly = true)
    public RiskAssessment getRiskAssessmentById(String id) {
        return riskAssessmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Risk assessment not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<RiskAssessment> getRiskAssessmentsByBudget(String budgetProfileId) {
        return riskAssessmentRepository.findByBudgetProfileIdAndIsActiveTrue(budgetProfileId);
    }

    @Transactional(readOnly = true)
    public List<RiskAssessment> getRiskAssessmentsByStudent(String studentId) {
        return riskAssessmentRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<RiskAssessment> getRiskAssessmentsByType(String riskType) {
        return riskAssessmentRepository.findByRiskTypeAndIsActiveTrue(riskType);
    }

    @Transactional(readOnly = true)
    public List<RiskAssessment> getRiskAssessmentsByLevel(String riskLevel) {
        return riskAssessmentRepository.findByRiskLevelAndIsActiveTrue(riskLevel);
    }

    @Transactional(readOnly = true)
    public List<RiskAssessment> getLowRiskByStudent(String studentId) {
        return riskAssessmentRepository.findLowRiskByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<RiskAssessment> getHighRiskByStudent(String studentId) {
        return riskAssessmentRepository.findHighRiskByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<RiskAssessment> getRiskAssessmentsByBudgetAndType(String budgetProfileId, String type) {
        return riskAssessmentRepository.findByBudgetAndType(budgetProfileId, type);
    }

    @Transactional
    public RiskAssessment updateRiskAssessment(String id, RiskAssessment updatedAssessment) {
        RiskAssessment existing = getRiskAssessmentById(id);
        existing.setRiskType(updatedAssessment.getRiskType());
        existing.setRiskLevel(updatedAssessment.getRiskLevel());
        existing.setRiskScore(updatedAssessment.getRiskScore());
        existing.setBudgetRisk(updatedAssessment.getBudgetRisk());
        existing.setCostRisk(updatedAssessment.getCostRisk());
        existing.setCountryRisk(updatedAssessment.getCountryRisk());
        existing.setAcademicRisk(updatedAssessment.getAcademicRisk());
        existing.setFinancialRisk(updatedAssessment.getFinancialRisk());
        existing.setRiskNotes(updatedAssessment.getRiskNotes());
        existing.setAssessedBy(updatedAssessment.getAssessedBy());
        existing.setAssessedAt(LocalDateTime.now());
        existing.setActive(updatedAssessment.getIsActive());
        return riskAssessmentRepository.save(existing);
    }

    @Transactional
    public void deactivateRiskAssessment(String id) {
        RiskAssessment existing = getRiskAssessmentById(id);
        existing.setActive(false);
        riskAssessmentRepository.save(existing);
    }
}
