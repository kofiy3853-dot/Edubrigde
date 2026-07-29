package com.edubridge.studentservices.repositories;

import com.edubridge.studentservices.entities.GuidanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuidanceRecordRepository extends JpaRepository<GuidanceRecord, String> {

    List<GuidanceRecord> findByMentorshipIdAndIsActiveTrue(String mentorshipId);

    List<GuidanceRecord> findByStudentIdAndIsActiveTrue(String studentId);

    List<GuidanceRecord> findByMentorIdAndIsActiveTrue(String mentorId);

    List<GuidanceRecord> findByGuidanceTypeAndIsActiveTrue(String guidanceType);

    List<GuidanceRecord> findByGuidanceStatusAndIsActiveTrue(String guidanceStatus);

    @Query("SELECT gr FROM GuidanceRecord gr WHERE gr.studentId = :studentId AND gr.guidanceStatus = 'SCHEDULED' AND gr.isActive = true")
    List<GuidanceRecord> findScheduledByStudent(@Param("studentId") String studentId);

    @Query("SELECT gr FROM GuidanceRecord gr WHERE gr.mentorId = :mentorId AND gr.guidanceStatus = 'SCHEDULED' AND gr.isActive = true")
    List<GuidanceRecord> findScheduledByMentor(@Param("mentorId") String mentorId);
}
