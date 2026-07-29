package com.edubridge.studentservices.services;

import com.edubridge.studentservices.entities.AssessmentLifecycleRecord;
import com.edubridge.studentservices.repositories.AssessmentLifecycleRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssessmentLifecycleRecordService {

    private final AssessmentLifecycleRecordRepository assessmentLifecycleRecordRepository;

    @Transactional
    public AssessmentLifecycleRecord createLifecycleRecord(AssessmentLifecycleRecord record) {
        log.info("Creating assessment lifecycle record for profile: {}", record.getAssessmentProfileId());
        return assessmentLifecycleRecordRepository.save(record);
    }

    @Transactional(readOnly = true)
    public AssessmentLifecycleRecord getLifecycleRecordById(String id) {
        return assessmentLifecycleRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assessment lifecycle record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<AssessmentLifecycleRecord> getLifecycleRecordsByProfile(String assessmentProfileId) {
        return assessmentLifecycleRecordRepository.findByAssessmentProfileIdAndIsActiveTrue(assessmentProfileId);
    }

    @Transactional(readOnly = true)
    public List<AssessmentLifecycleRecord> getLifecycleRecordsByStudent(String studentId) {
        return assessmentLifecycleRecordRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional
    public AssessmentLifecycleRecord updateLifecycleStatus(String id, String newStatus, String reason, String changedBy) {
        AssessmentLifecycleRecord existing = getLifecycleRecordById(id);
        existing.setPreviousStatus(existing.getNewStatus());
        existing.setNewStatus(newStatus);
        existing.setReason(reason);
        existing.setChangedBy(changedBy);
        existing.setChangedAt(LocalDateTime.now());
        return assessmentLifecycleRecordRepository.save(existing);
    }

    @Transactional
    public void deactivateLifecycleRecord(String id) {
        AssessmentLifecycleRecord existing = getLifecycleRecordById(id);
        existing.setActive(false);
        assessmentLifecycleRecordRepository.save(existing);
    }
}
