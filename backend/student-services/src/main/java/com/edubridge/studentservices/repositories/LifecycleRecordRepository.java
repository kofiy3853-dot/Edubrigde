package com.edubridge.studentservices.repositories;

import com.edubridge.studentservices.entities.LifecycleRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LifecycleRecordRepository extends JpaRepository<LifecycleRecord, String> {

    List<LifecycleRecord> findByMentorshipIdAndIsActiveTrue(String mentorshipId);

    List<LifecycleRecord> findByStudentIdAndIsActiveTrue(String studentId);

    @Query("SELECT lr FROM LifecycleRecord lr WHERE lr.mentorshipId = :mentorshipId ORDER BY lr.changedAt DESC")
    List<LifecycleRecord> findByMentorshipIdOrderedByDate(@Param("mentorshipId") String mentorshipId);
}
