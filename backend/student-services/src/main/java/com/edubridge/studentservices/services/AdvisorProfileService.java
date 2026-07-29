package com.edubridge.studentservices.services;

import com.edubridge.studentservices.entities.AdvisorProfile;
import com.edubridge.studentservices.repositories.AdvisorProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdvisorProfileService {

    private final AdvisorProfileRepository advisorProfileRepository;

    @Transactional
    public AdvisorProfile createAdvisor(AdvisorProfile advisor) {
        log.info("Creating advisor profile for user: {} type: {}", advisor.getUserId(), advisor.getAdvisorType());
        return advisorProfileRepository.save(advisor);
    }

    @Transactional(readOnly = true)
    public AdvisorProfile getAdvisorById(String id) {
        return advisorProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Advisor not found: " + id));
    }

    @Transactional(readOnly = true)
    public AdvisorProfile getAdvisorByUserId(String userId) {
        return advisorProfileRepository.findByUserIdAndIsActiveTrue(userId)
                .stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Advisor not found for user: " + userId));
    }

    @Transactional(readOnly = true)
    public List<AdvisorProfile> getAdvisorsByType(String advisorType) {
        return advisorProfileRepository.findByAdvisorTypeAndIsActiveTrue(advisorType);
    }

    @Transactional(readOnly = true)
    public List<AdvisorProfile> getAdvisorsByAvailability(String availabilityStatus) {
        return advisorProfileRepository.findByAvailabilityStatusAndIsActiveTrue(availabilityStatus);
    }

    @Transactional(readOnly = true)
    public List<AdvisorProfile> getAdvisorsBySpecialization(String specialization) {
        return advisorProfileRepository.findBySpecializationAndIsActiveTrue(specialization);
    }

    @Transactional(readOnly = true)
    public List<AdvisorProfile> getAvailableAdvisors() {
        return advisorProfileRepository.findAvailableAdvisors();
    }

    @Transactional(readOnly = true)
    public List<AdvisorProfile> getAvailableByType(String type) {
        return advisorProfileRepository.findAvailableByType(type);
    }

    @Transactional(readOnly = true)
    public List<AdvisorProfile> getTopRatedAdvisors(BigDecimal minRating) {
        return advisorProfileRepository.findTopRated(minRating);
    }

    @Transactional
    public AdvisorProfile updateAdvisor(String id, AdvisorProfile updatedAdvisor) {
        AdvisorProfile existing = getAdvisorById(id);
        existing.setAdvisorType(updatedAdvisor.getAdvisorType());
        existing.setSpecialization(updatedAdvisor.getSpecialization());
        existing.setQualifications(updatedAdvisor.getQualifications());
        existing.setExperienceYears(updatedAdvisor.getExperienceYears());
        existing.setLanguages(updatedAdvisor.getLanguages());
        existing.setAvailabilityStatus(updatedAdvisor.getAvailabilityStatus());
        existing.setMaxConsultationsPerDay(updatedAdvisor.getMaxConsultationsPerDay());
        existing.setCurrentConsultations(updatedAdvisor.getCurrentConsultations());
        existing.setActive(updatedAdvisor.getIsActive());
        return advisorProfileRepository.save(existing);
    }

    @Transactional
    public AdvisorProfile updateAvailability(String id, String availabilityStatus) {
        AdvisorProfile existing = getAdvisorById(id);
        existing.setAvailabilityStatus(availabilityStatus);
        return advisorProfileRepository.save(existing);
    }

    @Transactional
    public void deactivateAdvisor(String id) {
        AdvisorProfile existing = getAdvisorById(id);
        existing.setActive(false);
        advisorProfileRepository.save(existing);
    }
}
