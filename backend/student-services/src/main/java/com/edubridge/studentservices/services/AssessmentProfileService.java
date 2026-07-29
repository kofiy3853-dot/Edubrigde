package com.edubridge.studentservices.services;

import com.edubridge.studentservices.entities.AssessmentProfile;
import com.edubridge.studentservices.repositories.AssessmentProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssessmentProfileService {

    private final AssessmentProfileRepository assessmentProfileRepository;

    @Transactional
    public AssessmentProfile createAssessmentProfile(AssessmentProfile profile) {
        log.info("Creating assessment profile for student: {}", profile.getStudentId());
        return assessmentProfileRepository.save(profile);
    }

    @Transactional(readOnly = true)
    public AssessmentProfile getAssessmentProfileById(String id) {
        return assessmentProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assessment profile not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<AssessmentProfile> getAssessmentProfilesByStudent(String studentId) {
        return assessmentProfileRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<AssessmentProfile> getAssessmentProfilesByType(String assessmentType) {
        return assessmentProfileRepository.findByAssessmentTypeAndIsActiveTrue(assessmentType);
    }

    @Transactional(readOnly = true)
    public List<AssessmentProfile> getAssessmentProfilesByStatus(String assessmentStatus) {
        return assessmentProfileRepository.findByAssessmentStatusAndIsActiveTrue(assessmentStatus);
    }

    @Transactional(readOnly = true)
    public List<AssessmentProfile> getAssessmentProfilesByPeriod(String assessmentPeriod) {
        return assessmentProfileRepository.findByAssessmentPeriodAndIsActiveTrue(assessmentPeriod);
    }

    @Transactional(readOnly = true)
    public List<AssessmentProfile> getInProgressByStudent(String studentId) {
        return assessmentProfileRepository.findInProgressByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<AssessmentProfile> getPlanningProfiles() {
        return assessmentProfileRepository.findPlanningProfiles();
    }

    @Transactional
    public AssessmentProfile updateAssessmentProfile(String id, AssessmentProfile updated) {
        AssessmentProfile existing = getAssessmentProfileById(id);
        existing.setAssessmentType(updated.getAssessmentType());
        existing.setAssessmentStatus(updated.getAssessmentStatus());
        existing.setAssessmentPeriod(updated.getAssessmentPeriod());
        existing.setTargetScore(updated.getTargetScore());
        existing.setCurrentScore(updated.getCurrentScore());
        existing.setAssessmentCriteria(updated.getAssessmentCriteria());
        existing.setAssessmentNotes(updated.getAssessmentNotes());
        existing.setNextAssessmentDate(updated.getNextAssessmentDate());
        existing.setLastAssessmentDate(updated.getLastAssessmentDate());
        existing.setAssessorId(updated.getAssessorId());
        return assessmentProfileRepository.save(existing);
    }

    @Transactional
    public AssessmentProfile completeAssessmentProfile(String id) {
        AssessmentProfile existing = getAssessmentProfileById(id);
        existing.setAssessmentStatus("COMPLETED");
        return assessmentProfileRepository.save(existing);
    }

    @Transactional
    public void deactivateAssessmentProfile(String id) {
        AssessmentProfile existing = getAssessmentProfileById(id);
        existing.setActive(false);
        assessmentProfileRepository.save(existing);
    }
}
