package com.edubridge.studentservices.repositories;

import com.edubridge.studentservices.entities.LifecycleRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LifecycleRecordRepository extends JpaRepository<LifecycleRecord, String> {

    List<LifecycleRecord> findByConsultationIdAndIsActiveTrue(String consultationId);

    List<LifecycleRecord> findByStudentIdAndIsActiveTrue(String studentId);

    List<LifecycleRecord> findByNewStatusAndIsActiveTrue(String newStatus);

    @Query("SELECT lr FROM LifecycleRecord lr WHERE lr.consultationId = :consultationId AND lr.studentId = :studentId AND lr.isActive = true ORDER BY lr.changedAt DESC")
    List<LifecycleRecord> findByConsultationAndStudent(@Param("consultationId") String consultationId, @Param("studentId") String studentId);

    @Query("SELECT lr FROM LifecycleRecord lr WHERE lr.consultationId = :consultationId ORDER BY lr.changedAt DESC LIMIT 1")
    LifecycleRecord findLatestByConsultation(@Param("consultationId") String consultationId);

    @Query("SELECT lr FROM LifecycleRecord lr WHERE lr.studentId = :studentId ORDER BY lr.changedAt DESC")
    List<LifecycleRecord> findLatestByStudent(@Param("studentId") String studentId);
}
