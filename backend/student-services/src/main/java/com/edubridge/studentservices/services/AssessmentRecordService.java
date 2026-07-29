package com.edubridge.studentservices.services;

import com.edubridge.studentservices.entities.AssessmentRecord;
import com.edubridge.studentservices.repositories.AssessmentRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssessmentRecordService {

    private final AssessmentRecordRepository assessmentRecordRepository;

    @Transactional
    public AssessmentRecord createAssessment(AssessmentRecord assessment) {
        log.info("Creating assessment for consultation: {} type: {}", assessment.getConsultationId(), assessment.getAssessmentType());
        return assessmentRecordRepository.save(assessment);
    }

    @Transactional(readOnly = true)
    public AssessmentRecord getAssessmentById(String id) {
        return assessmentRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assessment not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<AssessmentRecord> getAssessmentsByConsultation(String consultationId) {
        return assessmentRecordRepository.findByConsultationIdAndIsActiveTrue(consultationId);
    }

    @Transactional(readOnly = true)
    public List<AssessmentRecord> getAssessmentsByStudent(String studentId) {
        return assessmentRecordRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<AssessmentRecord> getAssessmentsByAdvisor(String advisorId) {
        return assessmentRecordRepository.findByAdvisorIdAndIsActiveTrue(advisorId);
    }

    @Transactional(readOnly = true)
    public List<AssessmentRecord> getAssessmentsByType(String assessmentType) {
        return assessmentRecordRepository.findByAssessmentTypeAndIsActiveTrue(assessmentType);
    }

    @Transactional(readOnly = true)
    public List<AssessmentRecord> getAssessmentsByStatus(String assessmentStatus) {
        return assessmentRecordRepository.findByAssessmentStatusAndIsActiveTrue(assessmentStatus);
    }

    @Transactional(readOnly = true)
    public List<AssessmentRecord> getAssessmentsByStudentAndType(String studentId, String type) {
        return assessmentRecordRepository.findByStudentAndType(studentId, type);
    }

    @Transactional(readOnly = true)
    public List<AssessmentRecord> getAssessmentsByAdvisorAndType(String advisorId, String type) {
        return assessmentRecordRepository.findByAdvisorAndType(advisorId, type);
    }

    @Transactional
    public AssessmentRecord updateAssessment(String id, AssessmentRecord updatedAssessment) {
        AssessmentRecord existing = getAssessmentById(id);
        existing.setAssessmentType(updatedAssessment.getAssessmentType());
        existing.setAssessmentStatus(updatedAssessment.getAssessmentStatus());
        existing.setAssessmentScore(updatedAssessment.getAssessmentScore());
        existing.setSatisfactionScore(updatedAssessment.getSatisfactionScore());
        existing.setEffectivenessScore(updatedAssessment.getEffectivenessScore());
        existing.setAssessmentNotes(updatedAssessment.getAssessmentNotes());
        existing.setRecommendations(updatedAssessment.getRecommendations());
        existing.setActive(updatedAssessment.getIsActive());
        return assessmentRecordRepository.save(existing);
    }

    @Transactional
    public AssessmentRecord completeAssessment(String id, String notes, String recommendations) {
        AssessmentRecord existing = getAssessmentById(id);
        existing.setAssessmentStatus("COMPLETED");
        existing.setAssessedAt(LocalDateTime.now());
        existing.setAssessmentNotes(notes);
        existing.setRecommendations(recommendations);
        return assessmentRecordRepository.save(existing);
    }

    @Transactional
    public void deactivateAssessment(String id) {
        AssessmentRecord existing = getAssessmentById(id);
        existing.setActive(false);
        assessmentRecordRepository.save(existing);
    }
}
