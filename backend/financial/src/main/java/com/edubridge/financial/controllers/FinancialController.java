package com.edubridge.financial.controllers;

import com.edubridge.financial.dto.*;
import com.edubridge.financial.entities.*;
import com.edubridge.financial.services.*;
import com.edubridge.shared.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/financial")
@RequiredArgsConstructor
public class FinancialController {

    private final FinancialProfileService profileService;
    private final CostRecordService costRecordService;
    private final CountryProfileService countryProfileService;
    private final FinancialAssessmentService assessmentService;
    private final FinancialPolicyService policyService;
    private final FinancialLifecycleService lifecycleService;

    // ========== Financial Profile Endpoints ==========

    @PostMapping("/planning")
    public ResponseEntity<ApiResponse<FinancialProfileResponse>> createProfile(
            @RequestBody FinancialProfile profile) {
        FinancialProfile created = profileService.createProfile(profile);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success(toProfileResponse(created)));
    }

    @GetMapping("/planning")
    public ResponseEntity<ApiResponse<List<FinancialProfileResponse>>> getAllProfiles() {
        List<FinancialProfileResponse> profiles = profileService.getActiveProfiles().stream()
            .map(this::toProfileResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(profiles));
    }

    @GetMapping("/planning/{id}")
    public ResponseEntity<ApiResponse<FinancialProfileResponse>> getProfileById(@PathVariable String id) {
        FinancialProfile profile = profileService.getProfileById(id);
        return ResponseEntity.ok(ApiResponse.success(toProfileResponse(profile)));
    }

    @GetMapping("/planning/student/{studentId}")
    public ResponseEntity<ApiResponse<List<FinancialProfileResponse>>> getProfilesByStudent(
            @PathVariable String studentId) {
        List<FinancialProfileResponse> profiles = profileService.getProfilesByStudent(studentId).stream()
            .map(this::toProfileResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(profiles));
    }

    @GetMapping("/planning/country/{countryCode}")
    public ResponseEntity<ApiResponse<List<FinancialProfileResponse>>> getProfilesByCountry(
            @PathVariable String countryCode) {
        List<FinancialProfileResponse> profiles = profileService.getProfilesByCountry(countryCode).stream()
            .map(this::toProfileResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(profiles));
    }

    @PutMapping("/planning/{id}")
    public ResponseEntity<ApiResponse<FinancialProfileResponse>> updateProfile(
            @PathVariable String id, @RequestBody FinancialProfile profile) {
        FinancialProfile updated = profileService.updateProfile(id, profile);
        return ResponseEntity.ok(ApiResponse.success(toProfileResponse(updated)));
    }

    @PostMapping("/planning/{id}/deactivate")
    public ResponseEntity<ApiResponse<FinancialProfileResponse>> deactivateProfile(@PathVariable String id) {
        FinancialProfile deactivated = profileService.deactivateProfile(id);
        return ResponseEntity.ok(ApiResponse.success(toProfileResponse(deactivated)));
    }

    @PostMapping("/planning/{id}/activate")
    public ResponseEntity<ApiResponse<FinancialProfileResponse>> activateProfile(@PathVariable String id) {
        FinancialProfile activated = profileService.activateProfile(id);
        return ResponseEntity.ok(ApiResponse.success(toProfileResponse(activated)));
    }

    // ========== Cost Record Endpoints ==========

    @PostMapping("/costs")
    public ResponseEntity<ApiResponse<CostRecordResponse>> createCostRecord(
            @RequestBody CostRecord costRecord) {
        CostRecord created = costRecordService.createCostRecord(costRecord);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success(toCostRecordResponse(created)));
    }

    @GetMapping("/costs")
    public ResponseEntity<ApiResponse<List<CostRecordResponse>>> getAllCostRecords() {
        List<CostRecordResponse> records = costRecordService.getActiveCostRecords().stream()
            .map(this::toCostRecordResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(records));
    }

    @GetMapping("/costs/{id}")
    public ResponseEntity<ApiResponse<CostRecordResponse>> getCostRecordById(@PathVariable String id) {
        CostRecord record = costRecordService.getCostRecordById(id);
        return ResponseEntity.ok(ApiResponse.success(toCostRecordResponse(record)));
    }

    @GetMapping("/costs/university/{universityId}")
    public ResponseEntity<ApiResponse<List<CostRecordResponse>>> getCostRecordsByUniversity(
            @PathVariable String universityId) {
        List<CostRecordResponse> records = costRecordService.getCostRecordsByUniversity(universityId).stream()
            .map(this::toCostRecordResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(records));
    }

    @GetMapping("/costs/country/{countryCode}")
    public ResponseEntity<ApiResponse<List<CostRecordResponse>>> getCostRecordsByCountry(
            @PathVariable String countryCode) {
        List<CostRecordResponse> records = costRecordService.getCostRecordsByCountry(countryCode).stream()
            .map(this::toCostRecordResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(records));
    }

    @GetMapping("/costs/type/{costType}")
    public ResponseEntity<ApiResponse<List<CostRecordResponse>>> getCostRecordsByType(
            @PathVariable String costType) {
        List<CostRecordResponse> records = costRecordService.getCostRecordsByType(costType).stream()
            .map(this::toCostRecordResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(records));
    }

    @PutMapping("/costs/{id}")
    public ResponseEntity<ApiResponse<CostRecordResponse>> updateCostRecord(
            @PathVariable String id, @RequestBody CostRecord costRecord) {
        CostRecord updated = costRecordService.updateCostRecord(id, costRecord);
        return ResponseEntity.ok(ApiResponse.success(toCostRecordResponse(updated)));
    }

    @PostMapping("/costs/{id}/deactivate")
    public ResponseEntity<ApiResponse<CostRecordResponse>> deactivateCostRecord(@PathVariable String id) {
        CostRecord deactivated = costRecordService.deactivateCostRecord(id);
        return ResponseEntity.ok(ApiResponse.success(toCostRecordResponse(deactivated)));
    }

    @PostMapping("/costs/{id}/activate")
    public ResponseEntity<ApiResponse<CostRecordResponse>> activateCostRecord(@PathVariable String id) {
        CostRecord activated = costRecordService.activateCostRecord(id);
        return ResponseEntity.ok(ApiResponse.success(toCostRecordResponse(activated)));
    }

    // ========== Country Profile Endpoints ==========

    @PostMapping("/countries")
    public ResponseEntity<ApiResponse<CountryProfileResponse>> createCountryProfile(
            @RequestBody CountryProfile profile) {
        CountryProfile created = countryProfileService.createCountryProfile(profile);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success(toCountryProfileResponse(created)));
    }

    @GetMapping("/countries")
    public ResponseEntity<ApiResponse<List<CountryProfileResponse>>> getAllCountryProfiles() {
        List<CountryProfileResponse> profiles = countryProfileService.getActiveCountryProfiles().stream()
            .map(this::toCountryProfileResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(profiles));
    }

    @GetMapping("/countries/{id}")
    public ResponseEntity<ApiResponse<CountryProfileResponse>> getCountryProfileById(@PathVariable String id) {
        CountryProfile profile = countryProfileService.getCountryProfileById(id);
        return ResponseEntity.ok(ApiResponse.success(toCountryProfileResponse(profile)));
    }

    @GetMapping("/countries/code/{countryCode}")
    public ResponseEntity<ApiResponse<CountryProfileResponse>> getCountryProfileByCode(
            @PathVariable String countryCode) {
        CountryProfile profile = countryProfileService.getCountryProfileByCode(countryCode);
        return ResponseEntity.ok(ApiResponse.success(toCountryProfileResponse(profile)));
    }

    @PutMapping("/countries/{id}")
    public ResponseEntity<ApiResponse<CountryProfileResponse>> updateCountryProfile(
            @PathVariable String id, @RequestBody CountryProfile profile) {
        CountryProfile updated = countryProfileService.updateCountryProfile(id, profile);
        return ResponseEntity.ok(ApiResponse.success(toCountryProfileResponse(updated)));
    }

    @PostMapping("/countries/{id}/deactivate")
    public ResponseEntity<ApiResponse<CountryProfileResponse>> deactivateCountryProfile(@PathVariable String id) {
        CountryProfile deactivated = countryProfileService.deactivateCountryProfile(id);
        return ResponseEntity.ok(ApiResponse.success(toCountryProfileResponse(deactivated)));
    }

    @PostMapping("/countries/{id}/activate")
    public ResponseEntity<ApiResponse<CountryProfileResponse>> activateCountryProfile(@PathVariable String id) {
        CountryProfile activated = countryProfileService.activateCountryProfile(id);
        return ResponseEntity.ok(ApiResponse.success(toCountryProfileResponse(activated)));
    }

    // ========== Financial Assessment Endpoints ==========

    @PostMapping("/assessments")
    public ResponseEntity<ApiResponse<FinancialAssessmentResponse>> createAssessment(
            @RequestBody FinancialAssessment assessment) {
        FinancialAssessment created = assessmentService.createAssessment(assessment);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success(toAssessmentResponse(created)));
    }

    @GetMapping("/assessments")
    public ResponseEntity<ApiResponse<List<FinancialAssessmentResponse>>> getAllAssessments() {
        List<FinancialAssessmentResponse> assessments = assessmentService.getActiveAssessments().stream()
            .map(this::toAssessmentResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(assessments));
    }

    @GetMapping("/assessments/{id}")
    public ResponseEntity<ApiResponse<FinancialAssessmentResponse>> getAssessmentById(@PathVariable String id) {
        FinancialAssessment assessment = assessmentService.getAssessmentById(id);
        return ResponseEntity.ok(ApiResponse.success(toAssessmentResponse(assessment)));
    }

    @GetMapping("/assessments/student/{studentId}")
    public ResponseEntity<ApiResponse<List<FinancialAssessmentResponse>>> getAssessmentsByStudent(
            @PathVariable String studentId) {
        List<FinancialAssessmentResponse> assessments = assessmentService.getAssessmentsByStudent(studentId).stream()
            .map(this::toAssessmentResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(assessments));
    }

    @GetMapping("/assessments/status/{status}")
    public ResponseEntity<ApiResponse<List<FinancialAssessmentResponse>>> getAssessmentsByStatus(
            @PathVariable String status) {
        List<FinancialAssessmentResponse> assessments = assessmentService.getAssessmentsByStatus(status).stream()
            .map(this::toAssessmentResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(assessments));
    }

    @PutMapping("/assessments/{id}")
    public ResponseEntity<ApiResponse<FinancialAssessmentResponse>> updateAssessment(
            @PathVariable String id, @RequestBody FinancialAssessment assessment) {
        FinancialAssessment updated = assessmentService.updateAssessment(id, assessment);
        return ResponseEntity.ok(ApiResponse.success(toAssessmentResponse(updated)));
    }

    @PostMapping("/assessments/{id}/deactivate")
    public ResponseEntity<ApiResponse<FinancialAssessmentResponse>> deactivateAssessment(@PathVariable String id) {
        FinancialAssessment deactivated = assessmentService.deactivateAssessment(id);
        return ResponseEntity.ok(ApiResponse.success(toAssessmentResponse(deactivated)));
    }

    @PostMapping("/assessments/{id}/activate")
    public ResponseEntity<ApiResponse<FinancialAssessmentResponse>> activateAssessment(@PathVariable String id) {
        FinancialAssessment activated = assessmentService.activateAssessment(id);
        return ResponseEntity.ok(ApiResponse.success(toAssessmentResponse(activated)));
    }

    // ========== Financial Policy Endpoints ==========

    @PostMapping("/policies")
    public ResponseEntity<ApiResponse<FinancialPolicyResponse>> createPolicy(
            @RequestBody FinancialPolicy policy) {
        FinancialPolicy created = policyService.createPolicy(policy);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success(toPolicyResponse(created)));
    }

    @GetMapping("/policies")
    public ResponseEntity<ApiResponse<List<FinancialPolicyResponse>>> getAllPolicies() {
        List<FinancialPolicyResponse> policies = policyService.getActivePolicies().stream()
            .map(this::toPolicyResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(policies));
    }

    @GetMapping("/policies/{id}")
    public ResponseEntity<ApiResponse<FinancialPolicyResponse>> getPolicyById(@PathVariable String id) {
        FinancialPolicy policy = policyService.getPolicyById(id);
        return ResponseEntity.ok(ApiResponse.success(toPolicyResponse(policy)));
    }

    @GetMapping("/policies/university/{universityId}")
    public ResponseEntity<ApiResponse<List<FinancialPolicyResponse>>> getPoliciesByUniversity(
            @PathVariable String universityId) {
        List<FinancialPolicyResponse> policies = policyService.getPoliciesByUniversity(universityId).stream()
            .map(this::toPolicyResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(policies));
    }

    @GetMapping("/policies/country/{countryCode}")
    public ResponseEntity<ApiResponse<List<FinancialPolicyResponse>>> getPoliciesByCountry(
            @PathVariable String countryCode) {
        List<FinancialPolicyResponse> policies = policyService.getPoliciesByCountry(countryCode).stream()
            .map(this::toPolicyResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(policies));
    }

    @GetMapping("/policies/type/{policyType}")
    public ResponseEntity<ApiResponse<List<FinancialPolicyResponse>>> getPoliciesByType(
            @PathVariable String policyType) {
        List<FinancialPolicyResponse> policies = policyService.getPoliciesByType(policyType).stream()
            .map(this::toPolicyResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(policies));
    }

    @PutMapping("/policies/{id}")
    public ResponseEntity<ApiResponse<FinancialPolicyResponse>> updatePolicy(
            @PathVariable String id, @RequestBody FinancialPolicy policy) {
        FinancialPolicy updated = policyService.updatePolicy(id, policy);
        return ResponseEntity.ok(ApiResponse.success(toPolicyResponse(updated)));
    }

    @PostMapping("/policies/{id}/deactivate")
    public ResponseEntity<ApiResponse<FinancialPolicyResponse>> deactivatePolicy(@PathVariable String id) {
        FinancialPolicy deactivated = policyService.deactivatePolicy(id);
        return ResponseEntity.ok(ApiResponse.success(toPolicyResponse(deactivated)));
    }

    @PostMapping("/policies/{id}/activate")
    public ResponseEntity<ApiResponse<FinancialPolicyResponse>> activatePolicy(@PathVariable String id) {
        FinancialPolicy activated = policyService.activatePolicy(id);
        return ResponseEntity.ok(ApiResponse.success(toPolicyResponse(activated)));
    }

    // ========== Financial Lifecycle Endpoints ==========

    @PostMapping("/lifecycle")
    public ResponseEntity<ApiResponse<FinancialLifecycleResponse>> createLifecycleRecord(
            @RequestBody FinancialLifecycle lifecycle) {
        FinancialLifecycle created = lifecycleService.createLifecycleRecord(lifecycle);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success(toLifecycleResponse(created)));
    }

    @GetMapping("/lifecycle/assessment/{assessmentId}")
    public ResponseEntity<ApiResponse<List<FinancialLifecycleResponse>>> getLifecycleRecords(
            @PathVariable String assessmentId) {
        List<FinancialLifecycleResponse> records = lifecycleService.getLifecycleRecordsByAssessment(assessmentId)
            .stream()
            .map(this::toLifecycleResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(records));
    }

    @GetMapping("/lifecycle/assessment/{assessmentId}/current")
    public ResponseEntity<ApiResponse<FinancialLifecycleResponse>> getCurrentLifecycle(
            @PathVariable String assessmentId) {
        return lifecycleService.getCurrentLifecycleByAssessment(assessmentId)
            .map(lifecycle -> ResponseEntity.ok(ApiResponse.success(toLifecycleResponse(lifecycle))))
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/lifecycle/{id}/status")
    public ResponseEntity<ApiResponse<FinancialLifecycleResponse>> updateLifecycleStatus(
            @PathVariable String id,
            @RequestParam String status,
            @RequestParam(required = false) String reason,
            @RequestParam(required = false) String changedBy) {
        FinancialLifecycle updated = lifecycleService.updateLifecycleStatus(id, status, reason, changedBy);
        return ResponseEntity.ok(ApiResponse.success(toLifecycleResponse(updated)));
    }

    @PostMapping("/lifecycle/{id}/deactivate")
    public ResponseEntity<ApiResponse<FinancialLifecycleResponse>> deactivateLifecycleRecord(
            @PathVariable String id) {
        FinancialLifecycle deactivated = lifecycleService.deactivateLifecycleRecord(id);
        return ResponseEntity.ok(ApiResponse.success(toLifecycleResponse(deactivated)));
    }

    @PostMapping("/lifecycle/{id}/activate")
    public ResponseEntity<ApiResponse<FinancialLifecycleResponse>> activateLifecycleRecord(
            @PathVariable String id) {
        FinancialLifecycle activated = lifecycleService.activateLifecycleRecord(id);
        return ResponseEntity.ok(ApiResponse.success(toLifecycleResponse(activated)));
    }

    // ========== Helper Methods ==========

    private FinancialProfileResponse toProfileResponse(FinancialProfile profile) {
        return FinancialProfileResponse.builder()
            .id(profile.getId())
            .studentId(profile.getStudentId())
            .universityId(profile.getUniversityId())
            .programId(profile.getProgramId())
            .countryCode(profile.getCountryCode())
            .currencyCode(profile.getCurrencyCode())
            .estimatedTuition(profile.getEstimatedTuition())
            .estimatedLivingExpenses(profile.getEstimatedLivingExpenses())
            .estimatedAccommodation(profile.getEstimatedAccommodation())
            .estimatedTransportation(profile.getEstimatedTransportation())
            .estimatedFood(profile.getEstimatedFood())
            .estimatedHealthcare(profile.getEstimatedHealthcare())
            .estimatedAcademicExpenses(profile.getEstimatedAcademicExpenses())
            .totalEstimatedCost(profile.getTotalEstimatedCost())
            .availableFunds(profile.getAvailableFunds())
            .expectedScholarships(profile.getExpectedScholarships())
            .expectedLoans(profile.getExpectedLoans())
            .financialGap(profile.getFinancialGap())
            .affordabilityScore(profile.getAffordabilityScore())
            .riskLevel(profile.getRiskLevel())
            .notes(profile.getNotes())
            .isActive(profile.getIsActive())
            .createdAt(profile.getCreatedAt())
            .updatedAt(profile.getUpdatedAt())
            .build();
    }

    private CostRecordResponse toCostRecordResponse(CostRecord record) {
        return CostRecordResponse.builder()
            .id(record.getId())
            .universityId(record.getUniversityId())
            .programId(record.getProgramId())
            .countryCode(record.getCountryCode())
            .costType(record.getCostType())
            .name(record.getName())
            .description(record.getDescription())
            .amount(record.getAmount())
            .currencyCode(record.getCurrencyCode())
            .frequency(record.getFrequency())
            .academicYear(record.getAcademicYear())
            .isMandatory(record.getIsMandatory())
            .isEstimated(record.getIsEstimated())
            .source(record.getSource())
            .isActive(record.getIsActive())
            .createdAt(record.getCreatedAt())
            .updatedAt(record.getUpdatedAt())
            .build();
    }

    private CountryProfileResponse toCountryProfileResponse(CountryProfile profile) {
        return CountryProfileResponse.builder()
            .id(profile.getId())
            .countryCode(profile.getCountryCode())
            .countryName(profile.getCountryName())
            .currencyCode(profile.getCurrencyCode())
            .currencyName(profile.getCurrencyName())
            .exchangeRateToUsd(profile.getExchangeRateToUsd())
            .avgMonthlyLivingCost(profile.getAvgMonthlyLivingCost())
            .avgMonthlyAccommodation(profile.getAvgMonthlyAccommodation())
            .avgMonthlyFood(profile.getAvgMonthlyFood())
            .avgMonthlyTransportation(profile.getAvgMonthlyTransportation())
            .avgMonthlyHealthcare(profile.getAvgMonthlyHealthcare())
            .avgAnnualTuitionPublic(profile.getAvgAnnualTuitionPublic())
            .avgAnnualTuitionPrivate(profile.getAvgAnnualTuitionPrivate())
            .studentVisaCost(profile.getStudentVisaCost())
            .healthInsuranceRequired(profile.getHealthInsuranceRequired())
            .healthInsuranceCost(profile.getHealthInsuranceCost())
            .workHoursAllowedPerWeek(profile.getWorkHoursAllowedPerWeek())
            .minimumBankStatement(profile.getMinimumBankStatement())
            .notes(profile.getNotes())
            .isActive(profile.getIsActive())
            .createdAt(profile.getCreatedAt())
            .updatedAt(profile.getUpdatedAt())
            .build();
    }

    private FinancialAssessmentResponse toAssessmentResponse(FinancialAssessment assessment) {
        return FinancialAssessmentResponse.builder()
            .id(assessment.getId())
            .studentId(assessment.getStudentId())
            .universityId(assessment.getUniversityId())
            .programId(assessment.getProgramId())
            .countryCode(assessment.getCountryCode())
            .status(assessment.getStatus())
            .assessmentType(assessment.getAssessmentType())
            .totalEstimatedCost(assessment.getTotalEstimatedCost())
            .totalAvailableFunds(assessment.getTotalAvailableFunds())
            .financialGap(assessment.getFinancialGap())
            .affordabilityScore(assessment.getAffordabilityScore())
            .riskLevel(assessment.getRiskLevel())
            .assessmentResults(assessment.getAssessmentResults())
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

    private FinancialPolicyResponse toPolicyResponse(FinancialPolicy policy) {
        return FinancialPolicyResponse.builder()
            .id(policy.getId())
            .universityId(policy.getUniversityId())
            .countryCode(policy.getCountryCode())
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

    private FinancialLifecycleResponse toLifecycleResponse(FinancialLifecycle lifecycle) {
        return FinancialLifecycleResponse.builder()
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
