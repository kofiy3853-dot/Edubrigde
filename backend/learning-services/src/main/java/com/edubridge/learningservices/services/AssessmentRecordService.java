package com.edubridge.learningservices.services;

import com.edubridge.learningservices.entities.AssessmentRecord;
import com.edubridge.learningservices.repositories.AssessmentRecordRepository;
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
    public AssessmentRecord createAssessmentRecord(AssessmentRecord record) {
        log.info("Creating assessment record for student: {} type: {}", record.getStudentId(), record.getAssessmentType());
        return assessmentRecordRepository.save(record);
    }

    @Transactional(readOnly = true)
    public AssessmentRecord getAssessmentRecordById(String id) {
        return assessmentRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assessment record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<AssessmentRecord> getAssessmentRecordsByCourse(String courseProfileId) {
        return assessmentRecordRepository.findByCourseProfileIdAndIsActiveTrue(courseProfileId);
    }

    @Transactional(readOnly = true)
    public List<AssessmentRecord> getAssessmentRecordsByStudent(String studentId) {
        return assessmentRecordRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<AssessmentRecord> getAssessmentRecordsByType(String assessmentType) {
        return assessmentRecordRepository.findByAssessmentTypeAndIsActiveTrue(assessmentType);
    }

    @Transactional(readOnly = true)
    public List<AssessmentRecord> getPendingByStudent(String studentId) {
        return assessmentRecordRepository.findPendingByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<AssessmentRecord> getCompletedByStudent(String studentId) {
        return assessmentRecordRepository.findCompletedByStudent(studentId);
    }

    @Transactional
    public AssessmentRecord updateAssessmentRecord(String id, AssessmentRecord updated) {
        AssessmentRecord existing = getAssessmentRecordById(id);
        existing.setAssessmentType(updated.getAssessmentType());
        existing.setAssessmentStatus(updated.getAssessmentStatus());
        existing.setScore(updated.getScore());
        existing.setMaxScore(updated.getMaxScore());
        existing.setPercentage(updated.getPercentage());
        existing.setGrade(updated.getGrade());
        existing.setAssessmentNotes(updated.getAssessmentNotes());
        existing.setRecommendations(updated.getRecommendations());
        return assessmentRecordRepository.save(existing);
    }

    @Transactional
    public AssessmentRecord completeAssessment(String id, String notes, String recommendations) {
        AssessmentRecord existing = getAssessmentRecordById(id);
        existing.setAssessmentStatus("COMPLETED");
        existing.setAssessedAt(LocalDateTime.now());
        if (notes != null) {
            existing.setAssessmentNotes(notes);
        }
        if (recommendations != null) {
            existing.setRecommendations(recommendations);
        }
        return assessmentRecordRepository.save(existing);
    }

    @Transactional
    public void deactivateAssessmentRecord(String id) {
        AssessmentRecord existing = getAssessmentRecordById(id);
        existing.setActive(false);
        assessmentRecordRepository.save(existing);
    }
}
