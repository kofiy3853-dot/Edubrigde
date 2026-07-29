package com.edubridge.financial.services;

import com.edubridge.financial.entities.FinancialPolicy;
import com.edubridge.financial.repositories.FinancialPolicyRepository;
import com.edubridge.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FinancialPolicyService {

    private final FinancialPolicyRepository policyRepository;

    @Transactional
    public FinancialPolicy createPolicy(FinancialPolicy policy) {
        FinancialPolicy saved = policyRepository.save(policy);
        log.info("Financial policy created: {} for university {}", saved.getId(), saved.getUniversityId());
        return saved;
    }

    @Transactional(readOnly = true)
    public FinancialPolicy getPolicyById(String id) {
        return policyRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Financial policy not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<FinancialPolicy> getPoliciesByUniversity(String universityId) {
        return policyRepository.findByUniversityId(universityId);
    }

    @Transactional(readOnly = true)
    public List<FinancialPolicy> getPoliciesByCountry(String countryCode) {
        return policyRepository.findByCountryCode(countryCode);
    }

    @Transactional(readOnly = true)
    public List<FinancialPolicy> getPoliciesByType(String policyType) {
        return policyRepository.findByPolicyType(policyType);
    }

    @Transactional(readOnly = true)
    public List<FinancialPolicy> getPoliciesByEffect(String effect) {
        return policyRepository.findByEffect(effect);
    }

    @Transactional(readOnly = true)
    public List<FinancialPolicy> getActivePolicies() {
        return policyRepository.findByIsActiveTrue();
    }

    @Transactional
    public FinancialPolicy updatePolicy(String id, FinancialPolicy policy) {
        FinancialPolicy existing = getPolicyById(id);

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

        FinancialPolicy saved = policyRepository.save(existing);
        log.info("Financial policy updated: {} for university {}", saved.getId(), saved.getUniversityId());
        return saved;
    }

    @Transactional
    public FinancialPolicy deactivatePolicy(String id) {
        FinancialPolicy existing = getPolicyById(id);
        existing.setIsActive(false);
        FinancialPolicy saved = policyRepository.save(existing);
        log.info("Financial policy deactivated: {} for university {}", saved.getId(), saved.getUniversityId());
        return saved;
    }

    @Transactional
    public FinancialPolicy activatePolicy(String id) {
        FinancialPolicy existing = getPolicyById(id);
        existing.setIsActive(true);
        FinancialPolicy saved = policyRepository.save(existing);
        log.info("Financial policy activated: {} for university {}", saved.getId(), saved.getUniversityId());
        return saved;
    }
}
