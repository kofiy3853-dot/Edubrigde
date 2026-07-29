package com.edubridge.academic.services;

import com.edubridge.academic.entities.ProgramLifecycle;
import com.edubridge.academic.repositories.ProgramLifecycleRepository;
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
public class ProgramLifecycleService {

    private final ProgramLifecycleRepository programLifecycleRepository;

    @Transactional
    public ProgramLifecycle createLifecycleRecord(ProgramLifecycle lifecycle) {
        // Mark previous current record as not current
        Optional<ProgramLifecycle> current = programLifecycleRepository.findCurrentByProgram(lifecycle.getProgramId());
        current.ifPresent(c -> {
            c.setIsCurrent(false);
            programLifecycleRepository.save(c);
        });

        lifecycle.setIsCurrent(true);
        ProgramLifecycle saved = programLifecycleRepository.save(lifecycle);
        log.info("Program lifecycle created: {} for program {} with status {}", saved.getId(), saved.getProgramId(), saved.getStatus());
        return saved;
    }

    @Transactional(readOnly = true)
    public ProgramLifecycle getLifecycleRecordById(String id) {
        return programLifecycleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Program lifecycle record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<ProgramLifecycle> getLifecycleRecordsByProgram(String programId) {
        return programLifecycleRepository.findByProgramId(programId);
    }

    @Transactional(readOnly = true)
    public Optional<ProgramLifecycle> getCurrentLifecycleByProgram(String programId) {
        return programLifecycleRepository.findCurrentByProgram(programId);
    }

    @Transactional(readOnly = true)
    public List<ProgramLifecycle> getLifecycleRecordsByStatus(String status) {
        return programLifecycleRepository.findByStatus(status);
    }

    @Transactional(readOnly = true)
    public List<ProgramLifecycle> getActiveLifecycleRecords() {
        return programLifecycleRepository.findByIsActiveTrue();
    }

    @Transactional
    public ProgramLifecycle updateLifecycleStatus(String id, String newStatus, String reason, String changedBy) {
        ProgramLifecycle existing = getLifecycleRecordById(id);

        // Mark current record as not current
        existing.setIsCurrent(false);
        programLifecycleRepository.save(existing);

        // Create new lifecycle record
        ProgramLifecycle newLifecycle = ProgramLifecycle.builder()
            .programId(existing.getProgramId())
            .status(newStatus)
            .previousStatus(existing.getStatus())
            .reason(reason)
            .changedBy(changedBy)
            .effectiveDate(Instant.now())
            .isCurrent(true)
            .build();

        ProgramLifecycle saved = programLifecycleRepository.save(newLifecycle);
        log.info("Program lifecycle updated: {} for program {} from {} to {}", saved.getId(), saved.getProgramId(), existing.getStatus(), newStatus);
        return saved;
    }

    @Transactional
    public ProgramLifecycle deactivateLifecycleRecord(String id) {
        ProgramLifecycle existing = getLifecycleRecordById(id);
        existing.setIsActive(false);
        ProgramLifecycle saved = programLifecycleRepository.save(existing);
        log.info("Program lifecycle deactivated: {} for program {}", saved.getId(), saved.getProgramId());
        return saved;
    }

    @Transactional
    public ProgramLifecycle activateLifecycleRecord(String id) {
        ProgramLifecycle existing = getLifecycleRecordById(id);
        existing.setIsActive(true);
        ProgramLifecycle saved = programLifecycleRepository.save(existing);
        log.info("Program lifecycle activated: {} for program {}", saved.getId(), saved.getProgramId());
        return saved;
    }
}
