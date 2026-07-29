package com.edubridge.learningservices.services;

import com.edubridge.learningservices.entities.StudySkillRecord;
import com.edubridge.learningservices.repositories.StudySkillRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudySkillRecordService {

    private final StudySkillRecordRepository studySkillRecordRepository;

    @Transactional
    public StudySkillRecord createStudySkillRecord(StudySkillRecord record) {
        log.info("Creating study skill record for student: {} skill: {}", record.getStudentId(), record.getSkillName());
        return studySkillRecordRepository.save(record);
    }

    @Transactional(readOnly = true)
    public StudySkillRecord getStudySkillRecordById(String id) {
        return studySkillRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Study skill record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<StudySkillRecord> getStudySkillRecordsByCourse(String courseProfileId) {
        return studySkillRecordRepository.findByCourseProfileIdAndIsActiveTrue(courseProfileId);
    }

    @Transactional(readOnly = true)
    public List<StudySkillRecord> getStudySkillRecordsByStudent(String studentId) {
        return studySkillRecordRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<StudySkillRecord> getStudySkillRecordsByCategory(String skillCategory) {
        return studySkillRecordRepository.findBySkillCategoryAndIsActiveTrue(skillCategory);
    }

    @Transactional(readOnly = true)
    public List<StudySkillRecord> getMasteredByStudent(String studentId) {
        return studySkillRecordRepository.findMasteredByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<StudySkillRecord> getNeedsReviewByStudent(String studentId) {
        return studySkillRecordRepository.findNeedsReviewByStudent(studentId);
    }

    @Transactional
    public StudySkillRecord updateStudySkillRecord(String id, StudySkillRecord updated) {
        StudySkillRecord existing = getStudySkillRecordById(id);
        existing.setSkillName(updated.getSkillName());
        existing.setSkillCategory(updated.getSkillCategory());
        existing.setSkillStatus(updated.getSkillStatus());
        existing.setProficiencyLevel(updated.getProficiencyLevel());
        existing.setProficiencyScore(updated.getProficiencyScore());
        existing.setDescription(updated.getDescription());
        existing.setPracticeHours(updated.getPracticeHours());
        return studySkillRecordRepository.save(existing);
    }

    @Transactional
    public StudySkillRecord masterSkill(String id) {
        StudySkillRecord existing = getStudySkillRecordById(id);
        existing.setSkillStatus("MASTERED");
        existing.setLastPracticedAt(LocalDateTime.now());
        return studySkillRecordRepository.save(existing);
    }

    @Transactional
    public void deactivateStudySkillRecord(String id) {
        StudySkillRecord existing = getStudySkillRecordById(id);
        existing.setActive(false);
        studySkillRecordRepository.save(existing);
    }
}
