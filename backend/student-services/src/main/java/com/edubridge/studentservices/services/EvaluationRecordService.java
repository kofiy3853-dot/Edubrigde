package com.edubridge.studentservices.services;

import com.edubridge.studentservices.entities.EvaluationRecord;
import com.edubridge.studentservices.repositories.EvaluationRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EvaluationRecordService {

    private final EvaluationRecordRepository evaluationRecordRepository;

    @Transactional
    public EvaluationRecord createEvaluationRecord(EvaluationRecord record) {
        log.info("Creating evaluation record for student: {}", record.getStudentId());
        return evaluationRecordRepository.save(record);
    }

    @Transactional(readOnly = true)
    public EvaluationRecord getEvaluationRecordById(String id) {
        return evaluationRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluation record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<EvaluationRecord> getEvaluationRecordsByProfile(String assessmentProfileId) {
        return evaluationRecordRepository.findByAssessmentProfileIdAndIsActiveTrue(assessmentProfileId);
    }

    @Transactional(readOnly = true)
    public List<EvaluationRecord> getEvaluationRecordsByStudent(String studentId) {
        return evaluationRecordRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<EvaluationRecord> getEvaluationRecordsByType(String evaluationType) {
        return evaluationRecordRepository.findByEvaluationTypeAndIsActiveTrue(evaluationType);
    }

    @Transactional(readOnly = true)
    public List<EvaluationRecord> getEvaluationRecordsByStatus(String evaluationStatus) {
        return evaluationRecordRepository.findByEvaluationStatusAndIsActiveTrue(evaluationStatus);
    }

    @Transactional(readOnly = true)
    public List<EvaluationRecord> getPendingByStudent(String studentId) {
        return evaluationRecordRepository.findPendingByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<EvaluationRecord> getCompletedByStudent(String studentId) {
        return evaluationRecordRepository.findCompletedByStudent(studentId);
    }

    @Transactional
    public EvaluationRecord updateEvaluationRecord(String id, EvaluationRecord updated) {
        EvaluationRecord existing = getEvaluationRecordById(id);
        existing.setEvaluationType(updated.getEvaluationType());
        existing.setEvaluationStatus(updated.getEvaluationStatus());
        existing.setOverallScore(updated.getOverallScore());
        existing.setEvaluationSummary(updated.getEvaluationSummary());
        existing.setStrengths(updated.getStrengths());
        existing.setAreasForImprovement(updated.getAreasForImprovement());
        existing.setRecommendations(updated.getRecommendations());
        existing.setEvaluatorId(updated.getEvaluatorId());
        return evaluationRecordRepository.save(existing);
    }

    @Transactional
    public EvaluationRecord completeEvaluation(String id, String summary, String recommendations) {
        EvaluationRecord existing = getEvaluationRecordById(id);
        existing.setEvaluationStatus("COMPLETED");
        existing.setEvaluatedAt(LocalDateTime.now());
        if (summary != null) {
            existing.setEvaluationSummary(summary);
        }
        if (recommendations != null) {
            existing.setRecommendations(recommendations);
        }
        return evaluationRecordRepository.save(existing);
    }

    @Transactional
    public void deactivateEvaluationRecord(String id) {
        EvaluationRecord existing = getEvaluationRecordById(id);
        existing.setActive(false);
        evaluationRecordRepository.save(existing);
    }
}
