package com.edubridge.studentservices.controllers;

import com.edubridge.studentservices.dto.*;
import com.edubridge.studentservices.entities.*;
import com.edubridge.studentservices.services.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/student-services/mentorships")
@RequiredArgsConstructor
@Slf4j
public class MentorshipManagementController {

    private final MentorshipProfileService mentorshipProfileService;
    private final MatchingRecordService matchingRecordService;
    private final MentorProfileService mentorProfileService;
    private final GuidanceRecordService guidanceRecordService;
    private final GrowthRecordService growthRecordService;
    private final LifecycleRecordService lifecycleRecordService;

    // ========== MENTORSHIP PROFILE MANAGEMENT ==========

    @PostMapping("/profiles")
    public ResponseEntity<MentorshipProfileResponse> createMentorshipProfile(@RequestBody MentorshipProfile mentorship) {
        MentorshipProfile created = mentorshipProfileService.createMentorshipProfile(mentorship);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToMentorshipResponse(created));
    }

    @GetMapping("/profiles/{id}")
    public ResponseEntity<MentorshipProfileResponse> getMentorshipProfile(@PathVariable String id) {
        MentorshipProfile profile = mentorshipProfileService.getMentorshipProfileById(id);
        return ResponseEntity.ok(mapToMentorshipResponse(profile));
    }

    @GetMapping("/profiles/student/{studentId}")
    public ResponseEntity<List<MentorshipProfileResponse>> getMentorshipProfilesByStudent(@PathVariable String studentId) {
        List<MentorshipProfile> profiles = mentorshipProfileService.getMentorshipProfilesByStudent(studentId);
        return ResponseEntity.ok(profiles.stream().map(this::mapToMentorshipResponse).collect(Collectors.toList()));
    }

    @GetMapping("/profiles/type/{type}")
    public ResponseEntity<List<MentorshipProfileResponse>> getMentorshipProfilesByType(@PathVariable String type) {
        List<MentorshipProfile> profiles = mentorshipProfileService.getMentorshipProfilesByType(type);
        return ResponseEntity.ok(profiles.stream().map(this::mapToMentorshipResponse).collect(Collectors.toList()));
    }

    @GetMapping("/profiles/status/{status}")
    public ResponseEntity<List<MentorshipProfileResponse>> getMentorshipProfilesByStatus(@PathVariable String status) {
        List<MentorshipProfile> profiles = mentorshipProfileService.getMentorshipProfilesByStatus(status);
        return ResponseEntity.ok(profiles.stream().map(this::mapToMentorshipResponse).collect(Collectors.toList()));
    }

    @GetMapping("/profiles/pending")
    public ResponseEntity<List<MentorshipProfileResponse>> getPendingMentorships() {
        List<MentorshipProfile> profiles = mentorshipProfileService.getPendingMentorships();
        return ResponseEntity.ok(profiles.stream().map(this::mapToMentorshipResponse).collect(Collectors.toList()));
    }

    @PutMapping("/profiles/{id}")
    public ResponseEntity<MentorshipProfileResponse> updateMentorshipProfile(@PathVariable String id, @RequestBody MentorshipProfile mentorship) {
        MentorshipProfile updated = mentorshipProfileService.updateMentorshipProfile(id, mentorship);
        return ResponseEntity.ok(mapToMentorshipResponse(updated));
    }

    @PostMapping("/profiles/{id}/cancel")
    public ResponseEntity<MentorshipProfileResponse> cancelMentorship(@PathVariable String id) {
        MentorshipProfile cancelled = mentorshipProfileService.cancelMentorship(id);
        return ResponseEntity.ok(mapToMentorshipResponse(cancelled));
    }

    @DeleteMapping("/profiles/{id}")
    public ResponseEntity<Void> deactivateMentorshipProfile(@PathVariable String id) {
        mentorshipProfileService.deactivateMentorshipProfile(id);
        return ResponseEntity.noContent().build();
    }

    // ========== MATCHING MANAGEMENT ==========

    @PostMapping("/matching")
    public ResponseEntity<MatchingRecordResponse> createMatchingRecord(@RequestBody MatchingRecord matching) {
        MatchingRecord created = matchingRecordService.createMatchingRecord(matching);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToMatchingResponse(created));
    }

    @GetMapping("/matching/{id}")
    public ResponseEntity<MatchingRecordResponse> getMatchingRecord(@PathVariable String id) {
        MatchingRecord record = matchingRecordService.getMatchingRecordById(id);
        return ResponseEntity.ok(mapToMatchingResponse(record));
    }

    @GetMapping("/matching/mentorship/{mentorshipId}")
    public ResponseEntity<List<MatchingRecordResponse>> getMatchingRecordsByMentorship(@PathVariable String mentorshipId) {
        List<MatchingRecord> records = matchingRecordService.getMatchingRecordsByMentorship(mentorshipId);
        return ResponseEntity.ok(records.stream().map(this::mapToMatchingResponse).collect(Collectors.toList()));
    }

    @GetMapping("/matching/student/{studentId}")
    public ResponseEntity<List<MatchingRecordResponse>> getMatchingRecordsByStudent(@PathVariable String studentId) {
        List<MatchingRecord> records = matchingRecordService.getMatchingRecordsByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToMatchingResponse).collect(Collectors.toList()));
    }

    @GetMapping("/matching/mentor/{mentorId}")
    public ResponseEntity<List<MatchingRecordResponse>> getMatchingRecordsByMentor(@PathVariable String mentorId) {
        List<MatchingRecord> records = matchingRecordService.getMatchingRecordsByMentor(mentorId);
        return ResponseEntity.ok(records.stream().map(this::mapToMatchingResponse).collect(Collectors.toList()));
    }

    @GetMapping("/matching/status/{status}")
    public ResponseEntity<List<MatchingRecordResponse>> getMatchingRecordsByStatus(@PathVariable String status) {
        List<MatchingRecord> records = matchingRecordService.getMatchingRecordsByStatus(status);
        return ResponseEntity.ok(records.stream().map(this::mapToMatchingResponse).collect(Collectors.toList()));
    }

    @PutMapping("/matching/{id}")
    public ResponseEntity<MatchingRecordResponse> updateMatchingRecord(@PathVariable String id, @RequestBody MatchingRecord matching) {
        MatchingRecord updated = matchingRecordService.updateMatchingRecord(id, matching);
        return ResponseEntity.ok(mapToMatchingResponse(updated));
    }

    @PostMapping("/matching/{id}/accept")
    public ResponseEntity<MatchingRecordResponse> acceptMatching(@PathVariable String id) {
        MatchingRecord accepted = matchingRecordService.acceptMatching(id);
        return ResponseEntity.ok(mapToMatchingResponse(accepted));
    }

    @PostMapping("/matching/{id}/reject")
    public ResponseEntity<MatchingRecordResponse> rejectMatching(@PathVariable String id) {
        MatchingRecord rejected = matchingRecordService.rejectMatching(id);
        return ResponseEntity.ok(mapToMatchingResponse(rejected));
    }

    @DeleteMapping("/matching/{id}")
    public ResponseEntity<Void> deactivateMatchingRecord(@PathVariable String id) {
        matchingRecordService.deactivateMatchingRecord(id);
        return ResponseEntity.noContent().build();
    }

    // ========== MENTOR PROFILE MANAGEMENT ==========

    @PostMapping("/mentors")
    public ResponseEntity<MentorProfileResponse> createMentorProfile(@RequestBody MentorProfile mentor) {
        MentorProfile created = mentorProfileService.createMentorProfile(mentor);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToMentorResponse(created));
    }

    @GetMapping("/mentors/{id}")
    public ResponseEntity<MentorProfileResponse> getMentorProfile(@PathVariable String id) {
        MentorProfile profile = mentorProfileService.getMentorProfileById(id);
        return ResponseEntity.ok(mapToMentorResponse(profile));
    }

    @GetMapping("/mentors/type/{type}")
    public ResponseEntity<List<MentorProfileResponse>> getMentorProfilesByType(@PathVariable String type) {
        List<MentorProfile> profiles = mentorProfileService.getMentorProfilesByType(type);
        return ResponseEntity.ok(profiles.stream().map(this::mapToMentorResponse).collect(Collectors.toList()));
    }

    @GetMapping("/mentors/expertise/{expertise}")
    public ResponseEntity<List<MentorProfileResponse>> getMentorProfilesByExpertise(@PathVariable String expertise) {
        List<MentorProfile> profiles = mentorProfileService.getMentorProfilesByExpertise(expertise);
        return ResponseEntity.ok(profiles.stream().map(this::mapToMentorResponse).collect(Collectors.toList()));
    }

    @GetMapping("/mentors/available")
    public ResponseEntity<List<MentorProfileResponse>> getAvailableMentors() {
        List<MentorProfile> profiles = mentorProfileService.getAvailableMentors();
        return ResponseEntity.ok(profiles.stream().map(this::mapToMentorResponse).collect(Collectors.toList()));
    }

    @PutMapping("/mentors/{id}")
    public ResponseEntity<MentorProfileResponse> updateMentorProfile(@PathVariable String id, @RequestBody MentorProfile mentor) {
        MentorProfile updated = mentorProfileService.updateMentorProfile(id, mentor);
        return ResponseEntity.ok(mapToMentorResponse(updated));
    }

    @PostMapping("/mentors/{id}/availability")
    public ResponseEntity<MentorProfileResponse> updateAvailability(
            @PathVariable String id,
            @RequestParam String availabilityStatus) {
        MentorProfile updated = mentorProfileService.updateAvailability(id, availabilityStatus);
        return ResponseEntity.ok(mapToMentorResponse(updated));
    }

    @DeleteMapping("/mentors/{id}")
    public ResponseEntity<Void> deactivateMentorProfile(@PathVariable String id) {
        mentorProfileService.deactivateMentorProfile(id);
        return ResponseEntity.noContent().build();
    }

    // ========== GUIDANCE MANAGEMENT ==========

    @PostMapping("/guidance")
    public ResponseEntity<GuidanceRecordResponse> createGuidanceRecord(@RequestBody GuidanceRecord guidance) {
        GuidanceRecord created = guidanceRecordService.createGuidanceRecord(guidance);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToGuidanceResponse(created));
    }

    @GetMapping("/guidance/{id}")
    public ResponseEntity<GuidanceRecordResponse> getGuidanceRecord(@PathVariable String id) {
        GuidanceRecord record = guidanceRecordService.getGuidanceRecordById(id);
        return ResponseEntity.ok(mapToGuidanceResponse(record));
    }

    @GetMapping("/guidance/mentorship/{mentorshipId}")
    public ResponseEntity<List<GuidanceRecordResponse>> getGuidanceRecordsByMentorship(@PathVariable String mentorshipId) {
        List<GuidanceRecord> records = guidanceRecordService.getGuidanceRecordsByMentorship(mentorshipId);
        return ResponseEntity.ok(records.stream().map(this::mapToGuidanceResponse).collect(Collectors.toList()));
    }

    @GetMapping("/guidance/student/{studentId}")
    public ResponseEntity<List<GuidanceRecordResponse>> getGuidanceRecordsByStudent(@PathVariable String studentId) {
        List<GuidanceRecord> records = guidanceRecordService.getGuidanceRecordsByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToGuidanceResponse).collect(Collectors.toList()));
    }

    @GetMapping("/guidance/type/{type}")
    public ResponseEntity<List<GuidanceRecordResponse>> getGuidanceRecordsByType(@PathVariable String type) {
        List<GuidanceRecord> records = guidanceRecordService.getGuidanceRecordsByType(type);
        return ResponseEntity.ok(records.stream().map(this::mapToGuidanceResponse).collect(Collectors.toList()));
    }

    @PutMapping("/guidance/{id}")
    public ResponseEntity<GuidanceRecordResponse> updateGuidanceRecord(@PathVariable String id, @RequestBody GuidanceRecord guidance) {
        GuidanceRecord updated = guidanceRecordService.updateGuidanceRecord(id, guidance);
        return ResponseEntity.ok(mapToGuidanceResponse(updated));
    }

    @PostMapping("/guidance/{id}/complete")
    public ResponseEntity<GuidanceRecordResponse> completeGuidance(
            @PathVariable String id,
            @RequestParam(required = false) String notes) {
        GuidanceRecord completed = guidanceRecordService.completeGuidance(id, notes);
        return ResponseEntity.ok(mapToGuidanceResponse(completed));
    }

    @DeleteMapping("/guidance/{id}")
    public ResponseEntity<Void> deactivateGuidanceRecord(@PathVariable String id) {
        guidanceRecordService.deactivateGuidanceRecord(id);
        return ResponseEntity.noContent().build();
    }

    // ========== GROWTH MANAGEMENT ==========

    @PostMapping("/growth")
    public ResponseEntity<GrowthRecordResponse> createGrowthRecord(@RequestBody GrowthRecord growth) {
        GrowthRecord created = growthRecordService.createGrowthRecord(growth);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToGrowthResponse(created));
    }

    @GetMapping("/growth/{id}")
    public ResponseEntity<GrowthRecordResponse> getGrowthRecord(@PathVariable String id) {
        GrowthRecord record = growthRecordService.getGrowthRecordById(id);
        return ResponseEntity.ok(mapToGrowthResponse(record));
    }

    @GetMapping("/growth/mentorship/{mentorshipId}")
    public ResponseEntity<List<GrowthRecordResponse>> getGrowthRecordsByMentorship(@PathVariable String mentorshipId) {
        List<GrowthRecord> records = growthRecordService.getGrowthRecordsByMentorship(mentorshipId);
        return ResponseEntity.ok(records.stream().map(this::mapToGrowthResponse).collect(Collectors.toList()));
    }

    @GetMapping("/growth/student/{studentId}")
    public ResponseEntity<List<GrowthRecordResponse>> getGrowthRecordsByStudent(@PathVariable String studentId) {
        List<GrowthRecord> records = growthRecordService.getGrowthRecordsByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToGrowthResponse).collect(Collectors.toList()));
    }

    @GetMapping("/growth/type/{type}")
    public ResponseEntity<List<GrowthRecordResponse>> getGrowthRecordsByType(@PathVariable String type) {
        List<GrowthRecord> records = growthRecordService.getGrowthRecordsByType(type);
        return ResponseEntity.ok(records.stream().map(this::mapToGrowthResponse).collect(Collectors.toList()));
    }

    @PutMapping("/growth/{id}")
    public ResponseEntity<GrowthRecordResponse> updateGrowthRecord(@PathVariable String id, @RequestBody GrowthRecord growth) {
        GrowthRecord updated = growthRecordService.updateGrowthRecord(id, growth);
        return ResponseEntity.ok(mapToGrowthResponse(updated));
    }

    @PostMapping("/growth/{id}/achieve")
    public ResponseEntity<GrowthRecordResponse> markAchieved(
            @PathVariable String id,
            @RequestParam(required = false) String evidence) {
        GrowthRecord achieved = growthRecordService.markAchieved(id, evidence);
        return ResponseEntity.ok(mapToGrowthResponse(achieved));
    }

    @DeleteMapping("/growth/{id}")
    public ResponseEntity<Void> deactivateGrowthRecord(@PathVariable String id) {
        growthRecordService.deactivateGrowthRecord(id);
        return ResponseEntity.noContent().build();
    }

    // ========== LIFECYCLE MANAGEMENT ==========

    @PostMapping("/lifecycle")
    public ResponseEntity<LifecycleRecordResponse> createLifecycleRecord(@RequestBody LifecycleRecord record) {
        LifecycleRecord created = lifecycleRecordService.createLifecycleRecord(record);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToLifecycleResponse(created));
    }

    @GetMapping("/lifecycle/{id}")
    public ResponseEntity<LifecycleRecordResponse> getLifecycleRecord(@PathVariable String id) {
        LifecycleRecord record = lifecycleRecordService.getLifecycleRecordById(id);
        return ResponseEntity.ok(mapToLifecycleResponse(record));
    }

    @GetMapping("/lifecycle/mentorship/{mentorshipId}")
    public ResponseEntity<List<LifecycleRecordResponse>> getLifecycleRecordsByMentorship(@PathVariable String mentorshipId) {
        List<LifecycleRecord> records = lifecycleRecordService.getLifecycleRecordsByMentorship(mentorshipId);
        return ResponseEntity.ok(records.stream().map(this::mapToLifecycleResponse).collect(Collectors.toList()));
    }

    @GetMapping("/lifecycle/student/{studentId}")
    public ResponseEntity<List<LifecycleRecordResponse>> getLifecycleRecordsByStudent(@PathVariable String studentId) {
        List<LifecycleRecord> records = lifecycleRecordService.getLifecycleRecordsByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToLifecycleResponse).collect(Collectors.toList()));
    }

    @PutMapping("/lifecycle/{id}")
    public ResponseEntity<LifecycleRecordResponse> updateLifecycleStatus(
            @PathVariable String id,
            @RequestParam String newStatus,
            @RequestParam(required = false) String reason,
            @RequestParam String changedBy) {
        LifecycleRecord updated = lifecycleRecordService.updateLifecycleStatus(id, newStatus, reason, changedBy);
        return ResponseEntity.ok(mapToLifecycleResponse(updated));
    }

    @DeleteMapping("/lifecycle/{id}")
    public ResponseEntity<Void> deactivateLifecycleRecord(@PathVariable String id) {
        lifecycleRecordService.deactivateLifecycleRecord(id);
        return ResponseEntity.noContent().build();
    }

    // ========== MAPPING METHODS ==========

    private MentorshipProfileResponse mapToMentorshipResponse(MentorshipProfile mentorship) {
        return MentorshipProfileResponse.builder()
                .id(mentorship.getId())
                .studentId(mentorship.getStudentId())
                .mentorshipType(mentorship.getMentorshipType())
                .mentorshipStatus(mentorship.getMentorshipStatus())
                .interests(mentorship.getInterests())
                .goals(mentorship.getGoals())
                .experienceLevel(mentorship.getExperienceLevel())
                .preferredMentorGender(mentorship.getPreferredMentorGender())
                .preferredLanguage(mentorship.getPreferredLanguage())
                .maxWeeklyHours(mentorship.getMaxWeeklyHours())
                .notes(mentorship.getNotes())
                .isActive(mentorship.getIsActive())
                .build();
    }

    private MatchingRecordResponse mapToMatchingResponse(MatchingRecord matching) {
        return MatchingRecordResponse.builder()
                .id(matching.getId())
                .mentorshipId(matching.getMentorshipId())
                .studentId(matching.getStudentId())
                .mentorId(matching.getMentorId())
                .matchingStatus(matching.getMatchingStatus())
                .compatibilityScore(matching.getCompatibilityScore())
                .matchingCriteria(matching.getMatchingCriteria())
                .studentFeedback(matching.getStudentFeedback())
                .mentorFeedback(matching.getMentorFeedback())
                .matchedAt(matching.getMatchedAt())
                .isActive(matching.getIsActive())
                .build();
    }

    private MentorProfileResponse mapToMentorResponse(MentorProfile mentor) {
        return MentorProfileResponse.builder()
                .id(mentor.getId())
                .userId(mentor.getUserId())
                .mentorType(mentor.getMentorType())
                .expertiseAreas(mentor.getExpertiseAreas())
                .qualifications(mentor.getQualifications())
                .experienceYears(mentor.getExperienceYears())
                .languages(mentor.getLanguages())
                .availabilityStatus(mentor.getAvailabilityStatus())
                .maxMentees(mentor.getMaxMentees())
                .currentMentees(mentor.getCurrentMentees())
                .rating(mentor.getRating())
                .totalReviews(mentor.getTotalReviews())
                .bio(mentor.getBio())
                .linkedinUrl(mentor.getLinkedinUrl())
                .isActive(mentor.getIsActive())
                .build();
    }

    private GuidanceRecordResponse mapToGuidanceResponse(GuidanceRecord guidance) {
        return GuidanceRecordResponse.builder()
                .id(guidance.getId())
                .mentorshipId(guidance.getMentorshipId())
                .studentId(guidance.getStudentId())
                .mentorId(guidance.getMentorId())
                .guidanceType(guidance.getGuidanceType())
                .guidanceStatus(guidance.getGuidanceStatus())
                .topic(guidance.getTopic())
                .description(guidance.getDescription())
                .scheduledAt(guidance.getScheduledAt())
                .completedAt(guidance.getCompletedAt())
                .durationMinutes(guidance.getDurationMinutes())
                .sessionNotes(guidance.getSessionNotes())
                .isActive(guidance.getIsActive())
                .build();
    }

    private GrowthRecordResponse mapToGrowthResponse(GrowthRecord growth) {
        return GrowthRecordResponse.builder()
                .id(growth.getId())
                .mentorshipId(growth.getMentorshipId())
                .studentId(growth.getStudentId())
                .growthType(growth.getGrowthType())
                .growthStatus(growth.getGrowthStatus())
                .milestoneName(growth.getMilestoneName())
                .description(growth.getDescription())
                .targetDate(growth.getTargetDate())
                .achievedDate(growth.getAchievedDate())
                .progressScore(growth.getProgressScore())
                .evidence(growth.getEvidence())
                .mentorNotes(growth.getMentorNotes())
                .isActive(growth.getIsActive())
                .build();
    }

    private LifecycleRecordResponse mapToLifecycleResponse(LifecycleRecord record) {
        return LifecycleRecordResponse.builder()
                .id(record.getId())
                .mentorshipId(record.getMentorshipId())
                .studentId(record.getStudentId())
                .previousStatus(record.getPreviousStatus())
                .newStatus(record.getNewStatus())
                .reason(record.getReason())
                .changedBy(record.getChangedBy())
                .changedAt(record.getChangedAt())
                .isActive(record.getIsActive())
                .build();
    }
}
