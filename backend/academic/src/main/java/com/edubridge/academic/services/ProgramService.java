package com.edubridge.academic.services;

import com.edubridge.academic.entities.Program;
import com.edubridge.academic.repositories.ProgramRepository;
import com.edubridge.shared.exceptions.BadRequestException;
import com.edubridge.shared.exceptions.ConflictException;
import com.edubridge.shared.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProgramService {

    private final ProgramRepository programRepository;

    @Transactional
    public Program createProgram(Program program) {
        if (programRepository.existsByCode(program.getCode())) {
            throw new ConflictException("Program with code " + program.getCode() + " already exists");
        }
        if (programRepository.existsByUniversityIdAndName(program.getUniversityId(), program.getName())) {
            throw new ConflictException("Program with name " + program.getName() + " already exists for university");
        }

        Program saved = programRepository.save(program);
        log.info("Program created: {} ({})", saved.getName(), saved.getId());
        return saved;
    }

    @Transactional(readOnly = true)
    public Program getProgramById(String id) {
        return programRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Program not found: " + id));
    }

    @Transactional(readOnly = true)
    public Program getProgramByCode(String code) {
        return programRepository.findByCode(code)
            .orElseThrow(() -> new ResourceNotFoundException("Program not found with code: " + code));
    }

    @Transactional(readOnly = true)
    public List<Program> getAllPrograms() {
        return programRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Program> getPrograms(Pageable pageable) {
        return programRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<Program> getProgramsByUniversity(String universityId) {
        return programRepository.findByUniversityId(universityId);
    }

    @Transactional(readOnly = true)
    public List<Program> getProgramsByLevel(String level) {
        return programRepository.findByLevel(level);
    }

    @Transactional(readOnly = true)
    public List<Program> getActivePrograms() {
        return programRepository.findByIsActiveTrue();
    }

    @Transactional(readOnly = true)
    public List<Program> getOnlinePrograms() {
        return programRepository.findByIsOnlineTrue();
    }

    @Transactional
    public Program updateProgram(String id, Program program) {
        Program existing = getProgramById(id);

        existing.setName(program.getName());
        existing.setLevel(program.getLevel());
        existing.setFieldOfStudy(program.getFieldOfStudy());
        existing.setDurationYears(program.getDurationYears());
        existing.setDurationMonths(program.getDurationMonths());
        existing.setCreditsRequired(program.getCreditsRequired());
        existing.setDescription(program.getDescription());
        existing.setRequirements(program.getRequirements());
        existing.setLanguageOfInstruction(program.getLanguageOfInstruction());
        existing.setTuitionFeeAmount(program.getTuitionFeeAmount());
        existing.setTuitionFeeCurrency(program.getTuitionFeeCurrency());
        existing.setIsOnline(program.getIsOnline());

        Program saved = programRepository.save(existing);
        log.info("Program updated: {} ({})", saved.getName(), saved.getId());
        return saved;
    }

    @Transactional
    public Program deactivateProgram(String id) {
        Program existing = getProgramById(id);
        existing.setIsActive(false);
        Program saved = programRepository.save(existing);
        log.info("Program deactivated: {} ({})", saved.getName(), saved.getId());
        return saved;
    }

    @Transactional
    public Program activateProgram(String id) {
        Program existing = getProgramById(id);
        existing.setIsActive(true);
        Program saved = programRepository.save(existing);
        log.info("Program activated: {} ({})", saved.getName(), saved.getId());
        return saved;
    }

    @Transactional(readOnly = true)
    public List<Program> searchPrograms(String query) {
        return programRepository.findAll().stream()
            .filter(p -> p.getName().toLowerCase().contains(query.toLowerCase())
                || p.getCode().toLowerCase().contains(query.toLowerCase())
                || p.getFieldOfStudy().toLowerCase().contains(query.toLowerCase()))
            .toList();
    }
}
