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
@RequestMapping("/api/v1/academic/programs")
@RequiredArgsConstructor
public class ProgramManagementController {

    private final AcademicLevelService academicLevelService;
    private final ProgramRequirementService programRequirementService;
    private final DurationPolicyService durationPolicyService;
    private final ProgramLifecycleService programLifecycleService;

    // ========== Academic Level Endpoints ==========

    @PostMapping("/levels")
    public ResponseEntity<ApiResponse<AcademicLevelResponse>> createAcademicLevel(
            @RequestBody AcademicLevel level) {
        AcademicLevel created = academicLevelService.createAcademicLevel(level);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success(toAcademicLevelResponse(created)));
    }

    @GetMapping("/levels")
    public ResponseEntity<ApiResponse<List<AcademicLevelResponse>>> getAllAcademicLevels() {
        List<AcademicLevelResponse> levels = academicLevelService.getAllAcademicLevels().stream()
            .map(this::toAcademicLevelResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(levels));
    }

    @GetMapping("/levels/active")
    public ResponseEntity<ApiResponse<List<AcademicLevelResponse>>> getActiveAcademicLevels() {
        List<AcademicLevelResponse> levels = academicLevelService.getActiveAcademicLevels().stream()
            .map(this::toAcademicLevelResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(levels));
    }

    @GetMapping("/levels/ordered")
    public ResponseEntity<ApiResponse<List<AcademicLevelResponse>>> getOrderedAcademicLevels() {
        List<AcademicLevelResponse> levels = academicLevelService.getOrderedAcademicLevels().stream()
            .map(this::toAcademicLevelResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(levels));
    }

    @GetMapping("/levels/{id}")
    public ResponseEntity<ApiResponse<AcademicLevelResponse>> getAcademicLevelById(@PathVariable String id) {
        AcademicLevel level = academicLevelService.getAcademicLevelById(id);
        return ResponseEntity.ok(ApiResponse.success(toAcademicLevelResponse(level)));
    }

    @GetMapping("/levels/code/{code}")
    public ResponseEntity<ApiResponse<AcademicLevelResponse>> getAcademicLevelByCode(@PathVariable String code) {
        AcademicLevel level = academicLevelService.getAcademicLevelByCode(code);
        return ResponseEntity.ok(ApiResponse.success(toAcademicLevelResponse(level)));
    }

    @PutMapping("/levels/{id}")
    public ResponseEntity<ApiResponse<AcademicLevelResponse>> updateAcademicLevel(
            @PathVariable String id, @RequestBody AcademicLevel level) {
        AcademicLevel updated = academicLevelService.updateAcademicLevel(id, level);
        return ResponseEntity.ok(ApiResponse.success(toAcademicLevelResponse(updated)));
    }

    @PostMapping("/levels/{id}/deactivate")
    public ResponseEntity<ApiResponse<AcademicLevelResponse>> deactivateAcademicLevel(@PathVariable String id) {
        AcademicLevel deactivated = academicLevelService.deactivateAcademicLevel(id);
        return ResponseEntity.ok(ApiResponse.success(toAcademicLevelResponse(deactivated)));
    }

    @PostMapping("/levels/{id}/activate")
    public ResponseEntity<ApiResponse<AcademicLevelResponse>> activateAcademicLevel(@PathVariable String id) {
        AcademicLevel activated = academicLevelService.activateAcademicLevel(id);
        return ResponseEntity.ok(ApiResponse.success(toAcademicLevelResponse(activated)));
    }

    // ========== Program Requirement Endpoints ==========

    @PostMapping("/{programId}/requirements")
    public ResponseEntity<ApiResponse<ProgramRequirementResponse>> createProgramRequirement(
            @PathVariable String programId, @RequestBody ProgramRequirement requirement) {
        requirement.setProgramId(programId);
        ProgramRequirement created = programRequirementService.createProgramRequirement(requirement);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success(toProgramRequirementResponse(created)));
    }

    @GetMapping("/{programId}/requirements")
    public ResponseEntity<ApiResponse<List<ProgramRequirementResponse>>> getRequirementsByProgram(
            @PathVariable String programId) {
        List<ProgramRequirementResponse> requirements = programRequirementService.getRequirementsByProgram(programId)
            .stream()
            .map(this::toProgramRequirementResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(requirements));
    }

    @GetMapping("/{programId}/requirements/type/{type}")
    public ResponseEntity<ApiResponse<List<ProgramRequirementResponse>>> getRequirementsByType(
            @PathVariable String programId, @PathVariable String type) {
        List<ProgramRequirementResponse> requirements = programRequirementService.getRequirementsByType(type)
            .stream()
            .filter(r -> r.getProgramId().equals(programId))
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(requirements));
    }

    @GetMapping("/{programId}/requirements/mandatory")
    public ResponseEntity<ApiResponse<List<ProgramRequirementResponse>>> getMandatoryRequirements(
            @PathVariable String programId) {
        List<ProgramRequirementResponse> requirements = programRequirementService.getRequirementsByProgram(programId)
            .stream()
            .filter(r -> r.getIsMandatory())
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(requirements));
    }

    @PutMapping("/{programId}/requirements/{id}")
    public ResponseEntity<ApiResponse<ProgramRequirementResponse>> updateProgramRequirement(
            @PathVariable String programId, @PathVariable String id,
            @RequestBody ProgramRequirement requirement) {
        requirement.setProgramId(programId);
        ProgramRequirement updated = programRequirementService.updateProgramRequirement(id, requirement);
        return ResponseEntity.ok(ApiResponse.success(toProgramRequirementResponse(updated)));
    }

    @PostMapping("/{programId}/requirements/{id}/deactivate")
    public ResponseEntity<ApiResponse<ProgramRequirementResponse>> deactivateProgramRequirement(
            @PathVariable String programId, @PathVariable String id) {
        ProgramRequirement deactivated = programRequirementService.deactivateProgramRequirement(id);
        return ResponseEntity.ok(ApiResponse.success(toProgramRequirementResponse(deactivated)));
    }

    @PostMapping("/{programId}/requirements/{id}/activate")
    public ResponseEntity<ApiResponse<ProgramRequirementResponse>> activateProgramRequirement(
            @PathVariable String programId, @PathVariable String id) {
        ProgramRequirement activated = programRequirementService.activateProgramRequirement(id);
        return ResponseEntity.ok(ApiResponse.success(toProgramRequirementResponse(activated)));
    }

    // ========== Duration Policy Endpoints ==========

    @PostMapping("/{programId}/durations")
    public ResponseEntity<ApiResponse<DurationPolicyResponse>> createDurationPolicy(
            @PathVariable String programId, @RequestBody DurationPolicy policy) {
        policy.setProgramId(programId);
        DurationPolicy created = durationPolicyService.createDurationPolicy(policy);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success(toDurationPolicyResponse(created)));
    }

    @GetMapping("/{programId}/durations")
    public ResponseEntity<ApiResponse<List<DurationPolicyResponse>>> getPoliciesByProgram(
            @PathVariable String programId) {
        List<DurationPolicyResponse> policies = durationPolicyService.getPoliciesByProgram(programId)
            .stream()
            .map(this::toDurationPolicyResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(policies));
    }

    @GetMapping("/{programId}/durations/type/{type}")
    public ResponseEntity<ApiResponse<List<DurationPolicyResponse>>> getPoliciesByType(
            @PathVariable String programId, @PathVariable String type) {
        List<DurationPolicyResponse> policies = durationPolicyService.getPoliciesByType(type)
            .stream()
            .filter(p -> p.getProgramId().equals(programId))
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(policies));
    }

    @PutMapping("/{programId}/durations/{id}")
    public ResponseEntity<ApiResponse<DurationPolicyResponse>> updateDurationPolicy(
            @PathVariable String programId, @PathVariable String id,
            @RequestBody DurationPolicy policy) {
        policy.setProgramId(programId);
        DurationPolicy updated = durationPolicyService.updateDurationPolicy(id, policy);
        return ResponseEntity.ok(ApiResponse.success(toDurationPolicyResponse(updated)));
    }

    @PostMapping("/{programId}/durations/{id}/deactivate")
    public ResponseEntity<ApiResponse<DurationPolicyResponse>> deactivateDurationPolicy(
            @PathVariable String programId, @PathVariable String id) {
        DurationPolicy deactivated = durationPolicyService.deactivateDurationPolicy(id);
        return ResponseEntity.ok(ApiResponse.success(toDurationPolicyResponse(deactivated)));
    }

    @PostMapping("/{programId}/durations/{id}/activate")
    public ResponseEntity<ApiResponse<DurationPolicyResponse>> activateDurationPolicy(
            @PathVariable String programId, @PathVariable String id) {
        DurationPolicy activated = durationPolicyService.activateDurationPolicy(id);
        return ResponseEntity.ok(ApiResponse.success(toDurationPolicyResponse(activated)));
    }

    // ========== Program Lifecycle Endpoints ==========

    @PostMapping("/{programId}/lifecycle")
    public ResponseEntity<ApiResponse<ProgramLifecycleResponse>> createLifecycleRecord(
            @PathVariable String programId, @RequestBody ProgramLifecycle lifecycle) {
        lifecycle.setProgramId(programId);
        ProgramLifecycle created = programLifecycleService.createLifecycleRecord(lifecycle);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success(toProgramLifecycleResponse(created)));
    }

    @GetMapping("/{programId}/lifecycle")
    public ResponseEntity<ApiResponse<List<ProgramLifecycleResponse>>> getLifecycleRecords(
            @PathVariable String programId) {
        List<ProgramLifecycleResponse> records = programLifecycleService.getLifecycleRecordsByProgram(programId)
            .stream()
            .map(this::toProgramLifecycleResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(records));
    }

    @GetMapping("/{programId}/lifecycle/current")
    public ResponseEntity<ApiResponse<ProgramLifecycleResponse>> getCurrentLifecycle(
            @PathVariable String programId) {
        return programLifecycleService.getCurrentLifecycleByProgram(programId)
            .map(lifecycle -> ResponseEntity.ok(ApiResponse.success(toProgramLifecycleResponse(lifecycle))))
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{programId}/lifecycle/status/{status}")
    public ResponseEntity<ApiResponse<List<ProgramLifecycleResponse>>> getLifecycleByStatus(
            @PathVariable String programId, @PathVariable String status) {
        List<ProgramLifecycleResponse> records = programLifecycleService.getLifecycleRecordsByStatus(status)
            .stream()
            .filter(r -> r.getProgramId().equals(programId))
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(records));
    }

    @PostMapping("/{programId}/lifecycle/{id}/status")
    public ResponseEntity<ApiResponse<ProgramLifecycleResponse>> updateLifecycleStatus(
            @PathVariable String programId, @PathVariable String id,
            @RequestParam String status, @RequestParam(required = false) String reason,
            @RequestParam(required = false) String changedBy) {
        ProgramLifecycle updated = programLifecycleService.updateLifecycleStatus(id, status, reason, changedBy);
        return ResponseEntity.ok(ApiResponse.success(toProgramLifecycleResponse(updated)));
    }

    @PostMapping("/{programId}/lifecycle/{id}/deactivate")
    public ResponseEntity<ApiResponse<ProgramLifecycleResponse>> deactivateLifecycleRecord(
            @PathVariable String programId, @PathVariable String id) {
        ProgramLifecycle deactivated = programLifecycleService.deactivateLifecycleRecord(id);
        return ResponseEntity.ok(ApiResponse.success(toProgramLifecycleResponse(deactivated)));
    }

    @PostMapping("/{programId}/lifecycle/{id}/activate")
    public ResponseEntity<ApiResponse<ProgramLifecycleResponse>> activateLifecycleRecord(
            @PathVariable String programId, @PathVariable String id) {
        ProgramLifecycle activated = programLifecycleService.activateLifecycleRecord(id);
        return ResponseEntity.ok(ApiResponse.success(toProgramLifecycleResponse(activated)));
    }

    // ========== Helper Methods ==========

    private AcademicLevelResponse toAcademicLevelResponse(AcademicLevel level) {
        return AcademicLevelResponse.builder()
            .id(level.getId())
            .name(level.getName())
            .code(level.getCode())
            .description(level.getDescription())
            .minDurationYears(level.getMinDurationYears())
            .maxDurationYears(level.getMaxDurationYears())
            .minDurationMonths(level.getMinDurationMonths())
            .maxDurationMonths(level.getMaxDurationMonths())
            .typicalDurationYears(level.getTypicalDurationYears())
            .typicalDurationMonths(level.getTypicalDurationMonths())
            .minCredits(level.getMinCredits())
            .maxCredits(level.getMaxCredits())
            .typicalCredits(level.getTypicalCredits())
            .requiresThesis(level.getRequiresThesis())
            .requiresInternship(level.getRequiresInternship())
            .requiresResearch(level.getRequiresResearch())
            .sortOrder(level.getSortOrder())
            .isActive(level.getIsActive())
            .createdAt(level.getCreatedAt())
            .updatedAt(level.getUpdatedAt())
            .build();
    }

    private ProgramRequirementResponse toProgramRequirementResponse(ProgramRequirement requirement) {
        return ProgramRequirementResponse.builder()
            .id(requirement.getId())
            .programId(requirement.getProgramId())
            .requirementType(requirement.getRequirementType())
            .name(requirement.getName())
            .description(requirement.getDescription())
            .isMandatory(requirement.getIsMandatory())
            .validationRule(requirement.getValidationRule())
            .minValue(requirement.getMinValue())
            .maxValue(requirement.getMaxValue())
            .allowedValues(requirement.getAllowedValues())
            .weight(requirement.getWeight())
            .isActive(requirement.getIsActive())
            .sortOrder(requirement.getSortOrder())
            .createdAt(requirement.getCreatedAt())
            .updatedAt(requirement.getUpdatedAt())
            .build();
    }

    private DurationPolicyResponse toDurationPolicyResponse(DurationPolicy policy) {
        return DurationPolicyResponse.builder()
            .id(policy.getId())
            .programId(policy.getProgramId())
            .durationType(policy.getDurationType())
            .name(policy.getName())
            .description(policy.getDescription())
            .minDurationYears(policy.getMinDurationYears())
            .maxDurationYears(policy.getMaxDurationYears())
            .minDurationMonths(policy.getMinDurationMonths())
            .maxDurationMonths(policy.getMaxDurationMonths())
            .defaultDurationYears(policy.getDefaultDurationYears())
            .defaultDurationMonths(policy.getDefaultDurationMonths())
            .creditsPerYear(policy.getCreditsPerYear())
            .maxCreditsPerSemester(policy.getMaxCreditsPerSemester())
            .maxExtensions(policy.getMaxExtensions())
            .extensionDurationMonths(policy.getExtensionDurationMonths())
            .effectiveDate(policy.getEffectiveDate())
            .expirationDate(policy.getExpirationDate())
            .isActive(policy.getIsActive())
            .createdAt(policy.getCreatedAt())
            .updatedAt(policy.getUpdatedAt())
            .build();
    }

    private ProgramLifecycleResponse toProgramLifecycleResponse(ProgramLifecycle lifecycle) {
        return ProgramLifecycleResponse.builder()
            .id(lifecycle.getId())
            .programId(lifecycle.getProgramId())
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
