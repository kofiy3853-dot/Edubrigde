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
@RequestMapping("/api/v1/learning-services/career-readiness")
@RequiredArgsConstructor
@Slf4j
public class CareerReadinessController {

    private final CareerProfileService careerProfileService;
    private final ProfessionalRecordService professionalRecordService;
    private final EmployabilityRecordService employabilityRecordService;
    private final LeadershipRecordService leadershipRecordService;
    private final CareerAssessmentRecordService careerAssessmentRecordService;
    private final CareerLifecycleRecordService careerLifecycleRecordService;

    // ========== CAREER PROFILE MANAGEMENT ==========

    @PostMapping("/profiles")
    public ResponseEntity<CareerProfileResponse> createCareerProfile(@RequestBody CareerProfile profile) {
        CareerProfile created = careerProfileService.createCareerProfile(profile);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToCareerProfileResponse(created));
    }

    @GetMapping("/profiles/{id}")
    public ResponseEntity<CareerProfileResponse> getCareerProfile(@PathVariable String id) {
        CareerProfile profile = careerProfileService.getCareerProfileById(id);
        return ResponseEntity.ok(mapToCareerProfileResponse(profile));
    }

    @GetMapping("/profiles/student/{studentId}")
    public ResponseEntity<List<CareerProfileResponse>> getCareerProfilesByStudent(@PathVariable String studentId) {
        List<CareerProfile> profiles = careerProfileService.getCareerProfilesByStudent(studentId);
        return ResponseEntity.ok(profiles.stream().map(this::mapToCareerProfileResponse).collect(Collectors.toList()));
    }

    @GetMapping("/profiles/status/{status}")
    public ResponseEntity<List<CareerProfileResponse>> getCareerProfilesByStatus(@PathVariable String status) {
        List<CareerProfile> profiles = careerProfileService.getCareerProfilesByStatus(status);
        return ResponseEntity.ok(profiles.stream().map(this::mapToCareerProfileResponse).collect(Collectors.toList()));
    }

    @GetMapping("/profiles/field/{field}")
    public ResponseEntity<List<CareerProfileResponse>> getCareerProfilesByField(@PathVariable String field) {
        List<CareerProfile> profiles = careerProfileService.getCareerProfilesByField(field);
        return ResponseEntity.ok(profiles.stream().map(this::mapToCareerProfileResponse).collect(Collectors.toList()));
    }

    @GetMapping("/profiles/high-readiness")
    public ResponseEntity<List<CareerProfileResponse>> getHighReadinessStudents(@RequestParam Double minScore) {
        List<CareerProfile> profiles = careerProfileService.getHighReadinessStudents(minScore);
        return ResponseEntity.ok(profiles.stream().map(this::mapToCareerProfileResponse).collect(Collectors.toList()));
    }

    @PutMapping("/profiles/{id}")
    public ResponseEntity<CareerProfileResponse> updateCareerProfile(@PathVariable String id, @RequestBody CareerProfile profile) {
        CareerProfile updated = careerProfileService.updateCareerProfile(id, profile);
        return ResponseEntity.ok(mapToCareerProfileResponse(updated));
    }

    @PostMapping("/profiles/{id}/review")
    public ResponseEntity<CareerProfileResponse> reviewCareerProfile(@PathVariable String id) {
        CareerProfile reviewed = careerProfileService.reviewCareerProfile(id);
        return ResponseEntity.ok(mapToCareerProfileResponse(reviewed));
    }

    @DeleteMapping("/profiles/{id}")
    public ResponseEntity<Void> deactivateCareerProfile(@PathVariable String id) {
        careerProfileService.deactivateCareerProfile(id);
        return ResponseEntity.noContent().build();
    }

    // ========== PROFESSIONAL DEVELOPMENT MANAGEMENT ==========

    @PostMapping("/professional")
    public ResponseEntity<ProfessionalRecordResponse> createProfessionalRecord(@RequestBody ProfessionalRecord record) {
        ProfessionalRecord created = professionalRecordService.createProfessionalRecord(record);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToProfessionalResponse(created));
    }

    @GetMapping("/professional/{id}")
    public ResponseEntity<ProfessionalRecordResponse> getProfessionalRecord(@PathVariable String id) {
        ProfessionalRecord record = professionalRecordService.getProfessionalRecordById(id);
        return ResponseEntity.ok(mapToProfessionalResponse(record));
    }

    @GetMapping("/professional/student/{studentId}")
    public ResponseEntity<List<ProfessionalRecordResponse>> getProfessionalByStudent(@PathVariable String studentId) {
        List<ProfessionalRecord> records = professionalRecordService.getProfessionalRecordsByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToProfessionalResponse).collect(Collectors.toList()));
    }

    @GetMapping("/professional/type/{type}")
    public ResponseEntity<List<ProfessionalRecordResponse>> getProfessionalByType(@PathVariable String type) {
        List<ProfessionalRecord> records = professionalRecordService.getProfessionalRecordsByType(type);
        return ResponseEntity.ok(records.stream().map(this::mapToProfessionalResponse).collect(Collectors.toList()));
    }

    @GetMapping("/professional/completed/{studentId}")
    public ResponseEntity<List<ProfessionalRecordResponse>> getCompletedByStudent(@PathVariable String studentId) {
        List<ProfessionalRecord> records = professionalRecordService.getCompletedByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToProfessionalResponse).collect(Collectors.toList()));
    }

    @GetMapping("/professional/expired")
    public ResponseEntity<List<ProfessionalRecordResponse>> getExpiredCertifications() {
        List<ProfessionalRecord> records = professionalRecordService.getExpiredCertifications();
        return ResponseEntity.ok(records.stream().map(this::mapToProfessionalResponse).collect(Collectors.toList()));
    }

    @PutMapping("/professional/{id}")
    public ResponseEntity<ProfessionalRecordResponse> updateProfessional(@PathVariable String id, @RequestBody ProfessionalRecord record) {
        ProfessionalRecord updated = professionalRecordService.updateProfessionalRecord(id, record);
        return ResponseEntity.ok(mapToProfessionalResponse(updated));
    }

    @PostMapping("/professional/{id}/complete")
    public ResponseEntity<ProfessionalRecordResponse> completeProfessional(@PathVariable String id) {
        ProfessionalRecord record = professionalRecordService.getProfessionalRecordById(id);
        record.setProfessionalStatus("COMPLETED");
        ProfessionalRecord completed = professionalRecordService.updateProfessionalRecord(id, record);
        return ResponseEntity.ok(mapToProfessionalResponse(completed));
    }

    @DeleteMapping("/professional/{id}")
    public ResponseEntity<Void> deactivateProfessional(@PathVariable String id) {
        professionalRecordService.deactivateProfessionalRecord(id);
        return ResponseEntity.noContent().build();
    }

    // ========== EMPLOYABILITY MANAGEMENT ==========

    @PostMapping("/employability")
    public ResponseEntity<EmployabilityRecordResponse> createEmployability(@RequestBody EmployabilityRecord record) {
        EmployabilityRecord created = employabilityRecordService.createEmployabilityRecord(record);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToEmployabilityResponse(created));
    }

    @GetMapping("/employability/{id}")
    public ResponseEntity<EmployabilityRecordResponse> getEmployability(@PathVariable String id) {
        EmployabilityRecord record = employabilityRecordService.getEmployabilityRecordById(id);
        return ResponseEntity.ok(mapToEmployabilityResponse(record));
    }

    @GetMapping("/employability/student/{studentId}")
    public ResponseEntity<List<EmployabilityRecordResponse>> getEmployabilityByStudent(@PathVariable String studentId) {
        List<EmployabilityRecord> records = employabilityRecordService.getEmployabilityRecordsByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToEmployabilityResponse).collect(Collectors.toList()));
    }

    @GetMapping("/employability/competency/{competency}")
    public ResponseEntity<List<EmployabilityRecordResponse>> getEmployabilityByCompetency(@PathVariable String competency) {
        List<EmployabilityRecord> records = employabilityRecordService.getEmployabilityRecordsByCompetency(competency);
        return ResponseEntity.ok(records.stream().map(this::mapToEmployabilityResponse).collect(Collectors.toList()));
    }

    @GetMapping("/employability/achieved/{studentId}")
    public ResponseEntity<List<EmployabilityRecordResponse>> getAchievedByStudent(@PathVariable String studentId) {
        List<EmployabilityRecord> records = employabilityRecordService.getAchievedByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToEmployabilityResponse).collect(Collectors.toList()));
    }

    @GetMapping("/employability/expired")
    public ResponseEntity<List<EmployabilityRecordResponse>> getExpiredEmployability() {
        List<EmployabilityRecord> records = employabilityRecordService.getExpiredEmployabilityRecords();
        return ResponseEntity.ok(records.stream().map(this::mapToEmployabilityResponse).collect(Collectors.toList()));
    }

    @PutMapping("/employability/{id}")
    public ResponseEntity<EmployabilityRecordResponse> updateEmployability(@PathVariable String id, @RequestBody EmployabilityRecord record) {
        EmployabilityRecord updated = employabilityRecordService.updateEmployabilityRecord(id, record);
        return ResponseEntity.ok(mapToEmployabilityResponse(updated));
    }

    @PostMapping("/employability/{id}/achieve")
    public ResponseEntity<EmployabilityRecordResponse> achieveEmployability(@PathVariable String id) {
        EmployabilityRecord record = employabilityRecordService.getEmployabilityRecordById(id);
        record.setEmployabilityStatus("ACHIEVED");
        EmployabilityRecord achieved = employabilityRecordService.updateEmployabilityRecord(id, record);
        return ResponseEntity.ok(mapToEmployabilityResponse(achieved));
    }

    @DeleteMapping("/employability/{id}")
    public ResponseEntity<Void> deactivateEmployability(@PathVariable String id) {
        employabilityRecordService.deactivateEmployabilityRecord(id);
        return ResponseEntity.noContent().build();
    }

    // ========== LEADERSHIP DEVELOPMENT MANAGEMENT ==========

    @PostMapping("/leadership")
    public ResponseEntity<LeadershipRecordResponse> createLeadership(@RequestBody LeadershipRecord record) {
        LeadershipRecord created = leadershipRecordService.createLeadershipRecord(record);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToLeadershipResponse(created));
    }

    @GetMapping("/leadership/{id}")
    public ResponseEntity<LeadershipRecordResponse> getLeadership(@PathVariable String id) {
        LeadershipRecord record = leadershipRecordService.getLeadershipRecordById(id);
        return ResponseEntity.ok(mapToLeadershipResponse(record));
    }

    @GetMapping("/leadership/student/{studentId}")
    public ResponseEntity<List<LeadershipRecordResponse>> getLeadershipByStudent(@PathVariable String studentId) {
        List<LeadershipRecord> records = leadershipRecordService.getLeadershipRecordsByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToLeadershipResponse).collect(Collectors.toList()));
    }

    @GetMapping("/leadership/type/{type}")
    public ResponseEntity<List<LeadershipRecordResponse>> getLeadershipByType(@PathVariable String type) {
        List<LeadershipRecord> records = leadershipRecordService.getLeadershipRecordsByType(type);
        return ResponseEntity.ok(records.stream().map(this::mapToLeadershipResponse).collect(Collectors.toList()));
    }

    @GetMapping("/leadership/achieved/{studentId}")
    public ResponseEntity<List<LeadershipRecordResponse>> getAchievedLeadershipByStudent(@PathVariable String studentId) {
        List<LeadershipRecord> records = leadershipRecordService.getAchievedByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToLeadershipResponse).collect(Collectors.toList()));
    }

    @GetMapping("/leadership/projects/{studentId}")
    public ResponseEntity<Integer> getTotalProjectsByStudent(@PathVariable String studentId) {
        Integer count = leadershipRecordService.countTotalProjectsByStudent(studentId);
        return ResponseEntity.ok(count);
    }

    @PutMapping("/leadership/{id}")
    public ResponseEntity<LeadershipRecordResponse> updateLeadership(@PathVariable String id, @RequestBody LeadershipRecord record) {
        LeadershipRecord updated = leadershipRecordService.updateLeadershipRecord(id, record);
        return ResponseEntity.ok(mapToLeadershipResponse(updated));
    }

    @PostMapping("/leadership/{id}/achieve")
    public ResponseEntity<LeadershipRecordResponse> achieveLeadership(@PathVariable String id) {
        LeadershipRecord record = leadershipRecordService.getLeadershipRecordById(id);
        record.setLeadershipStatus("ACHIEVED");
        LeadershipRecord achieved = leadershipRecordService.updateLeadershipRecord(id, record);
        return ResponseEntity.ok(mapToLeadershipResponse(achieved));
    }

    @DeleteMapping("/leadership/{id}")
    public ResponseEntity<Void> deactivateLeadership(@PathVariable String id) {
        leadershipRecordService.deactivateLeadershipRecord(id);
        return ResponseEntity.noContent().build();
    }

    // ========== CAREER ASSESSMENT MANAGEMENT ==========

    @PostMapping("/assessments")
    public ResponseEntity<CareerAssessmentRecordResponse> createCareerAssessment(@RequestBody CareerAssessmentRecord record) {
        CareerAssessmentRecord created = careerAssessmentRecordService.createCareerAssessmentRecord(record);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToCareerAssessmentResponse(created));
    }

    @GetMapping("/assessments/{id}")
    public ResponseEntity<CareerAssessmentRecordResponse> getCareerAssessment(@PathVariable String id) {
        CareerAssessmentRecord record = careerAssessmentRecordService.getCareerAssessmentRecordById(id);
        return ResponseEntity.ok(mapToCareerAssessmentResponse(record));
    }

    @GetMapping("/assessments/student/{studentId}")
    public ResponseEntity<List<CareerAssessmentRecordResponse>> getCareerAssessmentsByStudent(@PathVariable String studentId) {
        List<CareerAssessmentRecord> records = careerAssessmentRecordService.getCareerAssessmentRecordsByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToCareerAssessmentResponse).collect(Collectors.toList()));
    }

    @GetMapping("/assessments/domain/{domain}")
    public ResponseEntity<List<CareerAssessmentRecordResponse>> getCareerAssessmentsByDomain(@PathVariable String domain) {
        List<CareerAssessmentRecord> records = careerAssessmentRecordService.getCareerAssessmentRecordsByDomain(domain);
        return ResponseEntity.ok(records.stream().map(this::mapToCareerAssessmentResponse).collect(Collectors.toList()));
    }

    @GetMapping("/assessments/passed/{studentId}")
    public ResponseEntity<List<CareerAssessmentRecordResponse>> getPassedByStudent(@PathVariable String studentId) {
        List<CareerAssessmentRecord> records = careerAssessmentRecordService.getPassedByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToCareerAssessmentResponse).collect(Collectors.toList()));
    }

    @PutMapping("/assessments/{id}")
    public ResponseEntity<CareerAssessmentRecordResponse> updateCareerAssessment(@PathVariable String id, @RequestBody CareerAssessmentRecord record) {
        CareerAssessmentRecord updated = careerAssessmentRecordService.updateCareerAssessmentRecord(id, record);
        return ResponseEntity.ok(mapToCareerAssessmentResponse(updated));
    }

    @PostMapping("/assessments/{id}/grade")
    public ResponseEntity<CareerAssessmentRecordResponse> gradeCareerAssessment(
            @PathVariable String id,
            @RequestParam Double scoreEarned,
            @RequestParam Double scorePossible,
            @RequestParam String grade,
            @RequestParam String competencyLevel,
            @RequestParam(required = false) String feedback,
            @RequestParam(required = false) String recommendations,
            @RequestParam String assessedBy) {
        CareerAssessmentRecord graded = careerAssessmentRecordService.gradeCareerAssessmentRecord(id, scoreEarned, scorePossible, grade, competencyLevel, feedback, recommendations, assessedBy);
        return ResponseEntity.ok(mapToCareerAssessmentResponse(graded));
    }

    @DeleteMapping("/assessments/{id}")
    public ResponseEntity<Void> deactivateCareerAssessment(@PathVariable String id) {
        careerAssessmentRecordService.deactivateCareerAssessmentRecord(id);
        return ResponseEntity.noContent().build();
    }

    // ========== CAREER LIFECYCLE MANAGEMENT ==========

    @PostMapping("/lifecycle")
    public ResponseEntity<CareerLifecycleRecordResponse> createCareerLifecycle(@RequestBody CareerLifecycleRecord record) {
        CareerLifecycleRecord created = careerLifecycleRecordService.createCareerLifecycleRecord(record);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToCareerLifecycleResponse(created));
    }

    @GetMapping("/lifecycle/{id}")
    public ResponseEntity<CareerLifecycleRecordResponse> getCareerLifecycle(@PathVariable String id) {
        CareerLifecycleRecord record = careerLifecycleRecordService.getCareerLifecycleRecordById(id);
        return ResponseEntity.ok(mapToCareerLifecycleResponse(record));
    }

    @GetMapping("/lifecycle/student/{studentId}")
    public ResponseEntity<List<CareerLifecycleRecordResponse>> getCareerLifecycleByStudent(@PathVariable String studentId) {
        List<CareerLifecycleRecord> records = careerLifecycleRecordService.getCareerLifecycleRecordsByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToCareerLifecycleResponse).collect(Collectors.toList()));
    }

    @GetMapping("/lifecycle/entity/{entityType}/{entityId}")
    public ResponseEntity<List<CareerLifecycleRecordResponse>> getCareerLifecycleByEntity(
            @PathVariable String entityType, @PathVariable String entityId) {
        List<CareerLifecycleRecord> records = careerLifecycleRecordService.getCareerLifecycleByEntityTypeAndEntityId(entityType, entityId);
        return ResponseEntity.ok(records.stream().map(this::mapToCareerLifecycleResponse).collect(Collectors.toList()));
    }

    @DeleteMapping("/lifecycle/{id}")
    public ResponseEntity<Void> deactivateCareerLifecycle(@PathVariable String id) {
        careerLifecycleRecordService.deactivateCareerLifecycleRecord(id);
        return ResponseEntity.noContent().build();
    }

    // ========== MAPPING METHODS ==========

    private CareerProfileResponse mapToCareerProfileResponse(CareerProfile profile) {
        return CareerProfileResponse.builder()
                .id(profile.getId())
                .studentId(profile.getStudentId())
                .careerStatus(profile.getCareerStatus())
                .careerField(profile.getCareerField())
                .careerSubfield(profile.getCareerSubfield())
                .careerGoal(profile.getCareerGoal())
                .careerVision(profile.getCareerVision())
                .industryPreference(profile.getIndustryPreference())
                .workEnvironmentPreference(profile.getWorkEnvironmentPreference())
                .geographicPreference(profile.getGeographicPreference())
                .careerReadinessScore(profile.getCareerReadinessScore())
                .professionalMaturityScore(profile.getProfessionalMaturityScore())
                .targetCareerEntryDate(profile.getTargetCareerEntryDate())
                .actualCareerEntryDate(profile.getActualCareerEntryDate())
                .lastCareerReviewDate(profile.getLastCareerReviewDate())
                .notes(profile.getNotes())
                .isActive(profile.getIsActive())
                .build();
    }

    private ProfessionalRecordResponse mapToProfessionalResponse(ProfessionalRecord record) {
        return ProfessionalRecordResponse.builder()
                .id(record.getId())
                .studentId(record.getStudentId())
                .careerProfileId(record.getCareerProfileId())
                .professionalType(record.getProfessionalType())
                .professionalName(record.getProfessionalName())
                .professionalStatus(record.getProfessionalStatus())
                .skillArea(record.getSkillArea())
                .skillLevel(record.getSkillLevel())
                .proficiencyScore(record.getProficiencyScore())
                .targetProficiencyScore(record.getTargetProficiencyScore())
                .hoursLogged(record.getHoursLogged())
                .targetHours(record.getTargetHours())
                .certificationName(record.getCertificationName())
                .certificationDate(record.getCertificationDate())
                .certificationExpiry(record.getCertificationExpiry())
                .industryRecognized(record.getIndustryRecognized())
                .notes(record.getNotes())
                .isActive(record.getIsActive())
                .build();
    }

    private EmployabilityRecordResponse mapToEmployabilityResponse(EmployabilityRecord record) {
        return EmployabilityRecordResponse.builder()
                .id(record.getId())
                .studentId(record.getStudentId())
                .careerProfileId(record.getCareerProfileId())
                .employabilityType(record.getEmployabilityType())
                .employabilityName(record.getEmployabilityName())
                .employabilityStatus(record.getEmployabilityStatus())
                .competencyArea(record.getCompetencyArea())
                .competencyLevel(record.getCompetencyLevel())
                .competencyScore(record.getCompetencyScore())
                .targetCompetencyScore(record.getTargetCompetencyScore())
                .industryStandard(record.getIndustryStandard())
                .globalRecognized(record.getGlobalRecognized())
                .validFrom(record.getValidFrom())
                .validUntil(record.getValidUntil())
                .evidenceUrl(record.getEvidenceUrl())
                .notes(record.getNotes())
                .isActive(record.getIsActive())
                .build();
    }

    private LeadershipRecordResponse mapToLeadershipResponse(LeadershipRecord record) {
        return LeadershipRecordResponse.builder()
                .id(record.getId())
                .studentId(record.getStudentId())
                .careerProfileId(record.getCareerProfileId())
                .leadershipType(record.getLeadershipType())
                .leadershipName(record.getLeadershipName())
                .leadershipStatus(record.getLeadershipStatus())
                .leadershipLevel(record.getLeadershipLevel())
                .leadershipArea(record.getLeadershipArea())
                .leadershipScore(record.getLeadershipScore())
                .targetLeadershipScore(record.getTargetLeadershipScore())
                .teamSizeManaged(record.getTeamSizeManaged())
                .projectCount(record.getProjectCount())
                .innovationScore(record.getInnovationScore())
                .entrepreneurshipScore(record.getEntrepreneurshipScore())
                .startDate(record.getStartDate())
                .endDate(record.getEndDate())
                .notes(record.getNotes())
                .isActive(record.getIsActive())
                .build();
    }

    private CareerAssessmentRecordResponse mapToCareerAssessmentResponse(CareerAssessmentRecord record) {
        return CareerAssessmentRecordResponse.builder()
                .id(record.getId())
                .studentId(record.getStudentId())
                .careerProfileId(record.getCareerProfileId())
                .assessmentType(record.getAssessmentType())
                .assessmentName(record.getAssessmentName())
                .assessmentStatus(record.getAssessmentStatus())
                .assessmentDomain(record.getAssessmentDomain())
                .scoreEarned(record.getScoreEarned())
                .scorePossible(record.getScorePossible())
                .percentageScore(record.getPercentageScore())
                .grade(record.getGrade())
                .competencyLevel(record.getCompetencyLevel())
                .passingScore(record.getPassingScore())
                .isPassed(record.getIsPassed())
                .feedback(record.getFeedback())
                .recommendations(record.getRecommendations())
                .assessedBy(record.getAssessedBy())
                .assessedAt(record.getAssessedAt())
                .validUntil(record.getValidUntil())
                .isActive(record.getIsActive())
                .build();
    }

    private CareerLifecycleRecordResponse mapToCareerLifecycleResponse(CareerLifecycleRecord record) {
        return CareerLifecycleRecordResponse.builder()
                .id(record.getId())
                .careerProfileId(record.getCareerProfileId())
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
