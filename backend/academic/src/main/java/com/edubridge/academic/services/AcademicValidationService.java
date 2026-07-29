package com.edubridge.academic.services;

import com.edubridge.academic.entities.AcademicValidation;
import com.edubridge.academic.repositories.AcademicValidationRepository;
import com.edubridge.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AcademicValidationService {

    private final AcademicValidationRepository academicValidationRepository;

    @Transactional
    public AcademicValidation createAcademicValidation(AcademicValidation validation) {
        AcademicValidation saved = academicValidationRepository.save(validation);
        log.info("Academic validation created: {} for university {}", saved.getId(), saved.getUniversityId());
        return saved;
    }

    @Transactional(readOnly = true)
    public AcademicValidation getAcademicValidationById(String id) {
        return academicValidationRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Academic validation not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<AcademicValidation> getValidationsByUniversity(String universityId) {
        return academicValidationRepository.findByUniversityId(universityId);
    }

    @Transactional(readOnly = true)
    public List<AcademicValidation> getValidationsByProgram(String programId) {
        return academicValidationRepository.findByProgramId(programId);
    }

    @Transactional(readOnly = true)
    public List<AcademicValidation> getValidationsByType(String validationType) {
        return academicValidationRepository.findByValidationType(validationType);
    }

    @Transactional(readOnly = true)
    public List<AcademicValidation> getMandatoryValidations() {
        return academicValidationRepository.findByIsMandatoryTrue();
    }

    @Transactional(readOnly = true)
    public List<AcademicValidation> getActiveValidations() {
        return academicValidationRepository.findByIsActiveTrue();
    }

    @Transactional
    public AcademicValidation updateAcademicValidation(String id, AcademicValidation validation) {
        AcademicValidation existing = getAcademicValidationById(id);

        existing.setValidationType(validation.getValidationType());
        existing.setName(validation.getName());
        existing.setDescription(validation.getDescription());
        existing.setValidationRule(validation.getValidationRule());
        existing.setMinValue(validation.getMinValue());
        existing.setMaxValue(validation.getMaxValue());
        existing.setAllowedValues(validation.getAllowedValues());
        existing.setIsMandatory(validation.getIsMandatory());
        existing.setWeight(validation.getWeight());

        AcademicValidation saved = academicValidationRepository.save(existing);
        log.info("Academic validation updated: {} for university {}", saved.getId(), saved.getUniversityId());
        return saved;
    }

    @Transactional
    public AcademicValidation deactivateAcademicValidation(String id) {
        AcademicValidation existing = getAcademicValidationById(id);
        existing.setIsActive(false);
        AcademicValidation saved = academicValidationRepository.save(existing);
        log.info("Academic validation deactivated: {} for university {}", saved.getId(), saved.getUniversityId());
        return saved;
    }

    @Transactional
    public AcademicValidation activateAcademicValidation(String id) {
        AcademicValidation existing = getAcademicValidationById(id);
        existing.setIsActive(true);
        AcademicValidation saved = academicValidationRepository.save(existing);
        log.info("Academic validation activated: {} for university {}", saved.getId(), saved.getUniversityId());
        return saved;
    }
}
