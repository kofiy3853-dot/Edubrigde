package com.edubridge.financial.services;

import com.edubridge.financial.entities.AwardPolicy;
import com.edubridge.financial.repositories.AwardPolicyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AwardPolicyService {

    private final AwardPolicyRepository awardPolicyRepository;

    @Transactional
    public AwardPolicy createAwardPolicy(AwardPolicy policy) {
        log.info("Creating award policy: {} for scholarship: {}", policy.getName(), policy.getScholarshipId());
        return awardPolicyRepository.save(policy);
    }

    @Transactional(readOnly = true)
    public AwardPolicy getAwardPolicyById(String id) {
        return awardPolicyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Award policy not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<AwardPolicy> getAwardPoliciesByScholarship(String scholarshipId) {
        return awardPolicyRepository.findByScholarshipIdAndIsActiveTrue(scholarshipId);
    }

    @Transactional(readOnly = true)
    public List<AwardPolicy> getAwardPoliciesByType(String policyType) {
        return awardPolicyRepository.findByPolicyTypeAndIsActiveTrue(policyType);
    }

    @Transactional(readOnly = true)
    public List<AwardPolicy> getAwardPoliciesByCoverage(String coverageType) {
        return awardPolicyRepository.findByCoverageTypeAndIsActiveTrue(coverageType);
    }

    @Transactional(readOnly = true)
    public List<AwardPolicy> getRenewalRequiredPolicies() {
        return awardPolicyRepository.findRenewalRequired();
    }

    @Transactional(readOnly = true)
    public List<AwardPolicy> getAwardPoliciesByScholarshipAndType(String scholarshipId, String policyType) {
        return awardPolicyRepository.findByScholarshipAndType(scholarshipId, policyType);
    }

    @Transactional(readOnly = true)
    public List<AwardPolicy> getAwardPoliciesByScholarshipAndCoverage(String scholarshipId, String coverageType) {
        return awardPolicyRepository.findByScholarshipAndCoverage(scholarshipId, coverageType);
    }

    @Transactional
    public AwardPolicy updateAwardPolicy(String id, AwardPolicy updatedPolicy) {
        AwardPolicy existing = getAwardPolicyById(id);
        existing.setName(updatedPolicy.getName());
        existing.setDescription(updatedPolicy.getDescription());
        existing.setPolicyType(updatedPolicy.getPolicyType());
        existing.setCoverageType(updatedPolicy.getCoverageType());
        existing.setAmount(updatedPolicy.getAmount());
        existing.setPercentage(updatedPolicy.getPercentage());
        existing.setMaxDuration(updatedPolicy.getMaxDuration());
        existing.setRenewalRequired(updatedPolicy.getRenewalRequired());
        existing.setMinGpa(updatedPolicy.getMinGpa());
        existing.setMinCredits(updatedPolicy.getMinCredits());
        existing.setConditions(updatedPolicy.getConditions());
        existing.setEffectiveFrom(updatedPolicy.getEffectiveFrom());
        existing.setEffectiveTo(updatedPolicy.getEffectiveTo());
        existing.setActive(updatedPolicy.getIsActive());
        return awardPolicyRepository.save(existing);
    }

    @Transactional
    public void deactivateAwardPolicy(String id) {
        AwardPolicy existing = getAwardPolicyById(id);
        existing.setActive(false);
        awardPolicyRepository.save(existing);
    }
}
