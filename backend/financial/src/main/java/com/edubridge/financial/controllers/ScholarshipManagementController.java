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
@RequestMapping("/api/v1/financial/scholarships")
@RequiredArgsConstructor
@Slf4j
public class ScholarshipManagementController {

    private final ScholarshipCatalogService scholarshipCatalogService;
    private final EligibilityRuleService eligibilityRuleService;
    private final AwardPolicyService awardPolicyService;
    private final AssessmentRecordService assessmentRecordService;
    private final LifecycleRecordService lifecycleRecordService;
    private final AuditRecordService auditRecordService;

    // ========== SCHOLARSHIP CATALOG ==========

    @PostMapping("/catalog")
    public ResponseEntity<ScholarshipCatalogResponse> createScholarship(@RequestBody ScholarshipCatalog scholarship) {
        ScholarshipCatalog created = scholarshipCatalogService.createScholarship(scholarship);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToCatalogResponse(created));
    }

    @GetMapping("/catalog/{id}")
    public ResponseEntity<ScholarshipCatalogResponse> getScholarship(@PathVariable String id) {
        ScholarshipCatalog scholarship = scholarshipCatalogService.getScholarshipById(id);
        return ResponseEntity.ok(mapToCatalogResponse(scholarship));
    }

    @GetMapping("/catalog")
    public ResponseEntity<List<ScholarshipCatalogResponse>> getAllScholarships() {
        List<ScholarshipCatalog> scholarships = scholarshipCatalogService.getAllActiveScholarships();
        return ResponseEntity.ok(scholarships.stream().map(this::mapToCatalogResponse).collect(Collectors.toList()));
    }

    @GetMapping("/catalog/type/{type}")
    public ResponseEntity<List<ScholarshipCatalogResponse>> getScholarshipsByType(@PathVariable String type) {
        List<ScholarshipCatalog> scholarships = scholarshipCatalogService.getScholarshipsByType(type);
        return ResponseEntity.ok(scholarships.stream().map(this::mapToCatalogResponse).collect(Collectors.toList()));
    }

    @GetMapping("/catalog/country/{countryCode}")
    public ResponseEntity<List<ScholarshipCatalogResponse>> getScholarshipsByCountry(@PathVariable String countryCode) {
        List<ScholarshipCatalog> scholarships = scholarshipCatalogService.getScholarshipsByCountry(countryCode);
        return ResponseEntity.ok(scholarships.stream().map(this::mapToCatalogResponse).collect(Collectors.toList()));
    }

    @GetMapping("/catalog/organization/{organization}")
    public ResponseEntity<List<ScholarshipCatalogResponse>> getScholarshipsByOrganization(@PathVariable String organization) {
        List<ScholarshipCatalog> scholarships = scholarshipCatalogService.getScholarshipsByOrganization(organization);
        return ResponseEntity.ok(scholarships.stream().map(this::mapToCatalogResponse).collect(Collectors.toList()));
    }

    @GetMapping("/catalog/renewable")
    public ResponseEntity<List<ScholarshipCatalogResponse>> getRenewableScholarships() {
        List<ScholarshipCatalog> scholarships = scholarshipCatalogService.getRenewableScholarships();
        return ResponseEntity.ok(scholarships.stream().map(this::mapToCatalogResponse).collect(Collectors.toList()));
    }

    @GetMapping("/catalog/available")
    public ResponseEntity<List<ScholarshipCatalogResponse>> getAvailableScholarships() {
        List<ScholarshipCatalog> scholarships = scholarshipCatalogService.getAvailableScholarships();
        return ResponseEntity.ok(scholarships.stream().map(this::mapToCatalogResponse).collect(Collectors.toList()));
    }

    @GetMapping("/catalog/open")
    public ResponseEntity<List<ScholarshipCatalogResponse>> getOpenDeadlineScholarships() {
        List<ScholarshipCatalog> scholarships = scholarshipCatalogService.getOpenDeadlineScholarships();
        return ResponseEntity.ok(scholarships.stream().map(this::mapToCatalogResponse).collect(Collectors.toList()));
    }

    @PutMapping("/catalog/{id}")
    public ResponseEntity<ScholarshipCatalogResponse> updateScholarship(@PathVariable String id, @RequestBody ScholarshipCatalog scholarship) {
        ScholarshipCatalog updated = scholarshipCatalogService.updateScholarship(id, scholarship);
        return ResponseEntity.ok(mapToCatalogResponse(updated));
    }

    @DeleteMapping("/catalog/{id}")
    public ResponseEntity<Void> deactivateScholarship(@PathVariable String id) {
        scholarshipCatalogService.deactivateScholarship(id);
        return ResponseEntity.noContent().build();
    }

    // ========== ELIGIBILITY RULES ==========

    @PostMapping("/eligibility")
    public ResponseEntity<EligibilityRuleResponse> createEligibilityRule(@RequestBody EligibilityRule rule) {
        EligibilityRule created = eligibilityRuleService.createEligibilityRule(rule);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToEligibilityResponse(created));
    }

    @GetMapping("/eligibility/{id}")
    public ResponseEntity<EligibilityRuleResponse> getEligibilityRule(@PathVariable String id) {
        EligibilityRule rule = eligibilityRuleService.getEligibilityRuleById(id);
        return ResponseEntity.ok(mapToEligibilityResponse(rule));
    }

    @GetMapping("/eligibility/scholarship/{scholarshipId}")
    public ResponseEntity<List<EligibilityRuleResponse>> getEligibilityRulesByScholarship(@PathVariable String scholarshipId) {
        List<EligibilityRule> rules = eligibilityRuleService.getEligibilityRulesByScholarship(scholarshipId);
        return ResponseEntity.ok(rules.stream().map(this::mapToEligibilityResponse).collect(Collectors.toList()));
    }

    @GetMapping("/eligibility/type/{ruleType}")
    public ResponseEntity<List<EligibilityRuleResponse>> getEligibilityRulesByType(@PathVariable String ruleType) {
        List<EligibilityRule> rules = eligibilityRuleService.getEligibilityRulesByType(ruleType);
        return ResponseEntity.ok(rules.stream().map(this::mapToEligibilityResponse).collect(Collectors.toList()));
    }

    @GetMapping("/eligibility/category/{ruleCategory}")
    public ResponseEntity<List<EligibilityRuleResponse>> getEligibilityRulesByCategory(@PathVariable String ruleCategory) {
        List<EligibilityRule> rules = eligibilityRuleService.getEligibilityRulesByCategory(ruleCategory);
        return ResponseEntity.ok(rules.stream().map(this::mapToEligibilityResponse).collect(Collectors.toList()));
    }

    @GetMapping("/eligibility/mandatory")
    public ResponseEntity<List<EligibilityRuleResponse>> getMandatoryEligibilityRules() {
        List<EligibilityRule> rules = eligibilityRuleService.getMandatoryEligibilityRules();
        return ResponseEntity.ok(rules.stream().map(this::mapToEligibilityResponse).collect(Collectors.toList()));
    }

    @PutMapping("/eligibility/{id}")
    public ResponseEntity<EligibilityRuleResponse> updateEligibilityRule(@PathVariable String id, @RequestBody EligibilityRule rule) {
        EligibilityRule updated = eligibilityRuleService.updateEligibilityRule(id, rule);
        return ResponseEntity.ok(mapToEligibilityResponse(updated));
    }

    @DeleteMapping("/eligibility/{id}")
    public ResponseEntity<Void> deactivateEligibilityRule(@PathVariable String id) {
        eligibilityRuleService.deactivateEligibilityRule(id);
        return ResponseEntity.noContent().build();
    }

    // ========== AWARD POLICIES ==========

    @PostMapping("/awards")
    public ResponseEntity<AwardPolicyResponse> createAwardPolicy(@RequestBody AwardPolicy policy) {
        AwardPolicy created = awardPolicyService.createAwardPolicy(policy);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToAwardResponse(created));
    }

    @GetMapping("/awards/{id}")
    public ResponseEntity<AwardPolicyResponse> getAwardPolicy(@PathVariable String id) {
        AwardPolicy policy = awardPolicyService.getAwardPolicyById(id);
        return ResponseEntity.ok(mapToAwardResponse(policy));
    }

    @GetMapping("/awards/scholarship/{scholarshipId}")
    public ResponseEntity<List<AwardPolicyResponse>> getAwardPoliciesByScholarship(@PathVariable String scholarshipId) {
        List<AwardPolicy> policies = awardPolicyService.getAwardPoliciesByScholarship(scholarshipId);
        return ResponseEntity.ok(policies.stream().map(this::mapToAwardResponse).collect(Collectors.toList()));
    }

    @GetMapping("/awards/type/{policyType}")
    public ResponseEntity<List<AwardPolicyResponse>> getAwardPoliciesByType(@PathVariable String policyType) {
        List<AwardPolicy> policies = awardPolicyService.getAwardPoliciesByType(policyType);
        return ResponseEntity.ok(policies.stream().map(this::mapToAwardResponse).collect(Collectors.toList()));
    }

    @GetMapping("/awards/coverage/{coverageType}")
    public ResponseEntity<List<AwardPolicyResponse>> getAwardPoliciesByCoverage(@PathVariable String coverageType) {
        List<AwardPolicy> policies = awardPolicyService.getAwardPoliciesByCoverage(coverageType);
        return ResponseEntity.ok(policies.stream().map(this::mapToAwardResponse).collect(Collectors.toList()));
    }

    @GetMapping("/awards/renewal-required")
    public ResponseEntity<List<AwardPolicyResponse>> getRenewalRequiredPolicies() {
        List<AwardPolicy> policies = awardPolicyService.getRenewalRequiredPolicies();
        return ResponseEntity.ok(policies.stream().map(this::mapToAwardResponse).collect(Collectors.toList()));
    }

    @PutMapping("/awards/{id}")
    public ResponseEntity<AwardPolicyResponse> updateAwardPolicy(@PathVariable String id, @RequestBody AwardPolicy policy) {
        AwardPolicy updated = awardPolicyService.updateAwardPolicy(id, policy);
        return ResponseEntity.ok(mapToAwardResponse(updated));
    }

    @DeleteMapping("/awards/{id}")
    public ResponseEntity<Void> deactivateAwardPolicy(@PathVariable String id) {
        awardPolicyService.deactivateAwardPolicy(id);
        return ResponseEntity.noContent().build();
    }

    // ========== ASSESSMENT RECORDS ==========

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

    @GetMapping("/assessments/student/{studentId}")
    public ResponseEntity<List<AssessmentRecordResponse>> getAssessmentsByStudent(@PathVariable String studentId) {
        List<AssessmentRecord> assessments = assessmentRecordService.getAssessmentsByStudent(studentId);
        return ResponseEntity.ok(assessments.stream().map(this::mapToAssessmentResponse).collect(Collectors.toList()));
    }

    @GetMapping("/assessments/scholarship/{scholarshipId}")
    public ResponseEntity<List<AssessmentRecordResponse>> getAssessmentsByScholarship(@PathVariable String scholarshipId) {
        List<AssessmentRecord> assessments = assessmentRecordService.getAssessmentsByScholarship(scholarshipId);
        return ResponseEntity.ok(assessments.stream().map(this::mapToAssessmentResponse).collect(Collectors.toList()));
    }

    @GetMapping("/assessments/status/{status}")
    public ResponseEntity<List<AssessmentRecordResponse>> getAssessmentsByStatus(@PathVariable String status) {
        List<AssessmentRecord> assessments = assessmentRecordService.getAssessmentsByStatus(status);
        return ResponseEntity.ok(assessments.stream().map(this::mapToAssessmentResponse).collect(Collectors.toList()));
    }

    @GetMapping("/assessments/risk/{riskLevel}")
    public ResponseEntity<List<AssessmentRecordResponse>> getAssessmentsByRiskLevel(@PathVariable String riskLevel) {
        List<AssessmentRecord> assessments = assessmentRecordService.getAssessmentsByRiskLevel(riskLevel);
        return ResponseEntity.ok(assessments.stream().map(this::mapToAssessmentResponse).collect(Collectors.toList()));
    }

    @GetMapping("/assessments/eligible/student/{studentId}")
    public ResponseEntity<List<AssessmentRecordResponse>> getEligibleAssessmentsByStudent(@PathVariable String studentId) {
        List<AssessmentRecord> assessments = assessmentRecordService.getEligibleAssessmentsByStudent(studentId);
        return ResponseEntity.ok(assessments.stream().map(this::mapToAssessmentResponse).collect(Collectors.toList()));
    }

    @GetMapping("/assessments/high-risk")
    public ResponseEntity<List<AssessmentRecordResponse>> getHighRiskAssessments() {
        List<AssessmentRecord> assessments = assessmentRecordService.getHighRiskAssessments();
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
            @RequestParam String assessedBy,
            @RequestParam(required = false) String notes) {
        AssessmentRecord completed = assessmentRecordService.completeAssessment(id, assessedBy, notes);
        return ResponseEntity.ok(mapToAssessmentResponse(completed));
    }

    @DeleteMapping("/assessments/{id}")
    public ResponseEntity<Void> deactivateAssessment(@PathVariable String id) {
        assessmentRecordService.deactivateAssessment(id);
        return ResponseEntity.noContent().build();
    }

    // ========== LIFECYCLE RECORDS ==========

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

    @GetMapping("/lifecycle/scholarship/{scholarshipId}")
    public ResponseEntity<List<LifecycleRecordResponse>> getLifecycleRecordsByScholarship(@PathVariable String scholarshipId) {
        List<LifecycleRecord> records = lifecycleRecordService.getLifecycleRecordsByScholarship(scholarshipId);
        return ResponseEntity.ok(records.stream().map(this::mapToLifecycleResponse).collect(Collectors.toList()));
    }

    @GetMapping("/lifecycle/student/{studentId}")
    public ResponseEntity<List<LifecycleRecordResponse>> getLifecycleRecordsByStudent(@PathVariable String studentId) {
        List<LifecycleRecord> records = lifecycleRecordService.getLifecycleRecordsByStudent(studentId);
        return ResponseEntity.ok(records.stream().map(this::mapToLifecycleResponse).collect(Collectors.toList()));
    }

    @GetMapping("/lifecycle/status/{status}")
    public ResponseEntity<List<LifecycleRecordResponse>> getLifecycleRecordsByStatus(@PathVariable String status) {
        List<LifecycleRecord> records = lifecycleRecordService.getLifecycleRecordsByStatus(status);
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

    // ========== AUDIT RECORDS ==========

    @PostMapping("/audits")
    public ResponseEntity<AuditRecordResponse> createAuditRecord(@RequestBody AuditRecord audit) {
        AuditRecord created = auditRecordService.createAuditRecord(audit);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToAuditResponse(created));
    }

    @GetMapping("/audits/{id}")
    public ResponseEntity<AuditRecordResponse> getAuditRecord(@PathVariable String id) {
        AuditRecord audit = auditRecordService.getAuditRecordById(id);
        return ResponseEntity.ok(mapToAuditResponse(audit));
    }

    @GetMapping("/audits/scholarship/{scholarshipId}")
    public ResponseEntity<List<AuditRecordResponse>> getAuditRecordsByScholarship(@PathVariable String scholarshipId) {
        List<AuditRecord> audits = auditRecordService.getAuditRecordsByScholarship(scholarshipId);
        return ResponseEntity.ok(audits.stream().map(this::mapToAuditResponse).collect(Collectors.toList()));
    }

    @GetMapping("/audits/student/{studentId}")
    public ResponseEntity<List<AuditRecordResponse>> getAuditRecordsByStudent(@PathVariable String studentId) {
        List<AuditRecord> audits = auditRecordService.getAuditRecordsByStudent(studentId);
        return ResponseEntity.ok(audits.stream().map(this::mapToAuditResponse).collect(Collectors.toList()));
    }

    @GetMapping("/audits/action/{action}")
    public ResponseEntity<List<AuditRecordResponse>> getAuditRecordsByAction(@PathVariable String action) {
        List<AuditRecord> audits = auditRecordService.getAuditRecordsByAction(action);
        return ResponseEntity.ok(audits.stream().map(this::mapToAuditResponse).collect(Collectors.toList()));
    }

    @DeleteMapping("/audits/{id}")
    public ResponseEntity<Void> deactivateAuditRecord(@PathVariable String id) {
        auditRecordService.deactivateAuditRecord(id);
        return ResponseEntity.noContent().build();
    }

    // ========== MAPPING METHODS ==========

    private ScholarshipCatalogResponse mapToCatalogResponse(ScholarshipCatalog scholarship) {
        return ScholarshipCatalogResponse.builder()
                .id(scholarship.getId())
                .name(scholarship.getName())
                .code(scholarship.getCode())
                .description(scholarship.getDescription())
                .scholarshipType(scholarship.getScholarshipType())
                .fundingOrganization(scholarship.getFundingOrganization())
                .countryCode(scholarship.getCountryCode())
                .currencyCode(scholarship.getCurrencyCode())
                .minAmount(scholarship.getMinAmount())
                .maxAmount(scholarship.getMaxAmount())
                .coverageType(scholarship.getCoverageType())
                .academicRequirement(scholarship.getAcademicRequirement())
                .financialRequirement(scholarship.getFinancialRequirement())
                .applicationDeadline(scholarship.getApplicationDeadline())
                .academicYear(scholarship.getAcademicYear())
                .isRenewable(scholarship.getIsRenewable())
                .renewalCriteria(scholarship.getRenewalCriteria())
                .maxRecipients(scholarship.getMaxRecipients())
                .currentRecipients(scholarship.getCurrentRecipients())
                .eligibleCountries(scholarship.getEligibleCountries())
                .eligiblePrograms(scholarship.getEligiblePrograms())
                .isActive(scholarship.getIsActive())
                .build();
    }

    private EligibilityRuleResponse mapToEligibilityResponse(EligibilityRule rule) {
        return EligibilityRuleResponse.builder()
                .id(rule.getId())
                .scholarshipId(rule.getScholarshipId())
                .name(rule.getName())
                .description(rule.getDescription())
                .ruleType(rule.getRuleType())
                .ruleCategory(rule.getRuleCategory())
                .ruleCondition(rule.getRuleCondition())
                .ruleValue(rule.getRuleValue())
                .operator(rule.getOperator())
                .priority(rule.getPriority())
                .isMandatory(rule.getIsMandatory())
                .isActive(rule.getIsActive())
                .build();
    }

    private AwardPolicyResponse mapToAwardResponse(AwardPolicy policy) {
        return AwardPolicyResponse.builder()
                .id(policy.getId())
                .scholarshipId(policy.getScholarshipId())
                .name(policy.getName())
                .description(policy.getDescription())
                .policyType(policy.getPolicyType())
                .coverageType(policy.getCoverageType())
                .amount(policy.getAmount())
                .percentage(policy.getPercentage())
                .maxDuration(policy.getMaxDuration())
                .renewalRequired(policy.getRenewalRequired())
                .minGpa(policy.getMinGpa())
                .minCredits(policy.getMinCredits())
                .conditions(policy.getConditions())
                .effectiveFrom(policy.getEffectiveFrom())
                .effectiveTo(policy.getEffectiveTo())
                .isActive(policy.getIsActive())
                .build();
    }

    private AssessmentRecordResponse mapToAssessmentResponse(AssessmentRecord assessment) {
        return AssessmentRecordResponse.builder()
                .id(assessment.getId())
                .studentId(assessment.getStudentId())
                .scholarshipId(assessment.getScholarshipId())
                .assessmentStatus(assessment.getAssessmentStatus())
                .academicScore(assessment.getAcademicScore())
                .financialScore(assessment.getFinancialScore())
                .eligibilityScore(assessment.getEligibilityScore())
                .overallScore(assessment.getOverallScore())
                .riskLevel(assessment.getRiskLevel())
                .assessmentNotes(assessment.getAssessmentNotes())
                .assessedBy(assessment.getAssessedBy())
                .assessedAt(assessment.getAssessedAt())
                .validUntil(assessment.getValidUntil())
                .isActive(assessment.getIsActive())
                .build();
    }

    private LifecycleRecordResponse mapToLifecycleResponse(LifecycleRecord record) {
        return LifecycleRecordResponse.builder()
                .id(record.getId())
                .scholarshipId(record.getScholarshipId())
                .studentId(record.getStudentId())
                .previousStatus(record.getPreviousStatus())
                .newStatus(record.getNewStatus())
                .reason(record.getReason())
                .changedBy(record.getChangedBy())
                .changedAt(record.getChangedAt())
                .isActive(record.getIsActive())
                .build();
    }

    private AuditRecordResponse mapToAuditResponse(AuditRecord audit) {
        return AuditRecordResponse.builder()
                .id(audit.getId())
                .scholarshipId(audit.getScholarshipId())
                .studentId(audit.getStudentId())
                .action(audit.getAction())
                .entityType(audit.getEntityType())
                .entityId(audit.getEntityId())
                .oldValue(audit.getOldValue())
                .newValue(audit.getNewValue())
                .performedBy(audit.getPerformedBy())
                .performedAt(audit.getPerformedAt())
                .isActive(audit.getIsActive())
                .build();
    }
}
