package com.edubridge.financial.services;

import com.edubridge.financial.entities.BudgetLifecycleRecord;
import com.edubridge.financial.repositories.BudgetLifecycleRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BudgetLifecycleRecordService {

    private final BudgetLifecycleRecordRepository budgetLifecycleRecordRepository;

    @Transactional
    public BudgetLifecycleRecord createLifecycleRecord(BudgetLifecycleRecord record) {
        log.info("Creating budget lifecycle record for budget: {} status: {}", record.getBudgetProfileId(), record.getNewStatus());
        if (record.getChangedAt() == null) {
            record.setChangedAt(LocalDateTime.now());
        }
        return budgetLifecycleRecordRepository.save(record);
    }

    @Transactional(readOnly = true)
    public BudgetLifecycleRecord getLifecycleRecordById(String id) {
        return budgetLifecycleRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Budget lifecycle record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<BudgetLifecycleRecord> getLifecycleRecordsByBudget(String budgetProfileId) {
        return budgetLifecycleRecordRepository.findByBudgetProfileIdAndIsActiveTrue(budgetProfileId);
    }

    @Transactional(readOnly = true)
    public List<BudgetLifecycleRecord> getLifecycleRecordsByStudent(String studentId) {
        return budgetLifecycleRecordRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<BudgetLifecycleRecord> getLifecycleRecordsByStatus(String newStatus) {
        return budgetLifecycleRecordRepository.findByNewStatusAndIsActiveTrue(newStatus);
    }

    @Transactional(readOnly = true)
    public List<BudgetLifecycleRecord> getLifecycleRecordsByBudgetAndStudent(String budgetProfileId, String studentId) {
        return budgetLifecycleRecordRepository.findByBudgetAndStudent(budgetProfileId, studentId);
    }

    @Transactional(readOnly = true)
    public BudgetLifecycleRecord getLatestLifecycleByBudget(String budgetProfileId) {
        return budgetLifecycleRecordRepository.findLatestByBudget(budgetProfileId);
    }

    @Transactional(readOnly = true)
    public List<BudgetLifecycleRecord> getLatestLifecycleByStudent(String studentId) {
        return budgetLifecycleRecordRepository.findLatestByStudent(studentId);
    }

    @Transactional
    public BudgetLifecycleRecord updateLifecycleStatus(String id, String newStatus, String reason, String changedBy) {
        BudgetLifecycleRecord existing = getLifecycleRecordById(id);
        existing.setPreviousStatus(existing.getNewStatus());
        existing.setNewStatus(newStatus);
        existing.setReason(reason);
        existing.setChangedBy(changedBy);
        existing.setChangedAt(LocalDateTime.now());
        return budgetLifecycleRecordRepository.save(existing);
    }

    @Transactional
    public void deactivateLifecycleRecord(String id) {
        BudgetLifecycleRecord existing = getLifecycleRecordById(id);
        existing.setActive(false);
        budgetLifecycleRecordRepository.save(existing);
    }
}
