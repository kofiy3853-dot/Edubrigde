package com.edubridge.academic.services;

import com.edubridge.academic.entities.EligibilityRule;
import com.edubridge.academic.repositories.EligibilityRuleRepository;
import com.edubridge.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EligibilityService {

    private final EligibilityRuleRepository eligibilityRuleRepository;

    @Transactional
    public EligibilityRule createEligibilityRule(EligibilityRule rule) {
        EligibilityRule saved = eligibilityRuleRepository.save(rule);
        log.info("Eligibility rule created: {} for university {}", saved.getId(), saved.getUniversityId());
        return saved;
    }

    @Transactional(readOnly = true)
    public EligibilityRule getEligibilityRuleById(String id) {
        return eligibilityRuleRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Eligibility rule not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<EligibilityRule> getRulesByUniversity(String universityId) {
        return eligibilityRuleRepository.findByUniversityId(universityId);
    }

    @Transactional(readOnly = true)
    public List<EligibilityRule> getRulesByProgram(String programId) {
        return eligibilityRuleRepository.findByProgramId(programId);
    }

    @Transactional(readOnly = true)
    public List<EligibilityRule> getRulesByType(String ruleType) {
        return eligibilityRuleRepository.findByRuleType(ruleType);
    }

    @Transactional(readOnly = true)
    public List<EligibilityRule> getRulesByEffect(String effect) {
        return eligibilityRuleRepository.findByEffect(effect);
    }

    @Transactional(readOnly = true)
    public List<EligibilityRule> getMandatoryRules() {
        return eligibilityRuleRepository.findByIsMandatoryTrue();
    }

    @Transactional(readOnly = true)
    public List<EligibilityRule> getActiveRules() {
        return eligibilityRuleRepository.findByIsActiveTrue();
    }

    @Transactional
    public EligibilityRule updateEligibilityRule(String id, EligibilityRule rule) {
        EligibilityRule existing = getEligibilityRuleById(id);

        existing.setRuleType(rule.getRuleType());
        existing.setName(rule.getName());
        existing.setDescription(rule.getDescription());
        existing.setRuleExpression(rule.getRuleExpression());
        existing.setEffect(rule.getEffect());
        existing.setConditions(rule.getConditions());
        existing.setPriority(rule.getPriority());
        existing.setIsMandatory(rule.getIsMandatory());

        EligibilityRule saved = eligibilityRuleRepository.save(existing);
        log.info("Eligibility rule updated: {} for university {}", saved.getId(), saved.getUniversityId());
        return saved;
    }

    @Transactional
    public EligibilityRule deactivateEligibilityRule(String id) {
        EligibilityRule existing = getEligibilityRuleById(id);
        existing.setIsActive(false);
        EligibilityRule saved = eligibilityRuleRepository.save(existing);
        log.info("Eligibility rule deactivated: {} for university {}", saved.getId(), saved.getUniversityId());
        return saved;
    }

    @Transactional
    public EligibilityRule activateEligibilityRule(String id) {
        EligibilityRule existing = getEligibilityRuleById(id);
        existing.setIsActive(true);
        EligibilityRule saved = eligibilityRuleRepository.save(existing);
        log.info("Eligibility rule activated: {} for university {}", saved.getId(), saved.getUniversityId());
        return saved;
    }
}
