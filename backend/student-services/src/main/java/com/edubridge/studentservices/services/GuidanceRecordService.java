package com.edubridge.studentservices.services;

import com.edubridge.studentservices.entities.GuidanceRecord;
import com.edubridge.studentservices.repositories.GuidanceRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GuidanceRecordService {

    private final GuidanceRecordRepository guidanceRecordRepository;

    @Transactional
    public GuidanceRecord createGuidanceRecord(GuidanceRecord guidance) {
        log.info("Creating guidance record for student: {}", guidance.getStudentId());
        return guidanceRecordRepository.save(guidance);
    }

    @Transactional(readOnly = true)
    public GuidanceRecord getGuidanceRecordById(String id) {
        return guidanceRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guidance record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<GuidanceRecord> getGuidanceRecordsByMentorship(String mentorshipId) {
        return guidanceRecordRepository.findByMentorshipIdAndIsActiveTrue(mentorshipId);
    }

    @Transactional(readOnly = true)
    public List<GuidanceRecord> getGuidanceRecordsByStudent(String studentId) {
        return guidanceRecordRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<GuidanceRecord> getGuidanceRecordsByMentor(String mentorId) {
        return guidanceRecordRepository.findByMentorIdAndIsActiveTrue(mentorId);
    }

    @Transactional(readOnly = true)
    public List<GuidanceRecord> getGuidanceRecordsByType(String guidanceType) {
        return guidanceRecordRepository.findByGuidanceTypeAndIsActiveTrue(guidanceType);
    }

    @Transactional(readOnly = true)
    public List<GuidanceRecord> getGuidanceRecordsByStatus(String guidanceStatus) {
        return guidanceRecordRepository.findByGuidanceStatusAndIsActiveTrue(guidanceStatus);
    }

    @Transactional(readOnly = true)
    public List<GuidanceRecord> getScheduledByStudent(String studentId) {
        return guidanceRecordRepository.findScheduledByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<GuidanceRecord> getScheduledByMentor(String mentorId) {
        return guidanceRecordRepository.findScheduledByMentor(mentorId);
    }

    @Transactional
    public GuidanceRecord updateGuidanceRecord(String id, GuidanceRecord updated) {
        GuidanceRecord existing = getGuidanceRecordById(id);
        existing.setGuidanceType(updated.getGuidanceType());
        existing.setGuidanceStatus(updated.getGuidanceStatus());
        existing.setTopic(updated.getTopic());
        existing.setDescription(updated.getDescription());
        existing.setScheduledAt(updated.getScheduledAt());
        existing.setDurationMinutes(updated.getDurationMinutes());
        existing.setSessionNotes(updated.getSessionNotes());
        return guidanceRecordRepository.save(existing);
    }

    @Transactional
    public GuidanceRecord completeGuidance(String id, String notes) {
        GuidanceRecord existing = getGuidanceRecordById(id);
        existing.setGuidanceStatus("COMPLETED");
        existing.setCompletedAt(LocalDateTime.now());
        if (notes != null) {
            existing.setSessionNotes(notes);
        }
        return guidanceRecordRepository.save(existing);
    }

    @Transactional
    public void deactivateGuidanceRecord(String id) {
        GuidanceRecord existing = getGuidanceRecordById(id);
        existing.setActive(false);
        guidanceRecordRepository.save(existing);
    }
}
