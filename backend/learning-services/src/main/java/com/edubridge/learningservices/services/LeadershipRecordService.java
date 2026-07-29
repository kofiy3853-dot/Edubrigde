package com.edubridge.learningservices.services;

import com.edubridge.learningservices.entities.LeadershipRecord;
import com.edubridge.learningservices.repositories.LeadershipRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LeadershipRecordService {

    private final LeadershipRecordRepository leadershipRecordRepository;

    @Transactional
    public LeadershipRecord createLeadershipRecord(LeadershipRecord record) {
        log.info("Creating leadership record for student: {} type: {}", record.getStudentId(), record.getLeadershipType());
        return leadershipRecordRepository.save(record);
    }

    @Transactional(readOnly = true)
    public LeadershipRecord getLeadershipRecordById(String id) {
        return leadershipRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leadership record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<LeadershipRecord> getLeadershipRecordsByStudent(String studentId) {
        return leadershipRecordRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<LeadershipRecord> getLeadershipRecordsByCareerProfile(String careerProfileId) {
        return leadershipRecordRepository.findByCareerProfileIdAndIsActiveTrue(careerProfileId);
    }

    @Transactional(readOnly = true)
    public List<LeadershipRecord> getLeadershipRecordsByType(String leadershipType) {
        return leadershipRecordRepository.findByLeadershipTypeAndIsActiveTrue(leadershipType);
    }

    @Transactional(readOnly = true)
    public List<LeadershipRecord> getLeadershipRecordsByStatus(String leadershipStatus) {
        return leadershipRecordRepository.findByLeadershipStatusAndIsActiveTrue(leadershipStatus);
    }

    @Transactional(readOnly = true)
    public List<LeadershipRecord> getLeadershipRecordsByLevel(String leadershipLevel) {
        return leadershipRecordRepository.findByLeadershipLevelAndIsActiveTrue(leadershipLevel);
    }

    @Transactional(readOnly = true)
    public List<LeadershipRecord> getAchievedByStudent(String studentId) {
        return leadershipRecordRepository.findAchievedByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<LeadershipRecord> getLeadershipByStudentAndType(String studentId, String type) {
        return leadershipRecordRepository.findByStudentAndType(studentId, type);
    }

    @Transactional(readOnly = true)
    public Integer countTotalProjectsByStudent(String studentId) {
        return leadershipRecordRepository.countTotalProjectsByStudent(studentId);
    }

    @Transactional
    public LeadershipRecord updateLeadershipRecord(String id, LeadershipRecord updated) {
        LeadershipRecord existing = getLeadershipRecordById(id);
        existing.setLeadershipType(updated.getLeadershipType());
        existing.setLeadershipName(updated.getLeadershipName());
        existing.setLeadershipStatus(updated.getLeadershipStatus());
        existing.setLeadershipLevel(updated.getLeadershipLevel());
        existing.setLeadershipArea(updated.getLeadershipArea());
        existing.setLeadershipScore(updated.getLeadershipScore());
        existing.setTargetLeadershipScore(updated.getTargetLeadershipScore());
        existing.setTeamSizeManaged(updated.getTeamSizeManaged());
        existing.setProjectCount(updated.getProjectCount());
        existing.setInnovationScore(updated.getInnovationScore());
        existing.setEntrepreneurshipScore(updated.getEntrepreneurshipScore());
        existing.setStartDate(updated.getStartDate());
        existing.setEndDate(updated.getEndDate());
        existing.setNotes(updated.getNotes());
        return leadershipRecordRepository.save(existing);
    }

    @Transactional
    public void deactivateLeadershipRecord(String id) {
        LeadershipRecord existing = getLeadershipRecordById(id);
        existing.setActive(false);
        leadershipRecordRepository.save(existing);
    }
}
