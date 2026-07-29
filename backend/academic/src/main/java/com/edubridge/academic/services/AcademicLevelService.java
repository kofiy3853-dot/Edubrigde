package com.edubridge.academic.services;

import com.edubridge.academic.entities.AcademicLevel;
import com.edubridge.academic.repositories.AcademicLevelRepository;
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
public class AcademicLevelService {

    private final AcademicLevelRepository academicLevelRepository;

    @Transactional
    public AcademicLevel createAcademicLevel(AcademicLevel level) {
        if (academicLevelRepository.existsByCode(level.getCode())) {
            throw new ConflictException("Academic level with code " + level.getCode() + " already exists");
        }
        if (academicLevelRepository.existsByName(level.getName())) {
            throw new ConflictException("Academic level with name " + level.getName() + " already exists");
        }

        AcademicLevel saved = academicLevelRepository.save(level);
        log.info("Academic level created: {} ({})", saved.getName(), saved.getId());
        return saved;
    }

    @Transactional(readOnly = true)
    public AcademicLevel getAcademicLevelById(String id) {
        return academicLevelRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Academic level not found: " + id));
    }

    @Transactional(readOnly = true)
    public AcademicLevel getAcademicLevelByCode(String code) {
        return academicLevelRepository.findByCode(code)
            .orElseThrow(() -> new ResourceNotFoundException("Academic level not found with code: " + code));
    }

    @Transactional(readOnly = true)
    public List<AcademicLevel> getAllAcademicLevels() {
        return academicLevelRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<AcademicLevel> getActiveAcademicLevels() {
        return academicLevelRepository.findByIsActiveTrue();
    }

    @Transactional(readOnly = true)
    public List<AcademicLevel> getOrderedAcademicLevels() {
        return academicLevelRepository.findActiveOrderedBySortOrder();
    }

    @Transactional
    public AcademicLevel updateAcademicLevel(String id, AcademicLevel level) {
        AcademicLevel existing = getAcademicLevelById(id);

        existing.setName(level.getName());
        existing.setDescription(level.getDescription());
        existing.setMinDurationYears(level.getMinDurationYears());
        existing.setMaxDurationYears(level.getMaxDurationYears());
        existing.setMinDurationMonths(level.getMinDurationMonths());
        existing.setMaxDurationMonths(level.getMaxDurationMonths());
        existing.setTypicalDurationYears(level.getTypicalDurationYears());
        existing.setTypicalDurationMonths(level.getTypicalDurationMonths());
        existing.setMinCredits(level.getMinCredits());
        existing.setMaxCredits(level.getMaxCredits());
        existing.setTypicalCredits(level.getTypicalCredits());
        existing.setRequiresThesis(level.getRequiresThesis());
        existing.setRequiresInternship(level.getRequiresInternship());
        existing.setRequiresResearch(level.getRequiresResearch());
        existing.setSortOrder(level.getSortOrder());

        AcademicLevel saved = academicLevelRepository.save(existing);
        log.info("Academic level updated: {} ({})", saved.getName(), saved.getId());
        return saved;
    }

    @Transactional
    public AcademicLevel deactivateAcademicLevel(String id) {
        AcademicLevel existing = getAcademicLevelById(id);
        existing.setIsActive(false);
        AcademicLevel saved = academicLevelRepository.save(existing);
        log.info("Academic level deactivated: {} ({})", saved.getName(), saved.getId());
        return saved;
    }

    @Transactional
    public AcademicLevel activateAcademicLevel(String id) {
        AcademicLevel existing = getAcademicLevelById(id);
        existing.setIsActive(true);
        AcademicLevel saved = academicLevelRepository.save(existing);
        log.info("Academic level activated: {} ({})", saved.getName(), saved.getId());
        return saved;
    }
}
