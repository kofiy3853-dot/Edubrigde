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
@RequestMapping("/api/v1/student-services/assessments")
@RequiredArgsConstructor
@Slf4j
public class StudentAssessmentController {

    private final AssessmentProfileService assessmentProfileService;
    private final PerformanceRecordService performanceRecordService;
    private final EvaluationRecordService evaluationRecordService;
    private final FeedbackRecordService feedbackRecordService;
    private final CompetencyRecordService competencyRecordService;
    private final AssessmentLifecycleRecordService lifecycleRecordService;

    // ========== ASSESSMENT PROFILE MANAGEMENT ==========

    @PostMapping("/profiles")
    public ResponseEntity<AssessmentProfileResponse> createAssessmentProfile(@RequestBody AssessmentProfile profile) {
        AssessmentProfile created = assessmentProfileService.createAssessmentProfile(profile);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToAssessmentProfileResponse(created));
    }

    @GetMapping("/profiles/{id}")
    public ResponseEntity<AssessmentProfileResponse> getAssessmentProfile(@PathVariable String id) {
        AssessmentProfile profile = assessmentProfileService.getAssessmentProfileById(id);
        return ResponseEntity.ok(mapToAssessmentProfileResponse(profile));
    }

    @GetMapping("/profiles/student/{studentId}")
    public ResponseEntity<List<AssessmentProfileResponse>> getAssessmentProfilesByStudent(@PathVariable String studentId) {
        List<AssessmentProfile> profiles = assessmentProfileService.getAssessmentProfilesByStudent(studentId);
        return ResponseEntity.ok(profiles.stream().map(this::mapToAssessmentProfileResponse).collect(Collectors.toList()));
    }

    @GetMapping("/profiles/type/{type}")
    public ResponseEntity<List<AssessmentProfileResponse>> getAssessmentProfilesByType(@PathVariable String type) {
        List<AssessmentProfile> profiles = assessmentProfileService.getAssessmentProfilesByType(type);
        return ResponseEntity.ok(profiles.stream().map(this::mapToAssessmentProfileResponse).collect(Collectors.toList()));
    }

    @GetMapping("/profiles/status/{status}")
    public ResponseEntity<List<AssessmentProfileResponse>> getAssessmentProfilesByStatus(@PathVariable String status) {
        List<AssessmentProfile> profiles = assessmentProfileService.getAssessmentProfilesByStatus(status);
        return ResponseEntity.ok(profiles.stream().map(this::mapToAssessmentProfileResponse).collect(Collectors.toList()));
    }

    @GetMapping("/profiles/planning")
    public ResponseEntity<List<AssessmentProfileResponse>> getPlanningProfiles() {
        List<AssessmentProfile> profiles = assessmentProfileService.getPlanningProfiles();
        return ResponseEntity.ok(profiles.stream().map(this::mapToAssessmentProfileResponse).collect(Collectors.toList()));
    }

    @PutMapping("/profiles/{id}")
    public ResponseEntity<AssessmentProfileResponse> updateAssessmentProfile(@PathVariable String id, @RequestBody AssessmentProfile profile) {
        AssessmentProfile updated = assessmentProfileService.updateAssessmentProfile(id, profile);
        return ResponseEntity.ok(mapToAssessmentProfileResponse(updated));
    }

    @PostMapping("/profiles/{id}/complete")
    public ResponseEntity<AssessmentProfileResponse> completeAssessmentProfile(@PathVariable String id) {
        AssessmentProfile completed = assessmentProfileService.completeAssessmentProfile(id);
        return ResponseEntity.ok(mapToAssessmentProfileResponse(completed));
    }

    @DeleteMapping("/profiles/{id}")
    public ResponseEntity<Void> deactivateAssessmentProfile(@PathVariable String id) {
        assessmentProfileService.deactivateAssessmentProfile(id);
        return ResponseEntity.noContent().build();
    }

    // ========== PERFORMANCE MANAGEMENT ==========

    @PostMapping("/performance")
    public ResponseEntity<PerformanceRecordResponse> createPerformanceRecord(@RequestBody PerformanceRecord record) {
        PerformanceRecord created = performanceRecordService.createPerformanceRecord(record);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToPerformanceResponse(created));
    }

    @GetMapping("/performance/{id}")
    public ResponseEntity<PerformanceRecordResponse> getPerformanceRecord(@PathVariable String id) {
        PerformanceRecord record = performanceRecordService.getPerformanceRecordById(id);
        return ResponseEntity.ok(mapToPerformanceResponse(record));
    }

    @GetMapping("/performance/student/{studentId}")
    public ResponseEntity<List<PerformanceRecordResponse>> getPerformanceRecordsByStudent(@PathVariable String studentId) {
        List<PerformanceRecord> records = performanceRecordService.getPerformanceRecordsByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToPerformanceResponse).collect(Collectors.toList()));
    }

    @GetMapping("/performance/type/{type}")
    public ResponseEntity<List<PerformanceRecordResponse>> getPerformanceRecordsByType(@PathVariable String type) {
        List<PerformanceRecord> records = performanceRecordService.getPerformanceRecordsByType(type);
        return ResponseEntity.ok(records.stream().map(this::mapToPerformanceResponse).collect(Collectors.toList()));
    }

    @GetMapping("/performance/latest")
    public ResponseEntity<List<PerformanceRecordResponse>> getLatestPerformanceByStudent(@RequestParam String studentId) {
        List<PerformanceRecord> records = performanceRecordService.getLatestByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToPerformanceResponse).collect(Collectors.toList()));
    }

    @PutMapping("/performance/{id}")
    public ResponseEntity<PerformanceRecordResponse> updatePerformanceRecord(@PathVariable String id, @RequestBody PerformanceRecord record) {
        PerformanceRecord updated = performanceRecordService.updatePerformanceRecord(id, record);
        return ResponseEntity.ok(mapToPerformanceResponse(updated));
    }

    @PostMapping("/performance/{id}/verify")
    public ResponseEntity<PerformanceRecordResponse> verifyPerformance(@PathVariable String id) {
        PerformanceRecord verified = performanceRecordService.verifyPerformance(id);
        return ResponseEntity.ok(mapToPerformanceResponse(verified));
    }

    @DeleteMapping("/performance/{id}")
    public ResponseEntity<Void> deactivatePerformanceRecord(@PathVariable String id) {
        performanceRecordService.deactivatePerformanceRecord(id);
        return ResponseEntity.noContent().build();
    }

    // ========== EVALUATION MANAGEMENT ==========

    @PostMapping("/evaluations")
    public ResponseEntity<EvaluationRecordResponse> createEvaluationRecord(@RequestBody EvaluationRecord record) {
        EvaluationRecord created = evaluationRecordService.createEvaluationRecord(record);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToEvaluationResponse(created));
    }

    @GetMapping("/evaluations/{id}")
    public ResponseEntity<EvaluationRecordResponse> getEvaluationRecord(@PathVariable String id) {
        EvaluationRecord record = evaluationRecordService.getEvaluationRecordById(id);
        return ResponseEntity.ok(mapToEvaluationResponse(record));
    }

    @GetMapping("/evaluations/student/{studentId}")
    public ResponseEntity<List<EvaluationRecordResponse>> getEvaluationRecordsByStudent(@PathVariable String studentId) {
        List<EvaluationRecord> records = evaluationRecordService.getEvaluationRecordsByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToEvaluationResponse).collect(Collectors.toList()));
    }

    @GetMapping("/evaluations/type/{type}")
    public ResponseEntity<List<EvaluationRecordResponse>> getEvaluationRecordsByType(@PathVariable String type) {
        List<EvaluationRecord> records = evaluationRecordService.getEvaluationRecordsByType(type);
        return ResponseEntity.ok(records.stream().map(this::mapToEvaluationResponse).collect(Collectors.toList()));
    }

    @GetMapping("/evaluations/pending")
    public ResponseEntity<List<EvaluationRecordResponse>> getPendingEvaluationsByStudent(@RequestParam String studentId) {
        List<EvaluationRecord> records = evaluationRecordService.getPendingByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToEvaluationResponse).collect(Collectors.toList()));
    }

    @PutMapping("/evaluations/{id}")
    public ResponseEntity<EvaluationRecordResponse> updateEvaluationRecord(@PathVariable String id, @RequestBody EvaluationRecord record) {
        EvaluationRecord updated = evaluationRecordService.updateEvaluationRecord(id, record);
        return ResponseEntity.ok(mapToEvaluationResponse(updated));
    }

    @PostMapping("/evaluations/{id}/complete")
    public ResponseEntity<EvaluationRecordResponse> completeEvaluation(
            @PathVariable String id,
            @RequestParam(required = false) String summary,
            @RequestParam(required = false) String recommendations) {
        EvaluationRecord completed = evaluationRecordService.completeEvaluation(id, summary, recommendations);
        return ResponseEntity.ok(mapToEvaluationResponse(completed));
    }

    @DeleteMapping("/evaluations/{id}")
    public ResponseEntity<Void> deactivateEvaluationRecord(@PathVariable String id) {
        evaluationRecordService.deactivateEvaluationRecord(id);
        return ResponseEntity.noContent().build();
    }

    // ========== FEEDBACK MANAGEMENT ==========

    @PostMapping("/feedback")
    public ResponseEntity<FeedbackRecordResponse> createFeedbackRecord(@RequestBody FeedbackRecord record) {
        FeedbackRecord created = feedbackRecordService.createFeedbackRecord(record);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToFeedbackResponse(created));
    }

    @GetMapping("/feedback/{id}")
    public ResponseEntity<FeedbackRecordResponse> getFeedbackRecord(@PathVariable String id) {
        FeedbackRecord record = feedbackRecordService.getFeedbackRecordById(id);
        return ResponseEntity.ok(mapToFeedbackResponse(record));
    }

    @GetMapping("/feedback/student/{studentId}")
    public ResponseEntity<List<FeedbackRecordResponse>> getFeedbackRecordsByStudent(@PathVariable String studentId) {
        List<FeedbackRecord> records = feedbackRecordService.getFeedbackRecordsByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToFeedbackResponse).collect(Collectors.toList()));
    }

    @GetMapping("/feedback/type/{type}")
    public ResponseEntity<List<FeedbackRecordResponse>> getFeedbackRecordsByType(@PathVariable String type) {
        List<FeedbackRecord> records = feedbackRecordService.getFeedbackRecordsByType(type);
        return ResponseEntity.ok(records.stream().map(this::mapToFeedbackResponse).collect(Collectors.toList()));
    }

    @GetMapping("/feedback/submitted")
    public ResponseEntity<List<FeedbackRecordResponse>> getSubmittedFeedbackByStudent(@RequestParam String studentId) {
        List<FeedbackRecord> records = feedbackRecordService.getSubmittedByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToFeedbackResponse).collect(Collectors.toList()));
    }

    @PutMapping("/feedback/{id}")
    public ResponseEntity<FeedbackRecordResponse> updateFeedbackRecord(@PathVariable String id, @RequestBody FeedbackRecord record) {
        FeedbackRecord updated = feedbackRecordService.updateFeedbackRecord(id, record);
        return ResponseEntity.ok(mapToFeedbackResponse(updated));
    }

    @PostMapping("/feedback/{id}/review")
    public ResponseEntity<FeedbackRecordResponse> reviewFeedback(@PathVariable String id) {
        FeedbackRecord reviewed = feedbackRecordService.reviewFeedback(id);
        return ResponseEntity.ok(mapToFeedbackResponse(reviewed));
    }

    @DeleteMapping("/feedback/{id}")
    public ResponseEntity<Void> deactivateFeedbackRecord(@PathVariable String id) {
        feedbackRecordService.deactivateFeedbackRecord(id);
        return ResponseEntity.noContent().build();
    }

    // ========== COMPETENCY MANAGEMENT ==========

    @PostMapping("/competencies")
    public ResponseEntity<CompetencyRecordResponse> createCompetencyRecord(@RequestBody CompetencyRecord record) {
        CompetencyRecord created = competencyRecordService.createCompetencyRecord(record);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToCompetencyResponse(created));
    }

    @GetMapping("/competencies/{id}")
    public ResponseEntity<CompetencyRecordResponse> getCompetencyRecord(@PathVariable String id) {
        CompetencyRecord record = competencyRecordService.getCompetencyRecordById(id);
        return ResponseEntity.ok(mapToCompetencyResponse(record));
    }

    @GetMapping("/competencies/student/{studentId}")
    public ResponseEntity<List<CompetencyRecordResponse>> getCompetencyRecordsByStudent(@PathVariable String studentId) {
        List<CompetencyRecord> records = competencyRecordService.getCompetencyRecordsByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToCompetencyResponse).collect(Collectors.toList()));
    }

    @GetMapping("/competencies/category/{category}")
    public ResponseEntity<List<CompetencyRecordResponse>> getCompetencyRecordsByCategory(@PathVariable String category) {
        List<CompetencyRecord> records = competencyRecordService.getCompetencyRecordsByCategory(category);
        return ResponseEntity.ok(records.stream().map(this::mapToCompetencyResponse).collect(Collectors.toList()));
    }

    @GetMapping("/competencies/expert")
    public ResponseEntity<List<CompetencyRecordResponse>> getExpertCompetenciesByStudent(@RequestParam String studentId) {
        List<CompetencyRecord> records = competencyRecordService.getExpertByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToCompetencyResponse).collect(Collectors.toList()));
    }

    @PutMapping("/competencies/{id}")
    public ResponseEntity<CompetencyRecordResponse> updateCompetencyRecord(@PathVariable String id, @RequestBody CompetencyRecord record) {
        CompetencyRecord updated = competencyRecordService.updateCompetencyRecord(id, record);
        return ResponseEntity.ok(mapToCompetencyResponse(updated));
    }

    @PostMapping("/competencies/{id}/assess")
    public ResponseEntity<CompetencyRecordResponse> assessCompetency(
            @PathVariable String id,
            @RequestParam String proficiencyLevel,
            @RequestParam(required = false) String evidence) {
        CompetencyRecord assessed = competencyRecordService.assessCompetency(id, proficiencyLevel, evidence);
        return ResponseEntity.ok(mapToCompetencyResponse(assessed));
    }

    @DeleteMapping("/competencies/{id}")
    public ResponseEntity<Void> deactivateCompetencyRecord(@PathVariable String id) {
        competencyRecordService.deactivateCompetencyRecord(id);
        return ResponseEntity.noContent().build();
    }

    // ========== LIFECYCLE MANAGEMENT ==========

    @PostMapping("/lifecycle")
    public ResponseEntity<AssessmentLifecycleRecordResponse> createLifecycleRecord(@RequestBody AssessmentLifecycleRecord record) {
        AssessmentLifecycleRecord created = lifecycleRecordService.createLifecycleRecord(record);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToLifecycleResponse(created));
    }

    @GetMapping("/lifecycle/{id}")
    public ResponseEntity<AssessmentLifecycleRecordResponse> getLifecycleRecord(@PathVariable String id) {
        AssessmentLifecycleRecord record = lifecycleRecordService.getLifecycleRecordById(id);
        return ResponseEntity.ok(mapToLifecycleResponse(record));
    }

    @GetMapping("/lifecycle/profile/{profileId}")
    public ResponseEntity<List<AssessmentLifecycleRecordResponse>> getLifecycleRecordsByProfile(@PathVariable String profileId) {
        List<AssessmentLifecycleRecord> records = lifecycleRecordService.getLifecycleRecordsByProfile(profileId);
        return ResponseEntity.ok(records.stream().map(this::mapToLifecycleResponse).collect(Collectors.toList()));
    }

    @GetMapping("/lifecycle/student/{studentId}")
    public ResponseEntity<List<AssessmentLifecycleRecordResponse>> getLifecycleRecordsByStudent(@PathVariable String studentId) {
        List<AssessmentLifecycleRecord> records = lifecycleRecordService.getLifecycleRecordsByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToLifecycleResponse).collect(Collectors.toList()));
    }

    @PutMapping("/lifecycle/{id}")
    public ResponseEntity<AssessmentLifecycleRecordResponse> updateLifecycleStatus(
            @PathVariable String id,
            @RequestParam String newStatus,
            @RequestParam(required = false) String reason,
            @RequestParam String changedBy) {
        AssessmentLifecycleRecord updated = lifecycleRecordService.updateLifecycleStatus(id, newStatus, reason, changedBy);
        return ResponseEntity.ok(mapToLifecycleResponse(updated));
    }

    @DeleteMapping("/lifecycle/{id}")
    public ResponseEntity<Void> deactivateLifecycleRecord(@PathVariable String id) {
        lifecycleRecordService.deactivateLifecycleRecord(id);
        return ResponseEntity.noContent().build();
    }

    // ========== MAPPING METHODS ==========

    private AssessmentProfileResponse mapToAssessmentProfileResponse(AssessmentProfile profile) {
        return AssessmentProfileResponse.builder()
                .id(profile.getId())
                .studentId(profile.getStudentId())
                .assessmentType(profile.getAssessmentType())
                .assessmentStatus(profile.getAssessmentStatus())
                .assessmentPeriod(profile.getAssessmentPeriod())
                .targetScore(profile.getTargetScore())
                .currentScore(profile.getCurrentScore())
                .assessmentCriteria(profile.getAssessmentCriteria())
                .assessmentNotes(profile.getAssessmentNotes())
                .nextAssessmentDate(profile.getNextAssessmentDate())
                .lastAssessmentDate(profile.getLastAssessmentDate())
                .assessorId(profile.getAssessorId())
                .isActive(profile.getIsActive())
                .build();
    }

    private PerformanceRecordResponse mapToPerformanceResponse(PerformanceRecord record) {
        return PerformanceRecordResponse.builder()
                .id(record.getId())
                .assessmentProfileId(record.getAssessmentProfileId())
                .studentId(record.getStudentId())
                .performanceType(record.getPerformanceType())
                .performanceStatus(record.getPerformanceStatus())
                .subjectArea(record.getSubjectArea())
                .score(record.getScore())
                .maxScore(record.getMaxScore())
                .percentage(record.getPercentage())
                .grade(record.getGrade())
                .rank(record.getRank())
                .recordedDate(record.getRecordedDate())
                .notes(record.getNotes())
                .isActive(record.getIsActive())
                .build();
    }

    private EvaluationRecordResponse mapToEvaluationResponse(EvaluationRecord record) {
        return EvaluationRecordResponse.builder()
                .id(record.getId())
                .assessmentProfileId(record.getAssessmentProfileId())
                .studentId(record.getStudentId())
                .evaluationType(record.getEvaluationType())
                .evaluationStatus(record.getEvaluationStatus())
                .overallScore(record.getOverallScore())
                .evaluationSummary(record.getEvaluationSummary())
                .strengths(record.getStrengths())
                .areasForImprovement(record.getAreasForImprovement())
                .recommendations(record.getRecommendations())
                .evaluatorId(record.getEvaluatorId())
                .evaluatedAt(record.getEvaluatedAt())
                .isActive(record.getIsActive())
                .build();
    }

    private FeedbackRecordResponse mapToFeedbackResponse(FeedbackRecord record) {
        return FeedbackRecordResponse.builder()
                .id(record.getId())
                .assessmentProfileId(record.getAssessmentProfileId())
                .studentId(record.getStudentId())
                .feedbackType(record.getFeedbackType())
                .feedbackStatus(record.getFeedbackStatus())
                .feedbackCategory(record.getFeedbackCategory())
                .rating(record.getRating())
                .feedbackContent(record.getFeedbackContent())
                .actionItems(record.getActionItems())
                .feedbackFromId(record.getFeedbackFromId())
                .submittedAt(record.getSubmittedAt())
                .isActive(record.getIsActive())
                .build();
    }

    private CompetencyRecordResponse mapToCompetencyResponse(CompetencyRecord record) {
        return CompetencyRecordResponse.builder()
                .id(record.getId())
                .assessmentProfileId(record.getAssessmentProfileId())
                .studentId(record.getStudentId())
                .competencyName(record.getCompetencyName())
                .competencyCategory(record.getCompetencyCategory())
                .proficiencyLevel(record.getProficiencyLevel())
                .proficiencyScore(record.getProficiencyScore())
                .targetLevel(record.getTargetLevel())
                .evidence(record.getEvidence())
                .lastAssessedDate(record.getLastAssessedDate())
                .nextAssessmentDate(record.getNextAssessmentDate())
                .notes(record.getNotes())
                .isActive(record.getIsActive())
                .build();
    }

    private AssessmentLifecycleRecordResponse mapToLifecycleResponse(AssessmentLifecycleRecord record) {
        return AssessmentLifecycleRecordResponse.builder()
                .id(record.getId())
                .assessmentProfileId(record.getAssessmentProfileId())
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
