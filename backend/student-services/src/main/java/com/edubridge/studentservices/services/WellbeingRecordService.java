package com.edubridge.studentservices.services;

import com.edubridge.studentservices.entities.WellbeingRecord;
import com.edubridge.studentservices.repositories.WellbeingRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WellbeingRecordService {

    private final WellbeingRecordRepository wellbeingRecordRepository;

    @Transactional
    public WellbeingRecord createWellbeingRecord(WellbeingRecord record) {
        log.info("Creating wellbeing record for student: {}", record.getStudentId());
        return wellbeingRecordRepository.save(record);
    }

    @Transactional(readOnly = true)
    public WellbeingRecord getWellbeingRecordById(String id) {
        return wellbeingRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wellbeing record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<WellbeingRecord> getWellbeingRecordsByProfile(String supportProfileId) {
        return wellbeingRecordRepository.findBySupportProfileIdAndIsActiveTrue(supportProfileId);
    }

    @Transactional(readOnly = true)
    public List<WellbeingRecord> getWellbeingRecordsByStudent(String studentId) {
        return wellbeingRecordRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<WellbeingRecord> getWellbeingRecordsByType(String wellbeingType) {
        return wellbeingRecordRepository.findByWellbeingTypeAndIsActiveTrue(wellbeingType);
    }

    @Transactional(readOnly = true)
    public List<WellbeingRecord> getWellbeingRecordsByStatus(String wellbeingStatus) {
        return wellbeingRecordRepository.findByWellbeingStatusAndIsActiveTrue(wellbeingStatus);
    }

    @Transactional(readOnly = true)
    public List<WellbeingRecord> getInTreatmentByStudent(String studentId) {
        return wellbeingRecordRepository.findInTreatmentByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<WellbeingRecord> getCriticalCases() {
        return wellbeingRecordRepository.findCriticalCases();
    }

    @Transactional
    public WellbeingRecord updateWellbeingRecord(String id, WellbeingRecord updated) {
        WellbeingRecord existing = getWellbeingRecordById(id);
        existing.setWellbeingType(updated.getWellbeingType());
        existing.setWellbeingStatus(updated.getWellbeingStatus());
        existing.setSeverityLevel(updated.getSeverityLevel());
        existing.setDescription(updated.getDescription());
        existing.setCounselorId(updated.getCounselorId());
        existing.setSessionDate(updated.getSessionDate());
        existing.setNextSessionDate(updated.getNextSessionDate());
        existing.setTreatmentPlan(updated.getTreatmentPlan());
        existing.setProgressNotes(updated.getProgressNotes());
        return wellbeingRecordRepository.save(existing);
    }

    @Transactional
    public WellbeingRecord resolveWellbeing(String id) {
        WellbeingRecord existing = getWellbeingRecordById(id);
        existing.setWellbeingStatus("RESOLVED");
        return wellbeingRecordRepository.save(existing);
    }

    @Transactional
    public void deactivateWellbeingRecord(String id) {
        WellbeingRecord existing = getWellbeingRecordById(id);
        existing.setActive(false);
        wellbeingRecordRepository.save(existing);
    }
}
