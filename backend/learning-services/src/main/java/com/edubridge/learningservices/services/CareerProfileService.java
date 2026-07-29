package com.edubridge.learningservices.services;

import com.edubridge.learningservices.entities.CareerProfile;
import com.edubridge.learningservices.repositories.CareerProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CareerProfileService {

    private final CareerProfileRepository careerProfileRepository;

    @Transactional
    public CareerProfile createCareerProfile(CareerProfile profile) {
        log.info("Creating career profile for student: {} field: {}", profile.getStudentId(), profile.getCareerField());
        return careerProfileRepository.save(profile);
    }

    @Transactional(readOnly = true)
    public CareerProfile getCareerProfileById(String id) {
        return careerProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Career profile not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<CareerProfile> getCareerProfilesByStudent(String studentId) {
        return careerProfileRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<CareerProfile> getCareerProfilesByStatus(String careerStatus) {
        return careerProfileRepository.findByCareerStatusAndIsActiveTrue(careerStatus);
    }

    @Transactional(readOnly = true)
    public List<CareerProfile> getCareerProfilesByField(String careerField) {
        return careerProfileRepository.findByCareerFieldAndIsActiveTrue(careerField);
    }

    @Transactional(readOnly = true)
    public List<CareerProfile> getCareerProfilesByIndustry(String industryPreference) {
        return careerProfileRepository.findByIndustryPreferenceAndIsActiveTrue(industryPreference);
    }

    @Transactional(readOnly = true)
    public List<CareerProfile> getExploringStudents(String studentId) {
        return careerProfileRepository.findExploringStudents(studentId);
    }

    @Transactional(readOnly = true)
    public List<CareerProfile> getHighReadinessStudents(Double minScore) {
        return careerProfileRepository.findHighReadinessStudents(minScore);
    }

    @Transactional(readOnly = true)
    public List<CareerProfile> getStudentsBelowReadinessThreshold(Double threshold) {
        return careerProfileRepository.findStudentsBelowReadinessThreshold(threshold);
    }

    @Transactional
    public CareerProfile updateCareerProfile(String id, CareerProfile updated) {
        CareerProfile existing = getCareerProfileById(id);
        existing.setCareerStatus(updated.getCareerStatus());
        existing.setCareerField(updated.getCareerField());
        existing.setCareerSubfield(updated.getCareerSubfield());
        existing.setCareerGoal(updated.getCareerGoal());
        existing.setCareerVision(updated.getCareerVision());
        existing.setIndustryPreference(updated.getIndustryPreference());
        existing.setWorkEnvironmentPreference(updated.getWorkEnvironmentPreference());
        existing.setGeographicPreference(updated.getGeographicPreference());
        existing.setCareerReadinessScore(updated.getCareerReadinessScore());
        existing.setProfessionalMaturityScore(updated.getProfessionalMaturityScore());
        existing.setTargetCareerEntryDate(updated.getTargetCareerEntryDate());
        existing.setNotes(updated.getNotes());
        return careerProfileRepository.save(existing);
    }

    @Transactional
    public CareerProfile reviewCareerProfile(String id) {
        CareerProfile existing = getCareerProfileById(id);
        existing.setLastCareerReviewDate(LocalDateTime.now());
        return careerProfileRepository.save(existing);
    }

    @Transactional
    public void deactivateCareerProfile(String id) {
        CareerProfile existing = getCareerProfileById(id);
        existing.setActive(false);
        careerProfileRepository.save(existing);
    }
}
