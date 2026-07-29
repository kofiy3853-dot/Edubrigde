package com.edubridge.financial.repositories;

import com.edubridge.financial.entities.AssessmentRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssessmentRecordRepository extends JpaRepository<AssessmentRecord, String> {

    List<AssessmentRecord> findByStudentIdAndIsActiveTrue(String studentId);

    List<AssessmentRecord> findByScholarshipIdAndIsActiveTrue(String scholarshipId);

    List<AssessmentRecord> findByAssessmentStatusAndIsActiveTrue(String assessmentStatus);

    List<AssessmentRecord> findByRiskLevelAndIsActiveTrue(String riskLevel);

    @Query("SELECT ar FROM AssessmentRecord ar WHERE ar.studentId = :studentId AND ar.scholarshipId = :scholarshipId AND ar.isActive = true")
    List<AssessmentRecord> findByStudentAndScholarship(@Param("studentId") String studentId, @Param("scholarshipId") String scholarshipId);

    @Query("SELECT ar FROM AssessmentRecord ar WHERE ar.studentId = :studentId AND ar.assessmentStatus = 'ELIGIBLE' AND ar.isActive = true")
    List<AssessmentRecord> findEligibleByStudent(@Param("studentId") String studentId);

    @Query("SELECT ar FROM AssessmentRecord ar WHERE ar.riskLevel = 'HIGH' OR ar.riskLevel = 'CRITICAL' AND ar.isActive = true")
    List<AssessmentRecord> findHighRiskAssessments();
}
