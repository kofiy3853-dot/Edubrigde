package com.edubridge.academic.services;

import com.edubridge.academic.entities.AccreditationRecord;
import com.edubridge.academic.repositories.AccreditationRecordRepository;
import com.edubridge.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccreditationService {

    private final AccreditationRecordRepository accreditationRecordRepository;

    @Transactional
    public AccreditationRecord createAccreditationRecord(AccreditationRecord record) {
        AccreditationRecord saved = accreditationRecordRepository.save(record);
        log.info("Accreditation record created: {} for university {}", saved.getId(), saved.getUniversityId());
        return saved;
    }

    @Transactional(readOnly = true)
    public AccreditationRecord getAccreditationRecordById(String id) {
        return accreditationRecordRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Accreditation record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<AccreditationRecord> getAccreditationRecordsByUniversity(String universityId) {
        return accreditationRecordRepository.findByUniversityId(universityId);
    }

    @Transactional(readOnly = true)
    public List<AccreditationRecord> getAccreditationRecordsByType(String accreditationType) {
        return accreditationRecordRepository.findByAccreditationType(accreditationType);
    }

    @Transactional(readOnly = true)
    public List<AccreditationRecord> getAccreditationRecordsByStatus(String status) {
        return accreditationRecordRepository.findByStatus(status);
    }

    @Transactional(readOnly = true)
    public List<AccreditationRecord> getActiveAccreditationRecords() {
        return accreditationRecordRepository.findByIsActiveTrue();
    }

    @Transactional
    public AccreditationRecord updateAccreditationRecord(String id, AccreditationRecord record) {
        AccreditationRecord existing = getAccreditationRecordById(id);

        existing.setAccreditationType(record.getAccreditationType());
        existing.setStatus(record.getStatus());
        existing.setAccreditationBody(record.getAccreditationBody());
        existing.setAccreditationReference(record.getAccreditationReference());
        existing.setAccreditationLevel(record.getAccreditationLevel());
        existing.setGrantedDate(record.getGrantedDate());
        existing.setExpirationDate(record.getExpirationDate());
        existing.setRenewalDate(record.getRenewalDate());
        existing.setVerificationUrl(record.getVerificationUrl());
        existing.setDocumentationUrl(record.getDocumentationUrl());
        existing.setNotes(record.getNotes());
        existing.setRequiresRenewal(record.getRequiresRenewal());

        AccreditationRecord saved = accreditationRecordRepository.save(existing);
        log.info("Accreditation record updated: {} for university {}", saved.getId(), saved.getUniversityId());
        return saved;
    }

    @Transactional
    public AccreditationRecord deactivateAccreditationRecord(String id) {
        AccreditationRecord existing = getAccreditationRecordById(id);
        existing.setIsActive(false);
        AccreditationRecord saved = accreditationRecordRepository.save(existing);
        log.info("Accreditation record deactivated: {} for university {}", saved.getId(), saved.getUniversityId());
        return saved;
    }

    @Transactional
    public AccreditationRecord activateAccreditationRecord(String id) {
        AccreditationRecord existing = getAccreditationRecordById(id);
        existing.setIsActive(true);
        AccreditationRecord saved = accreditationRecordRepository.save(existing);
        log.info("Accreditation record activated: {} for university {}", saved.getId(), saved.getUniversityId());
        return saved;
    }
}
