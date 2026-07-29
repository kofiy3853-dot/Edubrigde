package com.edubridge.studentservices.services;

import com.edubridge.studentservices.entities.SupportLifecycleRecord;
import com.edubridge.studentservices.repositories.SupportLifecycleRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SupportLifecycleRecordService {

    private final SupportLifecycleRecordRepository supportLifecycleRecordRepository;

    @Transactional
    public SupportLifecycleRecord createLifecycleRecord(SupportLifecycleRecord record) {
        log.info("Creating support lifecycle record for profile: {}", record.getSupportProfileId());
        return supportLifecycleRecordRepository.save(record);
    }

    @Transactional(readOnly = true)
    public SupportLifecycleRecord getLifecycleRecordById(String id) {
        return supportLifecycleRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Support lifecycle record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<SupportLifecycleRecord> getLifecycleRecordsByProfile(String supportProfileId) {
        return supportLifecycleRecordRepository.findBySupportProfileIdAndIsActiveTrue(supportProfileId);
    }

    @Transactional(readOnly = true)
    public List<SupportLifecycleRecord> getLifecycleRecordsByStudent(String studentId) {
        return supportLifecycleRecordRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional
    public SupportLifecycleRecord updateLifecycleStatus(String id, String newStatus, String reason, String changedBy) {
        SupportLifecycleRecord existing = getLifecycleRecordById(id);
        existing.setPreviousStatus(existing.getNewStatus());
        existing.setNewStatus(newStatus);
        existing.setReason(reason);
        existing.setChangedBy(changedBy);
        existing.setChangedAt(LocalDateTime.now());
        return supportLifecycleRecordRepository.save(existing);
    }

    @Transactional
    public void deactivateLifecycleRecord(String id) {
        SupportLifecycleRecord existing = getLifecycleRecordById(id);
        existing.setActive(false);
        supportLifecycleRecordRepository.save(existing);
    }
}
