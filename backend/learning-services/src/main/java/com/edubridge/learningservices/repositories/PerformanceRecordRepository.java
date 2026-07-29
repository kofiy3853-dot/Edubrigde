package com.edubridge.learningservices.repositories;

import com.edubridge.learningservices.entities.PerformanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerformanceRecordRepository extends JpaRepository<PerformanceRecord, String> {

    List<PerformanceRecord> findByStudentIdAndIsActiveTrue(String studentId);

    List<PerformanceRecord> findByGrowthProfileIdAndIsActiveTrue(String growthProfileId);

    List<PerformanceRecord> findByPerformanceTypeAndIsActiveTrue(String performanceType);

    List<PerformanceRecord> findByPerformanceStatusAndIsActiveTrue(String performanceStatus);

    List<PerformanceRecord> findBySubjectAreaAndIsActiveTrue(String subjectArea);

    @Query("SELECT pr FROM PerformanceRecord pr WHERE pr.studentId = :studentId AND pr.performanceStatus = 'GRADED' AND pr.isActive = true")
    List<PerformanceRecord> findGradedByStudent(@Param("studentId") String studentId);

    @Query("SELECT pr FROM PerformanceRecord pr WHERE pr.studentId = :studentId AND pr.academicPeriod = :period AND pr.isActive = true")
    List<PerformanceRecord> findByStudentAndPeriod(@Param("studentId") String studentId, @Param("period") String period);

    @Query("SELECT AVG(pr.percentageScore) FROM PerformanceRecord pr WHERE pr.studentId = :studentId AND pr.percentageScore IS NOT NULL AND pr.isActive = true")
    Double calculateAverageScoreByStudent(@Param("studentId") String studentId);
}
