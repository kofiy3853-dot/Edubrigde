package com.edubridge.financial.services;

import com.edubridge.financial.entities.CountryProfile;
import com.edubridge.financial.repositories.CountryProfileRepository;
import com.edubridge.shared.exceptions.ConflictException;
import com.edubridge.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CountryProfileService {

    private final CountryProfileRepository countryProfileRepository;

    @Transactional
    public CountryProfile createCountryProfile(CountryProfile profile) {
        if (countryProfileRepository.existsByCountryCode(profile.getCountryCode())) {
            throw new ConflictException("Country profile with code " + profile.getCountryCode() + " already exists");
        }
        if (countryProfileRepository.existsByCountryName(profile.getCountryName())) {
            throw new ConflictException("Country profile with name " + profile.getCountryName() + " already exists");
        }

        CountryProfile saved = countryProfileRepository.save(profile);
        log.info("Country profile created: {} ({})", saved.getCountryName(), saved.getId());
        return saved;
    }

    @Transactional(readOnly = true)
    public CountryProfile getCountryProfileById(String id) {
        return countryProfileRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Country profile not found: " + id));
    }

    @Transactional(readOnly = true)
    public CountryProfile getCountryProfileByCode(String countryCode) {
        return countryProfileRepository.findByCountryCode(countryCode)
            .orElseThrow(() -> new ResourceNotFoundException("Country profile not found with code: " + countryCode));
    }

    @Transactional(readOnly = true)
    public List<CountryProfile> getAllCountryProfiles() {
        return countryProfileRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<CountryProfile> getActiveCountryProfiles() {
        return countryProfileRepository.findByIsActiveTrue();
    }

    @Transactional
    public CountryProfile updateCountryProfile(String id, CountryProfile profile) {
        CountryProfile existing = getCountryProfileById(id);

        existing.setCountryName(profile.getCountryName());
        existing.setCurrencyCode(profile.getCurrencyCode());
        existing.setCurrencyName(profile.getCurrencyName());
        existing.setExchangeRateToUsd(profile.getExchangeRateToUsd());
        existing.setAvgMonthlyLivingCost(profile.getAvgMonthlyLivingCost());
        existing.setAvgMonthlyAccommodation(profile.getAvgMonthlyAccommodation());
        existing.setAvgMonthlyFood(profile.getAvgMonthlyFood());
        existing.setAvgMonthlyTransportation(profile.getAvgMonthlyTransportation());
        existing.setAvgMonthlyHealthcare(profile.getAvgMonthlyHealthcare());
        existing.setAvgAnnualTuitionPublic(profile.getAvgAnnualTuitionPublic());
        existing.setAvgAnnualTuitionPrivate(profile.getAvgAnnualTuitionPrivate());
        existing.setStudentVisaCost(profile.getStudentVisaCost());
        existing.setHealthInsuranceRequired(profile.getHealthInsuranceRequired());
        existing.setHealthInsuranceCost(profile.getHealthInsuranceCost());
        existing.setWorkHoursAllowedPerWeek(profile.getWorkHoursAllowedPerWeek());
        existing.setMinimumBankStatement(profile.getMinimumBankStatement());
        existing.setNotes(profile.getNotes());

        CountryProfile saved = countryProfileRepository.save(existing);
        log.info("Country profile updated: {} ({})", saved.getCountryName(), saved.getId());
        return saved;
    }

    @Transactional
    public CountryProfile deactivateCountryProfile(String id) {
        CountryProfile existing = getCountryProfileById(id);
        existing.setIsActive(false);
        CountryProfile saved = countryProfileRepository.save(existing);
        log.info("Country profile deactivated: {} ({})", saved.getCountryName(), saved.getId());
        return saved;
    }

    @Transactional
    public CountryProfile activateCountryProfile(String id) {
        CountryProfile existing = getCountryProfileById(id);
        existing.setIsActive(true);
        CountryProfile saved = countryProfileRepository.save(existing);
        log.info("Country profile activated: {} ({})", saved.getCountryName(), saved.getId());
        return saved;
    }
}
