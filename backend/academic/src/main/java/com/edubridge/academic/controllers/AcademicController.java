package com.edubridge.academic.controllers;

import com.edubridge.academic.dto.AdmissionRequirementResponse;
import com.edubridge.academic.dto.ProgramResponse;
import com.edubridge.academic.dto.UniversityResponse;
import com.edubridge.academic.entities.AdmissionRequirement;
import com.edubridge.academic.entities.Program;
import com.edubridge.academic.entities.University;
import com.edubridge.academic.services.AdmissionService;
import com.edubridge.academic.services.ProgramService;
import com.edubridge.academic.services.UniversityService;
import com.edubridge.shared.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/academic")
@RequiredArgsConstructor
public class AcademicController {

    private final UniversityService universityService;
    private final ProgramService programService;
    private final AdmissionService admissionService;

    // ========== University Endpoints ==========

    @PostMapping("/universities")
    public ResponseEntity<ApiResponse<UniversityResponse>> createUniversity(
            @Valid @RequestBody University university) {
        University created = universityService.createUniversity(university);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success(toUniversityResponse(created)));
    }

    @GetMapping("/universities")
    public ResponseEntity<ApiResponse<List<UniversityResponse>>> getAllUniversities() {
        List<UniversityResponse> universities = universityService.getAllUniversities().stream()
            .map(this::toUniversityResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(universities));
    }

    @GetMapping("/universities/{id}")
    public ResponseEntity<ApiResponse<UniversityResponse>> getUniversityById(@PathVariable String id) {
        University university = universityService.getUniversityById(id);
        return ResponseEntity.ok(ApiResponse.success(toUniversityResponse(university)));
    }

    @GetMapping("/universities/code/{code}")
    public ResponseEntity<ApiResponse<UniversityResponse>> getUniversityByCode(@PathVariable String code) {
        University university = universityService.getUniversityByCode(code);
        return ResponseEntity.ok(ApiResponse.success(toUniversityResponse(university)));
    }

    @GetMapping("/universities/country/{countryCode}")
    public ResponseEntity<ApiResponse<List<UniversityResponse>>> getUniversitiesByCountry(
            @PathVariable String countryCode) {
        List<UniversityResponse> universities = universityService.getUniversitiesByCountry(countryCode).stream()
            .map(this::toUniversityResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(universities));
    }

    @GetMapping("/universities/type/{institutionType}")
    public ResponseEntity<ApiResponse<List<UniversityResponse>>> getUniversitiesByType(
            @PathVariable String institutionType) {
        List<UniversityResponse> universities = universityService.getUniversitiesByType(institutionType).stream()
            .map(this::toUniversityResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(universities));
    }

    @GetMapping("/universities/partners")
    public ResponseEntity<ApiResponse<List<UniversityResponse>>> getPartnerUniversities() {
        List<UniversityResponse> universities = universityService.getPartnerUniversities().stream()
            .map(this::toUniversityResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(universities));
    }

    @PutMapping("/universities/{id}")
    public ResponseEntity<ApiResponse<UniversityResponse>> updateUniversity(
            @PathVariable String id, @Valid @RequestBody University university) {
        University updated = universityService.updateUniversity(id, university);
        return ResponseEntity.ok(ApiResponse.success(toUniversityResponse(updated)));
    }

    @PostMapping("/universities/{id}/deactivate")
    public ResponseEntity<ApiResponse<UniversityResponse>> deactivateUniversity(@PathVariable String id) {
        University deactivated = universityService.deactivateUniversity(id);
        return ResponseEntity.ok(ApiResponse.success(toUniversityResponse(deactivated)));
    }

    @PostMapping("/universities/{id}/activate")
    public ResponseEntity<ApiResponse<UniversityResponse>> activateUniversity(@PathVariable String id) {
        University activated = universityService.activateUniversity(id);
        return ResponseEntity.ok(ApiResponse.success(toUniversityResponse(activated)));
    }

    // ========== Program Endpoints ==========

    @PostMapping("/programs")
    public ResponseEntity<ApiResponse<ProgramResponse>> createProgram(
            @Valid @RequestBody Program program) {
        Program created = programService.createProgram(program);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success(toProgramResponse(created)));
    }

    @GetMapping("/programs")
    public ResponseEntity<ApiResponse<List<ProgramResponse>>> getAllPrograms() {
        List<ProgramResponse> programs = programService.getAllPrograms().stream()
            .map(this::toProgramResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(programs));
    }

    @GetMapping("/programs/{id}")
    public ResponseEntity<ApiResponse<ProgramResponse>> getProgramById(@PathVariable String id) {
        Program program = programService.getProgramById(id);
        return ResponseEntity.ok(ApiResponse.success(toProgramResponse(program)));
    }

    @GetMapping("/programs/code/{code}")
    public ResponseEntity<ApiResponse<ProgramResponse>> getProgramByCode(@PathVariable String code) {
        Program program = programService.getProgramByCode(code);
        return ResponseEntity.ok(ApiResponse.success(toProgramResponse(program)));
    }

    @GetMapping("/programs/university/{universityId}")
    public ResponseEntity<ApiResponse<List<ProgramResponse>>> getProgramsByUniversity(
            @PathVariable String universityId) {
        List<ProgramResponse> programs = programService.getProgramsByUniversity(universityId).stream()
            .map(this::toProgramResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(programs));
    }

    @GetMapping("/programs/level/{level}")
    public ResponseEntity<ApiResponse<List<ProgramResponse>>> getProgramsByLevel(@PathVariable String level) {
        List<ProgramResponse> programs = programService.getProgramsByLevel(level).stream()
            .map(this::toProgramResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(programs));
    }

    @PutMapping("/programs/{id}")
    public ResponseEntity<ApiResponse<ProgramResponse>> updateProgram(
            @PathVariable String id, @Valid @RequestBody Program program) {
        Program updated = programService.updateProgram(id, program);
        return ResponseEntity.ok(ApiResponse.success(toProgramResponse(updated)));
    }

    @PostMapping("/programs/{id}/deactivate")
    public ResponseEntity<ApiResponse<ProgramResponse>> deactivateProgram(@PathVariable String id) {
        Program deactivated = programService.deactivateProgram(id);
        return ResponseEntity.ok(ApiResponse.success(toProgramResponse(deactivated)));
    }

    @PostMapping("/programs/{id}/activate")
    public ResponseEntity<ApiResponse<ProgramResponse>> activateProgram(@PathVariable String id) {
        Program activated = programService.activateProgram(id);
        return ResponseEntity.ok(ApiResponse.success(toProgramResponse(activated)));
    }

    // ========== Admission Requirement Endpoints ==========

    @PostMapping("/admissions")
    public ResponseEntity<ApiResponse<AdmissionRequirementResponse>> createAdmissionRequirement(
            @Valid @RequestBody AdmissionRequirement requirement) {
        AdmissionRequirement created = admissionService.createAdmissionRequirement(requirement);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success(toAdmissionRequirementResponse(created)));
    }

    @GetMapping("/admissions/university/{universityId}")
    public ResponseEntity<ApiResponse<List<AdmissionRequirementResponse>>> getRequirementsByUniversity(
            @PathVariable String universityId) {
        List<AdmissionRequirementResponse> requirements = admissionService.getRequirementsByUniversity(universityId)
            .stream()
            .map(this::toAdmissionRequirementResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(requirements));
    }

    @GetMapping("/admissions/program/{programId}")
    public ResponseEntity<ApiResponse<List<AdmissionRequirementResponse>>> getRequirementsByProgram(
            @PathVariable String programId) {
        List<AdmissionRequirementResponse> requirements = admissionService.getRequirementsByProgram(programId)
            .stream()
            .map(this::toAdmissionRequirementResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(requirements));
    }

    @GetMapping("/admissions/type/{requirementType}")
    public ResponseEntity<ApiResponse<List<AdmissionRequirementResponse>>> getRequirementsByType(
            @PathVariable String requirementType) {
        List<AdmissionRequirementResponse> requirements = admissionService.getRequirementsByType(requirementType)
            .stream()
            .map(this::toAdmissionRequirementResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(requirements));
    }

    @PutMapping("/admissions/{id}")
    public ResponseEntity<ApiResponse<AdmissionRequirementResponse>> updateAdmissionRequirement(
            @PathVariable String id, @Valid @RequestBody AdmissionRequirement requirement) {
        AdmissionRequirement updated = admissionService.updateAdmissionRequirement(id, requirement);
        return ResponseEntity.ok(ApiResponse.success(toAdmissionRequirementResponse(updated)));
    }

    @PostMapping("/admissions/{id}/deactivate")
    public ResponseEntity<ApiResponse<AdmissionRequirementResponse>> deactivateAdmissionRequirement(
            @PathVariable String id) {
        AdmissionRequirement deactivated = admissionService.deactivateAdmissionRequirement(id);
        return ResponseEntity.ok(ApiResponse.success(toAdmissionRequirementResponse(deactivated)));
    }

    // ========== Helper Methods ==========

    private UniversityResponse toUniversityResponse(University university) {
        return UniversityResponse.builder()
            .id(university.getId())
            .name(university.getName())
            .code(university.getCode())
            .countryCode(university.getCountryCode())
            .city(university.getCity())
            .website(university.getWebsite())
            .institutionType(university.getInstitutionType())
            .accreditationStatus(university.getAccreditationStatus())
            .accreditationBody(university.getAccreditationBody())
            .accreditationDate(university.getAccreditationDate())
            .logoUrl(university.getLogoUrl())
            .description(university.getDescription())
            .establishedDate(university.getEstablishedDate())
            .phone(university.getPhone())
            .email(university.getEmail())
            .address(university.getAddress())
            .isActive(university.getIsActive())
            .isPartner(university.getIsPartner())
            .createdAt(university.getCreatedAt())
            .updatedAt(university.getUpdatedAt())
            .build();
    }

    private ProgramResponse toProgramResponse(Program program) {
        return ProgramResponse.builder()
            .id(program.getId())
            .universityId(program.getUniversityId())
            .name(program.getName())
            .code(program.getCode())
            .level(program.getLevel())
            .fieldOfStudy(program.getFieldOfStudy())
            .durationYears(program.getDurationYears())
            .durationMonths(program.getDurationMonths())
            .creditsRequired(program.getCreditsRequired())
            .description(program.getDescription())
            .requirements(program.getRequirements())
            .languageOfInstruction(program.getLanguageOfInstruction())
            .tuitionFeeAmount(program.getTuitionFeeAmount())
            .tuitionFeeCurrency(program.getTuitionFeeCurrency())
            .isActive(program.getIsActive())
            .isOnline(program.getIsOnline())
            .createdAt(program.getCreatedAt())
            .updatedAt(program.getUpdatedAt())
            .build();
    }

    private AdmissionRequirementResponse toAdmissionRequirementResponse(AdmissionRequirement requirement) {
        return AdmissionRequirementResponse.builder()
            .id(requirement.getId())
            .universityId(requirement.getUniversityId())
            .programId(requirement.getProgramId())
            .requirementType(requirement.getRequirementType())
            .name(requirement.getName())
            .description(requirement.getDescription())
            .isMandatory(requirement.getIsMandatory())
            .validationRule(requirement.getValidationRule())
            .minValue(requirement.getMinValue())
            .maxValue(requirement.getMaxValue())
            .allowedValues(requirement.getAllowedValues())
            .isActive(requirement.getIsActive())
            .priority(requirement.getPriority())
            .createdAt(requirement.getCreatedAt())
            .updatedAt(requirement.getUpdatedAt())
            .build();
    }
}
