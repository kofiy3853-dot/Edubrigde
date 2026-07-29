package com.edubridge.learningservices.services;

import com.edubridge.learningservices.entities.LearningProgressRecord;
import com.edubridge.learningservices.repositories.LearningProgressRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LearningProgressRecordService {

    private final LearningProgressRecordRepository learningProgressRecordRepository;

    @Transactional
    public LearningProgressRecord createLearningProgressRecord(LearningProgressRecord record) {
        log.info("Creating learning progress record for student: {} subject: {}", record.getStudentId(), record.getSubjectArea());
        return learningProgressRecordRepository.save(record);
    }

    @Transactional(readOnly = true)
    public LearningProgressRecord getLearningProgressRecordById(String id) {
        return learningProgressRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Learning progress record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<LearningProgressRecord> getLearningProgressRecordsByStudent(String studentId) {
        return learningProgressRecordRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<LearningProgressRecord> getLearningProgressRecordsByGrowthProfile(String growthProfileId) {
        return learningProgressRecordRepository.findByGrowthProfileIdAndIsActiveTrue(growthProfileId);
    }

    @Transactional(readOnly = true)
    public List<LearningProgressRecord> getLearningProgressRecordsBySubject(String subjectArea) {
        return learningProgressRecordRepository.findBySubjectAreaAndIsActiveTrue(subjectArea);
    }

    @Transactional(readOnly = true)
    public List<LearningProgressRecord> getLearningProgressRecordsByStatus(String progressStatus) {
        return learningProgressRecordRepository.findByProgressStatusAndIsActiveTrue(progressStatus);
    }

    @Transactional(readOnly = true)
    public List<LearningProgressRecord> getLearningProgressRecordsByCompetency(String competencyLevel) {
        return learningProgressRecordRepository.findByCompetencyLevelAndIsActiveTrue(competencyLevel);
    }

    @Transactional(readOnly = true)
    public List<LearningProgressRecord> getInProgressByStudent(String studentId) {
        return learningProgressRecordRepository.findInProgressByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<LearningProgressRecord> getMasteryAchieved() {
        return learningProgressRecordRepository.findMasteryAchieved();
    }

    @Transactional(readOnly = true)
    public List<LearningProgressRecord> getLearningProgressByStudentAndSubject(String studentId, String subjectArea) {
        return learningProgressRecordRepository.findByStudentAndSubject(studentId, subjectArea);
    }

    @Transactional
    public LearningProgressRecord updateLearningProgressRecord(String id, LearningProgressRecord updated) {
        LearningProgressRecord existing = getLearningProgressRecordById(id);
        existing.setSubjectArea(updated.getSubjectArea());
        existing.setSubjectCode(updated.getSubjectCode());
        existing.setProgressType(updated.getProgressType());
        existing.setProgressStatus(updated.getProgressStatus());
        existing.setCompetencyLevel(updated.getCompetencyLevel());
        existing.setProficiencyScore(updated.getProficiencyScore());
        existing.setTargetCompetencyLevel(updated.getTargetCompetencyLevel());
        existing.setMilestonesAchieved(updated.getMilestonesAchieved());
        existing.setMilestonesTotal(updated.getMilestonesTotal());
        existing.setLearningHoursLogged(updated.getLearningHoursLogged());
        existing.setMasteryThreshold(updated.getMasteryThreshold());
        existing.setNextMilestoneName(updated.getNextMilestoneName());
        existing.setNextMilestoneDeadline(updated.getNextMilestoneDeadline());
        existing.setNotes(updated.getNotes());
        return learningProgressRecordRepository.save(existing);
    }

    @Transactional
    public LearningProgressRecord completeMastery(String id) {
        LearningProgressRecord existing = getLearningProgressRecordById(id);
        existing.setIsMasteryAchieved(true);
        existing.setMasteryAchievedAt(LocalDateTime.now());
        existing.setMilestonesAchieved(existing.getMilestonesTotal());
        return learningProgressRecordRepository.save(existing);
    }

    @Transactional
    public void deactivateLearningProgressRecord(String id) {
        LearningProgressRecord existing = getLearningProgressRecordById(id);
        existing.setActive(false);
        learningProgressRecordRepository.save(existing);
    }
}
