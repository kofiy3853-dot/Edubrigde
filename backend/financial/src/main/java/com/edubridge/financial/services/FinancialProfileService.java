package com.edubridge.financial.services;

import com.edubridge.financial.entities.FinancialProfile;
import com.edubridge.financial.repositories.FinancialProfileRepository;
import com.edubridge.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FinancialProfileService {

    private final FinancialProfileRepository profileRepository;

    @Transactional
    public FinancialProfile createProfile(FinancialProfile profile) {
        FinancialProfile saved = profileRepository.save(profile);
        log.info("Financial profile created: {} for student {}", saved.getId(), saved.getStudentId());
        return saved;
    }

    @Transactional(readOnly = true)
    public FinancialProfile getProfileById(String id) {
        return profileRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Financial profile not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<FinancialProfile> getProfilesByStudent(String studentId) {
        return profileRepository.findByStudentId(studentId);
    }

    @Transactional(readOnly = true)
    public List<FinancialProfile> getProfilesByUniversity(String universityId) {
        return profileRepository.findByUniversityId(universityId);
    }

    @Transactional(readOnly = true)
    public List<FinancialProfile> getProfilesByCountry(String countryCode) {
        return profileRepository.findByCountryCode(countryCode);
    }

    @Transactional(readOnly = true)
    public List<FinancialProfile> getActiveProfiles() {
        return profileRepository.findByIsActiveTrue();
    }

    @Transactional
    public FinancialProfile updateProfile(String id, FinancialProfile profile) {
        FinancialProfile existing = getProfileById(id);

        existing.setUniversityId(profile.getUniversityId());
        existing.setProgramId(profile.getProgramId());
        existing.setCountryCode(profile.getCountryCode());
        existing.setCurrencyCode(profile.getCurrencyCode());
        existing.setEstimatedTuition(profile.getEstimatedTuition());
        existing.setEstimatedLivingExpenses(profile.getEstimatedLivingExpenses());
        existing.setEstimatedAccommodation(profile.getEstimatedAccommodation());
        existing.setEstimatedTransportation(profile.getEstimatedTransportation());
        existing.setEstimatedFood(profile.getEstimatedFood());
        existing.setEstimatedHealthcare(profile.getEstimatedHealthcare());
        existing.setEstimatedAcademicExpenses(profile.getEstimatedAcademicExpenses());
        existing.setTotalEstimatedCost(profile.getTotalEstimatedCost());
        existing.setAvailableFunds(profile.getAvailableFunds());
        existing.setExpectedScholarships(profile.getExpectedScholarships());
        existing.setExpectedLoans(profile.getExpectedLoans());
        existing.setFinancialGap(profile.getFinancialGap());
        existing.setAffordabilityScore(profile.getAffordabilityScore());
        existing.setRiskLevel(profile.getRiskLevel());
        existing.setNotes(profile.getNotes());

        FinancialProfile saved = profileRepository.save(existing);
        log.info("Financial profile updated: {} for student {}", saved.getId(), saved.getStudentId());
        return saved;
    }

    @Transactional
    public FinancialProfile deactivateProfile(String id) {
        FinancialProfile existing = getProfileById(id);
        existing.setIsActive(false);
        FinancialProfile saved = profileRepository.save(existing);
        log.info("Financial profile deactivated: {} for student {}", saved.getId(), saved.getStudentId());
        return saved;
    }

    @Transactional
    public FinancialProfile activateProfile(String id) {
        FinancialProfile existing = getProfileById(id);
        existing.setIsActive(true);
        FinancialProfile saved = profileRepository.save(existing);
        log.info("Financial profile activated: {} for student {}", saved.getId(), saved.getStudentId());
        return saved;
    }
}
