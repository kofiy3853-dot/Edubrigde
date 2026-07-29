package com.edubridge.studentservices.services;

import com.edubridge.studentservices.entities.PerformanceRecord;
import com.edubridge.studentservices.repositories.PerformanceRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PerformanceRecordService {

    private final PerformanceRecordRepository performanceRecordRepository;

    @Transactional
    public PerformanceRecord createPerformanceRecord(PerformanceRecord record) {
        log.info("Creating performance record for student: {}", record.getStudentId());
        return performanceRecordRepository.save(record);
    }

    @Transactional(readOnly = true)
    public PerformanceRecord getPerformanceRecordById(String id) {
        return performanceRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Performance record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<PerformanceRecord> getPerformanceRecordsByProfile(String assessmentProfileId) {
        return performanceRecordRepository.findByAssessmentProfileIdAndIsActiveTrue(assessmentProfileId);
    }

    @Transactional(readOnly = true)
    public List<PerformanceRecord> getPerformanceRecordsByStudent(String studentId) {
        return performanceRecordRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<PerformanceRecord> getPerformanceRecordsByType(String performanceType) {
        return performanceRecordRepository.findByPerformanceTypeAndIsActiveTrue(performanceType);
    }

    @Transactional(readOnly = true)
    public List<PerformanceRecord> getPerformanceRecordsByStatus(String performanceStatus) {
        return performanceRecordRepository.findByPerformanceStatusAndIsActiveTrue(performanceStatus);
    }

    @Transactional(readOnly = true)
    public List<PerformanceRecord> getRecordedByStudent(String studentId) {
        return performanceRecordRepository.findRecordedByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<PerformanceRecord> getLatestByStudent(String studentId) {
        return performanceRecordRepository.findLatestByStudent(studentId);
    }

    @Transactional
    public PerformanceRecord updatePerformanceRecord(String id, PerformanceRecord updated) {
        PerformanceRecord existing = getPerformanceRecordById(id);
        existing.setPerformanceType(updated.getPerformanceType());
        existing.setPerformanceStatus(updated.getPerformanceStatus());
        existing.setSubjectArea(updated.getSubjectArea());
        existing.setScore(updated.getScore());
        existing.setMaxScore(updated.getMaxScore());
        existing.setPercentage(updated.getPercentage());
        existing.setGrade(updated.getGrade());
        existing.setRank(updated.getRank());
        existing.setRecordedDate(updated.getRecordedDate());
        existing.setNotes(updated.getNotes());
        return performanceRecordRepository.save(existing);
    }

    @Transactional
    public PerformanceRecord verifyPerformance(String id) {
        PerformanceRecord existing = getPerformanceRecordById(id);
        existing.setPerformanceStatus("VERIFIED");
        return performanceRecordRepository.save(existing);
    }

    @Transactional
    public void deactivatePerformanceRecord(String id) {
        PerformanceRecord existing = getPerformanceRecordById(id);
        existing.setActive(false);
        performanceRecordRepository.save(existing);
    }
}
