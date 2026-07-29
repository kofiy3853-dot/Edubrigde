package com.edubridge.financial.services;

import com.edubridge.financial.entities.FinancialAssessment;
import com.edubridge.financial.repositories.FinancialAssessmentRepository;
import com.edubridge.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FinancialAssessmentService {

    private final FinancialAssessmentRepository assessmentRepository;

    @Transactional
    public FinancialAssessment createAssessment(FinancialAssessment assessment) {
        FinancialAssessment saved = assessmentRepository.save(assessment);
        log.info("Financial assessment created: {} for student {}", saved.getId(), saved.getStudentId());
        return saved;
    }

    @Transactional(readOnly = true)
    public FinancialAssessment getAssessmentById(String id) {
        return assessmentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Financial assessment not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<FinancialAssessment> getAssessmentsByStudent(String studentId) {
        return assessmentRepository.findByStudentId(studentId);
    }

    @Transactional(readOnly = true)
    public List<FinancialAssessment> getAssessmentsByUniversity(String universityId) {
        return assessmentRepository.findByUniversityId(universityId);
    }

    @Transactional(readOnly = true)
    public List<FinancialAssessment> getAssessmentsByStatus(String status) {
        return assessmentRepository.findByStatus(status);
    }

    @Transactional(readOnly = true)
    public List<FinancialAssessment> getActiveAssessments() {
        return assessmentRepository.findByIsActiveTrue();
    }

    @Transactional
    public FinancialAssessment updateAssessment(String id, FinancialAssessment assessment) {
        FinancialAssessment existing = getAssessmentById(id);

        existing.setStatus(assessment.getStatus());
        existing.setAssessmentType(assessment.getAssessmentType());
        existing.setTotalEstimatedCost(assessment.getTotalEstimatedCost());
        existing.setTotalAvailableFunds(assessment.getTotalAvailableFunds());
        existing.setFinancialGap(assessment.getFinancialGap());
        existing.setAffordabilityScore(assessment.getAffordabilityScore());
        existing.setRiskLevel(assessment.getRiskLevel());
        existing.setAssessmentResults(assessment.getAssessmentResults());
        existing.setValidationResults(assessment.getValidationResults());
        existing.setPolicyResults(assessment.getPolicyResults());
        existing.setNotes(assessment.getNotes());
        existing.setAssessedBy(assessment.getAssessedBy());
        existing.setAssessedAt(assessment.getAssessedAt());
        existing.setExpirationDate(assessment.getExpirationDate());

        FinancialAssessment saved = assessmentRepository.save(existing);
        log.info("Financial assessment updated: {} for student {}", saved.getId(), saved.getStudentId());
        return saved;
    }

    @Transactional
    public FinancialAssessment deactivateAssessment(String id) {
        FinancialAssessment existing = getAssessmentById(id);
        existing.setIsActive(false);
        FinancialAssessment saved = assessmentRepository.save(existing);
        log.info("Financial assessment deactivated: {} for student {}", saved.getId(), saved.getStudentId());
        return saved;
    }

    @Transactional
    public FinancialAssessment activateAssessment(String id) {
        FinancialAssessment existing = getAssessmentById(id);
        existing.setIsActive(true);
        FinancialAssessment saved = assessmentRepository.save(existing);
        log.info("Financial assessment activated: {} for student {}", saved.getId(), saved.getStudentId());
        return saved;
    }
}
