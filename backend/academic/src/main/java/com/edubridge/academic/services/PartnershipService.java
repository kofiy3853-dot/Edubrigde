package com.edubridge.academic.services;

import com.edubridge.academic.entities.InternationalPartnership;
import com.edubridge.academic.repositories.InternationalPartnershipRepository;
import com.edubridge.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PartnershipService {

    private final InternationalPartnershipRepository partnershipRepository;

    @Transactional
    public InternationalPartnership createPartnership(InternationalPartnership partnership) {
        InternationalPartnership saved = partnershipRepository.save(partnership);
        log.info("International partnership created: {} for university {}", saved.getId(), saved.getPartnerUniversityId());
        return saved;
    }

    @Transactional(readOnly = true)
    public InternationalPartnership getPartnershipById(String id) {
        return partnershipRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("International partnership not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<InternationalPartnership> getPartnershipsByUniversity(String universityId) {
        return partnershipRepository.findByPartnerUniversityId(universityId);
    }

    @Transactional(readOnly = true)
    public List<InternationalPartnership> getPartnershipsByType(String partnershipType) {
        return partnershipRepository.findByPartnershipType(partnershipType);
    }

    @Transactional(readOnly = true)
    public List<InternationalPartnership> getPartnershipsByStatus(String status) {
        return partnershipRepository.findByStatus(status);
    }

    @Transactional(readOnly = true)
    public List<InternationalPartnership> getPartnershipsByCountry(String country) {
        return partnershipRepository.findByPartnerInstitutionCountry(country);
    }

    @Transactional(readOnly = true)
    public List<InternationalPartnership> getActivePartnerships() {
        return partnershipRepository.findByIsActiveTrue();
    }

    @Transactional
    public InternationalPartnership updatePartnership(String id, InternationalPartnership partnership) {
        InternationalPartnership existing = getPartnershipById(id);

        existing.setPartnerInstitutionName(partnership.getPartnerInstitutionName());
        existing.setPartnerInstitutionCountry(partnership.getPartnerInstitutionCountry());
        existing.setPartnerInstitutionCode(partnership.getPartnerInstitutionCode());
        existing.setPartnershipType(partnership.getPartnershipType());
        existing.setStatus(partnership.getStatus());
        existing.setAgreementReference(partnership.getAgreementReference());
        existing.setStartDate(partnership.getStartDate());
        existing.setEndDate(partnership.getEndDate());
        existing.setRenewalDate(partnership.getRenewalDate());
        existing.setContactPerson(partnership.getContactPerson());
        existing.setContactEmail(partnership.getContactEmail());
        existing.setContactPhone(partnership.getContactPhone());
        existing.setNotes(partnership.getNotes());
        existing.setRequiresApproval(partnership.getRequiresApproval());

        InternationalPartnership saved = partnershipRepository.save(existing);
        log.info("International partnership updated: {} for university {}", saved.getId(), saved.getPartnerUniversityId());
        return saved;
    }

    @Transactional
    public InternationalPartnership deactivatePartnership(String id) {
        InternationalPartnership existing = getPartnershipById(id);
        existing.setIsActive(false);
        InternationalPartnership saved = partnershipRepository.save(existing);
        log.info("International partnership deactivated: {} for university {}", saved.getId(), saved.getPartnerUniversityId());
        return saved;
    }

    @Transactional
    public InternationalPartnership activatePartnership(String id) {
        InternationalPartnership existing = getPartnershipById(id);
        existing.setIsActive(true);
        InternationalPartnership saved = partnershipRepository.save(existing);
        log.info("International partnership activated: {} for university {}", saved.getId(), saved.getPartnerUniversityId());
        return saved;
    }
}
