package com.edubridge.academic.services;

import com.edubridge.academic.entities.AdmissionLifecycle;
import com.edubridge.academic.repositories.AdmissionLifecycleRepository;
import com.edubridge.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdmissionLifecycleService {

    private final AdmissionLifecycleRepository lifecycleRepository;

    @Transactional
    public AdmissionLifecycle createLifecycleRecord(AdmissionLifecycle lifecycle) {
        // Mark previous current record as not current
        Optional<AdmissionLifecycle> current = lifecycleRepository.findCurrentByAssessment(lifecycle.getAssessmentId());
        current.ifPresent(c -> {
            c.setIsCurrent(false);
            lifecycleRepository.save(c);
        });

        lifecycle.setIsCurrent(true);
        AdmissionLifecycle saved = lifecycleRepository.save(lifecycle);
        log.info("Admission lifecycle created: {} for assessment {} with status {}", saved.getId(), saved.getAssessmentId(), saved.getStatus());
        return saved;
    }

    @Transactional(readOnly = true)
    public AdmissionLifecycle getLifecycleRecordById(String id) {
        return lifecycleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Admission lifecycle record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<AdmissionLifecycle> getLifecycleRecordsByAssessment(String assessmentId) {
        return lifecycleRepository.findByAssessmentId(assessmentId);
    }

    @Transactional(readOnly = true)
    public Optional<AdmissionLifecycle> getCurrentLifecycleByAssessment(String assessmentId) {
        return lifecycleRepository.findCurrentByAssessment(assessmentId);
    }

    @Transactional(readOnly = true)
    public List<AdmissionLifecycle> getLifecycleRecordsByStatus(String status) {
        return lifecycleRepository.findByStatus(status);
    }

    @Transactional(readOnly = true)
    public List<AdmissionLifecycle> getActiveLifecycleRecords() {
        return lifecycleRepository.findByIsActiveTrue();
    }

    @Transactional
    public AdmissionLifecycle updateLifecycleStatus(String id, String newStatus, String reason, String changedBy) {
        AdmissionLifecycle existing = getLifecycleRecordById(id);

        // Mark current record as not current
        existing.setIsCurrent(false);
        lifecycleRepository.save(existing);

        // Create new lifecycle record
        AdmissionLifecycle newLifecycle = AdmissionLifecycle.builder()
            .assessmentId(existing.getAssessmentId())
            .status(newStatus)
            .previousStatus(existing.getStatus())
            .reason(reason)
            .changedBy(changedBy)
            .effectiveDate(Instant.now())
            .isCurrent(true)
            .build();

        AdmissionLifecycle saved = lifecycleRepository.save(newLifecycle);
        log.info("Admission lifecycle updated: {} for assessment {} from {} to {}", saved.getId(), saved.getAssessmentId(), existing.getStatus(), newStatus);
        return saved;
    }

    @Transactional
    public AdmissionLifecycle deactivateLifecycleRecord(String id) {
        AdmissionLifecycle existing = getLifecycleRecordById(id);
        existing.setIsActive(false);
        AdmissionLifecycle saved = lifecycleRepository.save(existing);
        log.info("Admission lifecycle deactivated: {} for assessment {}", saved.getId(), saved.getAssessmentId());
        return saved;
    }

    @Transactional
    public AdmissionLifecycle activateLifecycleRecord(String id) {
        AdmissionLifecycle existing = getLifecycleRecordById(id);
        existing.setIsActive(true);
        AdmissionLifecycle saved = lifecycleRepository.save(existing);
        log.info("Admission lifecycle activated: {} for assessment {}", saved.getId(), saved.getAssessmentId());
        return saved;
    }
}
