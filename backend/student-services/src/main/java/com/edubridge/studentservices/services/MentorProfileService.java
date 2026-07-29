package com.edubridge.studentservices.services;

import com.edubridge.studentservices.entities.MentorProfile;
import com.edubridge.studentservices.repositories.MentorProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MentorProfileService {

    private final MentorProfileRepository mentorProfileRepository;

    @Transactional
    public MentorProfile createMentorProfile(MentorProfile mentor) {
        log.info("Creating mentor profile for user: {}", mentor.getUserId());
        return mentorProfileRepository.save(mentor);
    }

    @Transactional(readOnly = true)
    public MentorProfile getMentorProfileById(String id) {
        return mentorProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mentor profile not found: " + id));
    }

    @Transactional(readOnly = true)
    public MentorProfile getMentorProfileByUserId(String userId) {
        return mentorProfileRepository.findByUserId(userId);
    }

    @Transactional(readOnly = true)
    public List<MentorProfile> getMentorProfilesByType(String mentorType) {
        return mentorProfileRepository.findByMentorTypeAndIsActiveTrue(mentorType);
    }

    @Transactional(readOnly = true)
    public List<MentorProfile> getMentorProfilesByExpertise(String expertise) {
        return mentorProfileRepository.findByExpertiseAreasContainingAndIsActiveTrue(expertise);
    }

    @Transactional(readOnly = true)
    public List<MentorProfile> getMentorProfilesByAvailability(String availabilityStatus) {
        return mentorProfileRepository.findByAvailabilityStatusAndIsActiveTrue(availabilityStatus);
    }

    @Transactional(readOnly = true)
    public List<MentorProfile> getAvailableMentors() {
        return mentorProfileRepository.findAvailableMentors();
    }

    @Transactional
    public MentorProfile updateMentorProfile(String id, MentorProfile updated) {
        MentorProfile existing = getMentorProfileById(id);
        existing.setMentorType(updated.getMentorType());
        existing.setExpertiseAreas(updated.getExpertiseAreas());
        existing.setQualifications(updated.getQualifications());
        existing.setExperienceYears(updated.getExperienceYears());
        existing.setLanguages(updated.getLanguages());
        existing.setAvailabilityStatus(updated.getAvailabilityStatus());
        existing.setMaxMentees(updated.getMaxMentees());
        existing.setBio(updated.getBio());
        existing.setLinkedinUrl(updated.getLinkedinUrl());
        return mentorProfileRepository.save(existing);
    }

    @Transactional
    public MentorProfile updateAvailability(String id, String availabilityStatus) {
        MentorProfile existing = getMentorProfileById(id);
        existing.setAvailabilityStatus(availabilityStatus);
        return mentorProfileRepository.save(existing);
    }

    @Transactional
    public void deactivateMentorProfile(String id) {
        MentorProfile existing = getMentorProfileById(id);
        existing.setActive(false);
        mentorProfileRepository.save(existing);
    }
}
