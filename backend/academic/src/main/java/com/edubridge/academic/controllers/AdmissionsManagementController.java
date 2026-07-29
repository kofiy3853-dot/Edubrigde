package com.edubridge.academic.controllers;

import com.edubridge.academic.dto.*;
import com.edubridge.academic.entities.*;
import com.edubridge.academic.services.*;
import com.edubridge.shared.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/academic/admissions")
@RequiredArgsConstructor
public class AdmissionsManagementController {

    private final EligibilityService eligibilityService;
    private final AcademicValidationService academicValidationService;
    private final AdmissionAssessmentService assessmentService;
    private final AdmissionPolicyService policyService;
    private final AdmissionLifecycleService lifecycleService;

    // ========== Eligibility Rule Endpoints ==========

    @PostMapping("/{universityId}/eligibility")
    public ResponseEntity<ApiResponse<EligibilityRuleResponse>> createEligibilityRule(
            @PathVariable String universityId, @RequestBody EligibilityRule rule) {
        rule.setUniversityId(universityId);
        EligibilityRule created = eligibilityService.createEligibilityRule(rule);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success(toEligibilityRuleResponse(created)));
    }

    @GetMapping("/{universityId}/eligibility")
    public ResponseEntity<ApiResponse<List<EligibilityRuleResponse>>> getEligibilityRules(
            @PathVariable String universityId) {
        List<EligibilityRuleResponse> rules = eligibilityService.getRulesByUniversity(universityId)
            .stream()
            .map(this::toEligibilityRuleResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(rules));
    }

    @GetMapping("/{universityId}/eligibility/type/{type}")
    public ResponseEntity<ApiResponse<List<EligibilityRuleResponse>>> getEligibilityRulesByType(
            @PathVariable String universityId, @PathVariable String type) {
        List<EligibilityRuleResponse> rules = eligibilityService.getRulesByType(type)
            .stream()
            .filter(r -> r.getUniversityId().equals(universityId))
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(rules));
    }

    @GetMapping("/{universityId}/eligibility/effect/{effect}")
    public ResponseEntity<ApiResponse<List<EligibilityRuleResponse>>> getEligibilityRulesByEffect(
            @PathVariable String universityId, @PathVariable String effect) {
        List<EligibilityRuleResponse> rules = eligibilityService.getRulesByEffect(effect)
            .stream()
            .filter(r -> r.getUniversityId().equals(universityId))
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(rules));
    }

    @PutMapping("/{universityId}/eligibility/{id}")
    public ResponseEntity<ApiResponse<EligibilityRuleResponse>> updateEligibilityRule(
            @PathVariable String universityId, @PathVariable String id,
            @RequestBody EligibilityRule rule) {
        rule.setUniversityId(universityId);
        EligibilityRule updated = eligibilityService.updateEligibilityRule(id, rule);
        return ResponseEntity.ok(ApiResponse.success(toEligibilityRuleResponse(updated)));
    }

    @PostMapping("/{universityId}/eligibility/{id}/deactivate")
    public ResponseEntity<ApiResponse<EligibilityRuleResponse>> deactivateEligibilityRule(
            @PathVariable String universityId, @PathVariable String id) {
        EligibilityRule deactivated = eligibilityService.deactivateEligibilityRule(id);
        return ResponseEntity.ok(ApiResponse.success(toEligibilityRuleResponse(deactivated)));
    }

    @PostMapping("/{universityId}/eligibility/{id}/activate")
    public ResponseEntity<ApiResponse<EligibilityRuleResponse>> activateEligibilityRule(
            @PathVariable String universityId, @PathVariable String id) {
        EligibilityRule activated = eligibilityService.activateEligibilityRule(id);
        return ResponseEntity.ok(ApiResponse.success(toEligibilityRuleResponse(activated)));
    }

    // ========== Academic Validation Endpoints ==========

    @PostMapping("/{universityId}/validation")
    public ResponseEntity<ApiResponse<AcademicValidationResponse>> createAcademicValidation(
            @PathVariable String universityId, @RequestBody AcademicValidation validation) {
        validation.setUniversityId(universityId);
        AcademicValidation created = academicValidationService.createAcademicValidation(validation);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success(toAcademicValidationResponse(created)));
    }

    @GetMapping("/{universityId}/validation")
    public ResponseEntity<ApiResponse<List<AcademicValidationResponse>>> getAcademicValidations(
            @PathVariable String universityId) {
        List<AcademicValidationResponse> validations = academicValidationService.getValidationsByUniversity(universityId)
            .stream()
            .map(this::toAcademicValidationResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(validations));
    }

    @GetMapping("/{universityId}/validation/type/{type}")
    public ResponseEntity<ApiResponse<List<AcademicValidationResponse>>> getAcademicValidationsByType(
            @PathVariable String universityId, @PathVariable String type) {
        List<AcademicValidationResponse> validations = academicValidationService.getValidationsByType(type)
            .stream()
            .filter(v -> v.getUniversityId().equals(universityId))
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(validations));
    }

    @PutMapping("/{universityId}/validation/{id}")
    public ResponseEntity<ApiResponse<AcademicValidationResponse>> updateAcademicValidation(
            @PathVariable String universityId, @PathVariable String id,
            @RequestBody AcademicValidation validation) {
        validation.setUniversityId(universityId);
        AcademicValidation updated = academicValidationService.updateAcademicValidation(id, validation);
        return ResponseEntity.ok(ApiResponse.success(toAcademicValidationResponse(updated)));
    }

    @PostMapping("/{universityId}/validation/{id}/deactivate")
    public ResponseEntity<ApiResponse<AcademicValidationResponse>> deactivateAcademicValidation(
            @PathVariable String universityId, @PathVariable String id) {
        AcademicValidation deactivated = academicValidationService.deactivateAcademicValidation(id);
        return ResponseEntity.ok(ApiResponse.success(toAcademicValidationResponse(deactivated)));
    }

    @PostMapping("/{universityId}/validation/{id}/activate")
    public ResponseEntity<ApiResponse<AcademicValidationResponse>> activateAcademicValidation(
            @PathVariable String universityId, @PathVariable String id) {
        AcademicValidation activated = academicValidationService.activateAcademicValidation(id);
        return ResponseEntity.ok(ApiResponse.success(toAcademicValidationResponse(activated)));
    }

    // ========== Admission Assessment Endpoints ==========

    @PostMapping("/{universityId}/assessments")
    public ResponseEntity<ApiResponse<AdmissionAssessmentResponse>> createAssessment(
            @PathVariable String universityId, @RequestBody AdmissionAssessment assessment) {
        assessment.setUniversityId(universityId);
        AdmissionAssessment created = assessmentService.createAssessment(assessment);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success(toAdmissionAssessmentResponse(created)));
    }

    @GetMapping("/{universityId}/assessments")
    public ResponseEntity<ApiResponse<List<AdmissionAssessmentResponse>>> getAssessments(
            @PathVariable String universityId) {
        List<AdmissionAssessmentResponse> assessments = assessmentService.getAssessmentsByUniversity(universityId)
            .stream()
            .map(this::toAdmissionAssessmentResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(assessments));
    }

    @GetMapping("/{universityId}/assessments/program/{programId}")
    public ResponseEntity<ApiResponse<List<AdmissionAssessmentResponse>>> getAssessmentsByProgram(
            @PathVariable String universityId, @PathVariable String programId) {
        List<AdmissionAssessmentResponse> assessments = assessmentService.getAssessmentsByProgram(programId)
            .stream()
            .filter(a -> a.getUniversityId().equals(universityId))
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(assessments));
    }

    @GetMapping("/{universityId}/assessments/student/{studentId}")
    public ResponseEntity<ApiResponse<List<AdmissionAssessmentResponse>>> getAssessmentsByStudent(
            @PathVariable String universityId, @PathVariable String studentId) {
        List<AdmissionAssessmentResponse> assessments = assessmentService.getAssessmentsByStudent(studentId)
            .stream()
            .filter(a -> a.getUniversityId().equals(universityId))
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(assessments));
    }

    @GetMapping("/{universityId}/assessments/status/{status}")
    public ResponseEntity<ApiResponse<List<AdmissionAssessmentResponse>>> getAssessmentsByStatus(
            @PathVariable String universityId, @PathVariable String status) {
        List<AdmissionAssessmentResponse> assessments = assessmentService.getAssessmentsByStatus(status)
            .stream()
            .filter(a -> a.getUniversityId().equals(universityId))
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(assessments));
    }

    @PutMapping("/{universityId}/assessments/{id}")
    public ResponseEntity<ApiResponse<AdmissionAssessmentResponse>> updateAssessment(
            @PathVariable String universityId, @PathVariable String id,
            @RequestBody AdmissionAssessment assessment) {
        assessment.setUniversityId(universityId);
        AdmissionAssessment updated = assessmentService.updateAssessment(id, assessment);
        return ResponseEntity.ok(ApiResponse.success(toAdmissionAssessmentResponse(updated)));
    }

    @PostMapping("/{universityId}/assessments/{id}/deactivate")
    public ResponseEntity<ApiResponse<AdmissionAssessmentResponse>> deactivateAssessment(
            @PathVariable String universityId, @PathVariable String id) {
        AdmissionAssessment deactivated = assessmentService.deactivateAssessment(id);
        return ResponseEntity.ok(ApiResponse.success(toAdmissionAssessmentResponse(deactivated)));
    }

    @PostMapping("/{universityId}/assessments/{id}/activate")
    public ResponseEntity<ApiResponse<AdmissionAssessmentResponse>> activateAssessment(
            @PathVariable String universityId, @PathVariable String id) {
        AdmissionAssessment activated = assessmentService.activateAssessment(id);
        return ResponseEntity.ok(ApiResponse.success(toAdmissionAssessmentResponse(activated)));
    }

    // ========== Admission Policy Endpoints ==========

    @PostMapping("/{universityId}/policies")
    public ResponseEntity<ApiResponse<AdmissionPolicyResponse>> createPolicy(
            @PathVariable String universityId, @RequestBody AdmissionPolicy policy) {
        policy.setUniversityId(universityId);
        AdmissionPolicy created = policyService.createPolicy(policy);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success(toAdmissionPolicyResponse(created)));
    }

    @GetMapping("/{universityId}/policies")
    public ResponseEntity<ApiResponse<List<AdmissionPolicyResponse>>> getPolicies(
            @PathVariable String universityId) {
        List<AdmissionPolicyResponse> policies = policyService.getPoliciesByUniversity(universityId)
            .stream()
            .map(this::toAdmissionPolicyResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(policies));
    }

    @GetMapping("/{universityId}/policies/type/{type}")
    public ResponseEntity<ApiResponse<List<AdmissionPolicyResponse>>> getPoliciesByType(
            @PathVariable String universityId, @PathVariable String type) {
        List<AdmissionPolicyResponse> policies = policyService.getPoliciesByType(type)
            .stream()
            .filter(p -> p.getUniversityId().equals(universityId))
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(policies));
    }

    @GetMapping("/{universityId}/policies/effect/{effect}")
    public ResponseEntity<ApiResponse<List<AdmissionPolicyResponse>>> getPoliciesByEffect(
            @PathVariable String universityId, @PathVariable String effect) {
        List<AdmissionPolicyResponse> policies = policyService.getPoliciesByEffect(effect)
            .stream()
            .filter(p -> p.getUniversityId().equals(universityId))
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(policies));
    }

    @PutMapping("/{universityId}/policies/{id}")
    public ResponseEntity<ApiResponse<AdmissionPolicyResponse>> updatePolicy(
            @PathVariable String universityId, @PathVariable String id,
            @RequestBody AdmissionPolicy policy) {
        policy.setUniversityId(universityId);
        AdmissionPolicy updated = policyService.updatePolicy(id, policy);
        return ResponseEntity.ok(ApiResponse.success(toAdmissionPolicyResponse(updated)));
    }

    @PostMapping("/{universityId}/policies/{id}/deactivate")
    public ResponseEntity<ApiResponse<AdmissionPolicyResponse>> deactivatePolicy(
            @PathVariable String universityId, @PathVariable String id) {
        AdmissionPolicy deactivated = policyService.deactivatePolicy(id);
        return ResponseEntity.ok(ApiResponse.success(toAdmissionPolicyResponse(deactivated)));
    }

    @PostMapping("/{universityId}/policies/{id}/activate")
    public ResponseEntity<ApiResponse<AdmissionPolicyResponse>> activatePolicy(
            @PathVariable String universityId, @PathVariable String id) {
        AdmissionPolicy activated = policyService.activatePolicy(id);
        return ResponseEntity.ok(ApiResponse.success(toAdmissionPolicyResponse(activated)));
    }

    // ========== Admission Lifecycle Endpoints ==========

    @PostMapping("/{universityId}/lifecycle")
    public ResponseEntity<ApiResponse<AdmissionLifecycleResponse>> createLifecycleRecord(
            @PathVariable String universityId, @RequestBody AdmissionLifecycle lifecycle) {
        AdmissionLifecycle created = lifecycleService.createLifecycleRecord(lifecycle);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success(toAdmissionLifecycleResponse(created)));
    }

    @GetMapping("/{universityId}/lifecycle/assessment/{assessmentId}")
    public ResponseEntity<ApiResponse<List<AdmissionLifecycleResponse>>> getLifecycleRecords(
            @PathVariable String universityId, @PathVariable String assessmentId) {
        List<AdmissionLifecycleResponse> records = lifecycleService.getLifecycleRecordsByAssessment(assessmentId)
            .stream()
            .map(this::toAdmissionLifecycleResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(records));
    }

    @GetMapping("/{universityId}/lifecycle/assessment/{assessmentId}/current")
    public ResponseEntity<ApiResponse<AdmissionLifecycleResponse>> getCurrentLifecycle(
            @PathVariable String universityId, @PathVariable String assessmentId) {
        return lifecycleService.getCurrentLifecycleByAssessment(assessmentId)
            .map(lifecycle -> ResponseEntity.ok(ApiResponse.success(toAdmissionLifecycleResponse(lifecycle))))
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{universityId}/lifecycle/status/{status}")
    public ResponseEntity<ApiResponse<List<AdmissionLifecycleResponse>>> getLifecycleByStatus(
            @PathVariable String universityId, @PathVariable String status) {
        List<AdmissionLifecycleResponse> records = lifecycleService.getLifecycleRecordsByStatus(status)
            .stream()
            .map(this::toAdmissionLifecycleResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(records));
    }

    @PostMapping("/{universityId}/lifecycle/{id}/status")
    public ResponseEntity<ApiResponse<AdmissionLifecycleResponse>> updateLifecycleStatus(
            @PathVariable String universityId, @PathVariable String id,
            @RequestParam String status, @RequestParam(required = false) String reason,
            @RequestParam(required = false) String changedBy) {
        AdmissionLifecycle updated = lifecycleService.updateLifecycleStatus(id, status, reason, changedBy);
        return ResponseEntity.ok(ApiResponse.success(toAdmissionLifecycleResponse(updated)));
    }

    @PostMapping("/{universityId}/lifecycle/{id}/deactivate")
    public ResponseEntity<ApiResponse<AdmissionLifecycleResponse>> deactivateLifecycleRecord(
            @PathVariable String universityId, @PathVariable String id) {
        AdmissionLifecycle deactivated = lifecycleService.deactivateLifecycleRecord(id);
        return ResponseEntity.ok(ApiResponse.success(toAdmissionLifecycleResponse(deactivated)));
    }

    @PostMapping("/{universityId}/lifecycle/{id}/activate")
    public ResponseEntity<ApiResponse<AdmissionLifecycleResponse>> activateLifecycleRecord(
            @PathVariable String universityId, @PathVariable String id) {
        AdmissionLifecycle activated = lifecycleService.activateLifecycleRecord(id);
        return ResponseEntity.ok(ApiResponse.success(toAdmissionLifecycleResponse(activated)));
    }

    // ========== Helper Methods ==========

    private EligibilityRuleResponse toEligibilityRuleResponse(EligibilityRule rule) {
        return EligibilityRuleResponse.builder()
            .id(rule.getId())
            .universityId(rule.getUniversityId())
            .programId(rule.getProgramId())
            .ruleType(rule.getRuleType())
            .name(rule.getName())
            .description(rule.getDescription())
            .ruleExpression(rule.getRuleExpression())
            .effect(rule.getEffect())
            .conditions(rule.getConditions())
            .priority(rule.getPriority())
            .isMandatory(rule.getIsMandatory())
            .isActive(rule.getIsActive())
            .createdAt(rule.getCreatedAt())
            .updatedAt(rule.getUpdatedAt())
            .build();
    }

    private AcademicValidationResponse toAcademicValidationResponse(AcademicValidation validation) {
        return AcademicValidationResponse.builder()
            .id(validation.getId())
            .universityId(validation.getUniversityId())
            .programId(validation.getProgramId())
            .validationType(validation.getValidationType())
            .name(validation.getName())
            .description(validation.getDescription())
            .validationRule(validation.getValidationRule())
            .minValue(validation.getMinValue())
            .maxValue(validation.getMaxValue())
            .allowedValues(validation.getAllowedValues())
            .isMandatory(validation.getIsMandatory())
            .weight(validation.getWeight())
            .isActive(validation.getIsActive())
            .createdAt(validation.getCreatedAt())
            .updatedAt(validation.getUpdatedAt())
            .build();
    }

    private AdmissionAssessmentResponse toAdmissionAssessmentResponse(AdmissionAssessment assessment) {
        return AdmissionAssessmentResponse.builder()
            .id(assessment.getId())
            .universityId(assessment.getUniversityId())
            .programId(assessment.getProgramId())
            .studentId(assessment.getStudentId())
            .status(assessment.getStatus())
            .assessmentType(assessment.getAssessmentType())
            .academicScore(assessment.getAcademicScore())
            .languageScore(assessment.getLanguageScore())
            .overallScore(assessment.getOverallScore())
            .eligibilityResult(assessment.getEligibilityResult())
            .validationResults(assessment.getValidationResults())
            .policyResults(assessment.getPolicyResults())
            .notes(assessment.getNotes())
            .assessedBy(assessment.getAssessedBy())
            .assessedAt(assessment.getAssessedAt())
            .expirationDate(assessment.getExpirationDate())
            .isActive(assessment.getIsActive())
            .createdAt(assessment.getCreatedAt())
            .updatedAt(assessment.getUpdatedAt())
            .build();
    }

    private AdmissionPolicyResponse toAdmissionPolicyResponse(AdmissionPolicy policy) {
        return AdmissionPolicyResponse.builder()
            .id(policy.getId())
            .universityId(policy.getUniversityId())
            .programId(policy.getProgramId())
            .policyType(policy.getPolicyType())
            .name(policy.getName())
            .code(policy.getCode())
            .description(policy.getDescription())
            .policyRule(policy.getPolicyRule())
            .effect(policy.getEffect())
            .priority(policy.getPriority())
            .isMandatory(policy.getIsMandatory())
            .effectiveDate(policy.getEffectiveDate())
            .expirationDate(policy.getExpirationDate())
            .isActive(policy.getIsActive())
            .requiresReview(policy.getRequiresReview())
            .lastReviewedDate(policy.getLastReviewedDate())
            .createdAt(policy.getCreatedAt())
            .updatedAt(policy.getUpdatedAt())
            .build();
    }

    private AdmissionLifecycleResponse toAdmissionLifecycleResponse(AdmissionLifecycle lifecycle) {
        return AdmissionLifecycleResponse.builder()
            .id(lifecycle.getId())
            .assessmentId(lifecycle.getAssessmentId())
            .status(lifecycle.getStatus())
            .previousStatus(lifecycle.getPreviousStatus())
            .reason(lifecycle.getReason())
            .changedBy(lifecycle.getChangedBy())
            .effectiveDate(lifecycle.getEffectiveDate())
            .expirationDate(lifecycle.getExpirationDate())
            .reviewDate(lifecycle.getReviewDate())
            .notes(lifecycle.getNotes())
            .isCurrent(lifecycle.getIsCurrent())
            .isActive(lifecycle.getIsActive())
            .createdAt(lifecycle.getCreatedAt())
            .updatedAt(lifecycle.getUpdatedAt())
            .build();
    }
}
