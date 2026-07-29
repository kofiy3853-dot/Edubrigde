package com.edubridge.studentservices.repositories;

import com.edubridge.studentservices.entities.FeedbackRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRecordRepository extends JpaRepository<FeedbackRecord, String> {

    List<FeedbackRecord> findByAssessmentProfileIdAndIsActiveTrue(String assessmentProfileId);

    List<FeedbackRecord> findByStudentIdAndIsActiveTrue(String studentId);

    List<FeedbackRecord> findByFeedbackTypeAndIsActiveTrue(String feedbackType);

    List<FeedbackRecord> findByFeedbackStatusAndIsActiveTrue(String feedbackStatus);

    @Query("SELECT fr FROM FeedbackRecord fr WHERE fr.studentId = :studentId AND fr.feedbackStatus = 'SUBMITTED' AND fr.isActive = true")
    List<FeedbackRecord> findSubmittedByStudent(@Param("studentId") String studentId);

    @Query("SELECT fr FROM FeedbackRecord fr WHERE fr.studentId = :studentId AND fr.feedbackStatus = 'REVIEWED' AND fr.isActive = true")
    List<FeedbackRecord> findReviewedByStudent(@Param("studentId") String studentId);
}
