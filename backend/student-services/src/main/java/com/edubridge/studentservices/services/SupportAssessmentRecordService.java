package com.edubridge.studentservices.services;

import com.edubridge.studentservices.entities.SupportAssessmentRecord;
import com.edubridge.studentservices.repositories.SupportAssessmentRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SupportAssessmentRecordService {

    private final SupportAssessmentRecordRepository supportAssessmentRecordRepository;

    @Transactional
    public SupportAssessmentRecord createAssessmentRecord(SupportAssessmentRecord record) {
        log.info("Creating support assessment record for student: {}", record.getStudentId());
        return supportAssessmentRecordRepository.save(record);
    }

    @Transactional(readOnly = true)
    public SupportAssessmentRecord getAssessmentRecordById(String id) {
        return supportAssessmentRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Support assessment record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<SupportAssessmentRecord> getAssessmentRecordsByProfile(String supportProfileId) {
        return supportAssessmentRecordRepository.findBySupportProfileIdAndIsActiveTrue(supportProfileId);
    }

    @Transactional(readOnly = true)
    public List<SupportAssessmentRecord> getAssessmentRecordsByStudent(String studentId) {
        return supportAssessmentRecordRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<SupportAssessmentRecord> getAssessmentRecordsByType(String assessmentType) {
        return supportAssessmentRecordRepository.findByAssessmentTypeAndIsActiveTrue(assessmentType);
    }

    @Transactional(readOnly = true)
    public List<SupportAssessmentRecord> getAssessmentRecordsByStatus(String assessmentStatus) {
        return supportAssessmentRecordRepository.findByAssessmentStatusAndIsActiveTrue(assessmentStatus);
    }

    @Transactional(readOnly = true)
    public List<SupportAssessmentRecord> getPendingByStudent(String studentId) {
        return supportAssessmentRecordRepository.findPendingByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<SupportAssessmentRecord> getHighRiskCases() {
        return supportAssessmentRecordRepository.findHighRiskCases();
    }

    @Transactional
    public SupportAssessmentRecord updateAssessmentRecord(String id, SupportAssessmentRecord updated) {
        SupportAssessmentRecord existing = getAssessmentRecordById(id);
        existing.setAssessmentType(updated.getAssessmentType());
        existing.setAssessmentStatus(updated.getAssessmentStatus());
        existing.setRiskLevel(updated.getRiskLevel());
        existing.setRiskScore(updated.getRiskScore());
        existing.setAssessmentNotes(updated.getAssessmentNotes());
        existing.setRecommendations(updated.getRecommendations());
        existing.setAssessedBy(updated.getAssessedBy());
        return supportAssessmentRecordRepository.save(existing);
    }

    @Transactional
    public SupportAssessmentRecord completeAssessment(String id, String notes, String recommendations) {
        SupportAssessmentRecord existing = getAssessmentRecordById(id);
        existing.setAssessmentStatus("COMPLETED");
        existing.setAssessedAt(LocalDateTime.now());
        if (notes != null) {
            existing.setAssessmentNotes(notes);
        }
        if (recommendations != null) {
            existing.setRecommendations(recommendations);
        }
        return supportAssessmentRecordRepository.save(existing);
    }

    @Transactional
    public void deactivateAssessmentRecord(String id) {
        SupportAssessmentRecord existing = getAssessmentRecordById(id);
        existing.setActive(false);
        supportAssessmentRecordRepository.save(existing);
    }
}
