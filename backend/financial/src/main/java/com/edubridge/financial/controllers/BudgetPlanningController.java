package com.edubridge.financial.controllers;

import com.edubridge.financial.dto.*;
import com.edubridge.financial.entities.*;
import com.edubridge.financial.services.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/financial/budgets")
@RequiredArgsConstructor
@Slf4j
public class BudgetPlanningController {

    private final BudgetProfileService budgetProfileService;
    private final AffordabilityRecordService affordabilityRecordService;
    private final SustainabilityRecordService sustainabilityRecordService;
    private final RiskAssessmentService riskAssessmentService;
    private final BudgetPolicyService budgetPolicyService;
    private final BudgetLifecycleRecordService budgetLifecycleRecordService;

    // ========== BUDGET MANAGEMENT ==========

    @PostMapping("/management")
    public ResponseEntity<BudgetProfileResponse> createBudgetProfile(@RequestBody BudgetProfile profile) {
        BudgetProfile created = budgetProfileService.createBudgetProfile(profile);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToBudgetResponse(created));
    }

    @GetMapping("/management/{id}")
    public ResponseEntity<BudgetProfileResponse> getBudgetProfile(@PathVariable String id) {
        BudgetProfile profile = budgetProfileService.getBudgetProfileById(id);
        return ResponseEntity.ok(mapToBudgetResponse(profile));
    }

    @GetMapping("/management/student/{studentId}")
    public ResponseEntity<List<BudgetProfileResponse>> getBudgetProfilesByStudent(@PathVariable String studentId) {
        List<BudgetProfile> profiles = budgetProfileService.getBudgetProfilesByStudent(studentId);
        return ResponseEntity.ok(profiles.stream().map(this::mapToBudgetResponse).collect(Collectors.toList()));
    }

    @GetMapping("/management/university/{universityId}")
    public ResponseEntity<List<BudgetProfileResponse>> getBudgetProfilesByUniversity(@PathVariable String universityId) {
        List<BudgetProfile> profiles = budgetProfileService.getBudgetProfilesByUniversity(universityId);
        return ResponseEntity.ok(profiles.stream().map(this::mapToBudgetResponse).collect(Collectors.toList()));
    }

    @GetMapping("/management/country/{countryCode}")
    public ResponseEntity<List<BudgetProfileResponse>> getBudgetProfilesByCountry(@PathVariable String countryCode) {
        List<BudgetProfile> profiles = budgetProfileService.getBudgetProfilesByCountry(countryCode);
        return ResponseEntity.ok(profiles.stream().map(this::mapToBudgetResponse).collect(Collectors.toList()));
    }

    @GetMapping("/management/status/{status}")
    public ResponseEntity<List<BudgetProfileResponse>> getBudgetProfilesByAffordabilityStatus(@PathVariable String status) {
        List<BudgetProfile> profiles = budgetProfileService.getBudgetProfilesByAffordabilityStatus(status);
        return ResponseEntity.ok(profiles.stream().map(this::mapToBudgetResponse).collect(Collectors.toList()));
    }

    @GetMapping("/management/risk/{riskLevel}")
    public ResponseEntity<List<BudgetProfileResponse>> getBudgetProfilesByRiskLevel(@PathVariable String riskLevel) {
        List<BudgetProfile> profiles = budgetProfileService.getBudgetProfilesByRiskLevel(riskLevel);
        return ResponseEntity.ok(profiles.stream().map(this::mapToBudgetResponse).collect(Collectors.toList()));
    }

    @GetMapping("/management/student/{studentId}/draft")
    public ResponseEntity<List<BudgetProfileResponse>> getDraftBudgetProfiles(@PathVariable String studentId) {
        List<BudgetProfile> profiles = budgetProfileService.getDraftBudgetProfilesByStudent(studentId);
        return ResponseEntity.ok(profiles.stream().map(this::mapToBudgetResponse).collect(Collectors.toList()));
    }

    @GetMapping("/management/student/{studentId}/finalized")
    public ResponseEntity<List<BudgetProfileResponse>> getFinalizedBudgetProfiles(@PathVariable String studentId) {
        List<BudgetProfile> profiles = budgetProfileService.getFinalizedBudgetProfilesByStudent(studentId);
        return ResponseEntity.ok(profiles.stream().map(this::mapToBudgetResponse).collect(Collectors.toList()));
    }

    @GetMapping("/management/high-risk")
    public ResponseEntity<List<BudgetProfileResponse>> getHighRiskBudgets() {
        List<BudgetProfile> profiles = budgetProfileService.getHighRiskBudgets();
        return ResponseEntity.ok(profiles.stream().map(this::mapToBudgetResponse).collect(Collectors.toList()));
    }

    @PutMapping("/management/{id}")
    public ResponseEntity<BudgetProfileResponse> updateBudgetProfile(@PathVariable String id, @RequestBody BudgetProfile profile) {
        BudgetProfile updated = budgetProfileService.updateBudgetProfile(id, profile);
        return ResponseEntity.ok(mapToBudgetResponse(updated));
    }

    @PostMapping("/management/{id}/finalize")
    public ResponseEntity<BudgetProfileResponse> finalizeBudgetProfile(@PathVariable String id) {
        BudgetProfile finalized = budgetProfileService.finalizeBudgetProfile(id);
        return ResponseEntity.ok(mapToBudgetResponse(finalized));
    }

    @DeleteMapping("/management/{id}")
    public ResponseEntity<Void> deactivateBudgetProfile(@PathVariable String id) {
        budgetProfileService.deactivateBudgetProfile(id);
        return ResponseEntity.noContent().build();
    }

    // ========== AFFORDABILITY RECORDS ==========

    @PostMapping("/affordability")
    public ResponseEntity<AffordabilityRecordResponse> createAffordabilityRecord(@RequestBody AffordabilityRecord record) {
        AffordabilityRecord created = affordabilityRecordService.createAffordabilityRecord(record);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToAffordabilityResponse(created));
    }

    @GetMapping("/affordability/{id}")
    public ResponseEntity<AffordabilityRecordResponse> getAffordabilityRecord(@PathVariable String id) {
        AffordabilityRecord record = affordabilityRecordService.getAffordabilityRecordById(id);
        return ResponseEntity.ok(mapToAffordabilityResponse(record));
    }

    @GetMapping("/affordability/budget/{budgetProfileId}")
    public ResponseEntity<List<AffordabilityRecordResponse>> getAffordabilityRecordsByBudget(@PathVariable String budgetProfileId) {
        List<AffordabilityRecord> records = affordabilityRecordService.getAffordabilityRecordsByBudget(budgetProfileId);
        return ResponseEntity.ok(records.stream().map(this::mapToAffordabilityResponse).collect(Collectors.toList()));
    }

    @GetMapping("/affordability/student/{studentId}")
    public ResponseEntity<List<AffordabilityRecordResponse>> getAffordabilityRecordsByStudent(@PathVariable String studentId) {
        List<AffordabilityRecord> records = affordabilityRecordService.getAffordabilityRecordsByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToAffordabilityResponse).collect(Collectors.toList()));
    }

    @GetMapping("/affordability/type/{type}")
    public ResponseEntity<List<AffordabilityRecordResponse>> getAffordabilityRecordsByType(@PathVariable String type) {
        List<AffordabilityRecord> records = affordabilityRecordService.getAffordabilityRecordsByType(type);
        return ResponseEntity.ok(records.stream().map(this::mapToAffordabilityResponse).collect(Collectors.toList()));
    }

    @GetMapping("/affordability/status/{status}")
    public ResponseEntity<List<AffordabilityRecordResponse>> getAffordabilityRecordsByStatus(@PathVariable String status) {
        List<AffordabilityRecord> records = affordabilityRecordService.getAffordabilityRecordsByStatus(status);
        return ResponseEntity.ok(records.stream().map(this::mapToAffordabilityResponse).collect(Collectors.toList()));
    }

    @PutMapping("/affordability/{id}")
    public ResponseEntity<AffordabilityRecordResponse> updateAffordabilityRecord(@PathVariable String id, @RequestBody AffordabilityRecord record) {
        AffordabilityRecord updated = affordabilityRecordService.updateAffordabilityRecord(id, record);
        return ResponseEntity.ok(mapToAffordabilityResponse(updated));
    }

    @DeleteMapping("/affordability/{id}")
    public ResponseEntity<Void> deactivateAffordabilityRecord(@PathVariable String id) {
        affordabilityRecordService.deactivateAffordabilityRecord(id);
        return ResponseEntity.noContent().build();
    }

    // ========== SUSTAINABILITY RECORDS ==========

    @PostMapping("/sustainability")
    public ResponseEntity<SustainabilityRecordResponse> createSustainabilityRecord(@RequestBody SustainabilityRecord record) {
        SustainabilityRecord created = sustainabilityRecordService.createSustainabilityRecord(record);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToSustainabilityResponse(created));
    }

    @GetMapping("/sustainability/{id}")
    public ResponseEntity<SustainabilityRecordResponse> getSustainabilityRecord(@PathVariable String id) {
        SustainabilityRecord record = sustainabilityRecordService.getSustainabilityRecordById(id);
        return ResponseEntity.ok(mapToSustainabilityResponse(record));
    }

    @GetMapping("/sustainability/budget/{budgetProfileId}")
    public ResponseEntity<List<SustainabilityRecordResponse>> getSustainabilityRecordsByBudget(@PathVariable String budgetProfileId) {
        List<SustainabilityRecord> records = sustainabilityRecordService.getSustainabilityRecordsByBudget(budgetProfileId);
        return ResponseEntity.ok(records.stream().map(this::mapToSustainabilityResponse).collect(Collectors.toList()));
    }

    @GetMapping("/sustainability/student/{studentId}")
    public ResponseEntity<List<SustainabilityRecordResponse>> getSustainabilityRecordsByStudent(@PathVariable String studentId) {
        List<SustainabilityRecord> records = sustainabilityRecordService.getSustainabilityRecordsByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToSustainabilityResponse).collect(Collectors.toList()));
    }

    @GetMapping("/sustainability/type/{type}")
    public ResponseEntity<List<SustainabilityRecordResponse>> getSustainabilityRecordsByType(@PathVariable String type) {
        List<SustainabilityRecord> records = sustainabilityRecordService.getSustainabilityRecordsByType(type);
        return ResponseEntity.ok(records.stream().map(this::mapToSustainabilityResponse).collect(Collectors.toList()));
    }

    @GetMapping("/sustainability/status/{status}")
    public ResponseEntity<List<SustainabilityRecordResponse>> getSustainabilityRecordsByStatus(@PathVariable String status) {
        List<SustainabilityRecord> records = sustainabilityRecordService.getSustainabilityRecordsByStatus(status);
        return ResponseEntity.ok(records.stream().map(this::mapToSustainabilityResponse).collect(Collectors.toList()));
    }

    @PutMapping("/sustainability/{id}")
    public ResponseEntity<SustainabilityRecordResponse> updateSustainabilityRecord(@PathVariable String id, @RequestBody SustainabilityRecord record) {
        SustainabilityRecord updated = sustainabilityRecordService.updateSustainabilityRecord(id, record);
        return ResponseEntity.ok(mapToSustainabilityResponse(updated));
    }

    @DeleteMapping("/sustainability/{id}")
    public ResponseEntity<Void> deactivateSustainabilityRecord(@PathVariable String id) {
        sustainabilityRecordService.deactivateSustainabilityRecord(id);
        return ResponseEntity.noContent().build();
    }

    // ========== RISK ASSESSMENTS ==========

    @PostMapping("/risk")
    public ResponseEntity<RiskAssessmentResponse> createRiskAssessment(@RequestBody RiskAssessment assessment) {
        RiskAssessment created = riskAssessmentService.createRiskAssessment(assessment);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToRiskResponse(created));
    }

    @GetMapping("/risk/{id}")
    public ResponseEntity<RiskAssessmentResponse> getRiskAssessment(@PathVariable String id) {
        RiskAssessment assessment = riskAssessmentService.getRiskAssessmentById(id);
        return ResponseEntity.ok(mapToRiskResponse(assessment));
    }

    @GetMapping("/risk/budget/{budgetProfileId}")
    public ResponseEntity<List<RiskAssessmentResponse>> getRiskAssessmentsByBudget(@PathVariable String budgetProfileId) {
        List<RiskAssessment> assessments = riskAssessmentService.getRiskAssessmentsByBudget(budgetProfileId);
        return ResponseEntity.ok(assessments.stream().map(this::mapToRiskResponse).collect(Collectors.toList()));
    }

    @GetMapping("/risk/student/{studentId}")
    public ResponseEntity<List<RiskAssessmentResponse>> getRiskAssessmentsByStudent(@PathVariable String studentId) {
        List<RiskAssessment> assessments = riskAssessmentService.getRiskAssessmentsByStudent(studentId);
        return ResponseEntity.ok(assessments.stream().map(this::mapToRiskResponse).collect(Collectors.toList()));
    }

    @GetMapping("/risk/type/{riskType}")
    public ResponseEntity<List<RiskAssessmentResponse>> getRiskAssessmentsByType(@PathVariable String riskType) {
        List<RiskAssessment> assessments = riskAssessmentService.getRiskAssessmentsByType(riskType);
        return ResponseEntity.ok(assessments.stream().map(this::mapToRiskResponse).collect(Collectors.toList()));
    }

    @GetMapping("/risk/level/{riskLevel}")
    public ResponseEntity<List<RiskAssessmentResponse>> getRiskAssessmentsByLevel(@PathVariable String riskLevel) {
        List<RiskAssessment> assessments = riskAssessmentService.getRiskAssessmentsByLevel(riskLevel);
        return ResponseEntity.ok(assessments.stream().map(this::mapToRiskResponse).collect(Collectors.toList()));
    }

    @PutMapping("/risk/{id}")
    public ResponseEntity<RiskAssessmentResponse> updateRiskAssessment(@PathVariable String id, @RequestBody RiskAssessment assessment) {
        RiskAssessment updated = riskAssessmentService.updateRiskAssessment(id, assessment);
        return ResponseEntity.ok(mapToRiskResponse(updated));
    }

    @DeleteMapping("/risk/{id}")
    public ResponseEntity<Void> deactivateRiskAssessment(@PathVariable String id) {
        riskAssessmentService.deactivateRiskAssessment(id);
        return ResponseEntity.noContent().build();
    }

    // ========== BUDGET POLICIES ==========

    @PostMapping("/policies")
    public ResponseEntity<BudgetPolicyResponse> createBudgetPolicy(@RequestBody BudgetPolicy policy) {
        BudgetPolicy created = budgetPolicyService.createBudgetPolicy(policy);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToPolicyResponse(created));
    }

    @GetMapping("/policies/{id}")
    public ResponseEntity<BudgetPolicyResponse> getBudgetPolicy(@PathVariable String id) {
        BudgetPolicy policy = budgetPolicyService.getBudgetPolicyById(id);
        return ResponseEntity.ok(mapToPolicyResponse(policy));
    }

    @GetMapping("/policies/university/{universityId}")
    public ResponseEntity<List<BudgetPolicyResponse>> getBudgetPoliciesByUniversity(@PathVariable String universityId) {
        List<BudgetPolicy> policies = budgetPolicyService.getBudgetPoliciesByUniversity(universityId);
        return ResponseEntity.ok(policies.stream().map(this::mapToPolicyResponse).collect(Collectors.toList()));
    }

    @GetMapping("/policies/country/{countryCode}")
    public ResponseEntity<List<BudgetPolicyResponse>> getBudgetPoliciesByCountry(@PathVariable String countryCode) {
        List<BudgetPolicy> policies = budgetPolicyService.getBudgetPoliciesByCountry(countryCode);
        return ResponseEntity.ok(policies.stream().map(this::mapToPolicyResponse).collect(Collectors.toList()));
    }

    @GetMapping("/policies/type/{policyType}")
    public ResponseEntity<List<BudgetPolicyResponse>> getBudgetPoliciesByType(@PathVariable String policyType) {
        List<BudgetPolicy> policies = budgetPolicyService.getBudgetPoliciesByType(policyType);
        return ResponseEntity.ok(policies.stream().map(this::mapToPolicyResponse).collect(Collectors.toList()));
    }

    @GetMapping("/policies/effect/{effect}")
    public ResponseEntity<List<BudgetPolicyResponse>> getBudgetPoliciesByEffect(@PathVariable String effect) {
        List<BudgetPolicy> policies = budgetPolicyService.getBudgetPoliciesByEffect(effect);
        return ResponseEntity.ok(policies.stream().map(this::mapToPolicyResponse).collect(Collectors.toList()));
    }

    @PutMapping("/policies/{id}")
    public ResponseEntity<BudgetPolicyResponse> updateBudgetPolicy(@PathVariable String id, @RequestBody BudgetPolicy policy) {
        BudgetPolicy updated = budgetPolicyService.updateBudgetPolicy(id, policy);
        return ResponseEntity.ok(mapToPolicyResponse(updated));
    }

    @DeleteMapping("/policies/{id}")
    public ResponseEntity<Void> deactivateBudgetPolicy(@PathVariable String id) {
        budgetPolicyService.deactivateBudgetPolicy(id);
        return ResponseEntity.noContent().build();
    }

    // ========== BUDGET LIFECYCLE ==========

    @PostMapping("/lifecycle")
    public ResponseEntity<BudgetLifecycleRecordResponse> createLifecycleRecord(@RequestBody BudgetLifecycleRecord record) {
        BudgetLifecycleRecord created = budgetLifecycleRecordService.createLifecycleRecord(record);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToLifecycleResponse(created));
    }

    @GetMapping("/lifecycle/{id}")
    public ResponseEntity<BudgetLifecycleRecordResponse> getLifecycleRecord(@PathVariable String id) {
        BudgetLifecycleRecord record = budgetLifecycleRecordService.getLifecycleRecordById(id);
        return ResponseEntity.ok(mapToLifecycleResponse(record));
    }

    @GetMapping("/lifecycle/budget/{budgetProfileId}")
    public ResponseEntity<List<BudgetLifecycleRecordResponse>> getLifecycleRecordsByBudget(@PathVariable String budgetProfileId) {
        List<BudgetLifecycleRecord> records = budgetLifecycleRecordService.getLifecycleRecordsByBudget(budgetProfileId);
        return ResponseEntity.ok(records.stream().map(this::mapToLifecycleResponse).collect(Collectors.toList()));
    }

    @GetMapping("/lifecycle/student/{studentId}")
    public ResponseEntity<List<BudgetLifecycleRecordResponse>> getLifecycleRecordsByStudent(@PathVariable String studentId) {
        List<BudgetLifecycleRecord> records = budgetLifecycleRecordService.getLifecycleRecordsByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToLifecycleResponse).collect(Collectors.toList()));
    }

    @PutMapping("/lifecycle/{id}")
    public ResponseEntity<BudgetLifecycleRecordResponse> updateLifecycleStatus(
            @PathVariable String id,
            @RequestParam String newStatus,
            @RequestParam(required = false) String reason,
            @RequestParam String changedBy) {
        BudgetLifecycleRecord updated = budgetLifecycleRecordService.updateLifecycleStatus(id, newStatus, reason, changedBy);
        return ResponseEntity.ok(mapToLifecycleResponse(updated));
    }

    @DeleteMapping("/lifecycle/{id}")
    public ResponseEntity<Void> deactivateLifecycleRecord(@PathVariable String id) {
        budgetLifecycleRecordService.deactivateLifecycleRecord(id);
        return ResponseEntity.noContent().build();
    }

    // ========== MAPPING METHODS ==========

    private BudgetProfileResponse mapToBudgetResponse(BudgetProfile profile) {
        return BudgetProfileResponse.builder()
                .id(profile.getId())
                .studentId(profile.getStudentId())
                .universityId(profile.getUniversityId())
                .programId(profile.getProgramId())
                .countryCode(profile.getCountryCode())
                .currencyCode(profile.getCurrencyCode())
                .academicYear(profile.getAcademicYear())
                .academicDuration(profile.getAcademicDuration())
                .monthlyBudget(profile.getMonthlyBudget())
                .annualBudget(profile.getAnnualBudget())
                .totalEducationalCost(profile.getTotalEducationalCost())
                .totalLivingExpense(profile.getTotalLivingExpense())
                .totalEmergencyReserve(profile.getTotalEmergencyReserve())
                .availableFunds(profile.getAvailableFunds())
                .financialGap(profile.getFinancialGap())
                .affordabilityStatus(profile.getAffordabilityStatus())
                .riskLevel(profile.getRiskLevel())
                .isFinalized(profile.getIsFinalized())
                .finalizedAt(profile.getFinalizedAt())
                .isActive(profile.getIsActive())
                .build();
    }

    private AffordabilityRecordResponse mapToAffordabilityResponse(AffordabilityRecord record) {
        return AffordabilityRecordResponse.builder()
                .id(record.getId())
                .budgetProfileId(record.getBudgetProfileId())
                .studentId(record.getStudentId())
                .universityId(record.getUniversityId())
                .countryCode(record.getCountryCode())
                .affordabilityType(record.getAffordabilityType())
                .affordabilityStatus(record.getAffordabilityStatus())
                .affordabilityScore(record.getAffordabilityScore())
                .educationalAffordability(record.getEducationalAffordability())
                .livingAffordability(record.getLivingAffordability())
                .emergencyAffordability(record.getEmergencyAffordability())
                .totalAffordability(record.getTotalAffordability())
                .affordabilityNotes(record.getAffordabilityNotes())
                .assessedBy(record.getAssessedBy())
                .assessedAt(record.getAssessedAt())
                .isActive(record.getIsActive())
                .build();
    }

    private SustainabilityRecordResponse mapToSustainabilityResponse(SustainabilityRecord record) {
        return SustainabilityRecordResponse.builder()
                .id(record.getId())
                .budgetProfileId(record.getBudgetProfileId())
                .studentId(record.getStudentId())
                .universityId(record.getUniversityId())
                .countryCode(record.getCountryCode())
                .sustainabilityType(record.getSustainabilityType())
                .sustainabilityStatus(record.getSustainabilityStatus())
                .sustainabilityScore(record.getSustainabilityScore())
                .monthlySustainability(record.getMonthlySustainability())
                .academicSustainability(record.getAcademicSustainability())
                .emergencySustainability(record.getEmergencySustainability())
                .longtermSustainability(record.getLongtermSustainability())
                .sustainabilityNotes(record.getSustainabilityNotes())
                .assessedBy(record.getAssessedBy())
                .assessedAt(record.getAssessedAt())
                .isActive(record.getIsActive())
                .build();
    }

    private RiskAssessmentResponse mapToRiskResponse(RiskAssessment assessment) {
        return RiskAssessmentResponse.builder()
                .id(assessment.getId())
                .budgetProfileId(assessment.getBudgetProfileId())
                .studentId(assessment.getStudentId())
                .universityId(assessment.getUniversityId())
                .countryCode(assessment.getCountryCode())
                .riskType(assessment.getRiskType())
                .riskLevel(assessment.getRiskLevel())
                .riskScore(assessment.getRiskScore())
                .budgetRisk(assessment.getBudgetRisk())
                .costRisk(assessment.getCostRisk())
                .countryRisk(assessment.getCountryRisk())
                .academicRisk(assessment.getAcademicRisk())
                .financialRisk(assessment.getFinancialRisk())
                .riskNotes(assessment.getRiskNotes())
                .assessedBy(assessment.getAssessedBy())
                .assessedAt(assessment.getAssessedAt())
                .isActive(assessment.getIsActive())
                .build();
    }

    private BudgetPolicyResponse mapToPolicyResponse(BudgetPolicy policy) {
        return BudgetPolicyResponse.builder()
                .id(policy.getId())
                .universityId(policy.getUniversityId())
                .countryCode(policy.getCountryCode())
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

    private BudgetLifecycleRecordResponse mapToLifecycleResponse(BudgetLifecycleRecord record) {
        return BudgetLifecycleRecordResponse.builder()
                .id(record.getId())
                .budgetProfileId(record.getBudgetProfileId())
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
