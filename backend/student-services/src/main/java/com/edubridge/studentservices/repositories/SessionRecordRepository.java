package com.edubridge.studentservices.repositories;

import com.edubridge.studentservices.entities.SessionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRecordRepository extends JpaRepository<SessionRecord, String> {

    List<SessionRecord> findByConsultationIdAndIsActiveTrue(String consultationId);

    List<SessionRecord> findByStudentIdAndIsActiveTrue(String studentId);

    List<SessionRecord> findByAdvisorIdAndIsActiveTrue(String advisorId);

    List<SessionRecord> findBySessionStatusAndIsActiveTrue(String sessionStatus);

    @Query("SELECT sr FROM SessionRecord sr WHERE sr.studentId = :studentId AND sr.sessionStatus = 'COMPLETED' AND sr.isActive = true")
    List<SessionRecord> findCompletedByStudent(@Param("studentId") String studentId);

    @Query("SELECT sr FROM SessionRecord sr WHERE sr.advisorId = :advisorId AND sr.sessionStatus = 'SCHEDULED' AND sr.isActive = true")
    List<SessionRecord> findScheduledByAdvisor(@Param("advisorId") String advisorId);

    @Query("SELECT sr FROM SessionRecord sr WHERE sr.followUpRequired = true AND sr.sessionStatus = 'COMPLETED' AND sr.isActive = true")
    List<SessionRecord> findRequiringFollowUp();
}
