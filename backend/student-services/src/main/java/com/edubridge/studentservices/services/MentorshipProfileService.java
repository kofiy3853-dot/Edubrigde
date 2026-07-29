package com.edubridge.studentservices.services;

import com.edubridge.studentservices.entities.MentorshipProfile;
import com.edubridge.studentservices.repositories.MentorshipProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MentorshipProfileService {

    private final MentorshipProfileRepository mentorshipProfileRepository;

    @Transactional
    public MentorshipProfile createMentorshipProfile(MentorshipProfile mentorship) {
        log.info("Creating mentorship profile for student: {}", mentorship.getStudentId());
        return mentorshipProfileRepository.save(mentorship);
    }

    @Transactional(readOnly = true)
    public MentorshipProfile getMentorshipProfileById(String id) {
        return mentorshipProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mentorship profile not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<MentorshipProfile> getMentorshipProfilesByStudent(String studentId) {
        return mentorshipProfileRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<MentorshipProfile> getMentorshipProfilesByType(String mentorshipType) {
        return mentorshipProfileRepository.findByMentorshipTypeAndIsActiveTrue(mentorshipType);
    }

    @Transactional(readOnly = true)
    public List<MentorshipProfile> getMentorshipProfilesByStatus(String mentorshipStatus) {
        return mentorshipProfileRepository.findByMentorshipStatusAndIsActiveTrue(mentorshipStatus);
    }

    @Transactional(readOnly = true)
    public List<MentorshipProfile> getMentorshipProfilesByExperienceLevel(String experienceLevel) {
        return mentorshipProfileRepository.findByExperienceLevelAndIsActiveTrue(experienceLevel);
    }

    @Transactional(readOnly = true)
    public List<MentorshipProfile> getActiveByStudent(String studentId) {
        return mentorshipProfileRepository.findActiveByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<MentorshipProfile> getPendingMentorships() {
        return mentorshipProfileRepository.findPendingMentorships();
    }

    @Transactional
    public MentorshipProfile updateMentorshipProfile(String id, MentorshipProfile updated) {
        MentorshipProfile existing = getMentorshipProfileById(id);
        existing.setMentorshipType(updated.getMentorshipType());
        existing.setMentorshipStatus(updated.getMentorshipStatus());
        existing.setInterests(updated.getInterests());
        existing.setGoals(updated.getGoals());
        existing.setExperienceLevel(updated.getExperienceLevel());
        existing.setPreferredMentorGender(updated.getPreferredMentorGender());
        existing.setPreferredLanguage(updated.getPreferredLanguage());
        existing.setMaxWeeklyHours(updated.getMaxWeeklyHours());
        existing.setNotes(updated.getNotes());
        return mentorshipProfileRepository.save(existing);
    }

    @Transactional
    public MentorshipProfile cancelMentorship(String id) {
        MentorshipProfile existing = getMentorshipProfileById(id);
        existing.setMentorshipStatus("CANCELLED");
        return mentorshipProfileRepository.save(existing);
    }

    @Transactional
    public void deactivateMentorshipProfile(String id) {
        MentorshipProfile existing = getMentorshipProfileById(id);
        existing.setActive(false);
        mentorshipProfileRepository.save(existing);
    }
}
