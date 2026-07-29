package com.edubridge.studentservices.repositories;

import com.edubridge.studentservices.entities.SupportAssessmentRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupportAssessmentRecordRepository extends JpaRepository<SupportAssessmentRecord, String> {

    List<SupportAssessmentRecord> findBySupportProfileIdAndIsActiveTrue(String supportProfileId);

    List<SupportAssessmentRecord> findByStudentIdAndIsActiveTrue(String studentId);

    List<SupportAssessmentRecord> findByAssessmentTypeAndIsActiveTrue(String assessmentType);

    List<SupportAssessmentRecord> findByAssessmentStatusAndIsActiveTrue(String assessmentStatus);

    List<SupportAssessmentRecord> findByRiskLevelAndIsActiveTrue(String riskLevel);

    @Query("SELECT sar FROM SupportAssessmentRecord sar WHERE sar.studentId = :studentId AND sar.assessmentStatus = 'PENDING' AND sar.isActive = true")
    List<SupportAssessmentRecord> findPendingByStudent(@Param("studentId") String studentId);

    @Query("SELECT sar FROM SupportAssessmentRecord sar WHERE sar.riskLevel = 'HIGH' OR sar.riskLevel = 'CRITICAL' AND sar.isActive = true")
    List<SupportAssessmentRecord> findHighRiskCases();
}
