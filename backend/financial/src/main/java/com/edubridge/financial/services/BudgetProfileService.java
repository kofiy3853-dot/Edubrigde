package com.edubridge.financial.services;

import com.edubridge.financial.entities.BudgetProfile;
import com.edubridge.financial.repositories.BudgetProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BudgetProfileService {

    private final BudgetProfileRepository budgetProfileRepository;

    @Transactional
    public BudgetProfile createBudgetProfile(BudgetProfile profile) {
        log.info("Creating budget profile for student: {} university: {}", profile.getStudentId(), profile.getUniversityId());

        BigDecimal financialGap = profile.getTotalEducationalCost()
                .add(profile.getTotalLivingExpense())
                .add(profile.getTotalEmergencyReserve())
                .subtract(profile.getAvailableFunds());
        profile.setFinancialGap(financialGap);

        if (profile.getAvailableFunds().compareTo(profile.getTotalEducationalCost().add(profile.getTotalLivingExpense())) >= 0) {
            profile.setAffordabilityStatus("FULLY_AFFORDABLE");
            profile.setRiskLevel("LOW");
        } else if (profile.getAvailableFunds().compareTo(profile.getTotalEducationalCost()) >= 0) {
            profile.setAffordabilityStatus("PARTIALLY_AFFORDABLE");
            profile.setRiskLevel("MEDIUM");
        } else if (financialGap.compareTo(BigDecimal.ZERO) <= 0) {
            profile.setAffordabilityStatus("CONDITIONALLY_AFFORDABLE");
            profile.setRiskLevel("MEDIUM");
        } else {
            profile.setAffordabilityStatus("NOT_AFFORDABLE");
            profile.setRiskLevel("HIGH");
        }

        return budgetProfileRepository.save(profile);
    }

    @Transactional(readOnly = true)
    public BudgetProfile getBudgetProfileById(String id) {
        return budgetProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Budget profile not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<BudgetProfile> getBudgetProfilesByStudent(String studentId) {
        return budgetProfileRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<BudgetProfile> getBudgetProfilesByUniversity(String universityId) {
        return budgetProfileRepository.findByUniversityIdAndIsActiveTrue(universityId);
    }

    @Transactional(readOnly = true)
    public List<BudgetProfile> getBudgetProfilesByCountry(String countryCode) {
        return budgetProfileRepository.findByCountryCodeAndIsActiveTrue(countryCode);
    }

    @Transactional(readOnly = true)
    public List<BudgetProfile> getBudgetProfilesByAffordabilityStatus(String affordabilityStatus) {
        return budgetProfileRepository.findByAffordabilityStatusAndIsActiveTrue(affordabilityStatus);
    }

    @Transactional(readOnly = true)
    public List<BudgetProfile> getBudgetProfilesByRiskLevel(String riskLevel) {
        return budgetProfileRepository.findByRiskLevelAndIsActiveTrue(riskLevel);
    }

    @Transactional(readOnly = true)
    public List<BudgetProfile> getDraftBudgetProfilesByStudent(String studentId) {
        return budgetProfileRepository.findDraftByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<BudgetProfile> getFinalizedBudgetProfilesByStudent(String studentId) {
        return budgetProfileRepository.findFinalizedByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<BudgetProfile> getHighRiskBudgets() {
        return budgetProfileRepository.findHighRiskBudgets();
    }

    @Transactional
    public BudgetProfile updateBudgetProfile(String id, BudgetProfile updatedProfile) {
        BudgetProfile existing = getBudgetProfileById(id);
        existing.setMonthlyBudget(updatedProfile.getMonthlyBudget());
        existing.setAnnualBudget(updatedProfile.getAnnualBudget());
        existing.setTotalEducationalCost(updatedProfile.getTotalEducationalCost());
        existing.setTotalLivingExpense(updatedProfile.getTotalLivingExpense());
        existing.setTotalEmergencyReserve(updatedProfile.getTotalEmergencyReserve());
        existing.setAvailableFunds(updatedProfile.getAvailableFunds());

        BigDecimal financialGap = existing.getTotalEducationalCost()
                .add(existing.getTotalLivingExpense())
                .add(existing.getTotalEmergencyReserve())
                .subtract(existing.getAvailableFunds());
        existing.setFinancialGap(financialGap);

        if (existing.getAvailableFunds().compareTo(existing.getTotalEducationalCost().add(existing.getTotalLivingExpense())) >= 0) {
            existing.setAffordabilityStatus("FULLY_AFFORDABLE");
            existing.setRiskLevel("LOW");
        } else if (existing.getAvailableFunds().compareTo(existing.getTotalEducationalCost()) >= 0) {
            existing.setAffordabilityStatus("PARTIALLY_AFFORDABLE");
            existing.setRiskLevel("MEDIUM");
        } else if (financialGap.compareTo(BigDecimal.ZERO) <= 0) {
            existing.setAffordabilityStatus("CONDITIONALLY_AFFORDABLE");
            existing.setRiskLevel("MEDIUM");
        } else {
            existing.setAffordabilityStatus("NOT_AFFORDABLE");
            existing.setRiskLevel("HIGH");
        }

        return budgetProfileRepository.save(existing);
    }

    @Transactional
    public BudgetProfile finalizeBudgetProfile(String id) {
        BudgetProfile existing = getBudgetProfileById(id);
        existing.setFinalized(true);
        existing.setFinalizedAt(LocalDateTime.now());
        return budgetProfileRepository.save(existing);
    }

    @Transactional
    public void deactivateBudgetProfile(String id) {
        BudgetProfile existing = getBudgetProfileById(id);
        existing.setActive(false);
        budgetProfileRepository.save(existing);
    }
}
