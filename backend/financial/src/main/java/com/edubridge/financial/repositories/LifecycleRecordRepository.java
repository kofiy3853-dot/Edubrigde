package com.edubridge.financial.repositories;

import com.edubridge.financial.entities.LifecycleRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LifecycleRecordRepository extends JpaRepository<LifecycleRecord, String> {

    List<LifecycleRecord> findByScholarshipIdAndIsActiveTrue(String scholarshipId);

    List<LifecycleRecord> findByStudentIdAndIsActiveTrue(String studentId);

    List<LifecycleRecord> findByNewStatusAndIsActiveTrue(String newStatus);

    @Query("SELECT lr FROM LifecycleRecord lr WHERE lr.scholarshipId = :scholarshipId AND lr.studentId = :studentId AND lr.isActive = true ORDER BY lr.changedAt DESC")
    List<LifecycleRecord> findByScholarshipAndStudent(@Param("scholarshipId") String scholarshipId, @Param("studentId") String studentId);

    @Query("SELECT lr FROM LifecycleRecord lr WHERE lr.scholarshipId = :scholarshipId ORDER BY lr.changedAt DESC LIMIT 1")
    LifecycleRecord findLatestByScholarship(@Param("scholarshipId") String scholarshipId);

    @Query("SELECT lr FROM LifecycleRecord lr WHERE lr.studentId = :studentId ORDER BY lr.changedAt DESC")
    List<LifecycleRecord> findLatestByStudent(@Param("studentId") String studentId);
}
