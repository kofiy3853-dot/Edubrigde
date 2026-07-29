package com.edubridge.financial.services;

import com.edubridge.financial.entities.CostPolicy;
import com.edubridge.financial.repositories.CostPolicyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CostPolicyService {

    private final CostPolicyRepository costPolicyRepository;

    @Transactional
    public CostPolicy createCostPolicy(CostPolicy policy) {
        log.info("Creating cost policy: {} type: {}", policy.getName(), policy.getPolicyType());
        return costPolicyRepository.save(policy);
    }

    @Transactional(readOnly = true)
    public CostPolicy getCostPolicyById(String id) {
        return costPolicyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cost policy not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<CostPolicy> getCostPoliciesByUniversity(String universityId) {
        return costPolicyRepository.findByUniversityIdAndIsActiveTrue(universityId);
    }

    @Transactional(readOnly = true)
    public List<CostPolicy> getCostPoliciesByCountry(String countryCode) {
        return costPolicyRepository.findByCountryCodeAndIsActiveTrue(countryCode);
    }

    @Transactional(readOnly = true)
    public List<CostPolicy> getCostPoliciesByType(String policyType) {
        return costPolicyRepository.findByPolicyTypeAndIsActiveTrue(policyType);
    }

    @Transactional(readOnly = true)
    public List<CostPolicy> getCostPoliciesByCategory(String categoryCode) {
        return costPolicyRepository.findByCategoryCodeAndIsActiveTrue(categoryCode);
    }

    @Transactional(readOnly = true)
    public List<CostPolicy> getCostPoliciesByUniversityAndType(String universityId, String policyType) {
        return costPolicyRepository.findByUniversityAndType(universityId, policyType);
    }

    @Transactional(readOnly = true)
    public List<CostPolicy> getCostPoliciesByCountryAndCategory(String countryCode, String categoryCode) {
        return costPolicyRepository.findByCountryAndCategory(countryCode, categoryCode);
    }

    @Transactional(readOnly = true)
    public List<CostPolicy> getPoliciesRequiringApproval() {
        return costPolicyRepository.findRequiringApproval();
    }

    @Transactional
    public CostPolicy updateCostPolicy(String id, CostPolicy updatedPolicy) {
        CostPolicy existing = getCostPolicyById(id);
        existing.setName(updatedPolicy.getName());
        existing.setDescription(updatedPolicy.getDescription());
        existing.setPolicyType(updatedPolicy.getPolicyType());
        existing.setPolicyRule(updatedPolicy.getPolicyRule());
        existing.setDiscountPercentage(updatedPolicy.getDiscountPercentage());
        existing.setDiscountAmount(updatedPolicy.getDiscountAmount());
        existing.setMinAmount(updatedPolicy.getMinAmount());
        existing.setMaxDiscount(updatedPolicy.getMaxDiscount());
        existing.setEffectiveFrom(updatedPolicy.getEffectiveFrom());
        existing.setEffectiveTo(updatedPolicy.getEffectiveTo());
        existing.setActive(updatedPolicy.getIsActive());
        existing.setRequiresApproval(updatedPolicy.getRequiresApproval());
        return costPolicyRepository.save(existing);
    }

    @Transactional
    public void deactivateCostPolicy(String id) {
        CostPolicy existing = getCostPolicyById(id);
        existing.setActive(false);
        costPolicyRepository.save(existing);
    }
}
