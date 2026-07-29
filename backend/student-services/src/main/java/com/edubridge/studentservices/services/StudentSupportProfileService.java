package com.edubridge.studentservices.services;

import com.edubridge.studentservices.entities.StudentSupportProfile;
import com.edubridge.studentservices.repositories.StudentSupportProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentSupportProfileService {

    private final StudentSupportProfileRepository studentSupportProfileRepository;

    @Transactional
    public StudentSupportProfile createSupportProfile(StudentSupportProfile profile) {
        log.info("Creating support profile for student: {}", profile.getStudentId());
        return studentSupportProfileRepository.save(profile);
    }

    @Transactional(readOnly = true)
    public StudentSupportProfile getSupportProfileById(String id) {
        return studentSupportProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Support profile not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<StudentSupportProfile> getSupportProfilesByStudent(String studentId) {
        return studentSupportProfileRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<StudentSupportProfile> getSupportProfilesByType(String supportType) {
        return studentSupportProfileRepository.findBySupportTypeAndIsActiveTrue(supportType);
    }

    @Transactional(readOnly = true)
    public List<StudentSupportProfile> getSupportProfilesByStatus(String supportStatus) {
        return studentSupportProfileRepository.findBySupportStatusAndIsActiveTrue(supportStatus);
    }

    @Transactional(readOnly = true)
    public List<StudentSupportProfile> getSupportProfilesByLevel(String supportLevel) {
        return studentSupportProfileRepository.findBySupportLevelAndIsActiveTrue(supportLevel);
    }

    @Transactional(readOnly = true)
    public List<StudentSupportProfile> getActiveByStudent(String studentId) {
        return studentSupportProfileRepository.findActiveByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<StudentSupportProfile> getIdentifiedProfiles() {
        return studentSupportProfileRepository.findIdentifiedProfiles();
    }

    @Transactional
    public StudentSupportProfile updateSupportProfile(String id, StudentSupportProfile updated) {
        StudentSupportProfile existing = getSupportProfileById(id);
        existing.setSupportType(updated.getSupportType());
        existing.setSupportStatus(updated.getSupportStatus());
        existing.setSupportLevel(updated.getSupportLevel());
        existing.setPrimaryConcern(updated.getPrimaryConcern());
        existing.setSecondaryConcerns(updated.getSecondaryConcerns());
        existing.setSupportPreferences(updated.getSupportPreferences());
        existing.setInterventionHistory(updated.getInterventionHistory());
        existing.setAssignedCounselorId(updated.getAssignedCounselorId());
        existing.setLastContactDate(updated.getLastContactDate());
        existing.setNextFollowUp(updated.getNextFollowUp());
        existing.setNotes(updated.getNotes());
        return studentSupportProfileRepository.save(existing);
    }

    @Transactional
    public StudentSupportProfile closeSupportProfile(String id) {
        StudentSupportProfile existing = getSupportProfileById(id);
        existing.setSupportStatus("CLOSED");
        return studentSupportProfileRepository.save(existing);
    }

    @Transactional
    public void deactivateSupportProfile(String id) {
        StudentSupportProfile existing = getSupportProfileById(id);
        existing.setActive(false);
        studentSupportProfileRepository.save(existing);
    }
}
