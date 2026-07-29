package com.edubridge.learningservices.services;

import com.edubridge.learningservices.entities.ProfessionalRecord;
import com.edubridge.learningservices.repositories.ProfessionalRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfessionalRecordService {

    private final ProfessionalRecordRepository professionalRecordRepository;

    @Transactional
    public ProfessionalRecord createProfessionalRecord(ProfessionalRecord record) {
        log.info("Creating professional record for student: {} type: {}", record.getStudentId(), record.getProfessionalType());
        return professionalRecordRepository.save(record);
    }

    @Transactional(readOnly = true)
    public ProfessionalRecord getProfessionalRecordById(String id) {
        return professionalRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professional record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<ProfessionalRecord> getProfessionalRecordsByStudent(String studentId) {
        return professionalRecordRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<ProfessionalRecord> getProfessionalRecordsByCareerProfile(String careerProfileId) {
        return professionalRecordRepository.findByCareerProfileIdAndIsActiveTrue(careerProfileId);
    }

    @Transactional(readOnly = true)
    public List<ProfessionalRecord> getProfessionalRecordsByType(String professionalType) {
        return professionalRecordRepository.findByProfessionalTypeAndIsActiveTrue(professionalType);
    }

    @Transactional(readOnly = true)
    public List<ProfessionalRecord> getProfessionalRecordsByStatus(String professionalStatus) {
        return professionalRecordRepository.findByProfessionalStatusAndIsActiveTrue(professionalStatus);
    }

    @Transactional(readOnly = true)
    public List<ProfessionalRecord> getProfessionalRecordsBySkillArea(String skillArea) {
        return professionalRecordRepository.findBySkillAreaAndIsActiveTrue(skillArea);
    }

    @Transactional(readOnly = true)
    public List<ProfessionalRecord> getCompletedByStudent(String studentId) {
        return professionalRecordRepository.findCompletedByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<ProfessionalRecord> getProfessionalByStudentAndType(String studentId, String type) {
        return professionalRecordRepository.findByStudentAndType(studentId, type);
    }

    @Transactional(readOnly = true)
    public List<ProfessionalRecord> getExpiredCertifications() {
        return professionalRecordRepository.findExpiredCertifications();
    }

    @Transactional
    public ProfessionalRecord updateProfessionalRecord(String id, ProfessionalRecord updated) {
        ProfessionalRecord existing = getProfessionalRecordById(id);
        existing.setProfessionalType(updated.getProfessionalType());
        existing.setProfessionalName(updated.getProfessionalName());
        existing.setProfessionalStatus(updated.getProfessionalStatus());
        existing.setSkillArea(updated.getSkillArea());
        existing.setSkillLevel(updated.getSkillLevel());
        existing.setProficiencyScore(updated.getProficiencyScore());
        existing.setTargetProficiencyScore(updated.getTargetProficiencyScore());
        existing.setHoursLogged(updated.getHoursLogged());
        existing.setTargetHours(updated.getTargetHours());
        existing.setCertificationName(updated.getCertificationName());
        existing.setCertificationDate(updated.getCertificationDate());
        existing.setCertificationExpiry(updated.getCertificationExpiry());
        existing.setIndustryRecognized(updated.getIndustryRecognized());
        existing.setNotes(updated.getNotes());
        return professionalRecordRepository.save(existing);
    }

    @Transactional
    public void deactivateProfessionalRecord(String id) {
        ProfessionalRecord existing = getProfessionalRecordById(id);
        existing.setActive(false);
        professionalRecordRepository.save(existing);
    }
}
