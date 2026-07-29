package com.edubridge.learningservices.controllers;

import com.edubridge.learningservices.dto.*;
import com.edubridge.learningservices.entities.*;
import com.edubridge.learningservices.services.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/learning-services/academic-growth")
@RequiredArgsConstructor
@Slf4j
public class AcademicGrowthController {

    private final GrowthProfileService growthProfileService;
    private final LearningProgressRecordService learningProgressRecordService;
    private final PerformanceRecordService performanceRecordService;
    private final SuccessPlanRecordService successPlanRecordService;
    private final GrowthAssessmentRecordService growthAssessmentRecordService;
    private final GrowthLifecycleRecordService growthLifecycleRecordService;

    // ========== GROWTH PROFILE MANAGEMENT ==========

    @PostMapping("/profiles")
    public ResponseEntity<GrowthProfileResponse> createGrowthProfile(@RequestBody GrowthProfile profile) {
        GrowthProfile created = growthProfileService.createGrowthProfile(profile);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToGrowthProfileResponse(created));
    }

    @GetMapping("/profiles/{id}")
    public ResponseEntity<GrowthProfileResponse> getGrowthProfile(@PathVariable String id) {
        GrowthProfile profile = growthProfileService.getGrowthProfileById(id);
        return ResponseEntity.ok(mapToGrowthProfileResponse(profile));
    }

    @GetMapping("/profiles/student/{studentId}")
    public ResponseEntity<List<GrowthProfileResponse>> getGrowthProfilesByStudent(@PathVariable String studentId) {
        List<GrowthProfile> profiles = growthProfileService.getGrowthProfilesByStudent(studentId);
        return ResponseEntity.ok(profiles.stream().map(this::mapToGrowthProfileResponse).collect(Collectors.toList()));
    }

    @GetMapping("/profiles/status/{status}")
    public ResponseEntity<List<GrowthProfileResponse>> getGrowthProfilesByStatus(@PathVariable String status) {
        List<GrowthProfile> profiles = growthProfileService.getGrowthProfilesByStatus(status);
        return ResponseEntity.ok(profiles.stream().map(this::mapToGrowthProfileResponse).collect(Collectors.toList()));
    }

    @GetMapping("/profiles/standing/{standing}")
    public ResponseEntity<List<GrowthProfileResponse>> getGrowthProfilesByStanding(@PathVariable String standing) {
        List<GrowthProfile> profiles = growthProfileService.getGrowthProfilesByStanding(standing);
        return ResponseEntity.ok(profiles.stream().map(this::mapToGrowthProfileResponse).collect(Collectors.toList()));
    }

    @GetMapping("/profiles/high-readiness")
    public ResponseEntity<List<GrowthProfileResponse>> getHighReadinessStudents(@RequestParam Double minScore) {
        List<GrowthProfile> profiles = growthProfileService.getHighReadinessStudents(minScore);
        return ResponseEntity.ok(profiles.stream().map(this::mapToGrowthProfileResponse).collect(Collectors.toList()));
    }

    @PutMapping("/profiles/{id}")
    public ResponseEntity<GrowthProfileResponse> updateGrowthProfile(@PathVariable String id, @RequestBody GrowthProfile profile) {
        GrowthProfile updated = growthProfileService.updateGrowthProfile(id, profile);
        return ResponseEntity.ok(mapToGrowthProfileResponse(updated));
    }

    @PostMapping("/profiles/{id}/review")
    public ResponseEntity<GrowthProfileResponse> reviewGrowthProfile(@PathVariable String id) {
        GrowthProfile reviewed = growthProfileService.reviewGrowthProfile(id);
        return ResponseEntity.ok(mapToGrowthProfileResponse(reviewed));
    }

    @DeleteMapping("/profiles/{id}")
    public ResponseEntity<Void> deactivateGrowthProfile(@PathVariable String id) {
        growthProfileService.deactivateGrowthProfile(id);
        return ResponseEntity.noContent().build();
    }

    // ========== LEARNING PROGRESS MANAGEMENT ==========

    @PostMapping("/learning-progress")
    public ResponseEntity<LearningProgressRecordResponse> createLearningProgressRecord(@RequestBody LearningProgressRecord record) {
        LearningProgressRecord created = learningProgressRecordService.createLearningProgressRecord(record);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToLearningProgressResponse(created));
    }

    @GetMapping("/learning-progress/{id}")
    public ResponseEntity<LearningProgressRecordResponse> getLearningProgressRecord(@PathVariable String id) {
        LearningProgressRecord record = learningProgressRecordService.getLearningProgressRecordById(id);
        return ResponseEntity.ok(mapToLearningProgressResponse(record));
    }

    @GetMapping("/learning-progress/student/{studentId}")
    public ResponseEntity<List<LearningProgressRecordResponse>> getLearningProgressByStudent(@PathVariable String studentId) {
        List<LearningProgressRecord> records = learningProgressRecordService.getLearningProgressRecordsByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToLearningProgressResponse).collect(Collectors.toList()));
    }

    @GetMapping("/learning-progress/subject/{subject}")
    public ResponseEntity<List<LearningProgressRecordResponse>> getLearningProgressBySubject(@PathVariable String subject) {
        List<LearningProgressRecord> records = learningProgressRecordService.getLearningProgressRecordsBySubject(subject);
        return ResponseEntity.ok(records.stream().map(this::mapToLearningProgressResponse).collect(Collectors.toList()));
    }

    @GetMapping("/learning-progress/mastery-achieved")
    public ResponseEntity<List<LearningProgressRecordResponse>> getMasteryAchieved() {
        List<LearningProgressRecord> records = learningProgressRecordService.getMasteryAchieved();
        return ResponseEntity.ok(records.stream().map(this::mapToLearningProgressResponse).collect(Collectors.toList()));
    }

    @PutMapping("/learning-progress/{id}")
    public ResponseEntity<LearningProgressRecordResponse> updateLearningProgress(@PathVariable String id, @RequestBody LearningProgressRecord record) {
        LearningProgressRecord updated = learningProgressRecordService.updateLearningProgressRecord(id, record);
        return ResponseEntity.ok(mapToLearningProgressResponse(updated));
    }

    @PostMapping("/learning-progress/{id}/complete-mastery")
    public ResponseEntity<LearningProgressRecordResponse> completeMastery(@PathVariable String id) {
        LearningProgressRecord completed = learningProgressRecordService.completeMastery(id);
        return ResponseEntity.ok(mapToLearningProgressResponse(completed));
    }

    @DeleteMapping("/learning-progress/{id}")
    public ResponseEntity<Void> deactivateLearningProgress(@PathVariable String id) {
        learningProgressRecordService.deactivateLearningProgressRecord(id);
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
    public ResponseEntity<List<PerformanceRecordResponse>> getPerformanceByStudent(@PathVariable String studentId) {
        List<PerformanceRecord> records = performanceRecordService.getPerformanceRecordsByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToPerformanceResponse).collect(Collectors.toList()));
    }

    @GetMapping("/performance/subject/{subject}")
    public ResponseEntity<List<PerformanceRecordResponse>> getPerformanceBySubject(@PathVariable String subject) {
        List<PerformanceRecord> records = performanceRecordService.getPerformanceRecordsBySubject(subject);
        return ResponseEntity.ok(records.stream().map(this::mapToPerformanceResponse).collect(Collectors.toList()));
    }

    @GetMapping("/performance/average/{studentId}")
    public ResponseEntity<Double> getAverageScoreByStudent(@PathVariable String studentId) {
        Double average = performanceRecordService.getAverageScoreByStudent(studentId);
        return ResponseEntity.ok(average);
    }

    @PutMapping("/performance/{id}")
    public ResponseEntity<PerformanceRecordResponse> updatePerformance(@PathVariable String id, @RequestBody PerformanceRecord record) {
        PerformanceRecord updated = performanceRecordService.updatePerformanceRecord(id, record);
        return ResponseEntity.ok(mapToPerformanceResponse(updated));
    }

    @PostMapping("/performance/{id}/grade")
    public ResponseEntity<PerformanceRecordResponse> gradePerformance(
            @PathVariable String id,
            @RequestParam Double scoreEarned,
            @RequestParam Double scorePossible,
            @RequestParam String grade,
            @RequestParam(required = false) String feedback,
            @RequestParam String gradedBy) {
        PerformanceRecord graded = performanceRecordService.gradePerformanceRecord(id, scoreEarned, scorePossible, grade, feedback, gradedBy);
        return ResponseEntity.ok(mapToPerformanceResponse(graded));
    }

    @DeleteMapping("/performance/{id}")
    public ResponseEntity<Void> deactivatePerformance(@PathVariable String id) {
        performanceRecordService.deactivatePerformanceRecord(id);
        return ResponseEntity.noContent().build();
    }

    // ========== SUCCESS PLANNING MANAGEMENT ==========

    @PostMapping("/success-plans")
    public ResponseEntity<SuccessPlanRecordResponse> createSuccessPlan(@RequestBody SuccessPlanRecord record) {
        SuccessPlanRecord created = successPlanRecordService.createSuccessPlanRecord(record);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToSuccessPlanResponse(created));
    }

    @GetMapping("/success-plans/{id}")
    public ResponseEntity<SuccessPlanRecordResponse> getSuccessPlan(@PathVariable String id) {
        SuccessPlanRecord record = successPlanRecordService.getSuccessPlanRecordById(id);
        return ResponseEntity.ok(mapToSuccessPlanResponse(record));
    }

    @GetMapping("/success-plans/student/{studentId}")
    public ResponseEntity<List<SuccessPlanRecordResponse>> getSuccessPlansByStudent(@PathVariable String studentId) {
        List<SuccessPlanRecord> records = successPlanRecordService.getSuccessPlanRecordsByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToSuccessPlanResponse).collect(Collectors.toList()));
    }

    @GetMapping("/success-plans/type/{type}")
    public ResponseEntity<List<SuccessPlanRecordResponse>> getSuccessPlansByType(@PathVariable String type) {
        List<SuccessPlanRecord> records = successPlanRecordService.getSuccessPlanRecordsByType(type);
        return ResponseEntity.ok(records.stream().map(this::mapToSuccessPlanResponse).collect(Collectors.toList()));
    }

    @GetMapping("/success-plans/overdue")
    public ResponseEntity<List<SuccessPlanRecordResponse>> getOverduePlans() {
        List<SuccessPlanRecord> records = successPlanRecordService.getOverduePlans();
        return ResponseEntity.ok(records.stream().map(this::mapToSuccessPlanResponse).collect(Collectors.toList()));
    }

    @PutMapping("/success-plans/{id}")
    public ResponseEntity<SuccessPlanRecordResponse> updateSuccessPlan(@PathVariable String id, @RequestBody SuccessPlanRecord record) {
        SuccessPlanRecord updated = successPlanRecordService.updateSuccessPlanRecord(id, record);
        return ResponseEntity.ok(mapToSuccessPlanResponse(updated));
    }

    @PostMapping("/success-plans/{id}/complete")
    public ResponseEntity<SuccessPlanRecordResponse> completeSuccessPlan(@PathVariable String id) {
        SuccessPlanRecord completed = successPlanRecordService.completeSuccessPlanRecord(id);
        return ResponseEntity.ok(mapToSuccessPlanResponse(completed));
    }

    @DeleteMapping("/success-plans/{id}")
    public ResponseEntity<Void> deactivateSuccessPlan(@PathVariable String id) {
        successPlanRecordService.deactivateSuccessPlanRecord(id);
        return ResponseEntity.noContent().build();
    }

    // ========== GROWTH ASSESSMENT MANAGEMENT ==========

    @PostMapping("/assessments")
    public ResponseEntity<GrowthAssessmentRecordResponse> createGrowthAssessment(@RequestBody GrowthAssessmentRecord record) {
        GrowthAssessmentRecord created = growthAssessmentRecordService.createGrowthAssessmentRecord(record);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToGrowthAssessmentResponse(created));
    }

    @GetMapping("/assessments/{id}")
    public ResponseEntity<GrowthAssessmentRecordResponse> getGrowthAssessment(@PathVariable String id) {
        GrowthAssessmentRecord record = growthAssessmentRecordService.getGrowthAssessmentRecordById(id);
        return ResponseEntity.ok(mapToGrowthAssessmentResponse(record));
    }

    @GetMapping("/assessments/student/{studentId}")
    public ResponseEntity<List<GrowthAssessmentRecordResponse>> getGrowthAssessmentsByStudent(@PathVariable String studentId) {
        List<GrowthAssessmentRecord> records = growthAssessmentRecordService.getGrowthAssessmentRecordsByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToGrowthAssessmentResponse).collect(Collectors.toList()));
    }

    @GetMapping("/assessments/domain/{domain}")
    public ResponseEntity<List<GrowthAssessmentRecordResponse>> getGrowthAssessmentsByDomain(@PathVariable String domain) {
        List<GrowthAssessmentRecord> records = growthAssessmentRecordService.getGrowthAssessmentRecordsByDomain(domain);
        return ResponseEntity.ok(records.stream().map(this::mapToGrowthAssessmentResponse).collect(Collectors.toList()));
    }

    @GetMapping("/assessments/passed/{studentId}")
    public ResponseEntity<List<GrowthAssessmentRecordResponse>> getPassedByStudent(@PathVariable String studentId) {
        List<GrowthAssessmentRecord> records = growthAssessmentRecordService.getPassedByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToGrowthAssessmentResponse).collect(Collectors.toList()));
    }

    @PutMapping("/assessments/{id}")
    public ResponseEntity<GrowthAssessmentRecordResponse> updateGrowthAssessment(@PathVariable String id, @RequestBody GrowthAssessmentRecord record) {
        GrowthAssessmentRecord updated = growthAssessmentRecordService.updateGrowthAssessmentRecord(id, record);
        return ResponseEntity.ok(mapToGrowthAssessmentResponse(updated));
    }

    @PostMapping("/assessments/{id}/grade")
    public ResponseEntity<GrowthAssessmentRecordResponse> gradeGrowthAssessment(
            @PathVariable String id,
            @RequestParam Double scoreEarned,
            @RequestParam Double scorePossible,
            @RequestParam String grade,
            @RequestParam String competencyLevel,
            @RequestParam(required = false) String feedback,
            @RequestParam String assessedBy) {
        GrowthAssessmentRecord graded = growthAssessmentRecordService.gradeGrowthAssessmentRecord(id, scoreEarned, scorePossible, grade, competencyLevel, feedback, assessedBy);
        return ResponseEntity.ok(mapToGrowthAssessmentResponse(graded));
    }

    @DeleteMapping("/assessments/{id}")
    public ResponseEntity<Void> deactivateGrowthAssessment(@PathVariable String id) {
        growthAssessmentRecordService.deactivateGrowthAssessmentRecord(id);
        return ResponseEntity.noContent().build();
    }

    // ========== GROWTH LIFECYCLE MANAGEMENT ==========

    @PostMapping("/lifecycle")
    public ResponseEntity<GrowthLifecycleRecordResponse> createGrowthLifecycle(@RequestBody GrowthLifecycleRecord record) {
        GrowthLifecycleRecord created = growthLifecycleRecordService.createGrowthLifecycleRecord(record);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToGrowthLifecycleResponse(created));
    }

    @GetMapping("/lifecycle/{id}")
    public ResponseEntity<GrowthLifecycleRecordResponse> getGrowthLifecycle(@PathVariable String id) {
        GrowthLifecycleRecord record = growthLifecycleRecordService.getGrowthLifecycleRecordById(id);
        return ResponseEntity.ok(mapToGrowthLifecycleResponse(record));
    }

    @GetMapping("/lifecycle/student/{studentId}")
    public ResponseEntity<List<GrowthLifecycleRecordResponse>> getGrowthLifecycleByStudent(@PathVariable String studentId) {
        List<GrowthLifecycleRecord> records = growthLifecycleRecordService.getGrowthLifecycleRecordsByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToGrowthLifecycleResponse).collect(Collectors.toList()));
    }

    @GetMapping("/lifecycle/entity/{entityType}/{entityId}")
    public ResponseEntity<List<GrowthLifecycleRecordResponse>> getGrowthLifecycleByEntity(
            @PathVariable String entityType, @PathVariable String entityId) {
        List<GrowthLifecycleRecord> records = growthLifecycleRecordService.getGrowthLifecycleByEntityTypeAndEntityId(entityType, entityId);
        return ResponseEntity.ok(records.stream().map(this::mapToGrowthLifecycleResponse).collect(Collectors.toList()));
    }

    @DeleteMapping("/lifecycle/{id}")
    public ResponseEntity<Void> deactivateGrowthLifecycle(@PathVariable String id) {
        growthLifecycleRecordService.deactivateGrowthLifecycleRecord(id);
        return ResponseEntity.noContent().build();
    }

    // ========== MAPPING METHODS ==========

    private GrowthProfileResponse mapToGrowthProfileResponse(GrowthProfile profile) {
        return GrowthProfileResponse.builder()
                .id(profile.getId())
                .studentId(profile.getStudentId())
                .academicLevel(profile.getAcademicLevel())
                .growthStatus(profile.getGrowthStatus())
                .currentGpa(profile.getCurrentGpa())
                .cumulativeGpa(profile.getCumulativeGpa())
                .totalCreditsEarned(profile.getTotalCreditsEarned())
                .totalCreditsAttempted(profile.getTotalCreditsAttempted())
                .creditCompletionRate(profile.getCreditCompletionRate())
                .academicStanding(profile.getAcademicStanding())
                .targetGraduationDate(profile.getTargetGraduationDate())
                .actualGraduationDate(profile.getActualGraduationDate())
                .academicInterests(profile.getAcademicInterests())
                .strengths(profile.getStrengths())
                .areasForImprovement(profile.getAreasForImprovement())
                .careerAlignmentScore(profile.getCareerAlignmentScore())
                .readinessScore(profile.getReadinessScore())
                .lastAcademicReviewDate(profile.getLastAcademicReviewDate())
                .isActive(profile.getIsActive())
                .build();
    }

    private LearningProgressRecordResponse mapToLearningProgressResponse(LearningProgressRecord record) {
        return LearningProgressRecordResponse.builder()
                .id(record.getId())
                .studentId(record.getStudentId())
                .growthProfileId(record.getGrowthProfileId())
                .subjectArea(record.getSubjectArea())
                .subjectCode(record.getSubjectCode())
                .progressType(record.getProgressType())
                .progressStatus(record.getProgressStatus())
                .competencyLevel(record.getCompetencyLevel())
                .proficiencyScore(record.getProficiencyScore())
                .targetCompetencyLevel(record.getTargetCompetencyLevel())
                .milestonesAchieved(record.getMilestonesAchieved())
                .milestonesTotal(record.getMilestonesTotal())
                .learningHoursLogged(record.getLearningHoursLogged())
                .masteryThreshold(record.getMasteryThreshold())
                .isMasteryAchieved(record.getIsMasteryAchieved())
                .masteryAchievedAt(record.getMasteryAchievedAt())
                .nextMilestoneName(record.getNextMilestoneName())
                .nextMilestoneDeadline(record.getNextMilestoneDeadline())
                .notes(record.getNotes())
                .isActive(record.getIsActive())
                .build();
    }

    private PerformanceRecordResponse mapToPerformanceResponse(PerformanceRecord record) {
        return PerformanceRecordResponse.builder()
                .id(record.getId())
                .studentId(record.getStudentId())
                .growthProfileId(record.getGrowthProfileId())
                .performanceType(record.getPerformanceType())
                .performanceName(record.getPerformanceName())
                .performanceStatus(record.getPerformanceStatus())
                .subjectArea(record.getSubjectArea())
                .scoreEarned(record.getScoreEarned())
                .scorePossible(record.getScorePossible())
                .percentageScore(record.getPercentageScore())
                .grade(record.getGrade())
                .gradePoint(record.getGradePoint())
                .academicPeriod(record.getAcademicPeriod())
                .academicYear(record.getAcademicYear())
                .weightPercentage(record.getWeightPercentage())
                .isWeighted(record.getIsWeighted())
                .performanceFeedback(record.getPerformanceFeedback())
                .submittedAt(record.getSubmittedAt())
                .gradedAt(record.getGradedAt())
                .gradedBy(record.getGradedBy())
                .isActive(record.getIsActive())
                .build();
    }

    private SuccessPlanRecordResponse mapToSuccessPlanResponse(SuccessPlanRecord record) {
        return SuccessPlanRecordResponse.builder()
                .id(record.getId())
                .studentId(record.getStudentId())
                .growthProfileId(record.getGrowthProfileId())
                .planType(record.getPlanType())
                .planName(record.getPlanName())
                .planStatus(record.getPlanStatus())
                .planDescription(record.getPlanDescription())
                .targetCompletionDate(record.getTargetCompletionDate())
                .actualCompletionDate(record.getActualCompletionDate())
                .priorityLevel(record.getPriorityLevel())
                .totalGoals(record.getTotalGoals())
                .completedGoals(record.getCompletedGoals())
                .goalCompletionPercentage(record.getGoalCompletionPercentage())
                .successMetrics(record.getSuccessMetrics())
                .actionItems(record.getActionItems())
                .responsibleAdvisorId(record.getResponsibleAdvisorId())
                .lastReviewDate(record.getLastReviewDate())
                .nextReviewDate(record.getNextReviewDate())
                .notes(record.getNotes())
                .isActive(record.getIsActive())
                .build();
    }

    private GrowthAssessmentRecordResponse mapToGrowthAssessmentResponse(GrowthAssessmentRecord record) {
        return GrowthAssessmentRecordResponse.builder()
                .id(record.getId())
                .studentId(record.getStudentId())
                .growthProfileId(record.getGrowthProfileId())
                .assessmentType(record.getAssessmentType())
                .assessmentName(record.getAssessmentName())
                .assessmentStatus(record.getAssessmentStatus())
                .assessmentDomain(record.getAssessmentDomain())
                .scoreEarned(record.getScoreEarned())
                .scorePossible(record.getScorePossible())
                .percentageScore(record.getPercentageScore())
                .grade(record.getGrade())
                .competencyLevel(record.getCompetencyLevel())
                .assessmentDurationMinutes(record.getAssessmentDurationMinutes())
                .passingScore(record.getPassingScore())
                .isPassed(record.getIsPassed())
                .feedback(record.getFeedback())
                .assessedBy(record.getAssessedBy())
                .assessedAt(record.getAssessedAt())
                .validUntil(record.getValidUntil())
                .isActive(record.getIsActive())
                .build();
    }

    private GrowthLifecycleRecordResponse mapToGrowthLifecycleResponse(GrowthLifecycleRecord record) {
        return GrowthLifecycleRecordResponse.builder()
                .id(record.getId())
                .growthProfileId(record.getGrowthProfileId())
                .studentId(record.getStudentId())
                .entityType(record.getEntityType())
                .entityId(record.getEntityId())
                .previousStatus(record.getPreviousStatus())
                .newStatus(record.getNewStatus())
                .reason(record.getReason())
                .changedBy(record.getChangedBy())
                .changedAt(record.getChangedAt())
                .isActive(record.getIsActive())
                .build();
    }
}
