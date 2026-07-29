package com.edubridge.academic.services;

import com.edubridge.academic.entities.AdmissionRequirement;
import com.edubridge.academic.repositories.AdmissionRequirementRepository;
import com.edubridge.shared.exceptions.ConflictException;
import com.edubridge.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdmissionService {

    private final AdmissionRequirementRepository admissionRequirementRepository;

    @Transactional
    public AdmissionRequirement createAdmissionRequirement(AdmissionRequirement requirement) {
        AdmissionRequirement saved = admissionRequirementRepository.save(requirement);
        log.info("Admission requirement created: {} ({})", saved.getName(), saved.getId());
        return saved;
    }

    @Transactional(readOnly = true)
    public AdmissionRequirement getAdmissionRequirementById(String id) {
        return admissionRequirementRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Admission requirement not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<AdmissionRequirement> getRequirementsByUniversity(String universityId) {
        return admissionRequirementRepository.findByUniversityId(universityId);
    }

    @Transactional(readOnly = true)
    public List<AdmissionRequirement> getRequirementsByProgram(String programId) {
        return admissionRequirementRepository.findByProgramId(programId);
    }

    @Transactional(readOnly = true)
    public List<AdmissionRequirement> getRequirementsByType(String requirementType) {
        return admissionRequirementRepository.findByRequirementType(requirementType);
    }

    @Transactional(readOnly = true)
    public List<AdmissionRequirement> getMandatoryRequirements() {
        return admissionRequirementRepository.findByIsMandatoryTrue();
    }

    @Transactional(readOnly = true)
    public List<AdmissionRequirement> getUniversityWideRequirements(String universityId) {
        return admissionRequirementRepository.findUniversityWideRequirements(universityId);
    }

    @Transactional(readOnly = true)
    public List<AdmissionRequirement> getRequirementsForProgram(String universityId, String programId) {
        return admissionRequirementRepository.findByUniversityIdAndProgramId(universityId, programId);
    }

    @Transactional
    public AdmissionRequirement updateAdmissionRequirement(String id, AdmissionRequirement requirement) {
        AdmissionRequirement existing = getAdmissionRequirementById(id);

        existing.setRequirementType(requirement.getRequirementType());
        existing.setName(requirement.getName());
        existing.setDescription(requirement.getDescription());
        existing.setIsMandatory(requirement.getIsMandatory());
        existing.setValidationRule(requirement.getValidationRule());
        existing.setMinValue(requirement.getMinValue());
        existing.setMaxValue(requirement.getMaxValue());
        existing.setAllowedValues(requirement.getAllowedValues());
        existing.setPriority(requirement.getPriority());

        AdmissionRequirement saved = admissionRequirementRepository.save(existing);
        log.info("Admission requirement updated: {} ({})", saved.getName(), saved.getId());
        return saved;
    }

    @Transactional
    public AdmissionRequirement deactivateAdmissionRequirement(String id) {
        AdmissionRequirement existing = getAdmissionRequirementById(id);
        existing.setIsActive(false);
        AdmissionRequirement saved = admissionRequirementRepository.save(existing);
        log.info("Admission requirement deactivated: {} ({})", saved.getName(), saved.getId());
        return saved;
    }

    @Transactional
    public AdmissionRequirement activateAdmissionRequirement(String id) {
        AdmissionRequirement existing = getAdmissionRequirementById(id);
        existing.setIsActive(true);
        AdmissionRequirement saved = admissionRequirementRepository.save(existing);
        log.info("Admission requirement activated: {} ({})", saved.getName(), saved.getId());
        return saved;
    }
}
