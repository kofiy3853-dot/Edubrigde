package com.edubridge.studentservices.services;

import com.edubridge.studentservices.entities.PolicyRecord;
import com.edubridge.studentservices.repositories.PolicyRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PolicyRecordService {

    private final PolicyRecordRepository policyRecordRepository;

    @Transactional
    public PolicyRecord createPolicy(PolicyRecord policy) {
        log.info("Creating policy: {} type: {}", policy.getName(), policy.getPolicyType());
        return policyRecordRepository.save(policy);
    }

    @Transactional(readOnly = true)
    public PolicyRecord getPolicyById(String id) {
        return policyRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Policy not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<PolicyRecord> getPoliciesByType(String policyType) {
        return policyRecordRepository.findByPolicyTypeAndIsActiveTrue(policyType);
    }

    @Transactional(readOnly = true)
    public List<PolicyRecord> getPoliciesByEffect(String effect) {
        return policyRecordRepository.findByEffectAndIsActiveTrue(effect);
    }

    @Transactional(readOnly = true)
    public List<PolicyRecord> getPoliciesByTypeAndEffect(String type, String effect) {
        return policyRecordRepository.findByTypeAndEffect(type, effect);
    }

    @Transactional(readOnly = true)
    public List<PolicyRecord> getDenyPolicies() {
        return policyRecordRepository.findDenyPolicies();
    }

    @Transactional(readOnly = true)
    public List<PolicyRecord> getAllActivePoliciesOrderedByPriority() {
        return policyRecordRepository.findAllActiveOrderedByPriority();
    }

    @Transactional
    public PolicyRecord updatePolicy(String id, PolicyRecord updatedPolicy) {
        PolicyRecord existing = getPolicyById(id);
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
        return policyRecordRepository.save(existing);
    }

    @Transactional
    public void deactivatePolicy(String id) {
        PolicyRecord existing = getPolicyById(id);
        existing.setActive(false);
        policyRecordRepository.save(existing);
    }
}
