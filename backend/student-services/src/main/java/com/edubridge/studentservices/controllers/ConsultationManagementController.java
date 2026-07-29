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
@RequestMapping("/api/v1/student-services/consultations")
@RequiredArgsConstructor
@Slf4j
public class ConsultationManagementController {

    private final ConsultationProfileService consultationProfileService;
    private final SessionRecordService sessionRecordService;
    private final AdvisorProfileService advisorProfileService;
    private final AssessmentRecordService assessmentRecordService;
    private final PolicyRecordService policyRecordService;
    private final LifecycleRecordService lifecycleRecordService;

    // ========== CONSULTATION MANAGEMENT ==========

    @PostMapping("/management")
    public ResponseEntity<ConsultationProfileResponse> createConsultation(@RequestBody ConsultationProfile consultation) {
        ConsultationProfile created = consultationProfileService.createConsultation(consultation);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToConsultationResponse(created));
    }

    @GetMapping("/management/{id}")
    public ResponseEntity<ConsultationProfileResponse> getConsultation(@PathVariable String id) {
        ConsultationProfile consultation = consultationProfileService.getConsultationById(id);
        return ResponseEntity.ok(mapToConsultationResponse(consultation));
    }

    @GetMapping("/management/student/{studentId}")
    public ResponseEntity<List<ConsultationProfileResponse>> getConsultationsByStudent(@PathVariable String studentId) {
        List<ConsultationProfile> consultations = consultationProfileService.getConsultationsByStudent(studentId);
        return ResponseEntity.ok(consultations.stream().map(this::mapToConsultationResponse).collect(Collectors.toList()));
    }

    @GetMapping("/management/advisor/{advisorId}")
    public ResponseEntity<List<ConsultationProfileResponse>> getConsultationsByAdvisor(@PathVariable String advisorId) {
        List<ConsultationProfile> consultations = consultationProfileService.getConsultationsByAdvisor(advisorId);
        return ResponseEntity.ok(consultations.stream().map(this::mapToConsultationResponse).collect(Collectors.toList()));
    }

    @GetMapping("/management/type/{type}")
    public ResponseEntity<List<ConsultationProfileResponse>> getConsultationsByType(@PathVariable String type) {
        List<ConsultationProfile> consultations = consultationProfileService.getConsultationsByType(type);
        return ResponseEntity.ok(consultations.stream().map(this::mapToConsultationResponse).collect(Collectors.toList()));
    }

    @GetMapping("/management/status/{status}")
    public ResponseEntity<List<ConsultationProfileResponse>> getConsultationsByStatus(@PathVariable String status) {
        List<ConsultationProfile> consultations = consultationProfileService.getConsultationsByStatus(status);
        return ResponseEntity.ok(consultations.stream().map(this::mapToConsultationResponse).collect(Collectors.toList()));
    }

    @GetMapping("/management/pending")
    public ResponseEntity<List<ConsultationProfileResponse>> getPendingConsultations() {
        List<ConsultationProfile> consultations = consultationProfileService.getPendingConsultations();
        return ResponseEntity.ok(consultations.stream().map(this::mapToConsultationResponse).collect(Collectors.toList()));
    }

    @PutMapping("/management/{id}")
    public ResponseEntity<ConsultationProfileResponse> updateConsultation(@PathVariable String id, @RequestBody ConsultationProfile consultation) {
        ConsultationProfile updated = consultationProfileService.updateConsultation(id, consultation);
        return ResponseEntity.ok(mapToConsultationResponse(updated));
    }

    @PostMapping("/management/{id}/cancel")
    public ResponseEntity<ConsultationProfileResponse> cancelConsultation(@PathVariable String id) {
        ConsultationProfile cancelled = consultationProfileService.cancelConsultation(id);
        return ResponseEntity.ok(mapToConsultationResponse(cancelled));
    }

    @DeleteMapping("/management/{id}")
    public ResponseEntity<Void> deactivateConsultation(@PathVariable String id) {
        consultationProfileService.deactivateConsultation(id);
        return ResponseEntity.noContent().build();
    }

    // ========== SESSION MANAGEMENT ==========

    @PostMapping("/sessions")
    public ResponseEntity<SessionRecordResponse> createSession(@RequestBody SessionRecord session) {
        SessionRecord created = sessionRecordService.createSession(session);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToSessionResponse(created));
    }

    @GetMapping("/sessions/{id}")
    public ResponseEntity<SessionRecordResponse> getSession(@PathVariable String id) {
        SessionRecord session = sessionRecordService.getSessionById(id);
        return ResponseEntity.ok(mapToSessionResponse(session));
    }

    @GetMapping("/sessions/consultation/{consultationId}")
    public ResponseEntity<List<SessionRecordResponse>> getSessionsByConsultation(@PathVariable String consultationId) {
        List<SessionRecord> sessions = sessionRecordService.getSessionsByConsultation(consultationId);
        return ResponseEntity.ok(sessions.stream().map(this::mapToSessionResponse).collect(Collectors.toList()));
    }

    @GetMapping("/sessions/student/{studentId}")
    public ResponseEntity<List<SessionRecordResponse>> getSessionsByStudent(@PathVariable String studentId) {
        List<SessionRecord> sessions = sessionRecordService.getSessionsByStudent(studentId);
        return ResponseEntity.ok(sessions.stream().map(this::mapToSessionResponse).collect(Collectors.toList()));
    }

    @GetMapping("/sessions/status/{status}")
    public ResponseEntity<List<SessionRecordResponse>> getSessionsByStatus(@PathVariable String status) {
        List<SessionRecord> sessions = sessionRecordService.getSessionsByStatus(status);
        return ResponseEntity.ok(sessions.stream().map(this::mapToSessionResponse).collect(Collectors.toList()));
    }

    @GetMapping("/sessions/follow-up")
    public ResponseEntity<List<SessionRecordResponse>> getSessionsRequiringFollowUp() {
        List<SessionRecord> sessions = sessionRecordService.getRequiringFollowUp();
        return ResponseEntity.ok(sessions.stream().map(this::mapToSessionResponse).collect(Collectors.toList()));
    }

    @PutMapping("/sessions/{id}")
    public ResponseEntity<SessionRecordResponse> updateSession(@PathVariable String id, @RequestBody SessionRecord session) {
        SessionRecord updated = sessionRecordService.updateSession(id, session);
        return ResponseEntity.ok(mapToSessionResponse(updated));
    }

    @PostMapping("/sessions/{id}/complete")
    public ResponseEntity<SessionRecordResponse> completeSession(
            @PathVariable String id,
            @RequestParam(required = false) String notes,
            @RequestParam(required = false) String actionItems) {
        SessionRecord completed = sessionRecordService.completeSession(id, notes, actionItems);
        return ResponseEntity.ok(mapToSessionResponse(completed));
    }

    @DeleteMapping("/sessions/{id}")
    public ResponseEntity<Void> deactivateSession(@PathVariable String id) {
        sessionRecordService.deactivateSession(id);
        return ResponseEntity.noContent().build();
    }

    // ========== ADVISOR MANAGEMENT ==========

    @PostMapping("/advisors")
    public ResponseEntity<AdvisorProfileResponse> createAdvisor(@RequestBody AdvisorProfile advisor) {
        AdvisorProfile created = advisorProfileService.createAdvisor(advisor);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToAdvisorResponse(created));
    }

    @GetMapping("/advisors/{id}")
    public ResponseEntity<AdvisorProfileResponse> getAdvisor(@PathVariable String id) {
        AdvisorProfile advisor = advisorProfileService.getAdvisorById(id);
        return ResponseEntity.ok(mapToAdvisorResponse(advisor));
    }

    @GetMapping("/advisors/type/{type}")
    public ResponseEntity<List<AdvisorProfileResponse>> getAdvisorsByType(@PathVariable String type) {
        List<AdvisorProfile> advisors = advisorProfileService.getAdvisorsByType(type);
        return ResponseEntity.ok(advisors.stream().map(this::mapToAdvisorResponse).collect(Collectors.toList()));
    }

    @GetMapping("/advisors/specialization/{specialization}")
    public ResponseEntity<List<AdvisorProfileResponse>> getAdvisorsBySpecialization(@PathVariable String specialization) {
        List<AdvisorProfile> advisors = advisorProfileService.getAdvisorsBySpecialization(specialization);
        return ResponseEntity.ok(advisors.stream().map(this::mapToAdvisorResponse).collect(Collectors.toList()));
    }

    @GetMapping("/advisors/available")
    public ResponseEntity<List<AdvisorProfileResponse>> getAvailableAdvisors() {
        List<AdvisorProfile> advisors = advisorProfileService.getAvailableAdvisors();
        return ResponseEntity.ok(advisors.stream().map(this::mapToAdvisorResponse).collect(Collectors.toList()));
    }

    @PutMapping("/advisors/{id}")
    public ResponseEntity<AdvisorProfileResponse> updateAdvisor(@PathVariable String id, @RequestBody AdvisorProfile advisor) {
        AdvisorProfile updated = advisorProfileService.updateAdvisor(id, advisor);
        return ResponseEntity.ok(mapToAdvisorResponse(updated));
    }

    @PostMapping("/advisors/{id}/availability")
    public ResponseEntity<AdvisorProfileResponse> updateAvailability(
            @PathVariable String id,
            @RequestParam String availabilityStatus) {
        AdvisorProfile updated = advisorProfileService.updateAvailability(id, availabilityStatus);
        return ResponseEntity.ok(mapToAdvisorResponse(updated));
    }

    @DeleteMapping("/advisors/{id}")
    public ResponseEntity<Void> deactivateAdvisor(@PathVariable String id) {
        advisorProfileService.deactivateAdvisor(id);
        return ResponseEntity.noContent().build();
    }

    // ========== ASSESSMENT MANAGEMENT ==========

    @PostMapping("/assessments")
    public ResponseEntity<AssessmentRecordResponse> createAssessment(@RequestBody AssessmentRecord assessment) {
        AssessmentRecord created = assessmentRecordService.createAssessment(assessment);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToAssessmentResponse(created));
    }

    @GetMapping("/assessments/{id}")
    public ResponseEntity<AssessmentRecordResponse> getAssessment(@PathVariable String id) {
        AssessmentRecord assessment = assessmentRecordService.getAssessmentById(id);
        return ResponseEntity.ok(mapToAssessmentResponse(assessment));
    }

    @GetMapping("/assessments/consultation/{consultationId}")
    public ResponseEntity<List<AssessmentRecordResponse>> getAssessmentsByConsultation(@PathVariable String consultationId) {
        List<AssessmentRecord> assessments = assessmentRecordService.getAssessmentsByConsultation(consultationId);
        return ResponseEntity.ok(assessments.stream().map(this::mapToAssessmentResponse).collect(Collectors.toList()));
    }

    @GetMapping("/assessments/student/{studentId}")
    public ResponseEntity<List<AssessmentRecordResponse>> getAssessmentsByStudent(@PathVariable String studentId) {
        List<AssessmentRecord> assessments = assessmentRecordService.getAssessmentsByStudent(studentId);
        return ResponseEntity.ok(assessments.stream().map(this::mapToAssessmentResponse).collect(Collectors.toList()));
    }

    @GetMapping("/assessments/type/{type}")
    public ResponseEntity<List<AssessmentRecordResponse>> getAssessmentsByType(@PathVariable String type) {
        List<AssessmentRecord> assessments = assessmentRecordService.getAssessmentsByType(type);
        return ResponseEntity.ok(assessments.stream().map(this::mapToAssessmentResponse).collect(Collectors.toList()));
    }

    @PutMapping("/assessments/{id}")
    public ResponseEntity<AssessmentRecordResponse> updateAssessment(@PathVariable String id, @RequestBody AssessmentRecord assessment) {
        AssessmentRecord updated = assessmentRecordService.updateAssessment(id, assessment);
        return ResponseEntity.ok(mapToAssessmentResponse(updated));
    }

    @PostMapping("/assessments/{id}/complete")
    public ResponseEntity<AssessmentRecordResponse> completeAssessment(
            @PathVariable String id,
            @RequestParam(required = false) String notes,
            @RequestParam(required = false) String recommendations) {
        AssessmentRecord completed = assessmentRecordService.completeAssessment(id, notes, recommendations);
        return ResponseEntity.ok(mapToAssessmentResponse(completed));
    }

    @DeleteMapping("/assessments/{id}")
    public ResponseEntity<Void> deactivateAssessment(@PathVariable String id) {
        assessmentRecordService.deactivateAssessment(id);
        return ResponseEntity.noContent().build();
    }

    // ========== POLICY MANAGEMENT ==========

    @PostMapping("/policies")
    public ResponseEntity<PolicyRecordResponse> createPolicy(@RequestBody PolicyRecord policy) {
        PolicyRecord created = policyRecordService.createPolicy(policy);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToPolicyResponse(created));
    }

    @GetMapping("/policies/{id}")
    public ResponseEntity<PolicyRecordResponse> getPolicy(@PathVariable String id) {
        PolicyRecord policy = policyRecordService.getPolicyById(id);
        return ResponseEntity.ok(mapToPolicyResponse(policy));
    }

    @GetMapping("/policies/type/{type}")
    public ResponseEntity<List<PolicyRecordResponse>> getPoliciesByType(@PathVariable String type) {
        List<PolicyRecord> policies = policyRecordService.getPoliciesByType(type);
        return ResponseEntity.ok(policies.stream().map(this::mapToPolicyResponse).collect(Collectors.toList()));
    }

    @GetMapping("/policies/effect/{effect}")
    public ResponseEntity<List<PolicyRecordResponse>> getPoliciesByEffect(@PathVariable String effect) {
        List<PolicyRecord> policies = policyRecordService.getPoliciesByEffect(effect);
        return ResponseEntity.ok(policies.stream().map(this::mapToPolicyResponse).collect(Collectors.toList()));
    }

    @GetMapping("/policies/active")
    public ResponseEntity<List<PolicyRecordResponse>> getAllActivePolicies() {
        List<PolicyRecord> policies = policyRecordService.getAllActivePoliciesOrderedByPriority();
        return ResponseEntity.ok(policies.stream().map(this::mapToPolicyResponse).collect(Collectors.toList()));
    }

    @PutMapping("/policies/{id}")
    public ResponseEntity<PolicyRecordResponse> updatePolicy(@PathVariable String id, @RequestBody PolicyRecord policy) {
        PolicyRecord updated = policyRecordService.updatePolicy(id, policy);
        return ResponseEntity.ok(mapToPolicyResponse(updated));
    }

    @DeleteMapping("/policies/{id}")
    public ResponseEntity<Void> deactivatePolicy(@PathVariable String id) {
        policyRecordService.deactivatePolicy(id);
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

    @GetMapping("/lifecycle/consultation/{consultationId}")
    public ResponseEntity<List<LifecycleRecordResponse>> getLifecycleRecordsByConsultation(@PathVariable String consultationId) {
        List<LifecycleRecord> records = lifecycleRecordService.getLifecycleRecordsByConsultation(consultationId);
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

    private ConsultationProfileResponse mapToConsultationResponse(ConsultationProfile consultation) {
        return ConsultationProfileResponse.builder()
                .id(consultation.getId())
                .studentId(consultation.getStudentId())
                .advisorId(consultation.getAdvisorId())
                .consultationType(consultation.getConsultationType())
                .consultationStatus(consultation.getConsultationStatus())
                .scheduledDate(consultation.getScheduledDate())
                .duration(consultation.getDuration())
                .topic(consultation.getTopic())
                .description(consultation.getDescription())
                .priority(consultation.getPriority())
                .isVirtual(consultation.getIsVirtual())
                .meetingLink(consultation.getMeetingLink())
                .notes(consultation.getNotes())
                .isActive(consultation.getIsActive())
                .build();
    }

    private SessionRecordResponse mapToSessionResponse(SessionRecord session) {
        return SessionRecordResponse.builder()
                .id(session.getId())
                .consultationId(session.getConsultationId())
                .studentId(session.getStudentId())
                .advisorId(session.getAdvisorId())
                .sessionType(session.getSessionType())
                .sessionStatus(session.getSessionStatus())
                .scheduledStart(session.getScheduledStart())
                .scheduledEnd(session.getScheduledEnd())
                .actualStart(session.getActualStart())
                .actualEnd(session.getActualEnd())
                .sessionNotes(session.getSessionNotes())
                .actionItems(session.getActionItems())
                .followUpRequired(session.getFollowUpRequired())
                .isActive(session.getIsActive())
                .build();
    }

    private AdvisorProfileResponse mapToAdvisorResponse(AdvisorProfile advisor) {
        return AdvisorProfileResponse.builder()
                .id(advisor.getId())
                .userId(advisor.getUserId())
                .advisorType(advisor.getAdvisorType())
                .specialization(advisor.getSpecialization())
                .qualifications(advisor.getQualifications())
                .experienceYears(advisor.getExperienceYears())
                .languages(advisor.getLanguages())
                .availabilityStatus(advisor.getAvailabilityStatus())
                .maxConsultationsPerDay(advisor.getMaxConsultationsPerDay())
                .currentConsultations(advisor.getCurrentConsultations())
                .rating(advisor.getRating())
                .totalReviews(advisor.getTotalReviews())
                .isActive(advisor.getIsActive())
                .build();
    }

    private AssessmentRecordResponse mapToAssessmentResponse(AssessmentRecord assessment) {
        return AssessmentRecordResponse.builder()
                .id(assessment.getId())
                .consultationId(assessment.getConsultationId())
                .studentId(assessment.getStudentId())
                .advisorId(assessment.getAdvisorId())
                .assessmentType(assessment.getAssessmentType())
                .assessmentStatus(assessment.getAssessmentStatus())
                .assessmentScore(assessment.getAssessmentScore())
                .satisfactionScore(assessment.getSatisfactionScore())
                .effectivenessScore(assessment.getEffectivenessScore())
                .assessmentNotes(assessment.getAssessmentNotes())
                .recommendations(assessment.getRecommendations())
                .assessedAt(assessment.getAssessedAt())
                .isActive(assessment.getIsActive())
                .build();
    }

    private PolicyRecordResponse mapToPolicyResponse(PolicyRecord policy) {
        return PolicyRecordResponse.builder()
                .id(policy.getId())
                .name(policy.getName())
                .description(policy.getDescription())
                .policyType(policy.getPolicyType())
                .policyRule(policy.getPolicyRule())
                .effect(policy.getEffect())
                .priority(policy.getPriority())
                .conditions(policy.getConditions())
                .effectiveFrom(policy.getEffectiveFrom())
                .effectiveTo(policy.getEffectiveTo())
                .isActive(policy.getIsActive())
                .build();
    }

    private LifecycleRecordResponse mapToLifecycleResponse(LifecycleRecord record) {
        return LifecycleRecordResponse.builder()
                .id(record.getId())
                .consultationId(record.getConsultationId())
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
