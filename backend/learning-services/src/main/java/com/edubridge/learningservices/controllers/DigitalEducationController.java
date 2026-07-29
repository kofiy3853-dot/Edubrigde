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
@RequestMapping("/api/v1/learning-services/digital-education")
@RequiredArgsConstructor
@Slf4j
public class DigitalEducationController {

    private final CourseProfileService courseProfileService;
    private final StudySkillRecordService studySkillRecordService;
    private final ResourceRecordService resourceRecordService;
    private final PreparationRecordService preparationRecordService;
    private final AssessmentRecordService assessmentRecordService;
    private final LifecycleRecordService lifecycleRecordService;

    // ========== COURSE MANAGEMENT ==========

    @PostMapping("/courses")
    public ResponseEntity<CourseProfileResponse> createCourseProfile(@RequestBody CourseProfile course) {
        CourseProfile created = courseProfileService.createCourseProfile(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToCourseResponse(created));
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseProfileResponse> getCourseProfile(@PathVariable String id) {
        CourseProfile course = courseProfileService.getCourseProfileById(id);
        return ResponseEntity.ok(mapToCourseResponse(course));
    }

    @GetMapping("/courses/student/{studentId}")
    public ResponseEntity<List<CourseProfileResponse>> getCourseProfilesByStudent(@PathVariable String studentId) {
        List<CourseProfile> courses = courseProfileService.getCourseProfilesByStudent(studentId);
        return ResponseEntity.ok(courses.stream().map(this::mapToCourseResponse).collect(Collectors.toList()));
    }

    @GetMapping("/courses/type/{type}")
    public ResponseEntity<List<CourseProfileResponse>> getCourseProfilesByType(@PathVariable String type) {
        List<CourseProfile> courses = courseProfileService.getCourseProfilesByType(type);
        return ResponseEntity.ok(courses.stream().map(this::mapToCourseResponse).collect(Collectors.toList()));
    }

    @GetMapping("/courses/status/{status}")
    public ResponseEntity<List<CourseProfileResponse>> getCourseProfilesByStatus(@PathVariable String status) {
        List<CourseProfile> courses = courseProfileService.getCourseProfilesByStatus(status);
        return ResponseEntity.ok(courses.stream().map(this::mapToCourseResponse).collect(Collectors.toList()));
    }

    @GetMapping("/courses/enrolled")
    public ResponseEntity<List<CourseProfileResponse>> getEnrolledCourses() {
        List<CourseProfile> courses = courseProfileService.getEnrolledCourses();
        return ResponseEntity.ok(courses.stream().map(this::mapToCourseResponse).collect(Collectors.toList()));
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<CourseProfileResponse> updateCourseProfile(@PathVariable String id, @RequestBody CourseProfile course) {
        CourseProfile updated = courseProfileService.updateCourseProfile(id, course);
        return ResponseEntity.ok(mapToCourseResponse(updated));
    }

    @PostMapping("/courses/{id}/complete")
    public ResponseEntity<CourseProfileResponse> completeCourseProfile(@PathVariable String id) {
        CourseProfile completed = courseProfileService.completeCourseProfile(id);
        return ResponseEntity.ok(mapToCourseResponse(completed));
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Void> deactivateCourseProfile(@PathVariable String id) {
        courseProfileService.deactivateCourseProfile(id);
        return ResponseEntity.noContent().build();
    }

    // ========== STUDY SKILLS MANAGEMENT ==========

    @PostMapping("/study-skills")
    public ResponseEntity<StudySkillRecordResponse> createStudySkillRecord(@RequestBody StudySkillRecord record) {
        StudySkillRecord created = studySkillRecordService.createStudySkillRecord(record);
        return ResponseEntity.status(HttpStatus.CREATED).map(this::mapToStudySkillResponse);
    }

    @GetMapping("/study-skills/{id}")
    public ResponseEntity<StudySkillRecordResponse> getStudySkillRecord(@PathVariable String id) {
        StudySkillRecord record = studySkillRecordService.getStudySkillRecordById(id);
        return ResponseEntity.ok(mapToStudySkillResponse(record));
    }

    @GetMapping("/study-skills/student/{studentId}")
    public ResponseEntity<List<StudySkillRecordResponse>> getStudySkillRecordsByStudent(@PathVariable String studentId) {
        List<StudySkillRecord> records = studySkillRecordService.getStudySkillRecordsByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToStudySkillResponse).collect(Collectors.toList()));
    }

    @GetMapping("/study-skills/category/{category}")
    public ResponseEntity<List<StudySkillRecordResponse>> getStudySkillRecordsByCategory(@PathVariable String category) {
        List<StudySkillRecord> records = studySkillRecordService.getStudySkillRecordsByCategory(category);
        return ResponseEntity.ok(records.stream().map(this::mapToStudySkillResponse).collect(Collectors.toList()));
    }

    @GetMapping("/study-skills/mastered")
    public ResponseEntity<List<StudySkillRecordResponse>> getMasteredSkillsByStudent(@RequestParam String studentId) {
        List<StudySkillRecord> records = studySkillRecordService.getMasteredByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToStudySkillResponse).collect(Collectors.toList()));
    }

    @PutMapping("/study-skills/{id}")
    public ResponseEntity<StudySkillRecordResponse> updateStudySkillRecord(@PathVariable String id, @RequestBody StudySkillRecord record) {
        StudySkillRecord updated = studySkillRecordService.updateStudySkillRecord(id, record);
        return ResponseEntity.ok(mapToStudySkillResponse(updated));
    }

    @PostMapping("/study-skills/{id}/master")
    public ResponseEntity<StudySkillRecordResponse> masterSkill(@PathVariable String id) {
        StudySkillRecord mastered = studySkillRecordService.masterSkill(id);
        return ResponseEntity.ok(mapToStudySkillResponse(mastered));
    }

    @DeleteMapping("/study-skills/{id}")
    public ResponseEntity<Void> deactivateStudySkillRecord(@PathVariable String id) {
        studySkillRecordService.deactivateStudySkillRecord(id);
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

    @GetMapping("/resources/accessible")
    public ResponseEntity<List<ResourceRecordResponse>> getAccessibleResourcesByStudent(@RequestParam String studentId) {
        List<ResourceRecord> records = resourceRecordService.getAccessibleByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToResourceResponse).collect(Collectors.toList()));
    }

    @PutMapping("/resources/{id}")
    public ResponseEntity<ResourceRecordResponse> updateResourceRecord(@PathVariable String id, @RequestBody ResourceRecord record) {
        ResourceRecord updated = resourceRecordService.updateResourceRecord(id, record);
        return ResponseEntity.ok(mapToResourceResponse(updated));
    }

    @PostMapping("/resources/{id}/access")
    public ResponseEntity<ResourceRecordResponse> accessResource(@PathVariable String id) {
        ResourceRecord accessed = resourceRecordService.accessResource(id);
        return ResponseEntity.ok(mapToResourceResponse(accessed));
    }

    @DeleteMapping("/resources/{id}")
    public ResponseEntity<Void> deactivateResourceRecord(@PathVariable String id) {
        resourceRecordService.deactivateResourceRecord(id);
        return ResponseEntity.noContent().build();
    }

    // ========== ACADEMIC PREPARATION MANAGEMENT ==========

    @PostMapping("/preparation")
    public ResponseEntity<PreparationRecordResponse> createPreparationRecord(@RequestBody PreparationRecord record) {
        PreparationRecord created = preparationRecordService.createPreparationRecord(record);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToPreparationResponse(created));
    }

    @GetMapping("/preparation/{id}")
    public ResponseEntity<PreparationRecordResponse> getPreparationRecord(@PathVariable String id) {
        PreparationRecord record = preparationRecordService.getPreparationRecordById(id);
        return ResponseEntity.ok(mapToPreparationResponse(record));
    }

    @GetMapping("/preparation/student/{studentId}")
    public ResponseEntity<List<PreparationRecordResponse>> getPreparationRecordsByStudent(@PathVariable String studentId) {
        List<PreparationRecord> records = preparationRecordService.getPreparationRecordsByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToPreparationResponse).collect(Collectors.toList()));
    }

    @GetMapping("/preparation/type/{type}")
    public ResponseEntity<List<PreparationRecordResponse>> getPreparationRecordsByType(@PathVariable String type) {
        List<PreparationRecord> records = preparationRecordService.getPreparationRecordsByType(type);
        return ResponseEntity.ok(records.stream().map(this::mapToPreparationResponse).collect(Collectors.toList()));
    }

    @GetMapping("/preparation/in-progress")
    public ResponseEntity<List<PreparationRecordResponse>> getInProgressPreparationByStudent(@RequestParam String studentId) {
        List<PreparationRecord> records = preparationRecordService.getInProgressByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToPreparationResponse).collect(Collectors.toList()));
    }

    @PutMapping("/preparation/{id}")
    public ResponseEntity<PreparationRecordResponse> updatePreparationRecord(@PathVariable String id, @RequestBody PreparationRecord record) {
        PreparationRecord updated = preparationRecordService.updatePreparationRecord(id, record);
        return ResponseEntity.ok(mapToPreparationResponse(updated));
    }

    @PostMapping("/preparation/{id}/complete")
    public ResponseEntity<PreparationRecordResponse> completePreparation(@PathVariable String id) {
        PreparationRecord completed = preparationRecordService.completePreparation(id);
        return ResponseEntity.ok(mapToPreparationResponse(completed));
    }

    @DeleteMapping("/preparation/{id}")
    public ResponseEntity<Void> deactivatePreparationRecord(@PathVariable String id) {
        preparationRecordService.deactivatePreparationRecord(id);
        return ResponseEntity.noContent().build();
    }

    // ========== ASSESSMENT MANAGEMENT ==========

    @PostMapping("/assessments")
    public ResponseEntity<AssessmentRecordResponse> createAssessmentRecord(@RequestBody AssessmentRecord record) {
        AssessmentRecord created = assessmentRecordService.createAssessmentRecord(record);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToAssessmentResponse(created));
    }

    @GetMapping("/assessments/{id}")
    public ResponseEntity<AssessmentRecordResponse> getAssessmentRecord(@PathVariable String id) {
        AssessmentRecord record = assessmentRecordService.getAssessmentRecordById(id);
        return ResponseEntity.ok(mapToAssessmentResponse(record));
    }

    @GetMapping("/assessments/student/{studentId}")
    public ResponseEntity<List<AssessmentRecordResponse>> getAssessmentRecordsByStudent(@PathVariable String studentId) {
        List<AssessmentRecord> records = assessmentRecordService.getAssessmentRecordsByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToAssessmentResponse).collect(Collectors.toList()));
    }

    @GetMapping("/assessments/type/{type}")
    public ResponseEntity<List<AssessmentRecordResponse>> getAssessmentRecordsByType(@PathVariable String type) {
        List<AssessmentRecord> records = assessmentRecordService.getAssessmentRecordsByType(type);
        return ResponseEntity.ok(records.stream().map(this::mapToAssessmentResponse).collect(Collectors.toList()));
    }

    @GetMapping("/assessments/pending")
    public ResponseEntity<List<AssessmentRecordResponse>> getPendingAssessmentsByStudent(@RequestParam String studentId) {
        List<AssessmentRecord> records = assessmentRecordService.getPendingByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToAssessmentResponse).collect(Collectors.toList()));
    }

    @PutMapping("/assessments/{id}")
    public ResponseEntity<AssessmentRecordResponse> updateAssessmentRecord(@PathVariable String id, @RequestBody AssessmentRecord record) {
        AssessmentRecord updated = assessmentRecordService.updateAssessmentRecord(id, record);
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
    public ResponseEntity<Void> deactivateAssessmentRecord(@PathVariable String id) {
        assessmentRecordService.deactivateAssessmentRecord(id);
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

    @GetMapping("/lifecycle/course/{courseId}")
    public ResponseEntity<List<LifecycleRecordResponse>> getLifecycleRecordsByCourse(@PathVariable String courseId) {
        List<LifecycleRecord> records = lifecycleRecordService.getLifecycleRecordsByCourse(courseId);
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

    private CourseProfileResponse mapToCourseResponse(CourseProfile course) {
        return CourseProfileResponse.builder()
                .id(course.getId())
                .studentId(course.getStudentId())
                .courseName(course.getCourseName())
                .courseType(course.getCourseType())
                .courseStatus(course.getCourseStatus())
                .difficultyLevel(course.getDifficultyLevel())
                .description(course.getDescription())
                .learningObjectives(course.getLearningObjectives())
                .prerequisites(course.getPrerequisites())
                .estimatedDurationHours(course.getEstimatedDurationHours())
                .completionPercentage(course.getCompletionPercentage())
                .enrolledAt(course.getEnrolledAt())
                .completedAt(course.getCompletedAt())
                .isActive(course.getIsActive())
                .build();
    }

    private StudySkillRecordResponse mapToStudySkillResponse(StudySkillRecord record) {
        return StudySkillRecordResponse.builder()
                .id(record.getId())
                .courseProfileId(record.getCourseProfileId())
                .studentId(record.getStudentId())
                .skillName(record.getSkillName())
                .skillCategory(record.getSkillCategory())
                .skillStatus(record.getSkillStatus())
                .proficiencyLevel(record.getProficiencyLevel())
                .proficiencyScore(record.getProficiencyScore())
                .description(record.getDescription())
                .practiceHours(record.getPracticeHours())
                .lastPracticedAt(record.getLastPracticedAt())
                .isActive(record.getIsActive())
                .build();
    }

    private ResourceRecordResponse mapToResourceResponse(ResourceRecord record) {
        return ResourceRecordResponse.builder()
                .id(record.getId())
                .courseProfileId(record.getCourseProfileId())
                .studentId(record.getStudentId())
                .resourceType(record.getResourceType())
                .resourceName(record.getResourceName())
                .resourceStatus(record.getResourceStatus())
                .description(record.getDescription())
                .resourceUrl(record.getResourceUrl())
                .formatType(record.getFormatType())
                .accessCount(record.getAccessCount())
                .lastAccessedAt(record.getLastAccessedAt())
                .isActive(record.getIsActive())
                .build();
    }

    private PreparationRecordResponse mapToPreparationResponse(PreparationRecord record) {
        return PreparationRecordResponse.builder()
                .id(record.getId())
                .courseProfileId(record.getCourseProfileId())
                .studentId(record.getStudentId())
                .preparationType(record.getPreparationType())
                .preparationStatus(record.getPreparationStatus())
                .targetInstitution(record.getTargetInstitution())
                .targetProgram(record.getTargetProgram())
                .readinessScore(record.getReadinessScore())
                .requirementsMet(record.getRequirementsMet())
                .requirementsPending(record.getRequirementsPending())
                .targetDate(record.getTargetDate())
                .notes(record.getNotes())
                .isActive(record.getIsActive())
                .build();
    }

    private AssessmentRecordResponse mapToAssessmentResponse(AssessmentRecord record) {
        return AssessmentRecordResponse.builder()
                .id(record.getId())
                .courseProfileId(record.getCourseProfileId())
                .studentId(record.getStudentId())
                .assessmentType(record.getAssessmentType())
                .assessmentStatus(record.getAssessmentStatus())
                .score(record.getScore())
                .maxScore(record.getMaxScore())
                .percentage(record.getPercentage())
                .grade(record.getGrade())
                .assessmentNotes(record.getAssessmentNotes())
                .recommendations(record.getRecommendations())
                .assessedAt(record.getAssessedAt())
                .isActive(record.getIsActive())
                .build();
    }

    private LifecycleRecordResponse mapToLifecycleResponse(LifecycleRecord record) {
        return LifecycleRecordResponse.builder()
                .id(record.getId())
                .courseProfileId(record.getCourseProfileId())
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
