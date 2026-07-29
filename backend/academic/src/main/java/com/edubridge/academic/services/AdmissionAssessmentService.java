package com.edubridge.academic.services;

import com.edubridge.academic.entities.AdmissionAssessment;
import com.edubridge.academic.repositories.AdmissionAssessmentRepository;
import com.edubridge.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdmissionAssessmentService {

    private final AdmissionAssessmentRepository assessmentRepository;

    @Transactional
    public AdmissionAssessment createAssessment(AdmissionAssessment assessment) {
        AdmissionAssessment saved = assessmentRepository.save(assessment);
        log.info("Admission assessment created: {} for university {} program {}", saved.getId(), saved.getUniversityId(), saved.getProgramId());
        return saved;
    }

    @Transactional(readOnly = true)
    public AdmissionAssessment getAssessmentById(String id) {
        return assessmentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Admission assessment not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<AdmissionAssessment> getAssessmentsByUniversity(String universityId) {
        return assessmentRepository.findByUniversityId(universityId);
    }

    @Transactional(readOnly = true)
    public List<AdmissionAssessment> getAssessmentsByProgram(String programId) {
        return assessmentRepository.findByProgramId(programId);
    }

    @Transactional(readOnly = true)
    public List<AdmissionAssessment> getAssessmentsByStudent(String studentId) {
        return assessmentRepository.findByStudentId(studentId);
    }

    @Transactional(readOnly = true)
    public List<AdmissionAssessment> getAssessmentsByStatus(String status) {
        return assessmentRepository.findByStatus(status);
    }

    @Transactional(readOnly = true)
    public List<AdmissionAssessment> getActiveAssessments() {
        return assessmentRepository.findByIsActiveTrue();
    }

    @Transactional
    public AdmissionAssessment updateAssessment(String id, AdmissionAssessment assessment) {
        AdmissionAssessment existing = getAssessmentById(id);

        existing.setStatus(assessment.getStatus());
        existing.setAssessmentType(assessment.getAssessmentType());
        existing.setAcademicScore(assessment.getAcademicScore());
        existing.setLanguageScore(assessment.getLanguageScore());
        existing.setOverallScore(assessment.getOverallScore());
        existing.setEligibilityResult(assessment.getEligibilityResult());
        existing.setValidationResults(assessment.getValidationResults());
        existing.setPolicyResults(assessment.getPolicyResults());
        existing.setNotes(assessment.getNotes());
        existing.setAssessedBy(assessment.getAssessedBy());
        existing.setAssessedAt(assessment.getAssessedAt());
        existing.setExpirationDate(assessment.getExpirationDate());

        AdmissionAssessment saved = assessmentRepository.save(existing);
        log.info("Admission assessment updated: {} for university {} program {}", saved.getId(), saved.getUniversityId(), saved.getProgramId());
        return saved;
    }

    @Transactional
    public AdmissionAssessment deactivateAssessment(String id) {
        AdmissionAssessment existing = getAssessmentById(id);
        existing.setIsActive(false);
        AdmissionAssessment saved = assessmentRepository.save(existing);
        log.info("Admission assessment deactivated: {} for university {} program {}", saved.getId(), saved.getUniversityId(), saved.getProgramId());
        return saved;
    }

    @Transactional
    public AdmissionAssessment activateAssessment(String id) {
        AdmissionAssessment existing = getAssessmentById(id);
        existing.setIsActive(true);
        AdmissionAssessment saved = assessmentRepository.save(existing);
        log.info("Admission assessment activated: {} for university {} program {}", saved.getId(), saved.getUniversityId(), saved.getProgramId());
        return saved;
    }
}
