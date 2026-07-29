package com.edubridge.learningservices.services;

import com.edubridge.learningservices.entities.EmployabilityRecord;
import com.edubridge.learningservices.repositories.EmployabilityRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployabilityRecordService {

    private final EmployabilityRecordRepository employabilityRecordRepository;

    @Transactional
    public EmployabilityRecord createEmployabilityRecord(EmployabilityRecord record) {
        log.info("Creating employability record for student: {} type: {}", record.getStudentId(), record.getEmployabilityType());
        return employabilityRecordRepository.save(record);
    }

    @Transactional(readOnly = true)
    public EmployabilityRecord getEmployabilityRecordById(String id) {
        return employabilityRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employability record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<EmployabilityRecord> getEmployabilityRecordsByStudent(String studentId) {
        return employabilityRecordRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<EmployabilityRecord> getEmployabilityRecordsByCareerProfile(String careerProfileId) {
        return employabilityRecordRepository.findByCareerProfileIdAndIsActiveTrue(careerProfileId);
    }

    @Transactional(readOnly = true)
    public List<EmployabilityRecord> getEmployabilityRecordsByType(String employabilityType) {
        return employabilityRecordRepository.findByEmployabilityTypeAndIsActiveTrue(employabilityType);
    }

    @Transactional(readOnly = true)
    public List<EmployabilityRecord> getEmployabilityRecordsByStatus(String employabilityStatus) {
        return employabilityRecordRepository.findByEmployabilityStatusAndIsActiveTrue(employabilityStatus);
    }

    @Transactional(readOnly = true)
    public List<EmployabilityRecord> getEmployabilityRecordsByCompetency(String competencyArea) {
        return employabilityRecordRepository.findByCompetencyAreaAndIsActiveTrue(competencyArea);
    }

    @Transactional(readOnly = true)
    public List<EmployabilityRecord> getAchievedByStudent(String studentId) {
        return employabilityRecordRepository.findAchievedByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<EmployabilityRecord> getEmployabilityByStudentAndLevel(String studentId, String level) {
        return employabilityRecordRepository.findByStudentAndLevel(studentId, level);
    }

    @Transactional(readOnly = true)
    public List<EmployabilityRecord> getExpiredEmployabilityRecords() {
        return employabilityRecordRepository.findExpiredEmployabilityRecords();
    }

    @Transactional
    public EmployabilityRecord updateEmployabilityRecord(String id, EmployabilityRecord updated) {
        EmployabilityRecord existing = getEmployabilityRecordById(id);
        existing.setEmployabilityType(updated.getEmployabilityType());
        existing.setEmployabilityName(updated.getEmployabilityName());
        existing.setEmployabilityStatus(updated.getEmployabilityStatus());
        existing.setCompetencyArea(updated.getCompetencyArea());
        existing.setCompetencyLevel(updated.getCompetencyLevel());
        existing.setCompetencyScore(updated.getCompetencyScore());
        existing.setTargetCompetencyScore(updated.getTargetCompetencyScore());
        existing.setIndustryStandard(updated.getIndustryStandard());
        existing.setGlobalRecognized(updated.getGlobalRecognized());
        existing.setValidFrom(updated.getValidFrom());
        existing.setValidUntil(updated.getValidUntil());
        existing.setEvidenceUrl(updated.getEvidenceUrl());
        existing.setNotes(updated.getNotes());
        return employabilityRecordRepository.save(existing);
    }

    @Transactional
    public void deactivateEmployabilityRecord(String id) {
        EmployabilityRecord existing = getEmployabilityRecordById(id);
        existing.setActive(false);
        employabilityRecordRepository.save(existing);
    }
}
