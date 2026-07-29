package com.edubridge.financial.services;

import com.edubridge.financial.entities.AffordabilityRecord;
import com.edubridge.financial.repositories.AffordabilityRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AffordabilityRecordService {

    private final AffordabilityRecordRepository affordabilityRecordRepository;

    @Transactional
    public AffordabilityRecord createAffordabilityRecord(AffordabilityRecord record) {
        log.info("Creating affordability record for budget: {} type: {}", record.getBudgetProfileId(), record.getAffordabilityType());
        return affordabilityRecordRepository.save(record);
    }

    @Transactional(readOnly = true)
    public AffordabilityRecord getAffordabilityRecordById(String id) {
        return affordabilityRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Affordability record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<AffordabilityRecord> getAffordabilityRecordsByBudget(String budgetProfileId) {
        return affordabilityRecordRepository.findByBudgetProfileIdAndIsActiveTrue(budgetProfileId);
    }

    @Transactional(readOnly = true)
    public List<AffordabilityRecord> getAffordabilityRecordsByStudent(String studentId) {
        return affordabilityRecordRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<AffordabilityRecord> getAffordabilityRecordsByType(String affordabilityType) {
        return affordabilityRecordRepository.findByAffordabilityTypeAndIsActiveTrue(affordabilityType);
    }

    @Transactional(readOnly = true)
    public List<AffordabilityRecord> getAffordabilityRecordsByStatus(String affordabilityStatus) {
        return affordabilityRecordRepository.findByAffordabilityStatusAndIsActiveTrue(affordabilityStatus);
    }

    @Transactional(readOnly = true)
    public List<AffordabilityRecord> getFullyAffordableByStudent(String studentId) {
        return affordabilityRecordRepository.findFullyAffordableByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<AffordabilityRecord> getNotAffordableByStudent(String studentId) {
        return affordabilityRecordRepository.findNotAffordableByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<AffordabilityRecord> getAffordabilityRecordsByBudgetAndType(String budgetProfileId, String type) {
        return affordabilityRecordRepository.findByBudgetAndType(budgetProfileId, type);
    }

    @Transactional
    public AffordabilityRecord updateAffordabilityRecord(String id, AffordabilityRecord updatedRecord) {
        AffordabilityRecord existing = getAffordabilityRecordById(id);
        existing.setAffordabilityType(updatedRecord.getAffordabilityType());
        existing.setAffordabilityStatus(updatedRecord.getAffordabilityStatus());
        existing.setAffordabilityScore(updatedRecord.getAffordabilityScore());
        existing.setEducationalAffordability(updatedRecord.getEducationalAffordability());
        existing.setLivingAffordability(updatedRecord.getLivingAffordability());
        existing.setEmergencyAffordability(updatedRecord.getEmergencyAffordability());
        existing.setTotalAffordability(updatedRecord.getTotalAffordability());
        existing.setAffordabilityNotes(updatedRecord.getAffordabilityNotes());
        existing.setAssessedBy(updatedRecord.getAssessedBy());
        existing.setAssessedAt(LocalDateTime.now());
        existing.setActive(updatedRecord.getIsActive());
        return affordabilityRecordRepository.save(existing);
    }

    @Transactional
    public void deactivateAffordabilityRecord(String id) {
        AffordabilityRecord existing = getAffordabilityRecordById(id);
        existing.setActive(false);
        affordabilityRecordRepository.save(existing);
    }
}
