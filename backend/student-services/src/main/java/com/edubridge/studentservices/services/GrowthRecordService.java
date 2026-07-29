package com.edubridge.studentservices.services;

import com.edubridge.studentservices.entities.GrowthRecord;
import com.edubridge.studentservices.repositories.GrowthRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GrowthRecordService {

    private final GrowthRecordRepository growthRecordRepository;

    @Transactional
    public GrowthRecord createGrowthRecord(GrowthRecord growth) {
        log.info("Creating growth record for student: {}", growth.getStudentId());
        return growthRecordRepository.save(growth);
    }

    @Transactional(readOnly = true)
    public GrowthRecord getGrowthRecordById(String id) {
        return growthRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Growth record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<GrowthRecord> getGrowthRecordsByMentorship(String mentorshipId) {
        return growthRecordRepository.findByMentorshipIdAndIsActiveTrue(mentorshipId);
    }

    @Transactional(readOnly = true)
    public List<GrowthRecord> getGrowthRecordsByStudent(String studentId) {
        return growthRecordRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<GrowthRecord> getGrowthRecordsByType(String growthType) {
        return growthRecordRepository.findByGrowthTypeAndIsActiveTrue(growthType);
    }

    @Transactional(readOnly = true)
    public List<GrowthRecord> getGrowthRecordsByStatus(String growthStatus) {
        return growthRecordRepository.findByGrowthStatusAndIsActiveTrue(growthStatus);
    }

    @Transactional(readOnly = true)
    public List<GrowthRecord> getTrackingByStudent(String studentId) {
        return growthRecordRepository.findTrackingByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<GrowthRecord> getAchievedByStudent(String studentId) {
        return growthRecordRepository.findAchievedByStudent(studentId);
    }

    @Transactional
    public GrowthRecord updateGrowthRecord(String id, GrowthRecord updated) {
        GrowthRecord existing = getGrowthRecordById(id);
        existing.setGrowthType(updated.getGrowthType());
        existing.setGrowthStatus(updated.getGrowthStatus());
        existing.setMilestoneName(updated.getMilestoneName());
        existing.setDescription(updated.getDescription());
        existing.setTargetDate(updated.getTargetDate());
        existing.setProgressScore(updated.getProgressScore());
        existing.setEvidence(updated.getEvidence());
        existing.setMentorNotes(updated.getMentorNotes());
        return growthRecordRepository.save(existing);
    }

    @Transactional
    public GrowthRecord markAchieved(String id, String evidence) {
        GrowthRecord existing = getGrowthRecordById(id);
        existing.setGrowthStatus("ACHIEVED");
        existing.setAchievedDate(LocalDateTime.now());
        if (evidence != null) {
            existing.setEvidence(evidence);
        }
        return growthRecordRepository.save(existing);
    }

    @Transactional
    public void deactivateGrowthRecord(String id) {
        GrowthRecord existing = getGrowthRecordById(id);
        existing.setActive(false);
        growthRecordRepository.save(existing);
    }
}
