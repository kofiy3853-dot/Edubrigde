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
@RequestMapping("/api/v1/academic/universities")
@RequiredArgsConstructor
public class UniversityManagementController {

    private final AccreditationService accreditationService;
    private final ClassificationService classificationService;
    private final PartnershipService partnershipService;
    private final UniversityPolicyService policyService;

    // ========== Accreditation Endpoints ==========

    @PostMapping("/{universityId}/accreditation")
    public ResponseEntity<ApiResponse<AccreditationRecordResponse>> createAccreditationRecord(
            @PathVariable String universityId, @RequestBody AccreditationRecord record) {
        record.setUniversityId(universityId);
        AccreditationRecord created = accreditationService.createAccreditationRecord(record);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success(toAccreditationResponse(created)));
    }

    @GetMapping("/{universityId}/accreditation")
    public ResponseEntity<ApiResponse<List<AccreditationRecordResponse>>> getAccreditationRecords(
            @PathVariable String universityId) {
        List<AccreditationRecordResponse> records = accreditationService.getAccreditationRecordsByUniversity(universityId)
            .stream()
            .map(this::toAccreditationResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(records));
    }

    @GetMapping("/{universityId}/accreditation/type/{type}")
    public ResponseEntity<ApiResponse<List<AccreditationRecordResponse>>> getAccreditationRecordsByType(
            @PathVariable String universityId, @PathVariable String type) {
        List<AccreditationRecordResponse> records = accreditationService.getAccreditationRecordsByType(type)
            .stream()
            .filter(r -> r.getUniversityId().equals(universityId))
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(records));
    }

    @GetMapping("/{universityId}/accreditation/status/{status}")
    public ResponseEntity<ApiResponse<List<AccreditationRecordResponse>>> getAccreditationRecordsByStatus(
            @PathVariable String universityId, @PathVariable String status) {
        List<AccreditationRecordResponse> records = accreditationService.getAccreditationRecordsByStatus(status)
            .stream()
            .filter(r -> r.getUniversityId().equals(universityId))
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(records));
    }

    @PutMapping("/{universityId}/accreditation/{id}")
    public ResponseEntity<ApiResponse<AccreditationRecordResponse>> updateAccreditationRecord(
            @PathVariable String universityId, @PathVariable String id,
            @RequestBody AccreditationRecord record) {
        record.setUniversityId(universityId);
        AccreditationRecord updated = accreditationService.updateAccreditationRecord(id, record);
        return ResponseEntity.ok(ApiResponse.success(toAccreditationResponse(updated)));
    }

    @PostMapping("/{universityId}/accreditation/{id}/deactivate")
    public ResponseEntity<ApiResponse<AccreditationRecordResponse>> deactivateAccreditationRecord(
            @PathVariable String universityId, @PathVariable String id) {
        AccreditationRecord deactivated = accreditationService.deactivateAccreditationRecord(id);
        return ResponseEntity.ok(ApiResponse.success(toAccreditationResponse(deactivated)));
    }

    @PostMapping("/{universityId}/accreditation/{id}/activate")
    public ResponseEntity<ApiResponse<AccreditationRecordResponse>> activateAccreditationRecord(
            @PathVariable String universityId, @PathVariable String id) {
        AccreditationRecord activated = accreditationService.activateAccreditationRecord(id);
        return ResponseEntity.ok(ApiResponse.success(toAccreditationResponse(activated)));
    }

    // ========== Classification Endpoints ==========

    @PostMapping("/{universityId}/classifications")
    public ResponseEntity<ApiResponse<InstitutionClassificationResponse>> createClassification(
            @PathVariable String universityId, @RequestBody InstitutionClassification classification) {
        classification.setUniversityId(universityId);
        InstitutionClassification created = classificationService.createClassification(classification);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success(toClassificationResponse(created)));
    }

    @GetMapping("/{universityId}/classifications")
    public ResponseEntity<ApiResponse<List<InstitutionClassificationResponse>>> getClassifications(
            @PathVariable String universityId) {
        List<InstitutionClassificationResponse> classifications = classificationService.getClassificationsByUniversity(universityId)
            .stream()
            .map(this::toClassificationResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(classifications));
    }

    @GetMapping("/{universityId}/classifications/type/{type}")
    public ResponseEntity<ApiResponse<List<InstitutionClassificationResponse>>> getClassificationsByType(
            @PathVariable String universityId, @PathVariable String type) {
        List<InstitutionClassificationResponse> classifications = classificationService.getClassificationsByType(type)
            .stream()
            .filter(c -> c.getUniversityId().equals(universityId))
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(classifications));
    }

    @PutMapping("/{universityId}/classifications/{id}")
    public ResponseEntity<ApiResponse<InstitutionClassificationResponse>> updateClassification(
            @PathVariable String universityId, @PathVariable String id,
            @RequestBody InstitutionClassification classification) {
        classification.setUniversityId(universityId);
        InstitutionClassification updated = classificationService.updateClassification(id, classification);
        return ResponseEntity.ok(ApiResponse.success(toClassificationResponse(updated)));
    }

    @PostMapping("/{universityId}/classifications/{id}/deactivate")
    public ResponseEntity<ApiResponse<InstitutionClassificationResponse>> deactivateClassification(
            @PathVariable String universityId, @PathVariable String id) {
        InstitutionClassification deactivated = classificationService.deactivateClassification(id);
        return ResponseEntity.ok(ApiResponse.success(toClassificationResponse(deactivated)));
    }

    @PostMapping("/{universityId}/classifications/{id}/activate")
    public ResponseEntity<ApiResponse<InstitutionClassificationResponse>> activateClassification(
            @PathVariable String universityId, @PathVariable String id) {
        InstitutionClassification activated = classificationService.activateClassification(id);
        return ResponseEntity.ok(ApiResponse.success(toClassificationResponse(activated)));
    }

    // ========== Partnership Endpoints ==========

    @PostMapping("/{universityId}/partnerships")
    public ResponseEntity<ApiResponse<InternationalPartnershipResponse>> createPartnership(
            @PathVariable String universityId, @RequestBody InternationalPartnership partnership) {
        partnership.setPartnerUniversityId(universityId);
        InternationalPartnership created = partnershipService.createPartnership(partnership);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success(toPartnershipResponse(created)));
    }

    @GetMapping("/{universityId}/partnerships")
    public ResponseEntity<ApiResponse<List<InternationalPartnershipResponse>>> getPartnerships(
            @PathVariable String universityId) {
        List<InternationalPartnershipResponse> partnerships = partnershipService.getPartnershipsByUniversity(universityId)
            .stream()
            .map(this::toPartnershipResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(partnerships));
    }

    @GetMapping("/{universityId}/partnerships/type/{type}")
    public ResponseEntity<ApiResponse<List<InternationalPartnershipResponse>>> getPartnershipsByType(
            @PathVariable String universityId, @PathVariable String type) {
        List<InternationalPartnershipResponse> partnerships = partnershipService.getPartnershipsByType(type)
            .stream()
            .filter(p -> p.getPartnerUniversityId().equals(universityId))
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(partnerships));
    }

    @GetMapping("/{universityId}/partnerships/status/{status}")
    public ResponseEntity<ApiResponse<List<InternationalPartnershipResponse>>> getPartnershipsByStatus(
            @PathVariable String universityId, @PathVariable String status) {
        List<InternationalPartnershipResponse> partnerships = partnershipService.getPartnershipsByStatus(status)
            .stream()
            .filter(p -> p.getPartnerUniversityId().equals(universityId))
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(partnerships));
    }

    @GetMapping("/{universityId}/partnerships/country/{country}")
    public ResponseEntity<ApiResponse<List<InternationalPartnershipResponse>>> getPartnershipsByCountry(
            @PathVariable String universityId, @PathVariable String country) {
        List<InternationalPartnershipResponse> partnerships = partnershipService.getPartnershipsByCountry(country)
            .stream()
            .filter(p -> p.getPartnerUniversityId().equals(universityId))
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(partnerships));
    }

    @PutMapping("/{universityId}/partnerships/{id}")
    public ResponseEntity<ApiResponse<InternationalPartnershipResponse>> updatePartnership(
            @PathVariable String universityId, @PathVariable String id,
            @RequestBody InternationalPartnership partnership) {
        partnership.setPartnerUniversityId(universityId);
        InternationalPartnership updated = partnershipService.updatePartnership(id, partnership);
        return ResponseEntity.ok(ApiResponse.success(toPartnershipResponse(updated)));
    }

    @PostMapping("/{universityId}/partnerships/{id}/deactivate")
    public ResponseEntity<ApiResponse<InternationalPartnershipResponse>> deactivatePartnership(
            @PathVariable String universityId, @PathVariable String id) {
        InternationalPartnership deactivated = partnershipService.deactivatePartnership(id);
        return ResponseEntity.ok(ApiResponse.success(toPartnershipResponse(deactivated)));
    }

    @PostMapping("/{universityId}/partnerships/{id}/activate")
    public ResponseEntity<ApiResponse<InternationalPartnershipResponse>> activatePartnership(
            @PathVariable String universityId, @PathVariable String id) {
        InternationalPartnership activated = partnershipService.activatePartnership(id);
        return ResponseEntity.ok(ApiResponse.success(toPartnershipResponse(activated)));
    }

    // ========== Policy Endpoints ==========

    @PostMapping("/{universityId}/policies")
    public ResponseEntity<ApiResponse<UniversityPolicyResponse>> createPolicy(
            @PathVariable String universityId, @RequestBody UniversityPolicy policy) {
        policy.setUniversityId(universityId);
        UniversityPolicy created = policyService.createPolicy(policy);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success(toPolicyResponse(created)));
    }

    @GetMapping("/{universityId}/policies")
    public ResponseEntity<ApiResponse<List<UniversityPolicyResponse>>> getPolicies(
            @PathVariable String universityId) {
        List<UniversityPolicyResponse> policies = policyService.getPoliciesByUniversity(universityId)
            .stream()
            .map(this::toPolicyResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(policies));
    }

    @GetMapping("/{universityId}/policies/type/{type}")
    public ResponseEntity<ApiResponse<List<UniversityPolicyResponse>>> getPoliciesByType(
            @PathVariable String universityId, @PathVariable String type) {
        List<UniversityPolicyResponse> policies = policyService.getPoliciesByType(type)
            .stream()
            .filter(p -> p.getUniversityId().equals(universityId))
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(policies));
    }

    @GetMapping("/{universityId}/policies/effect/{effect}")
    public ResponseEntity<ApiResponse<List<UniversityPolicyResponse>>> getPoliciesByEffect(
            @PathVariable String universityId, @PathVariable String effect) {
        List<UniversityPolicyResponse> policies = policyService.getPoliciesByEffect(effect)
            .stream()
            .filter(p -> p.getUniversityId().equals(universityId))
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(policies));
    }

    @PutMapping("/{universityId}/policies/{id}")
    public ResponseEntity<ApiResponse<UniversityPolicyResponse>> updatePolicy(
            @PathVariable String universityId, @PathVariable String id,
            @RequestBody UniversityPolicy policy) {
        policy.setUniversityId(universityId);
        UniversityPolicy updated = policyService.updatePolicy(id, policy);
        return ResponseEntity.ok(ApiResponse.success(toPolicyResponse(updated)));
    }

    @PostMapping("/{universityId}/policies/{id}/deactivate")
    public ResponseEntity<ApiResponse<UniversityPolicyResponse>> deactivatePolicy(
            @PathVariable String universityId, @PathVariable String id) {
        UniversityPolicy deactivated = policyService.deactivatePolicy(id);
        return ResponseEntity.ok(ApiResponse.success(toPolicyResponse(deactivated)));
    }

    @PostMapping("/{universityId}/policies/{id}/activate")
    public ResponseEntity<ApiResponse<UniversityPolicyResponse>> activatePolicy(
            @PathVariable String universityId, @PathVariable String id) {
        UniversityPolicy activated = policyService.activatePolicy(id);
        return ResponseEntity.ok(ApiResponse.success(toPolicyResponse(activated)));
    }

    // ========== Helper Methods ==========

    private AccreditationRecordResponse toAccreditationResponse(AccreditationRecord record) {
        return AccreditationRecordResponse.builder()
            .id(record.getId())
            .universityId(record.getUniversityId())
            .accreditationType(record.getAccreditationType())
            .status(record.getStatus())
            .accreditationBody(record.getAccreditationBody())
            .accreditationReference(record.getAccreditationReference())
            .accreditationLevel(record.getAccreditationLevel())
            .grantedDate(record.getGrantedDate())
            .expirationDate(record.getExpirationDate())
            .renewalDate(record.getRenewalDate())
            .verificationUrl(record.getVerificationUrl())
            .documentationUrl(record.getDocumentationUrl())
            .notes(record.getNotes())
            .isActive(record.getIsActive())
            .requiresRenewal(record.getRequiresRenewal())
            .createdAt(record.getCreatedAt())
            .updatedAt(record.getUpdatedAt())
            .build();
    }

    private InstitutionClassificationResponse toClassificationResponse(InstitutionClassification classification) {
        return InstitutionClassificationResponse.builder()
            .id(classification.getId())
            .universityId(classification.getUniversityId())
            .classificationType(classification.getClassificationType())
            .classificationValue(classification.getClassificationValue())
            .classificationCode(classification.getClassificationCode())
            .classificationBody(classification.getClassificationBody())
            .effectiveDate(classification.getEffectiveDate())
            .expirationDate(classification.getExpirationDate())
            .notes(classification.getNotes())
            .isPrimary(classification.getIsPrimary())
            .isActive(classification.getIsActive())
            .createdAt(classification.getCreatedAt())
            .updatedAt(classification.getUpdatedAt())
            .build();
    }

    private InternationalPartnershipResponse toPartnershipResponse(InternationalPartnership partnership) {
        return InternationalPartnershipResponse.builder()
            .id(partnership.getId())
            .partnerUniversityId(partnership.getPartnerUniversityId())
            .partnerInstitutionName(partnership.getPartnerInstitutionName())
            .partnerInstitutionCountry(partnership.getPartnerInstitutionCountry())
            .partnerInstitutionCode(partnership.getPartnerInstitutionCode())
            .partnershipType(partnership.getPartnershipType())
            .status(partnership.getStatus())
            .agreementReference(partnership.getAgreementReference())
            .startDate(partnership.getStartDate())
            .endDate(partnership.getEndDate())
            .renewalDate(partnership.getRenewalDate())
            .contactPerson(partnership.getContactPerson())
            .contactEmail(partnership.getContactEmail())
            .contactPhone(partnership.getContactPhone())
            .notes(partnership.getNotes())
            .isActive(partnership.getIsActive())
            .requiresApproval(partnership.getRequiresApproval())
            .createdAt(partnership.getCreatedAt())
            .updatedAt(partnership.getUpdatedAt())
            .build();
    }

    private UniversityPolicyResponse toPolicyResponse(UniversityPolicy policy) {
        return UniversityPolicyResponse.builder()
            .id(policy.getId())
            .universityId(policy.getUniversityId())
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
}
