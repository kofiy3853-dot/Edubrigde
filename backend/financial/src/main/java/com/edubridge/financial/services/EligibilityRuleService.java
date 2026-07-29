package com.edubridge.financial.services;

import com.edubridge.financial.entities.EligibilityRule;
import com.edubridge.financial.repositories.EligibilityRuleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EligibilityRuleService {

    private final EligibilityRuleRepository eligibilityRuleRepository;

    @Transactional
    public EligibilityRule createEligibilityRule(EligibilityRule rule) {
        log.info("Creating eligibility rule: {} for scholarship: {}", rule.getName(), rule.getScholarshipId());
        return eligibilityRuleRepository.save(rule);
    }

    @Transactional(readOnly = true)
    public EligibilityRule getEligibilityRuleById(String id) {
        return eligibilityRuleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Eligibility rule not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<EligibilityRule> getEligibilityRulesByScholarship(String scholarshipId) {
        return eligibilityRuleRepository.findByScholarshipIdAndIsActiveTrue(scholarshipId);
    }

    @Transactional(readOnly = true)
    public List<EligibilityRule> getEligibilityRulesByType(String ruleType) {
        return eligibilityRuleRepository.findByRuleTypeAndIsActiveTrue(ruleType);
    }

    @Transactional(readOnly = true)
    public List<EligibilityRule> getEligibilityRulesByCategory(String ruleCategory) {
        return eligibilityRuleRepository.findByRuleCategoryAndIsActiveTrue(ruleCategory);
    }

    @Transactional(readOnly = true)
    public List<EligibilityRule> getMandatoryEligibilityRules() {
        return eligibilityRuleRepository.findByIsMandatoryAndIsActiveTrue(true);
    }

    @Transactional(readOnly = true)
    public List<EligibilityRule> getEligibilityRulesByScholarshipOrdered(String scholarshipId) {
        return eligibilityRuleRepository.findByScholarshipOrdered(scholarshipId);
    }

    @Transactional(readOnly = true)
    public List<EligibilityRule> getMandatoryEligibilityRulesByScholarship(String scholarshipId) {
        return eligibilityRuleRepository.findMandatoryByScholarship(scholarshipId);
    }

    @Transactional(readOnly = true)
    public List<EligibilityRule> getEligibilityRulesByScholarshipAndType(String scholarshipId, String ruleType) {
        return eligibilityRuleRepository.findByScholarshipAndType(scholarshipId, ruleType);
    }

    @Transactional
    public EligibilityRule updateEligibilityRule(String id, EligibilityRule updatedRule) {
        EligibilityRule existing = getEligibilityRuleById(id);
        existing.setName(updatedRule.getName());
        existing.setDescription(updatedRule.getDescription());
        existing.setRuleType(updatedRule.getRuleType());
        existing.setRuleCategory(updatedRule.getRuleCategory());
        existing.setRuleCondition(updatedRule.getRuleCondition());
        existing.setRuleValue(updatedRule.getRuleValue());
        existing.setOperator(updatedRule.getOperator());
        existing.setPriority(updatedRule.getPriority());
        existing.setMandatory(updatedRule.getIsMandatory());
        existing.setActive(updatedRule.getIsActive());
        return eligibilityRuleRepository.save(existing);
    }

    @Transactional
    public void deactivateEligibilityRule(String id) {
        EligibilityRule existing = getEligibilityRuleById(id);
        existing.setActive(false);
        eligibilityRuleRepository.save(existing);
    }
}
