package com.edubridge.academic.services;

import com.edubridge.academic.entities.AdmissionPolicy;
import com.edubridge.academic.repositories.AdmissionPolicyRepository;
import com.edubridge.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdmissionPolicyService {

    private final AdmissionPolicyRepository policyRepository;

    @Transactional
    public AdmissionPolicy createPolicy(AdmissionPolicy policy) {
        AdmissionPolicy saved = policyRepository.save(policy);
        log.info("Admission policy created: {} for university {}", saved.getId(), saved.getUniversityId());
        return saved;
    }

    @Transactional(readOnly = true)
    public AdmissionPolicy getPolicyById(String id) {
        return policyRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Admission policy not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<AdmissionPolicy> getPoliciesByUniversity(String universityId) {
        return policyRepository.findByUniversityId(universityId);
    }

    @Transactional(readOnly = true)
    public List<AdmissionPolicy> getPoliciesByProgram(String programId) {
        return policyRepository.findByProgramId(programId);
    }

    @Transactional(readOnly = true)
    public List<AdmissionPolicy> getPoliciesByType(String policyType) {
        return policyRepository.findByPolicyType(policyType);
    }

    @Transactional(readOnly = true)
    public List<AdmissionPolicy> getPoliciesByEffect(String effect) {
        return policyRepository.findByEffect(effect);
    }

    @Transactional(readOnly = true)
    public List<AdmissionPolicy> getMandatoryPolicies() {
        return policyRepository.findByIsMandatoryTrue();
    }

    @Transactional(readOnly = true)
    public List<AdmissionPolicy> getActivePolicies() {
        return policyRepository.findByIsActiveTrue();
    }

    @Transactional
    public AdmissionPolicy updatePolicy(String id, AdmissionPolicy policy) {
        AdmissionPolicy existing = getPolicyById(id);

        existing.setPolicyType(policy.getPolicyType());
        existing.setName(policy.getName());
        existing.setCode(policy.getCode());
        existing.setDescription(policy.getDescription());
        existing.setPolicyRule(policy.getPolicyRule());
        existing.setEffect(policy.getEffect());
        existing.setPriority(policy.getPriority());
        existing.setIsMandatory(policy.getIsMandatory());
        existing.setEffectiveDate(policy.getEffectiveDate());
        existing.setExpirationDate(policy.getExpirationDate());
        existing.setRequiresReview(policy.getRequiresReview());

        AdmissionPolicy saved = policyRepository.save(existing);
        log.info("Admission policy updated: {} for university {}", saved.getId(), saved.getUniversityId());
        return saved;
    }

    @Transactional
    public AdmissionPolicy deactivatePolicy(String id) {
        AdmissionPolicy existing = getPolicyById(id);
        existing.setIsActive(false);
        AdmissionPolicy saved = policyRepository.save(existing);
        log.info("Admission policy deactivated: {} for university {}", saved.getId(), saved.getUniversityId());
        return saved;
    }

    @Transactional
    public AdmissionPolicy activatePolicy(String id) {
        AdmissionPolicy existing = getPolicyById(id);
        existing.setIsActive(true);
        AdmissionPolicy saved = policyRepository.save(existing);
        log.info("Admission policy activated: {} for university {}", saved.getId(), saved.getUniversityId());
        return saved;
    }
}
