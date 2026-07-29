package com.edubridge.studentservices.services;

import com.edubridge.studentservices.entities.MatchingRecord;
import com.edubridge.studentservices.repositories.MatchingRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MatchingRecordService {

    private final MatchingRecordRepository matchingRecordRepository;

    @Transactional
    public MatchingRecord createMatchingRecord(MatchingRecord matching) {
        log.info("Creating matching record for student: {} mentor: {}", matching.getStudentId(), matching.getMentorId());
        return matchingRecordRepository.save(matching);
    }

    @Transactional(readOnly = true)
    public MatchingRecord getMatchingRecordById(String id) {
        return matchingRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matching record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<MatchingRecord> getMatchingRecordsByMentorship(String mentorshipId) {
        return matchingRecordRepository.findByMentorshipIdAndIsActiveTrue(mentorshipId);
    }

    @Transactional(readOnly = true)
    public List<MatchingRecord> getMatchingRecordsByStudent(String studentId) {
        return matchingRecordRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<MatchingRecord> getMatchingRecordsByMentor(String mentorId) {
        return matchingRecordRepository.findByMentorIdAndIsActiveTrue(mentorId);
    }

    @Transactional(readOnly = true)
    public List<MatchingRecord> getMatchingRecordsByStatus(String matchingStatus) {
        return matchingRecordRepository.findByMatchingStatusAndIsActiveTrue(matchingStatus);
    }

    @Transactional(readOnly = true)
    public List<MatchingRecord> getProposedByStudent(String studentId) {
        return matchingRecordRepository.findProposedByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<MatchingRecord> getPendingByMentor(String mentorId) {
        return matchingRecordRepository.findPendingByMentor(mentorId);
    }

    @Transactional
    public MatchingRecord updateMatchingRecord(String id, MatchingRecord updated) {
        MatchingRecord existing = getMatchingRecordById(id);
        existing.setMatchingStatus(updated.getMatchingStatus());
        existing.setCompatibilityScore(updated.getCompatibilityScore());
        existing.setMatchingCriteria(updated.getMatchingCriteria());
        existing.setStudentFeedback(updated.getStudentFeedback());
        existing.setMentorFeedback(updated.getMentorFeedback());
        return matchingRecordRepository.save(existing);
    }

    @Transactional
    public MatchingRecord acceptMatching(String id) {
        MatchingRecord existing = getMatchingRecordById(id);
        existing.setMatchingStatus("ACCEPTED");
        existing.setMatchedAt(LocalDateTime.now());
        return matchingRecordRepository.save(existing);
    }

    @Transactional
    public MatchingRecord rejectMatching(String id) {
        MatchingRecord existing = getMatchingRecordById(id);
        existing.setMatchingStatus("REJECTED");
        return matchingRecordRepository.save(existing);
    }

    @Transactional
    public void deactivateMatchingRecord(String id) {
        MatchingRecord existing = getMatchingRecordById(id);
        existing.setActive(false);
        matchingRecordRepository.save(existing);
    }
}
