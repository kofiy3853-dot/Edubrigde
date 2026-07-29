package com.edubridge.financial.services;

import com.edubridge.financial.entities.BudgetPolicy;
import com.edubridge.financial.repositories.BudgetPolicyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BudgetPolicyService {

    private final BudgetPolicyRepository budgetPolicyRepository;

    @Transactional
    public BudgetPolicy createBudgetPolicy(BudgetPolicy policy) {
        log.info("Creating budget policy: {} type: {}", policy.getName(), policy.getPolicyType());
        return budgetPolicyRepository.save(policy);
    }

    @Transactional(readOnly = true)
    public BudgetPolicy getBudgetPolicyById(String id) {
        return budgetPolicyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Budget policy not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<BudgetPolicy> getBudgetPoliciesByUniversity(String universityId) {
        return budgetPolicyRepository.findByUniversityIdAndIsActiveTrue(universityId);
    }

    @Transactional(readOnly = true)
    public List<BudgetPolicy> getBudgetPoliciesByCountry(String countryCode) {
        return budgetPolicyRepository.findByCountryCodeAndIsActiveTrue(countryCode);
    }

    @Transactional(readOnly = true)
    public List<BudgetPolicy> getBudgetPoliciesByType(String policyType) {
        return budgetPolicyRepository.findByPolicyTypeAndIsActiveTrue(policyType);
    }

    @Transactional(readOnly = true)
    public List<BudgetPolicy> getBudgetPoliciesByEffect(String effect) {
        return budgetPolicyRepository.findByEffectAndIsActiveTrue(effect);
    }

    @Transactional(readOnly = true)
    public List<BudgetPolicy> getBudgetPoliciesByUniversityAndType(String universityId, String policyType) {
        return budgetPolicyRepository.findByUniversityAndType(universityId, policyType);
    }

    @Transactional(readOnly = true)
    public List<BudgetPolicy> getBudgetPoliciesByCountryAndType(String countryCode, String policyType) {
        return budgetPolicyRepository.findByCountryAndType(countryCode, policyType);
    }

    @Transactional(readOnly = true)
    public List<BudgetPolicy> getDenyPolicies() {
        return budgetPolicyRepository.findDenyPolicies();
    }

    @Transactional
    public BudgetPolicy updateBudgetPolicy(String id, BudgetPolicy updatedPolicy) {
        BudgetPolicy existing = getBudgetPolicyById(id);
        existing.setName(updatedPolicy.getName());
        existing.setDescription(updatedPolicy.getDescription());
        existing.setPolicyType(updatedPolicy.getPolicyType());
        existing.setPolicyRule(updatedPolicy.getPolicyRule());
        existing.setEffect(updatedPolicy.getEffect());
        existing.setPriority(updatedPolicy.getPriority());
        existing.setConditions(updatedPolicy.getConditions());
        existing.setEffectiveFrom(updatedPolicy.getEffectiveFrom());
        existing.setEffectiveTo(updatedPolicy.getEffectiveTo());
        existing.setActive(updatedPolicy.getIsActive());
        return budgetPolicyRepository.save(existing);
    }

    @Transactional
    public void deactivateBudgetPolicy(String id) {
        BudgetPolicy existing = getBudgetPolicyById(id);
        existing.setActive(false);
        budgetPolicyRepository.save(existing);
    }
}
