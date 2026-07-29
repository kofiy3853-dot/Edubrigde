package com.edubridge.learningservices.services;

import com.edubridge.learningservices.entities.GrowthProfile;
import com.edubridge.learningservices.repositories.GrowthProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GrowthProfileService {

    private final GrowthProfileRepository growthProfileRepository;

    @Transactional
    public GrowthProfile createGrowthProfile(GrowthProfile profile) {
        log.info("Creating growth profile for student: {} academic level: {}", profile.getStudentId(), profile.getAcademicLevel());
        return growthProfileRepository.save(profile);
    }

    @Transactional(readOnly = true)
    public GrowthProfile getGrowthProfileById(String id) {
        return growthProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Growth profile not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<GrowthProfile> getGrowthProfilesByStudent(String studentId) {
        return growthProfileRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<GrowthProfile> getGrowthProfilesByStatus(String growthStatus) {
        return growthProfileRepository.findByGrowthStatusAndIsActiveTrue(growthStatus);
    }

    @Transactional(readOnly = true)
    public List<GrowthProfile> getGrowthProfilesByAcademicLevel(String academicLevel) {
        return growthProfileRepository.findByAcademicLevelAndIsActiveTrue(academicLevel);
    }

    @Transactional(readOnly = true)
    public List<GrowthProfile> getGrowthProfilesByStanding(String academicStanding) {
        return growthProfileRepository.findByAcademicStandingAndIsActiveTrue(academicStanding);
    }

    @Transactional(readOnly = true)
    public List<GrowthProfile> getExcellingStudents(String studentId) {
        return growthProfileRepository.findExcellingStudents(studentId);
    }

    @Transactional(readOnly = true)
    public List<GrowthProfile> getHighReadinessStudents(Double minScore) {
        return growthProfileRepository.findHighReadinessStudents(minScore);
    }

    @Transactional(readOnly = true)
    public List<GrowthProfile> getStudentsBelowGpaThreshold(Double threshold) {
        return growthProfileRepository.findStudentsBelowGpaThreshold(threshold);
    }

    @Transactional
    public GrowthProfile updateGrowthProfile(String id, GrowthProfile updated) {
        GrowthProfile existing = getGrowthProfileById(id);
        existing.setAcademicLevel(updated.getAcademicLevel());
        existing.setGrowthStatus(updated.getGrowthStatus());
        existing.setCurrentGpa(updated.getCurrentGpa());
        existing.setCumulativeGpa(updated.getCumulativeGpa());
        existing.setTotalCreditsEarned(updated.getTotalCreditsEarned());
        existing.setTotalCreditsAttempted(updated.getTotalCreditsAttempted());
        existing.setCreditCompletionRate(updated.getCreditCompletionRate());
        existing.setAcademicStanding(updated.getAcademicStanding());
        existing.setTargetGraduationDate(updated.getTargetGraduationDate());
        existing.setAcademicInterests(updated.getAcademicInterests());
        existing.setStrengths(updated.getStrengths());
        existing.setAreasForImprovement(updated.getAreasForImprovement());
        existing.setCareerAlignmentScore(updated.getCareerAlignmentScore());
        existing.setReadinessScore(updated.getReadinessScore());
        return growthProfileRepository.save(existing);
    }

    @Transactional
    public GrowthProfile reviewGrowthProfile(String id) {
        GrowthProfile existing = getGrowthProfileById(id);
        existing.setLastAcademicReviewDate(LocalDateTime.now());
        return growthProfileRepository.save(existing);
    }

    @Transactional
    public void deactivateGrowthProfile(String id) {
        GrowthProfile existing = getGrowthProfileById(id);
        existing.setActive(false);
        growthProfileRepository.save(existing);
    }
}
