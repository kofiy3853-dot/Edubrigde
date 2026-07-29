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
@RequestMapping("/api/v1/student-services/support")
@RequiredArgsConstructor
@Slf4j
public class StudentSupportController {

    private final StudentSupportProfileService supportProfileService;
    private final AcademicSupportRecordService academicSupportRecordService;
    private final WellbeingRecordService wellbeingRecordService;
    private final ResourceRecordService resourceRecordService;
    private final SupportAssessmentRecordService assessmentRecordService;
    private final SupportLifecycleRecordService lifecycleRecordService;

    // ========== SUPPORT PROFILE MANAGEMENT ==========

    @PostMapping("/profiles")
    public ResponseEntity<StudentSupportProfileResponse> createSupportProfile(@RequestBody StudentSupportProfile profile) {
        StudentSupportProfile created = supportProfileService.createSupportProfile(profile);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToSupportProfileResponse(created));
    }

    @GetMapping("/profiles/{id}")
    public ResponseEntity<StudentSupportProfileResponse> getSupportProfile(@PathVariable String id) {
        StudentSupportProfile profile = supportProfileService.getSupportProfileById(id);
        return ResponseEntity.ok(mapToSupportProfileResponse(profile));
    }

    @GetMapping("/profiles/student/{studentId}")
    public ResponseEntity<List<StudentSupportProfileResponse>> getSupportProfilesByStudent(@PathVariable String studentId) {
        List<StudentSupportProfile> profiles = supportProfileService.getSupportProfilesByStudent(studentId);
        return ResponseEntity.ok(profiles.stream().map(this::mapToSupportProfileResponse).collect(Collectors.toList()));
    }

    @GetMapping("/profiles/type/{type}")
    public ResponseEntity<List<StudentSupportProfileResponse>> getSupportProfilesByType(@PathVariable String type) {
        List<StudentSupportProfile> profiles = supportProfileService.getSupportProfilesByType(type);
        return ResponseEntity.ok(profiles.stream().map(this::mapToSupportProfileResponse).collect(Collectors.toList()));
    }

    @GetMapping("/profiles/status/{status}")
    public ResponseEntity<List<StudentSupportProfileResponse>> getSupportProfilesByStatus(@PathVariable String status) {
        List<StudentSupportProfile> profiles = supportProfileService.getSupportProfilesByStatus(status);
        return ResponseEntity.ok(profiles.stream().map(this::mapToSupportProfileResponse).collect(Collectors.toList()));
    }

    @GetMapping("/profiles/identified")
    public ResponseEntity<List<StudentSupportProfileResponse>> getIdentifiedProfiles() {
        List<StudentSupportProfile> profiles = supportProfileService.getIdentifiedProfiles();
        return ResponseEntity.ok(profiles.stream().map(this::mapToSupportProfileResponse).collect(Collectors.toList()));
    }

    @PutMapping("/profiles/{id}")
    public ResponseEntity<StudentSupportProfileResponse> updateSupportProfile(@PathVariable String id, @RequestBody StudentSupportProfile profile) {
        StudentSupportProfile updated = supportProfileService.updateSupportProfile(id, profile);
        return ResponseEntity.ok(mapToSupportProfileResponse(updated));
    }

    @PostMapping("/profiles/{id}/close")
    public ResponseEntity<StudentSupportProfileResponse> closeSupportProfile(@PathVariable String id) {
        StudentSupportProfile closed = supportProfileService.closeSupportProfile(id);
        return ResponseEntity.ok(mapToSupportProfileResponse(closed));
    }

    @DeleteMapping("/profiles/{id}")
    public ResponseEntity<Void> deactivateSupportProfile(@PathVariable String id) {
        supportProfileService.deactivateSupportProfile(id);
        return ResponseEntity.noContent().build();
    }

    // ========== ACADEMIC SUPPORT MANAGEMENT ==========

    @PostMapping("/academic")
    public ResponseEntity<AcademicSupportRecordResponse> createAcademicSupportRecord(@RequestBody AcademicSupportRecord record) {
        AcademicSupportRecord created = academicSupportRecordService.createAcademicSupportRecord(record);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToAcademicSupportResponse(created));
    }

    @GetMapping("/academic/{id}")
    public ResponseEntity<AcademicSupportRecordResponse> getAcademicSupportRecord(@PathVariable String id) {
        AcademicSupportRecord record = academicSupportRecordService.getAcademicSupportRecordById(id);
        return ResponseEntity.ok(mapToAcademicSupportResponse(record));
    }

    @GetMapping("/academic/student/{studentId}")
    public ResponseEntity<List<AcademicSupportRecordResponse>> getAcademicSupportRecordsByStudent(@PathVariable String studentId) {
        List<AcademicSupportRecord> records = academicSupportRecordService.getAcademicSupportRecordsByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToAcademicSupportResponse).collect(Collectors.toList()));
    }

    @GetMapping("/academic/type/{type}")
    public ResponseEntity<List<AcademicSupportRecordResponse>> getAcademicSupportRecordsByType(@PathVariable String type) {
        List<AcademicSupportRecord> records = academicSupportRecordService.getAcademicSupportRecordsByType(type);
        return ResponseEntity.ok(records.stream().map(this::mapToAcademicSupportResponse).collect(Collectors.toList()));
    }

    @GetMapping("/academic/status/{status}")
    public ResponseEntity<List<AcademicSupportRecordResponse>> getAcademicSupportRecordsByStatus(@PathVariable String status) {
        List<AcademicSupportRecord> records = academicSupportRecordService.getAcademicSupportRecordsByStatus(status);
        return ResponseEntity.ok(records.stream().map(this::mapToAcademicSupportResponse).collect(Collectors.toList()));
    }

    @PutMapping("/academic/{id}")
    public ResponseEntity<AcademicSupportRecordResponse> updateAcademicSupportRecord(@PathVariable String id, @RequestBody AcademicSupportRecord record) {
        AcademicSupportRecord updated = academicSupportRecordService.updateAcademicSupportRecord(id, record);
        return ResponseEntity.ok(mapToAcademicSupportResponse(updated));
    }

    @PostMapping("/academic/{id}/complete")
    public ResponseEntity<AcademicSupportRecordResponse> completeAcademicSupport(
            @PathVariable String id,
            @RequestParam(required = false) String outcomeNotes) {
        AcademicSupportRecord completed = academicSupportRecordService.completeAcademicSupport(id, outcomeNotes);
        return ResponseEntity.ok(mapToAcademicSupportResponse(completed));
    }

    @DeleteMapping("/academic/{id}")
    public ResponseEntity<Void> deactivateAcademicSupportRecord(@PathVariable String id) {
        academicSupportRecordService.deactivateAcademicSupportRecord(id);
        return ResponseEntity.noContent().build();
    }

    // ========== WELLBEING MANAGEMENT ==========

    @PostMapping("/wellbeing")
    public ResponseEntity<WellbeingRecordResponse> createWellbeingRecord(@RequestBody WellbeingRecord record) {
        WellbeingRecord created = wellbeingRecordService.createWellbeingRecord(record);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToWellbeingResponse(created));
    }

    @GetMapping("/wellbeing/{id}")
    public ResponseEntity<WellbeingRecordResponse> getWellbeingRecord(@PathVariable String id) {
        WellbeingRecord record = wellbeingRecordService.getWellbeingRecordById(id);
        return ResponseEntity.ok(mapToWellbeingResponse(record));
    }

    @GetMapping("/wellbeing/student/{studentId}")
    public ResponseEntity<List<WellbeingRecordResponse>> getWellbeingRecordsByStudent(@PathVariable String studentId) {
        List<WellbeingRecord> records = wellbeingRecordService.getWellbeingRecordsByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToWellbeingResponse).collect(Collectors.toList()));
    }

    @GetMapping("/wellbeing/type/{type}")
    public ResponseEntity<List<WellbeingRecordResponse>> getWellbeingRecordsByType(@PathVariable String type) {
        List<WellbeingRecord> records = wellbeingRecordService.getWellbeingRecordsByType(type);
        return ResponseEntity.ok(records.stream().map(this::mapToWellbeingResponse).collect(Collectors.toList()));
    }

    @GetMapping("/wellbeing/critical")
    public ResponseEntity<List<WellbeingRecordResponse>> getCriticalCases() {
        List<WellbeingRecord> records = wellbeingRecordService.getCriticalCases();
        return ResponseEntity.ok(records.stream().map(this::mapToWellbeingResponse).collect(Collectors.toList()));
    }

    @PutMapping("/wellbeing/{id}")
    public ResponseEntity<WellbeingRecordResponse> updateWellbeingRecord(@PathVariable String id, @RequestBody WellbeingRecord record) {
        WellbeingRecord updated = wellbeingRecordService.updateWellbeingRecord(id, record);
        return ResponseEntity.ok(mapToWellbeingResponse(updated));
    }

    @PostMapping("/wellbeing/{id}/resolve")
    public ResponseEntity<WellbeingRecordResponse> resolveWellbeing(@PathVariable String id) {
        WellbeingRecord resolved = wellbeingRecordService.resolveWellbeing(id);
        return ResponseEntity.ok(mapToWellbeingResponse(resolved));
    }

    @DeleteMapping("/wellbeing/{id}")
    public ResponseEntity<Void> deactivateWellbeingRecord(@PathVariable String id) {
        wellbeingRecordService.deactivateWellbeingRecord(id);
        return ResponseEntity.noContent().build();
    }

    // ========== RESOURCE MANAGEMENT ==========

    @PostMapping("/resources")
    public ResponseEntity<ResourceRecordResponse> createResourceRecord(@RequestBody ResourceRecord record) {
        ResourceRecord created = resourceRecordService.createResourceRecord(record);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToResourceResponse(created));
    }

    @GetMapping("/resources/{id}")
    public ResponseEntity<ResourceRecordResponse> getResourceRecord(@PathVariable String id) {
        ResourceRecord record = resourceRecordService.getResourceRecordById(id);
        return ResponseEntity.ok(mapToResourceResponse(record));
    }

    @GetMapping("/resources/student/{studentId}")
    public ResponseEntity<List<ResourceRecordResponse>> getResourceRecordsByStudent(@PathVariable String studentId) {
        List<ResourceRecord> records = resourceRecordService.getResourceRecordsByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToResourceResponse).collect(Collectors.toList()));
    }

    @GetMapping("/resources/type/{type}")
    public ResponseEntity<List<ResourceRecordResponse>> getResourceRecordsByType(@PathVariable String type) {
        List<ResourceRecord> records = resourceRecordService.getResourceRecordsByType(type);
        return ResponseEntity.ok(records.stream().map(this::mapToResourceResponse).collect(Collectors.toList()));
    }

    @GetMapping("/resources/active")
    public ResponseEntity<List<ResourceRecordResponse>> getActiveResourcesByStudent(@RequestParam String studentId) {
        List<ResourceRecord> records = resourceRecordService.getActiveByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToResourceResponse).collect(Collectors.toList()));
    }

    @PutMapping("/resources/{id}")
    public ResponseEntity<ResourceRecordResponse> updateResourceRecord(@PathVariable String id, @RequestBody ResourceRecord record) {
        ResourceRecord updated = resourceRecordService.updateResourceRecord(id, record);
        return ResponseEntity.ok(mapToResourceResponse(updated));
    }

    @PostMapping("/resources/{id}/return")
    public ResponseEntity<ResourceRecordResponse> returnResource(@PathVariable String id) {
        ResourceRecord returned = resourceRecordService.returnResource(id);
        return ResponseEntity.ok(mapToResourceResponse(returned));
    }

    @DeleteMapping("/resources/{id}")
    public ResponseEntity<Void> deactivateResourceRecord(@PathVariable String id) {
        resourceRecordService.deactivateResourceRecord(id);
        return ResponseEntity.noContent().build();
    }

    // ========== ASSESSMENT MANAGEMENT ==========

    @PostMapping("/assessments")
    public ResponseEntity<SupportAssessmentRecordResponse> createAssessmentRecord(@RequestBody SupportAssessmentRecord record) {
        SupportAssessmentRecord created = assessmentRecordService.createAssessmentRecord(record);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToAssessmentResponse(created));
    }

    @GetMapping("/assessments/{id}")
    public ResponseEntity<SupportAssessmentRecordResponse> getAssessmentRecord(@PathVariable String id) {
        SupportAssessmentRecord record = assessmentRecordService.getAssessmentRecordById(id);
        return ResponseEntity.ok(mapToAssessmentResponse(record));
    }

    @GetMapping("/assessments/student/{studentId}")
    public ResponseEntity<List<SupportAssessmentRecordResponse>> getAssessmentRecordsByStudent(@PathVariable String studentId) {
        List<SupportAssessmentRecord> records = assessmentRecordService.getAssessmentRecordsByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToAssessmentResponse).collect(Collectors.toList()));
    }

    @GetMapping("/assessments/type/{type}")
    public ResponseEntity<List<SupportAssessmentRecordResponse>> getAssessmentRecordsByType(@PathVariable String type) {
        List<SupportAssessmentRecord> records = assessmentRecordService.getAssessmentRecordsByType(type);
        return ResponseEntity.ok(records.stream().map(this::mapToAssessmentResponse).collect(Collectors.toList()));
    }

    @GetMapping("/assessments/high-risk")
    public ResponseEntity<List<SupportAssessmentRecordResponse>> getHighRiskCases() {
        List<SupportAssessmentRecord> records = assessmentRecordService.getHighRiskCases();
        return ResponseEntity.ok(records.stream().map(this::mapToAssessmentResponse).collect(Collectors.toList()));
    }

    @PutMapping("/assessments/{id}")
    public ResponseEntity<SupportAssessmentRecordResponse> updateAssessmentRecord(@PathVariable String id, @RequestBody SupportAssessmentRecord record) {
        SupportAssessmentRecord updated = assessmentRecordService.updateAssessmentRecord(id, record);
        return ResponseEntity.ok(mapToAssessmentResponse(updated));
    }

    @PostMapping("/assessments/{id}/complete")
    public ResponseEntity<SupportAssessmentRecordResponse> completeAssessment(
            @PathVariable String id,
            @RequestParam(required = false) String notes,
            @RequestParam(required = false) String recommendations) {
        SupportAssessmentRecord completed = assessmentRecordService.completeAssessment(id, notes, recommendations);
        return ResponseEntity.ok(mapToAssessmentResponse(completed));
    }

    @DeleteMapping("/assessments/{id}")
    public ResponseEntity<Void> deactivateAssessmentRecord(@PathVariable String id) {
        assessmentRecordService.deactivateAssessmentRecord(id);
        return ResponseEntity.noContent().build();
    }

    // ========== LIFECYCLE MANAGEMENT ==========

    @PostMapping("/lifecycle")
    public ResponseEntity<SupportLifecycleRecordResponse> createLifecycleRecord(@RequestBody SupportLifecycleRecord record) {
        SupportLifecycleRecord created = lifecycleRecordService.createLifecycleRecord(record);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToLifecycleResponse(created));
    }

    @GetMapping("/lifecycle/{id}")
    public ResponseEntity<SupportLifecycleRecordResponse> getLifecycleRecord(@PathVariable String id) {
        SupportLifecycleRecord record = lifecycleRecordService.getLifecycleRecordById(id);
        return ResponseEntity.ok(mapToLifecycleResponse(record));
    }

    @GetMapping("/lifecycle/profile/{profileId}")
    public ResponseEntity<List<SupportLifecycleRecordResponse>> getLifecycleRecordsByProfile(@PathVariable String profileId) {
        List<SupportLifecycleRecord> records = lifecycleRecordService.getLifecycleRecordsByProfile(profileId);
        return ResponseEntity.ok(records.stream().map(this::mapToLifecycleResponse).collect(Collectors.toList()));
    }

    @GetMapping("/lifecycle/student/{studentId}")
    public ResponseEntity<List<SupportLifecycleRecordResponse>> getLifecycleRecordsByStudent(@PathVariable String studentId) {
        List<SupportLifecycleRecord> records = lifecycleRecordService.getLifecycleRecordsByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToLifecycleResponse).collect(Collectors.toList()));
    }

    @PutMapping("/lifecycle/{id}")
    public ResponseEntity<SupportLifecycleRecordResponse> updateLifecycleStatus(
            @PathVariable String id,
            @RequestParam String newStatus,
            @RequestParam(required = false) String reason,
            @RequestParam String changedBy) {
        SupportLifecycleRecord updated = lifecycleRecordService.updateLifecycleStatus(id, newStatus, reason, changedBy);
        return ResponseEntity.ok(mapToLifecycleResponse(updated));
    }

    @DeleteMapping("/lifecycle/{id}")
    public ResponseEntity<Void> deactivateLifecycleRecord(@PathVariable String id) {
        lifecycleRecordService.deactivateLifecycleRecord(id);
        return ResponseEntity.noContent().build();
    }

    // ========== MAPPING METHODS ==========

    private StudentSupportProfileResponse mapToSupportProfileResponse(StudentSupportProfile profile) {
        return StudentSupportProfileResponse.builder()
                .id(profile.getId())
                .studentId(profile.getStudentId())
                .supportType(profile.getSupportType())
                .supportStatus(profile.getSupportStatus())
                .supportLevel(profile.getSupportLevel())
                .primaryConcern(profile.getPrimaryConcern())
                .secondaryConcerns(profile.getSecondaryConcerns())
                .supportPreferences(profile.getSupportPreferences())
                .interventionHistory(profile.getInterventionHistory())
                .assignedCounselorId(profile.getAssignedCounselorId())
                .lastContactDate(profile.getLastContactDate())
                .nextFollowUp(profile.getNextFollowUp())
                .notes(profile.getNotes())
                .isActive(profile.getIsActive())
                .build();
    }

    private AcademicSupportRecordResponse mapToAcademicSupportResponse(AcademicSupportRecord record) {
        return AcademicSupportRecordResponse.builder()
                .id(record.getId())
                .supportProfileId(record.getSupportProfileId())
                .studentId(record.getStudentId())
                .supportType(record.getSupportType())
                .supportStatus(record.getSupportStatus())
                .subjectArea(record.getSubjectArea())
                .description(record.getDescription())
                .providerId(record.getProviderId())
                .scheduledDate(record.getScheduledDate())
                .completedDate(record.getCompletedDate())
                .frequency(record.getFrequency())
                .durationWeeks(record.getDurationWeeks())
                .outcomeNotes(record.getOutcomeNotes())
                .isActive(record.getIsActive())
                .build();
    }

    private WellbeingRecordResponse mapToWellbeingResponse(WellbeingRecord record) {
        return WellbeingRecordResponse.builder()
                .id(record.getId())
                .supportProfileId(record.getSupportProfileId())
                .studentId(record.getStudentId())
                .wellbeingType(record.getWellbeingType())
                .wellbeingStatus(record.getWellbeingStatus())
                .severityLevel(record.getSeverityLevel())
                .description(record.getDescription())
                .counselorId(record.getCounselorId())
                .sessionDate(record.getSessionDate())
                .nextSessionDate(record.getNextSessionDate())
                .treatmentPlan(record.getTreatmentPlan())
                .progressNotes(record.getProgressNotes())
                .isActive(record.getIsActive())
                .build();
    }

    private ResourceRecordResponse mapToResourceResponse(ResourceRecord record) {
        return ResourceRecordResponse.builder()
                .id(record.getId())
                .supportProfileId(record.getSupportProfileId())
                .studentId(record.getStudentId())
                .resourceType(record.getResourceType())
                .resourceName(record.getResourceName())
                .resourceStatus(record.getResourceStatus())
                .description(record.getDescription())
                .provider(record.getProvider())
                .allocatedDate(record.getAllocatedDate())
                .expiryDate(record.getExpiryDate())
                .utilizationRate(record.getUtilizationRate())
                .satisfactionScore(record.getSatisfactionScore())
                .notes(record.getNotes())
                .isActive(record.getIsActive())
                .build();
    }

    private SupportAssessmentRecordResponse mapToAssessmentResponse(SupportAssessmentRecord record) {
        return SupportAssessmentRecordResponse.builder()
                .id(record.getId())
                .supportProfileId(record.getSupportProfileId())
                .studentId(record.getStudentId())
                .assessmentType(record.getAssessmentType())
                .assessmentStatus(record.getAssessmentStatus())
                .riskLevel(record.getRiskLevel())
                .riskScore(record.getRiskScore())
                .assessmentNotes(record.getAssessmentNotes())
                .recommendations(record.getRecommendations())
                .assessedBy(record.getAssessedBy())
                .assessedAt(record.getAssessedAt())
                .isActive(record.getIsActive())
                .build();
    }

    private SupportLifecycleRecordResponse mapToLifecycleResponse(SupportLifecycleRecord record) {
        return SupportLifecycleRecordResponse.builder()
                .id(record.getId())
                .supportProfileId(record.getSupportProfileId())
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
