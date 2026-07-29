package com.edubridge.financial.services;

import com.edubridge.financial.entities.FinancialLifecycle;
import com.edubridge.financial.repositories.FinancialLifecycleRepository;
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
public class FinancialLifecycleService {

    private final FinancialLifecycleRepository lifecycleRepository;

    @Transactional
    public FinancialLifecycle createLifecycleRecord(FinancialLifecycle lifecycle) {
        Optional<FinancialLifecycle> current = lifecycleRepository.findCurrentByAssessment(lifecycle.getAssessmentId());
        current.ifPresent(c -> {
            c.setIsCurrent(false);
            lifecycleRepository.save(c);
        });

        lifecycle.setIsCurrent(true);
        FinancialLifecycle saved = lifecycleRepository.save(lifecycle);
        log.info("Financial lifecycle created: {} for assessment {} with status {}", saved.getId(), saved.getAssessmentId(), saved.getStatus());
        return saved;
    }

    @Transactional(readOnly = true)
    public FinancialLifecycle getLifecycleRecordById(String id) {
        return lifecycleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Financial lifecycle record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<FinancialLifecycle> getLifecycleRecordsByAssessment(String assessmentId) {
        return lifecycleRepository.findByAssessmentId(assessmentId);
    }

    @Transactional(readOnly = true)
    public Optional<FinancialLifecycle> getCurrentLifecycleByAssessment(String assessmentId) {
        return lifecycleRepository.findCurrentByAssessment(assessmentId);
    }

    @Transactional(readOnly = true)
    public List<FinancialLifecycle> getLifecycleRecordsByStatus(String status) {
        return lifecycleRepository.findByStatus(status);
    }

    @Transactional(readOnly = true)
    public List<FinancialLifecycle> getActiveLifecycleRecords() {
        return lifecycleRepository.findByIsActiveTrue();
    }

    @Transactional
    public FinancialLifecycle updateLifecycleStatus(String id, String newStatus, String reason, String changedBy) {
        FinancialLifecycle existing = getLifecycleRecordById(id);

        existing.setIsCurrent(false);
        lifecycleRepository.save(existing);

        FinancialLifecycle newLifecycle = FinancialLifecycle.builder()
            .assessmentId(existing.getAssessmentId())
            .status(newStatus)
            .previousStatus(existing.getStatus())
            .reason(reason)
            .changedBy(changedBy)
            .effectiveDate(Instant.now())
            .isCurrent(true)
            .build();

        FinancialLifecycle saved = lifecycleRepository.save(newLifecycle);
        log.info("Financial lifecycle updated: {} for assessment {} from {} to {}", saved.getId(), saved.getAssessmentId(), existing.getStatus(), newStatus);
        return saved;
    }

    @Transactional
    public FinancialLifecycle deactivateLifecycleRecord(String id) {
        FinancialLifecycle existing = getLifecycleRecordById(id);
        existing.setIsActive(false);
        FinancialLifecycle saved = lifecycleRepository.save(existing);
        log.info("Financial lifecycle deactivated: {} for assessment {}", saved.getId(), saved.getAssessmentId());
        return saved;
    }

    @Transactional
    public FinancialLifecycle activateLifecycleRecord(String id) {
        FinancialLifecycle existing = getLifecycleRecordById(id);
        existing.setIsActive(true);
        FinancialLifecycle saved = lifecycleRepository.save(existing);
        log.info("Financial lifecycle activated: {} for assessment {}", saved.getId(), saved.getAssessmentId());
        return saved;
    }
}
