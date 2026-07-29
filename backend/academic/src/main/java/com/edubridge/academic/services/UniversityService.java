package com.edubridge.academic.services;

import com.edubridge.academic.entities.University;
import com.edubridge.academic.repositories.UniversityRepository;
import com.edubridge.shared.exceptions.BadRequestException;
import com.edubridge.shared.exceptions.ConflictException;
import com.edubridge.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UniversityService {

    private final UniversityRepository universityRepository;

    @Transactional
    public University createUniversity(University university) {
        if (universityRepository.existsByCode(university.getCode())) {
            throw new ConflictException("University with code " + university.getCode() + " already exists");
        }
        if (universityRepository.existsByName(university.getName())) {
            throw new ConflictException("University with name " + university.getName() + " already exists");
        }

        University saved = universityRepository.save(university);
        log.info("University created: {} ({})", saved.getName(), saved.getId());
        return saved;
    }

    @Transactional(readOnly = true)
    public University getUniversityById(String id) {
        return universityRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("University not found: " + id));
    }

    @Transactional(readOnly = true)
    public University getUniversityByCode(String code) {
        return universityRepository.findByCode(code)
            .orElseThrow(() -> new ResourceNotFoundException("University not found with code: " + code));
    }

    @Transactional(readOnly = true)
    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<University> getUniversities(Pageable pageable) {
        return universityRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<University> getActiveUniversities() {
        return universityRepository.findByIsActiveTrue();
    }

    @Transactional(readOnly = true)
    public List<University> getUniversitiesByCountry(String countryCode) {
        return universityRepository.findByCountryCode(countryCode);
    }

    @Transactional(readOnly = true)
    public List<University> getUniversitiesByType(String institutionType) {
        return universityRepository.findByInstitutionType(institutionType);
    }

    @Transactional(readOnly = true)
    public List<University> getPartnerUniversities() {
        return universityRepository.findByIsPartnerTrue();
    }

    @Transactional
    public University updateUniversity(String id, University university) {
        University existing = getUniversityById(id);

        existing.setName(university.getName());
        existing.setCountryCode(university.getCountryCode());
        existing.setCity(university.getCity());
        existing.setWebsite(university.getWebsite());
        existing.setInstitutionType(university.getInstitutionType());
        existing.setAccreditationStatus(university.getAccreditationStatus());
        existing.setAccreditationBody(university.getAccreditationBody());
        existing.setAccreditationDate(university.getAccreditationDate());
        existing.setLogoUrl(university.getLogoUrl());
        existing.setDescription(university.getDescription());
        existing.setEstablishedDate(university.getEstablishedDate());
        existing.setPhone(university.getPhone());
        existing.setEmail(university.getEmail());
        existing.setAddress(university.getAddress());
        existing.setIsPartner(university.getIsPartner());

        University saved = universityRepository.save(existing);
        log.info("University updated: {} ({})", saved.getName(), saved.getId());
        return saved;
    }

    @Transactional
    public University deactivateUniversity(String id) {
        University existing = getUniversityById(id);
        existing.setIsActive(false);
        University saved = universityRepository.save(existing);
        log.info("University deactivated: {} ({})", saved.getName(), saved.getId());
        return saved;
    }

    @Transactional
    public University activateUniversity(String id) {
        University existing = getUniversityById(id);
        existing.setIsActive(true);
        University saved = universityRepository.save(existing);
        log.info("University activated: {} ({})", saved.getName(), saved.getId());
        return saved;
    }

    @Transactional(readOnly = true)
    public List<University> searchUniversities(String query) {
        return universityRepository.findAll().stream()
            .filter(u -> u.getName().toLowerCase().contains(query.toLowerCase())
                || u.getCode().toLowerCase().contains(query.toLowerCase())
                || (u.getCity() != null && u.getCity().toLowerCase().contains(query.toLowerCase())))
            .toList();
    }
}
