package com.edubridge.academic.services;

import com.edubridge.academic.entities.ProgramRequirement;
import com.edubridge.academic.repositories.ProgramRequirementRepository;
import com.edubridge.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProgramRequirementService {

    private final ProgramRequirementRepository programRequirementRepository;

    @Transactional
    public ProgramRequirement createProgramRequirement(ProgramRequirement requirement) {
        ProgramRequirement saved = programRequirementRepository.save(requirement);
        log.info("Program requirement created: {} for program {}", saved.getId(), saved.getProgramId());
        return saved;
    }

    @Transactional(readOnly = true)
    public ProgramRequirement getProgramRequirementById(String id) {
        return programRequirementRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Program requirement not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<ProgramRequirement> getRequirementsByProgram(String programId) {
        return programRequirementRepository.findByProgramId(programId);
    }

    @Transactional(readOnly = true)
    public List<ProgramRequirement> getRequirementsByType(String requirementType) {
        return programRequirementRepository.findByRequirementType(requirementType);
    }

    @Transactional(readOnly = true)
    public List<ProgramRequirement> getMandatoryRequirements() {
        return programRequirementRepository.findByIsMandatoryTrue();
    }

    @Transactional(readOnly = true)
    public List<ProgramRequirement> getActiveRequirements() {
        return programRequirementRepository.findByIsActiveTrue();
    }

    @Transactional
    public ProgramRequirement updateProgramRequirement(String id, ProgramRequirement requirement) {
        ProgramRequirement existing = getProgramRequirementById(id);

        existing.setRequirementType(requirement.getRequirementType());
        existing.setName(requirement.getName());
        existing.setDescription(requirement.getDescription());
        existing.setIsMandatory(requirement.getIsMandatory());
        existing.setValidationRule(requirement.getValidationRule());
        existing.setMinValue(requirement.getMinValue());
        existing.setMaxValue(requirement.getMaxValue());
        existing.setAllowedValues(requirement.getAllowedValues());
        existing.setWeight(requirement.getWeight());
        existing.setSortOrder(requirement.getSortOrder());

        ProgramRequirement saved = programRequirementRepository.save(existing);
        log.info("Program requirement updated: {} for program {}", saved.getId(), saved.getProgramId());
        return saved;
    }

    @Transactional
    public ProgramRequirement deactivateProgramRequirement(String id) {
        ProgramRequirement existing = getProgramRequirementById(id);
        existing.setIsActive(false);
        ProgramRequirement saved = programRequirementRepository.save(existing);
        log.info("Program requirement deactivated: {} for program {}", saved.getId(), saved.getProgramId());
        return saved;
    }

    @Transactional
    public ProgramRequirement activateProgramRequirement(String id) {
        ProgramRequirement existing = getProgramRequirementById(id);
        existing.setIsActive(true);
        ProgramRequirement saved = programRequirementRepository.save(existing);
        log.info("Program requirement activated: {} for program {}", saved.getId(), saved.getProgramId());
        return saved;
    }
}
