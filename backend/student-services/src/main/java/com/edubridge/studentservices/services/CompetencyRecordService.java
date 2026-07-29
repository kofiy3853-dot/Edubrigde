package com.edubridge.studentservices.services;

import com.edubridge.studentservices.entities.CompetencyRecord;
import com.edubridge.studentservices.repositories.CompetencyRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompetencyRecordService {

    private final CompetencyRecordRepository competencyRecordRepository;

    @Transactional
    public CompetencyRecord createCompetencyRecord(CompetencyRecord record) {
        log.info("Creating competency record for student: {}", record.getStudentId());
        return competencyRecordRepository.save(record);
    }

    @Transactional(readOnly = true)
    public CompetencyRecord getCompetencyRecordById(String id) {
        return competencyRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Competency record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<CompetencyRecord> getCompetencyRecordsByProfile(String assessmentProfileId) {
        return competencyRecordRepository.findByAssessmentProfileIdAndIsActiveTrue(assessmentProfileId);
    }

    @Transactional(readOnly = true)
    public List<CompetencyRecord> getCompetencyRecordsByStudent(String studentId) {
        return competencyRecordRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<CompetencyRecord> getCompetencyRecordsByCategory(String competencyCategory) {
        return competencyRecordRepository.findByCompetencyCategoryAndIsActiveTrue(competencyCategory);
    }

    @Transactional(readOnly = true)
    public List<CompetencyRecord> getCompetencyRecordsByLevel(String proficiencyLevel) {
        return competencyRecordRepository.findByProficiencyLevelAndIsActiveTrue(proficiencyLevel);
    }

    @Transactional(readOnly = true)
    public List<CompetencyRecord> getExpertByStudent(String studentId) {
        return competencyRecordRepository.findExpertByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<CompetencyRecord> getBeginnerByStudent(String studentId) {
        return competencyRecordRepository.findBeginnerByStudent(studentId);
    }

    @Transactional
    public CompetencyRecord updateCompetencyRecord(String id, CompetencyRecord updated) {
        CompetencyRecord existing = getCompetencyRecordById(id);
        existing.setCompetencyName(updated.getCompetencyName());
        existing.setCompetencyCategory(updated.getCompetencyCategory());
        existing.setProficiencyLevel(updated.getProficiencyLevel());
        existing.setProficiencyScore(updated.getProficiencyScore());
        existing.setTargetLevel(updated.getTargetLevel());
        existing.setEvidence(updated.getEvidence());
        existing.setLastAssessedDate(updated.getLastAssessedDate());
        existing.setNextAssessmentDate(updated.getNextAssessmentDate());
        existing.setNotes(updated.getNotes());
        return competencyRecordRepository.save(existing);
    }

    @Transactional
    public CompetencyRecord assessCompetency(String id, String proficiencyLevel, String evidence) {
        CompetencyRecord existing = getCompetencyRecordById(id);
        existing.setProficiencyLevel(proficiencyLevel);
        existing.setEvidence(evidence);
        existing.setLastAssessedDate(LocalDateTime.now());
        return competencyRecordRepository.save(existing);
    }

    @Transactional
    public void deactivateCompetencyRecord(String id) {
        CompetencyRecord existing = getCompetencyRecordById(id);
        existing.setActive(false);
        competencyRecordRepository.save(existing);
    }
}
