package com.edubridge.learningservices.services;

import com.edubridge.learningservices.entities.PreparationRecord;
import com.edubridge.learningservices.repositories.PreparationRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PreparationRecordService {

    private final PreparationRecordRepository preparationRecordRepository;

    @Transactional
    public PreparationRecord createPreparationRecord(PreparationRecord record) {
        log.info("Creating preparation record for student: {} type: {}", record.getStudentId(), record.getPreparationType());
        return preparationRecordRepository.save(record);
    }

    @Transactional(readOnly = true)
    public PreparationRecord getPreparationRecordById(String id) {
        return preparationRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Preparation record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<PreparationRecord> getPreparationRecordsByCourse(String courseProfileId) {
        return preparationRecordRepository.findByCourseProfileIdAndIsActiveTrue(courseProfileId);
    }

    @Transactional(readOnly = true)
    public List<PreparationRecord> getPreparationRecordsByStudent(String studentId) {
        return preparationRecordRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<PreparationRecord> getPreparationRecordsByType(String preparationType) {
        return preparationRecordRepository.findByPreparationTypeAndIsActiveTrue(preparationType);
    }

    @Transactional(readOnly = true)
    public List<PreparationRecord> getInProgressByStudent(String studentId) {
        return preparationRecordRepository.findInProgressByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<PreparationRecord> getPlanningByStudent(String studentId) {
        return preparationRecordRepository.findPlanningByStudent(studentId);
    }

    @Transactional
    public PreparationRecord updatePreparationRecord(String id, PreparationRecord updated) {
        PreparationRecord existing = getPreparationRecordById(id);
        existing.setPreparationType(updated.getPreparationType());
        existing.setPreparationStatus(updated.getPreparationStatus());
        existing.setTargetInstitution(updated.getTargetInstitution());
        existing.setTargetProgram(updated.getTargetProgram());
        existing.setReadinessScore(updated.getReadinessScore());
        existing.setRequirementsMet(updated.getRequirementsMet());
        existing.setRequirementsPending(updated.getRequirementsPending());
        existing.setTargetDate(updated.getTargetDate());
        existing.setNotes(updated.getNotes());
        return preparationRecordRepository.save(existing);
    }

    @Transactional
    public PreparationRecord completePreparation(String id) {
        PreparationRecord existing = getPreparationRecordById(id);
        existing.setPreparationStatus("COMPLETED");
        return preparationRecordRepository.save(existing);
    }

    @Transactional
    public void deactivatePreparationRecord(String id) {
        PreparationRecord existing = getPreparationRecordById(id);
        existing.setActive(false);
        preparationRecordRepository.save(existing);
    }
}
