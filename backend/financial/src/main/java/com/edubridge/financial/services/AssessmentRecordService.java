package com.edubridge.financial.services;

import com.edubridge.financial.entities.AssessmentRecord;
import com.edubridge.financial.repositories.AssessmentRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssessmentRecordService {

    private final AssessmentRecordRepository assessmentRecordRepository;

    @Transactional
    public AssessmentRecord createAssessment(AssessmentRecord assessment) {
        log.info("Creating assessment for student: {} scholarship: {}", assessment.getStudentId(), assessment.getScholarshipId());
        if (assessment.getAssessmentStatus() == null) {
            assessment.setAssessmentStatus("PENDING");
        }
        return assessmentRecordRepository.save(assessment);
    }

    @Transactional(readOnly = true)
    public AssessmentRecord getAssessmentById(String id) {
        return assessmentRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assessment record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<AssessmentRecord> getAssessmentsByStudent(String studentId) {
        return assessmentRecordRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<AssessmentRecord> getAssessmentsByScholarship(String scholarshipId) {
        return assessmentRecordRepository.findByScholarshipIdAndIsActiveTrue(scholarshipId);
    }

    @Transactional(readOnly = true)
    public List<AssessmentRecord> getAssessmentsByStatus(String assessmentStatus) {
        return assessmentRecordRepository.findByAssessmentStatusAndIsActiveTrue(assessmentStatus);
    }

    @Transactional(readOnly = true)
    public List<AssessmentRecord> getAssessmentsByRiskLevel(String riskLevel) {
        return assessmentRecordRepository.findByRiskLevelAndIsActiveTrue(riskLevel);
    }

    @Transactional(readOnly = true)
    public List<AssessmentRecord> getAssessmentsByStudentAndScholarship(String studentId, String scholarshipId) {
        return assessmentRecordRepository.findByStudentAndScholarship(studentId, scholarshipId);
    }

    @Transactional(readOnly = true)
    public List<AssessmentRecord> getEligibleAssessmentsByStudent(String studentId) {
        return assessmentRecordRepository.findEligibleByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<AssessmentRecord> getHighRiskAssessments() {
        return assessmentRecordRepository.findHighRiskAssessments();
    }

    @Transactional
    public AssessmentRecord updateAssessment(String id, AssessmentRecord updatedAssessment) {
        AssessmentRecord existing = getAssessmentById(id);
        existing.setAssessmentStatus(updatedAssessment.getAssessmentStatus());
        existing.setAcademicScore(updatedAssessment.getAcademicScore());
        existing.setFinancialScore(updatedAssessment.getFinancialScore());
        existing.setEligibilityScore(updatedAssessment.getEligibilityScore());
        existing.setOverallScore(updatedAssessment.getOverallScore());
        existing.setRiskLevel(updatedAssessment.getRiskLevel());
        existing.setAssessmentNotes(updatedAssessment.getAssessmentNotes());
        existing.setAssessedBy(updatedAssessment.getAssessedBy());
        existing.setAssessedAt(updatedAssessment.getAssessedAt());
        existing.setValidUntil(updatedAssessment.getValidUntil());
        existing.setActive(updatedAssessment.getIsActive());
        return assessmentRecordRepository.save(existing);
    }

    @Transactional
    public AssessmentRecord completeAssessment(String id, String assessedBy, String notes) {
        AssessmentRecord existing = getAssessmentById(id);
        existing.setAssessmentStatus("ELIGIBLE");
        existing.setAssessedBy(assessedBy);
        existing.setAssessedAt(LocalDateTime.now());
        existing.setAssessmentNotes(notes);

        if (existing.getAcademicScore() != null && existing.getFinancialScore() != null) {
            BigDecimal overall = existing.getAcademicScore().add(existing.getFinancialScore()).divide(BigDecimal.valueOf(2), 2, BigDecimal.ROUND_HALF_UP);
            existing.setOverallScore(overall);

            if (overall.compareTo(BigDecimal.valueOf(80)) >= 0) {
                existing.setRiskLevel("LOW");
            } else if (overall.compareTo(BigDecimal.valueOf(50)) >= 0) {
                existing.setRiskLevel("MEDIUM");
            } else if (overall.compareTo(BigDecimal.valueOf(20)) >= 0) {
                existing.setRiskLevel("HIGH");
            } else {
                existing.setRiskLevel("CRITICAL");
            }
        }

        return assessmentRecordRepository.save(existing);
    }

    @Transactional
    public void deactivateAssessment(String id) {
        AssessmentRecord existing = getAssessmentById(id);
        existing.setActive(false);
        assessmentRecordRepository.save(existing);
    }
}
