package com.edubridge.learningservices.services;

import com.edubridge.learningservices.entities.CareerAssessmentRecord;
import com.edubridge.learningservices.repositories.CareerAssessmentRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CareerAssessmentRecordService {

    private final CareerAssessmentRecordRepository careerAssessmentRecordRepository;

    @Transactional
    public CareerAssessmentRecord createCareerAssessmentRecord(CareerAssessmentRecord record) {
        log.info("Creating career assessment record for student: {} type: {}", record.getStudentId(), record.getAssessmentType());
        return careerAssessmentRecordRepository.save(record);
    }

    @Transactional(readOnly = true)
    public CareerAssessmentRecord getCareerAssessmentRecordById(String id) {
        return careerAssessmentRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Career assessment record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<CareerAssessmentRecord> getCareerAssessmentRecordsByStudent(String studentId) {
        return careerAssessmentRecordRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<CareerAssessmentRecord> getCareerAssessmentRecordsByCareerProfile(String careerProfileId) {
        return careerAssessmentRecordRepository.findByCareerProfileIdAndIsActiveTrue(careerProfileId);
    }

    @Transactional(readOnly = true)
    public List<CareerAssessmentRecord> getCareerAssessmentRecordsByType(String assessmentType) {
        return careerAssessmentRecordRepository.findByAssessmentTypeAndIsActiveTrue(assessmentType);
    }

    @Transactional(readOnly = true)
    public List<CareerAssessmentRecord> getCareerAssessmentRecordsByStatus(String assessmentStatus) {
        return careerAssessmentRecordRepository.findByAssessmentStatusAndIsActiveTrue(assessmentStatus);
    }

    @Transactional(readOnly = true)
    public List<CareerAssessmentRecord> getCareerAssessmentRecordsByDomain(String assessmentDomain) {
        return careerAssessmentRecordRepository.findByAssessmentDomainAndIsActiveTrue(assessmentDomain);
    }

    @Transactional(readOnly = true)
    public List<CareerAssessmentRecord> getPassedByStudent(String studentId) {
        return careerAssessmentRecordRepository.findPassedByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<CareerAssessmentRecord> getCareerAssessmentByStudentAndDomain(String studentId, String domain) {
        return careerAssessmentRecordRepository.findByStudentAndDomain(studentId, domain);
    }

    @Transactional(readOnly = true)
    public List<CareerAssessmentRecord> getExpiredAssessments() {
        return careerAssessmentRecordRepository.findExpiredAssessments();
    }

    @Transactional
    public CareerAssessmentRecord updateCareerAssessmentRecord(String id, CareerAssessmentRecord updated) {
        CareerAssessmentRecord existing = getCareerAssessmentRecordById(id);
        existing.setAssessmentType(updated.getAssessmentType());
        existing.setAssessmentName(updated.getAssessmentName());
        existing.setAssessmentStatus(updated.getAssessmentStatus());
        existing.setAssessmentDomain(updated.getAssessmentDomain());
        existing.setScoreEarned(updated.getScoreEarned());
        existing.setScorePossible(updated.getScorePossible());
        existing.setPercentageScore(updated.getPercentageScore());
        existing.setGrade(updated.getGrade());
        existing.setCompetencyLevel(updated.getCompetencyLevel());
        existing.setPassingScore(updated.getPassingScore());
        existing.setFeedback(updated.getFeedback());
        existing.setRecommendations(updated.getRecommendations());
        existing.setValidUntil(updated.getValidUntil());
        return careerAssessmentRecordRepository.save(existing);
    }

    @Transactional
    public CareerAssessmentRecord gradeCareerAssessmentRecord(String id, Double scoreEarned, Double scorePossible, String grade, String competencyLevel, String feedback, String recommendations, String assessedBy) {
        CareerAssessmentRecord existing = getCareerAssessmentRecordById(id);
        existing.setScoreEarned(scoreEarned);
        existing.setScorePossible(scorePossible);
        existing.setPercentageScore((scoreEarned / scorePossible) * 100.0);
        existing.setGrade(grade);
        existing.setCompetencyLevel(competencyLevel);
        existing.setFeedback(feedback);
        existing.setRecommendations(recommendations);
        existing.setAssessedBy(assessedBy);
        existing.setAssessedAt(LocalDateTime.now());
        existing.setIsPassed(existing.getPercentageScore() >= existing.getPassingScore());
        existing.setAssessmentStatus("GRADED");
        return careerAssessmentRecordRepository.save(existing);
    }

    @Transactional
    public void deactivateCareerAssessmentRecord(String id) {
        CareerAssessmentRecord existing = getCareerAssessmentRecordById(id);
        existing.setActive(false);
        careerAssessmentRecordRepository.save(existing);
    }
}
