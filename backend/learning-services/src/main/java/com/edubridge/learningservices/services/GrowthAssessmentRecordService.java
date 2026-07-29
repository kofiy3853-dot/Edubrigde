package com.edubridge.learningservices.services;

import com.edubridge.learningservices.entities.GrowthAssessmentRecord;
import com.edubridge.learningservices.repositories.GrowthAssessmentRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GrowthAssessmentRecordService {

    private final GrowthAssessmentRecordRepository growthAssessmentRecordRepository;

    @Transactional
    public GrowthAssessmentRecord createGrowthAssessmentRecord(GrowthAssessmentRecord record) {
        log.info("Creating growth assessment record for student: {} type: {}", record.getStudentId(), record.getAssessmentType());
        return growthAssessmentRecordRepository.save(record);
    }

    @Transactional(readOnly = true)
    public GrowthAssessmentRecord getGrowthAssessmentRecordById(String id) {
        return growthAssessmentRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Growth assessment record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<GrowthAssessmentRecord> getGrowthAssessmentRecordsByStudent(String studentId) {
        return growthAssessmentRecordRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<GrowthAssessmentRecord> getGrowthAssessmentRecordsByGrowthProfile(String growthProfileId) {
        return growthAssessmentRecordRepository.findByGrowthProfileIdAndIsActiveTrue(growthProfileId);
    }

    @Transactional(readOnly = true)
    public List<GrowthAssessmentRecord> getGrowthAssessmentRecordsByType(String assessmentType) {
        return growthAssessmentRecordRepository.findByAssessmentTypeAndIsActiveTrue(assessmentType);
    }

    @Transactional(readOnly = true)
    public List<GrowthAssessmentRecord> getGrowthAssessmentRecordsByStatus(String assessmentStatus) {
        return growthAssessmentRecordRepository.findByAssessmentStatusAndIsActiveTrue(assessmentStatus);
    }

    @Transactional(readOnly = true)
    public List<GrowthAssessmentRecord> getGrowthAssessmentRecordsByDomain(String assessmentDomain) {
        return growthAssessmentRecordRepository.findByAssessmentDomainAndIsActiveTrue(assessmentDomain);
    }

    @Transactional(readOnly = true)
    public List<GrowthAssessmentRecord> getPassedByStudent(String studentId) {
        return growthAssessmentRecordRepository.findPassedByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<GrowthAssessmentRecord> getGrowthAssessmentByStudentAndDomain(String studentId, String domain) {
        return growthAssessmentRecordRepository.findByStudentAndDomain(studentId, domain);
    }

    @Transactional(readOnly = true)
    public List<GrowthAssessmentRecord> getExpiredAssessments() {
        return growthAssessmentRecordRepository.findExpiredAssessments();
    }

    @Transactional
    public GrowthAssessmentRecord updateGrowthAssessmentRecord(String id, GrowthAssessmentRecord updated) {
        GrowthAssessmentRecord existing = getGrowthAssessmentRecordById(id);
        existing.setAssessmentType(updated.getAssessmentType());
        existing.setAssessmentName(updated.getAssessmentName());
        existing.setAssessmentStatus(updated.getAssessmentStatus());
        existing.setAssessmentDomain(updated.getAssessmentDomain());
        existing.setScoreEarned(updated.getScoreEarned());
        existing.setScorePossible(updated.getScorePossible());
        existing.setPercentageScore(updated.getPercentageScore());
        existing.setGrade(updated.getGrade());
        existing.setCompetencyLevel(updated.getCompetencyLevel());
        existing.setAssessmentDurationMinutes(updated.getAssessmentDurationMinutes());
        existing.setPassingScore(updated.getPassingScore());
        existing.setFeedback(updated.getFeedback());
        existing.setValidUntil(updated.getValidUntil());
        return growthAssessmentRecordRepository.save(existing);
    }

    @Transactional
    public GrowthAssessmentRecord gradeGrowthAssessmentRecord(String id, Double scoreEarned, Double scorePossible, String grade, String competencyLevel, String feedback, String assessedBy) {
        GrowthAssessmentRecord existing = getGrowthAssessmentRecordById(id);
        existing.setScoreEarned(scoreEarned);
        existing.setScorePossible(scorePossible);
        existing.setPercentageScore((scoreEarned / scorePossible) * 100.0);
        existing.setGrade(grade);
        existing.setCompetencyLevel(competencyLevel);
        existing.setFeedback(feedback);
        existing.setAssessedBy(assessedBy);
        existing.setAssessedAt(LocalDateTime.now());
        existing.setIsPassed(existing.getPercentageScore() >= existing.getPassingScore());
        existing.setAssessmentStatus("GRADED");
        return growthAssessmentRecordRepository.save(existing);
    }

    @Transactional
    public void deactivateGrowthAssessmentRecord(String id) {
        GrowthAssessmentRecord existing = getGrowthAssessmentRecordById(id);
        existing.setActive(false);
        growthAssessmentRecordRepository.save(existing);
    }
}
