package com.edubridge.studentservices.repositories;

import com.edubridge.studentservices.entities.PerformanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerformanceRecordRepository extends JpaRepository<PerformanceRecord, String> {

    List<PerformanceRecord> findByAssessmentProfileIdAndIsActiveTrue(String assessmentProfileId);

    List<PerformanceRecord> findByStudentIdAndIsActiveTrue(String studentId);

    List<PerformanceRecord> findByPerformanceTypeAndIsActiveTrue(String performanceType);

    List<PerformanceRecord> findByPerformanceStatusAndIsActiveTrue(String performanceStatus);

    @Query("SELECT pr FROM PerformanceRecord pr WHERE pr.studentId = :studentId AND pr.performanceStatus = 'RECORDED' AND pr.isActive = true")
    List<PerformanceRecord> findRecordedByStudent(@Param("studentId") String studentId);

    @Query("SELECT pr FROM PerformanceRecord pr WHERE pr.studentId = :studentId ORDER BY pr.recordedDate DESC")
    List<PerformanceRecord> findLatestByStudent(@Param("studentId") String studentId);
}
