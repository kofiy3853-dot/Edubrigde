package com.edubridge.academic.services;

import com.edubridge.academic.entities.UniversityPolicy;
import com.edubridge.academic.repositories.UniversityPolicyRepository;
import com.edubridge.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UniversityPolicyService {

    private final UniversityPolicyRepository policyRepository;

    @Transactional
    public UniversityPolicy createPolicy(UniversityPolicy policy) {
        UniversityPolicy saved = policyRepository.save(policy);
        log.info("University policy created: {} for university {}", saved.getId(), saved.getUniversityId());
        return saved;
    }

    @Transactional(readOnly = true)
    public UniversityPolicy getPolicyById(String id) {
        return policyRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("University policy not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<UniversityPolicy> getPoliciesByUniversity(String universityId) {
        return policyRepository.findByUniversityId(universityId);
    }

    @Transactional(readOnly = true)
    public List<UniversityPolicy> getPoliciesByType(String policyType) {
        return policyRepository.findByPolicyType(policyType);
    }

    @Transactional(readOnly = true)
    public List<UniversityPolicy> getPoliciesByEffect(String effect) {
        return policyRepository.findByEffect(effect);
    }

    @Transactional(readOnly = true)
    public List<UniversityPolicy> getMandatoryPolicies() {
        return policyRepository.findByIsMandatoryTrue();
    }

    @Transactional(readOnly = true)
    public List<UniversityPolicy> getActivePolicies() {
        return policyRepository.findByIsActiveTrue();
    }

    @Transactional
    public UniversityPolicy updatePolicy(String id, UniversityPolicy policy) {
        UniversityPolicy existing = getPolicyById(id);

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

        UniversityPolicy saved = policyRepository.save(existing);
        log.info("University policy updated: {} for university {}", saved.getId(), saved.getUniversityId());
        return saved;
    }

    @Transactional
    public UniversityPolicy deactivatePolicy(String id) {
        UniversityPolicy existing = getPolicyById(id);
        existing.setIsActive(false);
        UniversityPolicy saved = policyRepository.save(existing);
        log.info("University policy deactivated: {} for university {}", saved.getId(), saved.getUniversityId());
        return saved;
    }

    @Transactional
    public UniversityPolicy activatePolicy(String id) {
        UniversityPolicy existing = getPolicyById(id);
        existing.setIsActive(true);
        UniversityPolicy saved = policyRepository.save(existing);
        log.info("University policy activated: {} for university {}", saved.getId(), saved.getUniversityId());
        return saved;
    }
}
