package com.edubridge.academic.services;

import com.edubridge.academic.entities.DurationPolicy;
import com.edubridge.academic.repositories.DurationPolicyRepository;
import com.edubridge.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DurationPolicyService {

    private final DurationPolicyRepository durationPolicyRepository;

    @Transactional
    public DurationPolicy createDurationPolicy(DurationPolicy policy) {
        DurationPolicy saved = durationPolicyRepository.save(policy);
        log.info("Duration policy created: {} for program {}", saved.getId(), saved.getProgramId());
        return saved;
    }

    @Transactional(readOnly = true)
    public DurationPolicy getDurationPolicyById(String id) {
        return durationPolicyRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Duration policy not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<DurationPolicy> getPoliciesByProgram(String programId) {
        return durationPolicyRepository.findByProgramId(programId);
    }

    @Transactional(readOnly = true)
    public List<DurationPolicy> getPoliciesByType(String durationType) {
        return durationPolicyRepository.findByDurationType(durationType);
    }

    @Transactional(readOnly = true)
    public List<DurationPolicy> getActivePolicies() {
        return durationPolicyRepository.findByIsActiveTrue();
    }

    @Transactional
    public DurationPolicy updateDurationPolicy(String id, DurationPolicy policy) {
        DurationPolicy existing = getDurationPolicyById(id);

        existing.setDurationType(policy.getDurationType());
        existing.setName(policy.getName());
        existing.setDescription(policy.getDescription());
        existing.setMinDurationYears(policy.getMinDurationYears());
        existing.setMaxDurationYears(policy.getMaxDurationYears());
        existing.setMinDurationMonths(policy.getMinDurationMonths());
        existing.setMaxDurationMonths(policy.getMaxDurationMonths());
        existing.setDefaultDurationYears(policy.getDefaultDurationYears());
        existing.setDefaultDurationMonths(policy.getDefaultDurationMonths());
        existing.setCreditsPerYear(policy.getCreditsPerYear());
        existing.setMaxCreditsPerSemester(policy.getMaxCreditsPerSemester());
        existing.setMaxExtensions(policy.getMaxExtensions());
        existing.setExtensionDurationMonths(policy.getExtensionDurationMonths());
        existing.setEffectiveDate(policy.getEffectiveDate());
        existing.setExpirationDate(policy.getExpirationDate());

        DurationPolicy saved = durationPolicyRepository.save(existing);
        log.info("Duration policy updated: {} for program {}", saved.getId(), saved.getProgramId());
        return saved;
    }

    @Transactional
    public DurationPolicy deactivateDurationPolicy(String id) {
        DurationPolicy existing = getDurationPolicyById(id);
        existing.setIsActive(false);
        DurationPolicy saved = durationPolicyRepository.save(existing);
        log.info("Duration policy deactivated: {} for program {}", saved.getId(), saved.getProgramId());
        return saved;
    }

    @Transactional
    public DurationPolicy activateDurationPolicy(String id) {
        DurationPolicy existing = getDurationPolicyById(id);
        existing.setIsActive(true);
        DurationPolicy saved = durationPolicyRepository.save(existing);
        log.info("Duration policy activated: {} for program {}", saved.getId(), saved.getProgramId());
        return saved;
    }
}
