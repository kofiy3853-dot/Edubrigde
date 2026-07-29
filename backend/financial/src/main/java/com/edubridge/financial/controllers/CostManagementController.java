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
@RequestMapping("/api/v1/financial/cost-management")
@RequiredArgsConstructor
@Slf4j
public class CostManagementController {

    private final CostCategoryService costCategoryService;
    private final CostItemService costItemService;
    private final CostBreakdownService costBreakdownService;
    private final CostComparisonService costComparisonService;
    private final CostEstimateService costEstimateService;
    private final CostPolicyService costPolicyService;

    // ========== CATEGORIES ==========

    @PostMapping("/categories")
    public ResponseEntity<CostCategoryResponse> createCategory(@RequestBody CostCategory category) {
        CostCategory created = costCategoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToCategoryResponse(created));
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CostCategoryResponse> getCategory(@PathVariable String id) {
        CostCategory category = costCategoryService.getCategoryById(id);
        return ResponseEntity.ok(mapToCategoryResponse(category));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CostCategoryResponse>> getAllCategories() {
        List<CostCategory> categories = costCategoryService.getAllActiveCategories();
        return ResponseEntity.ok(categories.stream().map(this::mapToCategoryResponse).collect(Collectors.toList()));
    }

    @GetMapping("/categories/root")
    public ResponseEntity<List<CostCategoryResponse>> getRootCategories() {
        List<CostCategory> categories = costCategoryService.getRootCategories();
        return ResponseEntity.ok(categories.stream().map(this::mapToCategoryResponse).collect(Collectors.toList()));
    }

    @GetMapping("/categories/parent/{parentCode}")
    public ResponseEntity<List<CostCategoryResponse>> getSubCategories(@PathVariable String parentCode) {
        List<CostCategory> categories = costCategoryService.getSubCategories(parentCode);
        return ResponseEntity.ok(categories.stream().map(this::mapToCategoryResponse).collect(Collectors.toList()));
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<CostCategoryResponse> updateCategory(@PathVariable String id, @RequestBody CostCategory category) {
        CostCategory updated = costCategoryService.updateCategory(id, category);
        return ResponseEntity.ok(mapToCategoryResponse(updated));
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deactivateCategory(@PathVariable String id) {
        costCategoryService.deactivateCategory(id);
        return ResponseEntity.noContent().build();
    }

    // ========== COST ITEMS ==========

    @PostMapping("/items")
    public ResponseEntity<CostItemResponse> createCostItem(@RequestBody CostItem costItem) {
        CostItem created = costItemService.createCostItem(costItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToItemResponse(created));
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<CostItemResponse> getCostItem(@PathVariable String id) {
        CostItem item = costItemService.getCostItemById(id);
        return ResponseEntity.ok(mapToItemResponse(item));
    }

    @GetMapping("/items/university/{universityId}")
    public ResponseEntity<List<CostItemResponse>> getCostItemsByUniversity(@PathVariable String universityId) {
        List<CostItem> items = costItemService.getCostItemsByUniversity(universityId);
        return ResponseEntity.ok(items.stream().map(this::mapToItemResponse).collect(Collectors.toList()));
    }

    @GetMapping("/items/program/{programId}")
    public ResponseEntity<List<CostItemResponse>> getCostItemsByProgram(@PathVariable String programId) {
        List<CostItem> items = costItemService.getCostItemsByProgram(programId);
        return ResponseEntity.ok(items.stream().map(this::mapToItemResponse).collect(Collectors.toList()));
    }

    @GetMapping("/items/category/{categoryCode}")
    public ResponseEntity<List<CostItemResponse>> getCostItemsByCategory(@PathVariable String categoryCode) {
        List<CostItem> items = costItemService.getCostItemsByCategory(categoryCode);
        return ResponseEntity.ok(items.stream().map(this::mapToItemResponse).collect(Collectors.toList()));
    }

    @GetMapping("/items/university/{universityId}/category/{categoryCode}")
    public ResponseEntity<List<CostItemResponse>> getCostItemsByUniversityAndCategory(
            @PathVariable String universityId, @PathVariable String categoryCode) {
        List<CostItem> items = costItemService.getCostItemsByUniversityAndCategory(universityId, categoryCode);
        return ResponseEntity.ok(items.stream().map(this::mapToItemResponse).collect(Collectors.toList()));
    }

    @GetMapping("/items/university/{universityId}/year/{academicYear}")
    public ResponseEntity<List<CostItemResponse>> getCostItemsByUniversityAndYear(
            @PathVariable String universityId, @PathVariable Integer academicYear) {
        List<CostItem> items = costItemService.getCostItemsByUniversityAndYear(universityId, academicYear);
        return ResponseEntity.ok(items.stream().map(this::mapToItemResponse).collect(Collectors.toList()));
    }

    @GetMapping("/items/university/{universityId}/program/{programId}")
    public ResponseEntity<List<CostItemResponse>> getCostItemsByUniversityAndProgram(
            @PathVariable String universityId, @PathVariable String programId) {
        List<CostItem> items = costItemService.getCostItemsByUniversityAndProgram(universityId, programId);
        return ResponseEntity.ok(items.stream().map(this::mapToItemResponse).collect(Collectors.toList()));
    }

    @GetMapping("/items/university/{universityId}/mandatory")
    public ResponseEntity<List<CostItemResponse>> getMandatoryCostItems(@PathVariable String universityId) {
        List<CostItem> items = costItemService.getMandatoryCostItems(universityId);
        return ResponseEntity.ok(items.stream().map(this::mapToItemResponse).collect(Collectors.toList()));
    }

    @GetMapping("/items/university/{universityId}/optional")
    public ResponseEntity<List<CostItemResponse>> getOptionalCostItems(@PathVariable String universityId) {
        List<CostItem> items = costItemService.getOptionalCostItems(universityId);
        return ResponseEntity.ok(items.stream().map(this::mapToItemResponse).collect(Collectors.toList()));
    }

    @PutMapping("/items/{id}")
    public ResponseEntity<CostItemResponse> updateCostItem(@PathVariable String id, @RequestBody CostItem costItem) {
        CostItem updated = costItemService.updateCostItem(id, costItem);
        return ResponseEntity.ok(mapToItemResponse(updated));
    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> deactivateCostItem(@PathVariable String id) {
        costItemService.deactivateCostItem(id);
        return ResponseEntity.noContent().build();
    }

    // ========== COST BREAKDOWNS ==========

    @PostMapping("/breakdowns")
    public ResponseEntity<CostBreakdownResponse> createCostBreakdown(@RequestBody CostBreakdown breakdown) {
        CostBreakdown created = costBreakdownService.createCostBreakdown(breakdown);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToBreakdownResponse(created));
    }

    @GetMapping("/breakdowns/{id}")
    public ResponseEntity<CostBreakdownResponse> getCostBreakdown(@PathVariable String id) {
        CostBreakdown breakdown = costBreakdownService.getCostBreakdownById(id);
        return ResponseEntity.ok(mapToBreakdownResponse(breakdown));
    }

    @GetMapping("/breakdowns/university/{universityId}")
    public ResponseEntity<List<CostBreakdownResponse>> getCostBreakdownsByUniversity(@PathVariable String universityId) {
        List<CostBreakdown> breakdowns = costBreakdownService.getCostBreakdownsByUniversity(universityId);
        return ResponseEntity.ok(breakdowns.stream().map(this::mapToBreakdownResponse).collect(Collectors.toList()));
    }

    @GetMapping("/breakdowns/program/{programId}")
    public ResponseEntity<List<CostBreakdownResponse>> getCostBreakdownsByProgram(@PathVariable String programId) {
        List<CostBreakdown> breakdowns = costBreakdownService.getCostBreakdownsByProgram(programId);
        return ResponseEntity.ok(breakdowns.stream().map(this::mapToBreakdownResponse).collect(Collectors.toList()));
    }

    @GetMapping("/breakdowns/country/{countryCode}")
    public ResponseEntity<List<CostBreakdownResponse>> getCostBreakdownsByCountry(@PathVariable String countryCode) {
        List<CostBreakdown> breakdowns = costBreakdownService.getCostBreakdownsByCountry(countryCode);
        return ResponseEntity.ok(breakdowns.stream().map(this::mapToBreakdownResponse).collect(Collectors.toList()));
    }

    @GetMapping("/breakdowns/university/{universityId}/program/{programId}")
    public ResponseEntity<List<CostBreakdownResponse>> getCostBreakdownsByUniversityAndProgram(
            @PathVariable String universityId, @PathVariable String programId) {
        List<CostBreakdown> breakdowns = costBreakdownService.getCostBreakdownsByUniversityAndProgram(universityId, programId);
        return ResponseEntity.ok(breakdowns.stream().map(this::mapToBreakdownResponse).collect(Collectors.toList()));
    }

    @GetMapping("/breakdowns/country/{countryCode}/year/{academicYear}")
    public ResponseEntity<List<CostBreakdownResponse>> getCostBreakdownsByCountryAndYear(
            @PathVariable String countryCode, @PathVariable Integer academicYear) {
        List<CostBreakdown> breakdowns = costBreakdownService.getCostBreakdownsByCountryAndYear(countryCode, academicYear);
        return ResponseEntity.ok(breakdowns.stream().map(this::mapToBreakdownResponse).collect(Collectors.toList()));
    }

    @PutMapping("/breakdowns/{id}")
    public ResponseEntity<CostBreakdownResponse> updateCostBreakdown(@PathVariable String id, @RequestBody CostBreakdown breakdown) {
        CostBreakdown updated = costBreakdownService.updateCostBreakdown(id, breakdown);
        return ResponseEntity.ok(mapToBreakdownResponse(updated));
    }

    @DeleteMapping("/breakdowns/{id}")
    public ResponseEntity<Void> deactivateCostBreakdown(@PathVariable String id) {
        costBreakdownService.deactivateCostBreakdown(id);
        return ResponseEntity.noContent().build();
    }

    // ========== COST COMPARISONS ==========

    @PostMapping("/comparisons")
    public ResponseEntity<CostComparisonResponse> createCostComparison(@RequestBody CostComparison comparison) {
        CostComparison created = costComparisonService.createCostComparison(comparison);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToComparisonResponse(created));
    }

    @GetMapping("/comparisons/{id}")
    public ResponseEntity<CostComparisonResponse> getCostComparison(@PathVariable String id) {
        CostComparison comparison = costComparisonService.getCostComparisonById(id);
        return ResponseEntity.ok(mapToComparisonResponse(comparison));
    }

    @GetMapping("/comparisons/student/{studentId}")
    public ResponseEntity<List<CostComparisonResponse>> getCostComparisonsByStudent(@PathVariable String studentId) {
        List<CostComparison> comparisons = costComparisonService.getCostComparisonsByStudent(studentId);
        return ResponseEntity.ok(comparisons.stream().map(this::mapToComparisonResponse).collect(Collectors.toList()));
    }

    @GetMapping("/comparisons/student/{studentId}/completed")
    public ResponseEntity<List<CostComparisonResponse>> getCompletedComparisons(@PathVariable String studentId) {
        List<CostComparison> comparisons = costComparisonService.getCompletedComparisonsByStudent(studentId);
        return ResponseEntity.ok(comparisons.stream().map(this::mapToComparisonResponse).collect(Collectors.toList()));
    }

    @GetMapping("/comparisons/student/{studentId}/incomplete")
    public ResponseEntity<List<CostComparisonResponse>> getIncompleteComparisons(@PathVariable String studentId) {
        List<CostComparison> comparisons = costComparisonService.getIncompleteComparisonsByStudent(studentId);
        return ResponseEntity.ok(comparisons.stream().map(this::mapToComparisonResponse).collect(Collectors.toList()));
    }

    @PutMapping("/comparisons/{id}")
    public ResponseEntity<CostComparisonResponse> updateCostComparison(@PathVariable String id, @RequestBody CostComparison comparison) {
        CostComparison updated = costComparisonService.updateCostComparison(id, comparison);
        return ResponseEntity.ok(mapToComparisonResponse(updated));
    }

    @PostMapping("/comparisons/{id}/complete")
    public ResponseEntity<CostComparisonResponse> completeCostComparison(
            @PathVariable String id,
            @RequestParam String comparisonResult,
            @RequestParam String recommendedUniversityId) {
        CostComparison completed = costComparisonService.completeCostComparison(id, comparisonResult, recommendedUniversityId);
        return ResponseEntity.ok(mapToComparisonResponse(completed));
    }

    @DeleteMapping("/comparisons/{id}")
    public ResponseEntity<Void> deactivateCostComparison(@PathVariable String id) {
        costComparisonService.deactivateCostComparison(id);
        return ResponseEntity.noContent().build();
    }

    // ========== COST ESTIMATES ==========

    @PostMapping("/estimates")
    public ResponseEntity<CostEstimateResponse> createCostEstimate(@RequestBody CostEstimate estimate) {
        CostEstimate created = costEstimateService.createCostEstimate(estimate);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToEstimateResponse(created));
    }

    @GetMapping("/estimates/{id}")
    public ResponseEntity<CostEstimateResponse> getCostEstimate(@PathVariable String id) {
        CostEstimate estimate = costEstimateService.getCostEstimateById(id);
        return ResponseEntity.ok(mapToEstimateResponse(estimate));
    }

    @GetMapping("/estimates/student/{studentId}")
    public ResponseEntity<List<CostEstimateResponse>> getCostEstimatesByStudent(@PathVariable String studentId) {
        List<CostEstimate> estimates = costEstimateService.getCostEstimatesByStudent(studentId);
        return ResponseEntity.ok(estimates.stream().map(this::mapToEstimateResponse).collect(Collectors.toList()));
    }

    @GetMapping("/estimates/student/{studentId}/draft")
    public ResponseEntity<List<CostEstimateResponse>> getDraftEstimates(@PathVariable String studentId) {
        List<CostEstimate> estimates = costEstimateService.getDraftCostEstimatesByStudent(studentId);
        return ResponseEntity.ok(estimates.stream().map(this::mapToEstimateResponse).collect(Collectors.toList()));
    }

    @GetMapping("/estimates/student/{studentId}/finalized")
    public ResponseEntity<List<CostEstimateResponse>> getFinalizedEstimates(@PathVariable String studentId) {
        List<CostEstimate> estimates = costEstimateService.getFinalizedCostEstimatesByStudent(studentId);
        return ResponseEntity.ok(estimates.stream().map(this::mapToEstimateResponse).collect(Collectors.toList()));
    }

    @GetMapping("/estimates/risk/{riskLevel}")
    public ResponseEntity<List<CostEstimateResponse>> getEstimatesByRiskLevel(@PathVariable String riskLevel) {
        List<CostEstimate> estimates = costEstimateService.getCostEstimatesByRiskLevel(riskLevel);
        return ResponseEntity.ok(estimates.stream().map(this::mapToEstimateResponse).collect(Collectors.toList()));
    }

    @PutMapping("/estimates/{id}")
    public ResponseEntity<CostEstimateResponse> updateCostEstimate(@PathVariable String id, @RequestBody CostEstimate estimate) {
        CostEstimate updated = costEstimateService.updateCostEstimate(id, estimate);
        return ResponseEntity.ok(mapToEstimateResponse(updated));
    }

    @PostMapping("/estimates/{id}/finalize")
    public ResponseEntity<CostEstimateResponse> finalizeCostEstimate(@PathVariable String id) {
        CostEstimate finalized = costEstimateService.finalizeCostEstimate(id);
        return ResponseEntity.ok(mapToEstimateResponse(finalized));
    }

    @DeleteMapping("/estimates/{id}")
    public ResponseEntity<Void> deactivateCostEstimate(@PathVariable String id) {
        costEstimateService.deactivateCostEstimate(id);
        return ResponseEntity.noContent().build();
    }

    // ========== COST POLICIES ==========

    @PostMapping("/policies")
    public ResponseEntity<CostPolicyResponse> createCostPolicy(@RequestBody CostPolicy policy) {
        CostPolicy created = costPolicyService.createCostPolicy(policy);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToPolicyResponse(created));
    }

    @GetMapping("/policies/{id}")
    public ResponseEntity<CostPolicyResponse> getCostPolicy(@PathVariable String id) {
        CostPolicy policy = costPolicyService.getCostPolicyById(id);
        return ResponseEntity.ok(mapToPolicyResponse(policy));
    }

    @GetMapping("/policies/university/{universityId}")
    public ResponseEntity<List<CostPolicyResponse>> getCostPoliciesByUniversity(@PathVariable String universityId) {
        List<CostPolicy> policies = costPolicyService.getCostPoliciesByUniversity(universityId);
        return ResponseEntity.ok(policies.stream().map(this::mapToPolicyResponse).collect(Collectors.toList()));
    }

    @GetMapping("/policies/country/{countryCode}")
    public ResponseEntity<List<CostPolicyResponse>> getCostPoliciesByCountry(@PathVariable String countryCode) {
        List<CostPolicy> policies = costPolicyService.getCostPoliciesByCountry(countryCode);
        return ResponseEntity.ok(policies.stream().map(this::mapToPolicyResponse).collect(Collectors.toList()));
    }

    @GetMapping("/policies/type/{policyType}")
    public ResponseEntity<List<CostPolicyResponse>> getCostPoliciesByType(@PathVariable String policyType) {
        List<CostPolicy> policies = costPolicyService.getCostPoliciesByType(policyType);
        return ResponseEntity.ok(policies.stream().map(this::mapToPolicyResponse).collect(Collectors.toList()));
    }

    @GetMapping("/policies/category/{categoryCode}")
    public ResponseEntity<List<CostPolicyResponse>> getCostPoliciesByCategory(@PathVariable String categoryCode) {
        List<CostPolicy> policies = costPolicyService.getCostPoliciesByCategory(categoryCode);
        return ResponseEntity.ok(policies.stream().map(this::mapToPolicyResponse).collect(Collectors.toList()));
    }

    @GetMapping("/policies/university/{universityId}/type/{policyType}")
    public ResponseEntity<List<CostPolicyResponse>> getCostPoliciesByUniversityAndType(
            @PathVariable String universityId, @PathVariable String policyType) {
        List<CostPolicy> policies = costPolicyService.getCostPoliciesByUniversityAndType(universityId, policyType);
        return ResponseEntity.ok(policies.stream().map(this::mapToPolicyResponse).collect(Collectors.toList()));
    }

    @GetMapping("/policies/country/{countryCode}/category/{categoryCode}")
    public ResponseEntity<List<CostPolicyResponse>> getCostPoliciesByCountryAndCategory(
            @PathVariable String countryCode, @PathVariable String categoryCode) {
        List<CostPolicy> policies = costPolicyService.getCostPoliciesByCountryAndCategory(countryCode, categoryCode);
        return ResponseEntity.ok(policies.stream().map(this::mapToPolicyResponse).collect(Collectors.toList()));
    }

    @GetMapping("/policies/approval-required")
    public ResponseEntity<List<CostPolicyResponse>> getPoliciesRequiringApproval() {
        List<CostPolicy> policies = costPolicyService.getPoliciesRequiringApproval();
        return ResponseEntity.ok(policies.stream().map(this::mapToPolicyResponse).collect(Collectors.toList()));
    }

    @PutMapping("/policies/{id}")
    public ResponseEntity<CostPolicyResponse> updateCostPolicy(@PathVariable String id, @RequestBody CostPolicy policy) {
        CostPolicy updated = costPolicyService.updateCostPolicy(id, policy);
        return ResponseEntity.ok(mapToPolicyResponse(updated));
    }

    @DeleteMapping("/policies/{id}")
    public ResponseEntity<Void> deactivateCostPolicy(@PathVariable String id) {
        costPolicyService.deactivateCostPolicy(id);
        return ResponseEntity.noContent().build();
    }

    // ========== MAPPING METHODS ==========

    private CostCategoryResponse mapToCategoryResponse(CostCategory category) {
        return CostCategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .code(category.getCode())
                .description(category.getDescription())
                .parentCode(category.getParentCode())
                .sortOrder(category.getSortOrder())
                .isActive(category.getIsActive())
                .build();
    }

    private CostItemResponse mapToItemResponse(CostItem item) {
        return CostItemResponse.builder()
                .id(item.getId())
                .universityId(item.getUniversityId())
                .programId(item.getProgramId())
                .categoryCode(item.getCategoryCode())
                .name(item.getName())
                .description(item.getDescription())
                .amount(item.getAmount())
                .currencyCode(item.getCurrencyCode())
                .frequency(item.getFrequency())
                .academicYear(item.getAcademicYear())
                .isMandatory(item.getIsMandatory())
                .isEstimated(item.getIsEstimated())
                .isRefundable(item.getIsRefundable())
                .refundPolicy(item.getRefundPolicy())
                .source(item.getSource())
                .effectiveFrom(item.getEffectiveFrom())
                .effectiveTo(item.getEffectiveTo())
                .isActive(item.getIsActive())
                .build();
    }

    private CostBreakdownResponse mapToBreakdownResponse(CostBreakdown breakdown) {
        return CostBreakdownResponse.builder()
                .id(breakdown.getId())
                .universityId(breakdown.getUniversityId())
                .programId(breakdown.getProgramId())
                .countryCode(breakdown.getCountryCode())
                .name(breakdown.getName())
                .description(breakdown.getDescription())
                .academicYear(breakdown.getAcademicYear())
                .totalTuition(breakdown.getTotalTuition())
                .totalAccommodation(breakdown.getTotalAccommodation())
                .totalLivingExpenses(breakdown.getTotalLivingExpenses())
                .totalOtherCosts(breakdown.getTotalOtherCosts())
                .grandTotal(breakdown.getGrandTotal())
                .currencyCode(breakdown.getCurrencyCode())
                .isEstimated(breakdown.getIsEstimated())
                .source(breakdown.getSource())
                .validFrom(breakdown.getValidFrom())
                .validTo(breakdown.getValidTo())
                .isActive(breakdown.getIsActive())
                .build();
    }

    private CostComparisonResponse mapToComparisonResponse(CostComparison comparison) {
        return CostComparisonResponse.builder()
                .id(comparison.getId())
                .studentId(comparison.getStudentId())
                .name(comparison.getName())
                .description(comparison.getDescription())
                .countryCode(comparison.getCountryCode())
                .academicYear(comparison.getAcademicYear())
                .currencyCode(comparison.getCurrencyCode())
                .university1Id(comparison.getUniversity1Id())
                .university1ProgramId(comparison.getUniversity1ProgramId())
                .university1TotalCost(comparison.getUniversity1TotalCost())
                .university2Id(comparison.getUniversity2Id())
                .university2ProgramId(comparison.getUniversity2ProgramId())
                .university2TotalCost(comparison.getUniversity2TotalCost())
                .university3Id(comparison.getUniversity3Id())
                .university3ProgramId(comparison.getUniversity3ProgramId())
                .university3TotalCost(comparison.getUniversity3TotalCost())
                .comparisonResult(comparison.getComparisonResult())
                .recommendedUniversityId(comparison.getRecommendedUniversityId())
                .isCompleted(comparison.getIsCompleted())
                .isActive(comparison.getIsActive())
                .build();
    }

    private CostEstimateResponse mapToEstimateResponse(CostEstimate estimate) {
        return CostEstimateResponse.builder()
                .id(estimate.getId())
                .studentId(estimate.getStudentId())
                .universityId(estimate.getUniversityId())
                .programId(estimate.getProgramId())
                .countryCode(estimate.getCountryCode())
                .currencyCode(estimate.getCurrencyCode())
                .academicYear(estimate.getAcademicYear())
                .totalTuition(estimate.getTotalTuition())
                .totalAccommodation(estimate.getTotalAccommodation())
                .totalLivingExpenses(estimate.getTotalLivingExpenses())
                .totalOtherCosts(estimate.getTotalOtherCosts())
                .grandTotal(estimate.getGrandTotal())
                .availableFunds(estimate.getAvailableFunds())
                .financialGap(estimate.getFinancialGap())
                .affordabilityScore(estimate.getAffordabilityScore())
                .riskLevel(estimate.getRiskLevel())
                .isFinalized(estimate.getIsFinalized())
                .finalizedAt(estimate.getFinalizedAt())
                .isActive(estimate.getIsActive())
                .build();
    }

    private CostPolicyResponse mapToPolicyResponse(CostPolicy policy) {
        return CostPolicyResponse.builder()
                .id(policy.getId())
                .universityId(policy.getUniversityId())
                .countryCode(policy.getCountryCode())
                .categoryCode(policy.getCategoryCode())
                .name(policy.getName())
                .description(policy.getDescription())
                .policyType(policy.getPolicyType())
                .policyRule(policy.getPolicyRule())
                .discountPercentage(policy.getDiscountPercentage())
                .discountAmount(policy.getDiscountAmount())
                .minAmount(policy.getMinAmount())
                .maxDiscount(policy.getMaxDiscount())
                .effectiveFrom(policy.getEffectiveFrom())
                .effectiveTo(policy.getEffectiveTo())
                .isActive(policy.getIsActive())
                .requiresApproval(policy.getRequiresApproval())
                .build();
    }
}
