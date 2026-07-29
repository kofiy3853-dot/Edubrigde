package com.edubridge.studentservices.repositories;

import com.edubridge.studentservices.entities.MatchingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchingRecordRepository extends JpaRepository<MatchingRecord, String> {

    List<MatchingRecord> findByMentorshipIdAndIsActiveTrue(String mentorshipId);

    List<MatchingRecord> findByStudentIdAndIsActiveTrue(String studentId);

    List<MatchingRecord> findByMentorIdAndIsActiveTrue(String mentorId);

    List<MatchingRecord> findByMatchingStatusAndIsActiveTrue(String matchingStatus);

    @Query("SELECT mr FROM MatchingRecord mr WHERE mr.studentId = :studentId AND mr.matchingStatus = 'PROPOSED' AND mr.isActive = true")
    List<MatchingRecord> findProposedByStudent(@Param("studentId") String studentId);

    @Query("SELECT mr FROM MatchingRecord mr WHERE mr.mentorId = :mentorId AND mr.matchingStatus = 'PROPOSED' AND mr.isActive = true")
    List<MatchingRecord> findPendingByMentor(@Param("mentorId") String mentorId);
}
