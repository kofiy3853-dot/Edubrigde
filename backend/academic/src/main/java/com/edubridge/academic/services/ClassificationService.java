package com.edubridge.academic.services;

import com.edubridge.academic.entities.InstitutionClassification;
import com.edubridge.academic.repositories.InstitutionClassificationRepository;
import com.edubridge.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClassificationService {

    private final InstitutionClassificationRepository classificationRepository;

    @Transactional
    public InstitutionClassification createClassification(InstitutionClassification classification) {
        InstitutionClassification saved = classificationRepository.save(classification);
        log.info("Institution classification created: {} for university {}", saved.getId(), saved.getUniversityId());
        return saved;
    }

    @Transactional(readOnly = true)
    public InstitutionClassification getClassificationById(String id) {
        return classificationRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Institution classification not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<InstitutionClassification> getClassificationsByUniversity(String universityId) {
        return classificationRepository.findByUniversityId(universityId);
    }

    @Transactional(readOnly = true)
    public List<InstitutionClassification> getClassificationsByType(String classificationType) {
        return classificationRepository.findByClassificationType(classificationType);
    }

    @Transactional(readOnly = true)
    public List<InstitutionClassification> getPrimaryClassifications() {
        return classificationRepository.findByIsPrimaryTrue();
    }

    @Transactional(readOnly = true)
    public List<InstitutionClassification> getActiveClassifications() {
        return classificationRepository.findByIsActiveTrue();
    }

    @Transactional
    public InstitutionClassification updateClassification(String id, InstitutionClassification classification) {
        InstitutionClassification existing = getClassificationById(id);

        existing.setClassificationType(classification.getClassificationType());
        existing.setClassificationValue(classification.getClassificationValue());
        existing.setClassificationCode(classification.getClassificationCode());
        existing.setClassificationBody(classification.getClassificationBody());
        existing.setEffectiveDate(classification.getEffectiveDate());
        existing.setExpirationDate(classification.getExpirationDate());
        existing.setNotes(classification.getNotes());
        existing.setIsPrimary(classification.getIsPrimary());

        InstitutionClassification saved = classificationRepository.save(existing);
        log.info("Institution classification updated: {} for university {}", saved.getId(), saved.getUniversityId());
        return saved;
    }

    @Transactional
    public InstitutionClassification deactivateClassification(String id) {
        InstitutionClassification existing = getClassificationById(id);
        existing.setIsActive(false);
        InstitutionClassification saved = classificationRepository.save(existing);
        log.info("Institution classification deactivated: {} for university {}", saved.getId(), saved.getUniversityId());
        return saved;
    }

    @Transactional
    public InstitutionClassification activateClassification(String id) {
        InstitutionClassification existing = getClassificationById(id);
        existing.setIsActive(true);
        InstitutionClassification saved = classificationRepository.save(existing);
        log.info("Institution classification activated: {} for university {}", saved.getId(), saved.getUniversityId());
        return saved;
    }
}
