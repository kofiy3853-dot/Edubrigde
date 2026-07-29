package com.edubridge.financial.services;

import com.edubridge.financial.entities.LifecycleRecord;
import com.edubridge.financial.repositories.LifecycleRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LifecycleRecordService {

    private final LifecycleRecordRepository lifecycleRecordRepository;

    @Transactional
    public LifecycleRecord createLifecycleRecord(LifecycleRecord record) {
        log.info("Creating lifecycle record for scholarship: {} status: {}", record.getScholarshipId(), record.getNewStatus());
        if (record.getChangedAt() == null) {
            record.setChangedAt(LocalDateTime.now());
        }
        return lifecycleRecordRepository.save(record);
    }

    @Transactional(readOnly = true)
    public LifecycleRecord getLifecycleRecordById(String id) {
        return lifecycleRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lifecycle record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<LifecycleRecord> getLifecycleRecordsByScholarship(String scholarshipId) {
        return lifecycleRecordRepository.findByScholarshipIdAndIsActiveTrue(scholarshipId);
    }

    @Transactional(readOnly = true)
    public List<LifecycleRecord> getLifecycleRecordsByStudent(String studentId) {
        return lifecycleRecordRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<LifecycleRecord> getLifecycleRecordsByStatus(String newStatus) {
        return lifecycleRecordRepository.findByNewStatusAndIsActiveTrue(newStatus);
    }

    @Transactional(readOnly = true)
    public List<LifecycleRecord> getLifecycleRecordsByScholarshipAndStudent(String scholarshipId, String studentId) {
        return lifecycleRecordRepository.findByScholarshipAndStudent(scholarshipId, studentId);
    }

    @Transactional(readOnly = true)
    public LifecycleRecord getLatestLifecycleByScholarship(String scholarshipId) {
        return lifecycleRecordRepository.findLatestByScholarship(scholarshipId);
    }

    @Transactional(readOnly = true)
    public List<LifecycleRecord> getLatestLifecycleByStudent(String studentId) {
        return lifecycleRecordRepository.findLatestByStudent(studentId);
    }

    @Transactional
    public LifecycleRecord updateLifecycleStatus(String id, String newStatus, String reason, String changedBy) {
        LifecycleRecord existing = getLifecycleRecordById(id);
        existing.setPreviousStatus(existing.getNewStatus());
        existing.setNewStatus(newStatus);
        existing.setReason(reason);
        existing.setChangedBy(changedBy);
        existing.setChangedAt(LocalDateTime.now());
        return lifecycleRecordRepository.save(existing);
    }

    @Transactional
    public void deactivateLifecycleRecord(String id) {
        LifecycleRecord existing = getLifecycleRecordById(id);
        existing.setActive(false);
        lifecycleRecordRepository.save(existing);
    }
}
