package com.edubridge.financial.services;

import com.edubridge.financial.entities.SustainabilityRecord;
import com.edubridge.financial.repositories.SustainabilityRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SustainabilityRecordService {

    private final SustainabilityRecordRepository sustainabilityRecordRepository;

    @Transactional
    public SustainabilityRecord createSustainabilityRecord(SustainabilityRecord record) {
        log.info("Creating sustainability record for budget: {} type: {}", record.getBudgetProfileId(), record.getSustainabilityType());
        return sustainabilityRecordRepository.save(record);
    }

    @Transactional(readOnly = true)
    public SustainabilityRecord getSustainabilityRecordById(String id) {
        return sustainabilityRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sustainability record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<SustainabilityRecord> getSustainabilityRecordsByBudget(String budgetProfileId) {
        return sustainabilityRecordRepository.findByBudgetProfileIdAndIsActiveTrue(budgetProfileId);
    }

    @Transactional(readOnly = true)
    public List<SustainabilityRecord> getSustainabilityRecordsByStudent(String studentId) {
        return sustainabilityRecordRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<SustainabilityRecord> getSustainabilityRecordsByType(String sustainabilityType) {
        return sustainabilityRecordRepository.findBySustainabilityTypeAndIsActiveTrue(sustainabilityType);
    }

    @Transactional(readOnly = true)
    public List<SustainabilityRecord> getSustainabilityRecordsByStatus(String sustainabilityStatus) {
        return sustainabilityRecordRepository.findBySustainabilityStatusAndIsActiveTrue(sustainabilityStatus);
    }

    @Transactional(readOnly = true)
    public List<SustainabilityRecord> getSustainableByStudent(String studentId) {
        return sustainabilityRecordRepository.findSustainableByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<SustainabilityRecord> getNotSustainableByStudent(String studentId) {
        return sustainabilityRecordRepository.findNotSustainableByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<SustainabilityRecord> getSustainabilityRecordsByBudgetAndType(String budgetProfileId, String type) {
        return sustainabilityRecordRepository.findByBudgetAndType(budgetProfileId, type);
    }

    @Transactional
    public SustainabilityRecord updateSustainabilityRecord(String id, SustainabilityRecord updatedRecord) {
        SustainabilityRecord existing = getSustainabilityRecordById(id);
        existing.setSustainabilityType(updatedRecord.getSustainabilityType());
        existing.setSustainabilityStatus(updatedRecord.getSustainabilityStatus());
        existing.setSustainabilityScore(updatedRecord.getSustainabilityScore());
        existing.setMonthlySustainability(updatedRecord.getMonthlySustainability());
        existing.setAcademicSustainability(updatedRecord.getAcademicSustainability());
        existing.setEmergencySustainability(updatedRecord.getEmergencySustainability());
        existing.setLongtermSustainability(updatedRecord.getLongtermSustainability());
        existing.setSustainabilityNotes(updatedRecord.getSustainabilityNotes());
        existing.setAssessedBy(updatedRecord.getAssessedBy());
        existing.setAssessedAt(LocalDateTime.now());
        existing.setActive(updatedRecord.getIsActive());
        return sustainabilityRecordRepository.save(existing);
    }

    @Transactional
    public void deactivateSustainabilityRecord(String id) {
        SustainabilityRecord existing = getSustainabilityRecordById(id);
        existing.setActive(false);
        sustainabilityRecordRepository.save(existing);
    }
}
