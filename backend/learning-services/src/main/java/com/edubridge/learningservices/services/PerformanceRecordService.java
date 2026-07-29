package com.edubridge.learningservices.services;

import com.edubridge.learningservices.entities.PerformanceRecord;
import com.edubridge.learningservices.repositories.PerformanceRecordRepository;
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
        log.info("Creating performance record for student: {} type: {}", record.getStudentId(), record.getPerformanceType());
        return performanceRecordRepository.save(record);
    }

    @Transactional(readOnly = true)
    public PerformanceRecord getPerformanceRecordById(String id) {
        return performanceRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Performance record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<PerformanceRecord> getPerformanceRecordsByStudent(String studentId) {
        return performanceRecordRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<PerformanceRecord> getPerformanceRecordsByGrowthProfile(String growthProfileId) {
        return performanceRecordRepository.findByGrowthProfileIdAndIsActiveTrue(growthProfileId);
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
    public List<PerformanceRecord> getPerformanceRecordsBySubject(String subjectArea) {
        return performanceRecordRepository.findBySubjectAreaAndIsActiveTrue(subjectArea);
    }

    @Transactional(readOnly = true)
    public List<PerformanceRecord> getGradedByStudent(String studentId) {
        return performanceRecordRepository.findGradedByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<PerformanceRecord> getPerformanceByStudentAndPeriod(String studentId, String period) {
        return performanceRecordRepository.findByStudentAndPeriod(studentId, period);
    }

    @Transactional(readOnly = true)
    public Double getAverageScoreByStudent(String studentId) {
        return performanceRecordRepository.calculateAverageScoreByStudent(studentId);
    }

    @Transactional
    public PerformanceRecord updatePerformanceRecord(String id, PerformanceRecord updated) {
        PerformanceRecord existing = getPerformanceRecordById(id);
        existing.setPerformanceType(updated.getPerformanceType());
        existing.setPerformanceName(updated.getPerformanceName());
        existing.setPerformanceStatus(updated.getPerformanceStatus());
        existing.setSubjectArea(updated.getSubjectArea());
        existing.setScoreEarned(updated.getScoreEarned());
        existing.setScorePossible(updated.getScorePossible());
        existing.setPercentageScore(updated.getPercentageScore());
        existing.setGrade(updated.getGrade());
        existing.setGradePoint(updated.getGradePoint());
        existing.setAcademicPeriod(updated.getAcademicPeriod());
        existing.setAcademicYear(updated.getAcademicYear());
        existing.setWeightPercentage(updated.getWeightPercentage());
        existing.setIsWeighted(updated.getIsWeighted());
        existing.setPerformanceFeedback(updated.getPerformanceFeedback());
        return performanceRecordRepository.save(existing);
    }

    @Transactional
    public PerformanceRecord gradePerformanceRecord(String id, Double scoreEarned, Double scorePossible, String grade, String feedback, String gradedBy) {
        PerformanceRecord existing = getPerformanceRecordById(id);
        existing.setScoreEarned(scoreEarned);
        existing.setScorePossible(scorePossible);
        existing.setPercentageScore((scoreEarned / scorePossible) * 100.0);
        existing.setGrade(grade);
        existing.setPerformanceFeedback(feedback);
        existing.setGradedBy(gradedBy);
        existing.setPerformanceStatus("GRADED");
        return performanceRecordRepository.save(existing);
    }

    @Transactional
    public void deactivatePerformanceRecord(String id) {
        PerformanceRecord existing = getPerformanceRecordById(id);
        existing.setActive(false);
        performanceRecordRepository.save(existing);
    }
}
