package com.edubridge.studentservices.services;

import com.edubridge.studentservices.entities.FeedbackRecord;
import com.edubridge.studentservices.repositories.FeedbackRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedbackRecordService {

    private final FeedbackRecordRepository feedbackRecordRepository;

    @Transactional
    public FeedbackRecord createFeedbackRecord(FeedbackRecord record) {
        log.info("Creating feedback record for student: {}", record.getStudentId());
        return feedbackRecordRepository.save(record);
    }

    @Transactional(readOnly = true)
    public FeedbackRecord getFeedbackRecordById(String id) {
        return feedbackRecordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback record not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<FeedbackRecord> getFeedbackRecordsByProfile(String assessmentProfileId) {
        return feedbackRecordRepository.findByAssessmentProfileIdAndIsActiveTrue(assessmentProfileId);
    }

    @Transactional(readOnly = true)
    public List<FeedbackRecord> getFeedbackRecordsByStudent(String studentId) {
        return feedbackRecordRepository.findByStudentIdAndIsActiveTrue(studentId);
    }

    @Transactional(readOnly = true)
    public List<FeedbackRecord> getFeedbackRecordsByType(String feedbackType) {
        return feedbackRecordRepository.findByFeedbackTypeAndIsActiveTrue(feedbackType);
    }

    @Transactional(readOnly = true)
    public List<FeedbackRecord> getFeedbackRecordsByStatus(String feedbackStatus) {
        return feedbackRecordRepository.findByFeedbackStatusAndIsActiveTrue(feedbackStatus);
    }

    @Transactional(readOnly = true)
    public List<FeedbackRecord> getSubmittedByStudent(String studentId) {
        return feedbackRecordRepository.findSubmittedByStudent(studentId);
    }

    @Transactional(readOnly = true)
    public List<FeedbackRecord> getReviewedByStudent(String studentId) {
        return feedbackRecordRepository.findReviewedByStudent(studentId);
    }

    @Transactional
    public FeedbackRecord updateFeedbackRecord(String id, FeedbackRecord updated) {
        FeedbackRecord existing = getFeedbackRecordById(id);
        existing.setFeedbackType(updated.getFeedbackType());
        existing.setFeedbackStatus(updated.getFeedbackStatus());
        existing.setFeedbackCategory(updated.getFeedbackCategory());
        existing.setRating(updated.getRating());
        existing.setFeedbackContent(updated.getFeedbackContent());
        existing.setActionItems(updated.getActionItems());
        existing.setFeedbackFromId(updated.getFeedbackFromId());
        return feedbackRecordRepository.save(existing);
    }

    @Transactional
    public FeedbackRecord reviewFeedback(String id) {
        FeedbackRecord existing = getFeedbackRecordById(id);
        existing.setFeedbackStatus("REVIEWED");
        return feedbackRecordRepository.save(existing);
    }

    @Transactional
    public void deactivateFeedbackRecord(String id) {
        FeedbackRecord existing = getFeedbackRecordById(id);
        existing.setActive(false);
        feedbackRecordRepository.save(existing);
    }
}
