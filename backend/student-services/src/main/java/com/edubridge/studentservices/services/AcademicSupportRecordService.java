package com.edubridge.studentservices.services;

import com.edubridge.studentservices.entities.AcademicSupportRecord;
import com.edubridge.studentservices.repositories.AcademicSupportRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AcademicSupportRecordService {

    private final AcademicSupportRecordRepository academicSupportRecordRepository;

    @Transactional
    public AcademicSupportRecord createAcademicSupportRecord(AcademicSupportRecord record) {
        log.info("Creating academic support record for student: {}", record.getStudentId());
        return academicSupportRecordRepository.save(record);
    }

    @Transactional(readOnly = true)
    public AcademicSupportRecord getAcademicSupportRecordById(String id) {
        return academicSupportRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Academic support record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<AcademicSupportRecord> getAcademicSupportRecordsByProfile(String supportProfileId) {
        return academicSupportRecordRepository.findBySupportProfileIdAndIsActiveTrue(supportProfileId);
    }

    @Transactional(readOnly = true)
    public List<AcademicSupportRecord> getAcademicSupportRecordsByStudent(String studentId) {
        return academicSupportRecordRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<AcademicSupportRecord> getAcademicSupportRecordsByType(String supportType) {
        return academicSupportRecordRepository.findBySupportTypeAndIsActiveTrue(supportType);
    }

    @Transactional(readOnly = true)
    public List<AcademicSupportRecord> getAcademicSupportRecordsByStatus(String supportStatus) {
        return academicSupportRecordRepository.findBySupportStatusAndIsActiveTrue(supportStatus);
    }

    @Transactional(readOnly = true)
    public List<AcademicSupportRecord> getInProgressByStudent(String studentId) {
        return academicSupportRecordRepository.findInProgressByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<AcademicSupportRecord> getRequestedByStudent(String studentId) {
        return academicSupportRecordRepository.findRequestedByStudent(studentId);
    }

    @Transactional
    public AcademicSupportRecord updateAcademicSupportRecord(String id, AcademicSupportRecord updated) {
        AcademicSupportRecord existing = getAcademicSupportRecordById(id);
        existing.setSupportType(updated.getSupportType());
        existing.setSupportStatus(updated.getSupportStatus());
        existing.setSubjectArea(updated.getSubjectArea());
        existing.setDescription(updated.getDescription());
        existing.setProviderId(updated.getProviderId());
        existing.setScheduledDate(updated.getScheduledDate());
        existing.setFrequency(updated.getFrequency());
        existing.setDurationWeeks(updated.getDurationWeeks());
        existing.setOutcomeNotes(updated.getOutcomeNotes());
        return academicSupportRecordRepository.save(existing);
    }

    @Transactional
    public AcademicSupportRecord completeAcademicSupport(String id, String outcomeNotes) {
        AcademicSupportRecord existing = getAcademicSupportRecordById(id);
        existing.setSupportStatus("COMPLETED");
        existing.setCompletedDate(LocalDateTime.now());
        if (outcomeNotes != null) {
            existing.setOutcomeNotes(outcomeNotes);
        }
        return academicSupportRecordRepository.save(existing);
    }

    @Transactional
    public void deactivateAcademicSupportRecord(String id) {
        AcademicSupportRecord existing = getAcademicSupportRecordById(id);
        existing.setActive(false);
        academicSupportRecordRepository.save(existing);
    }
}
