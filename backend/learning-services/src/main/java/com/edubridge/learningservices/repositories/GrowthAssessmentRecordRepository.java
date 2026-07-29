package com.edubridge.learningservices.repositories;

import com.edubridge.learningservices.entities.GrowthAssessmentRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrowthAssessmentRecordRepository extends JpaRepository<GrowthAssessmentRecord, String> {

    List<GrowthAssessmentRecord> findByStudentIdAndIsActiveTrue(String studentId);

    List<GrowthAssessmentRecord> findByGrowthProfileIdAndIsActiveTrue(String growthProfileId);

    List<GrowthAssessmentRecord> findByAssessmentTypeAndIsActiveTrue(String assessmentType);

    List<GrowthAssessmentRecord> findByAssessmentStatusAndIsActiveTrue(String assessmentStatus);

    List<GrowthAssessmentRecord> findByAssessmentDomainAndIsActiveTrue(String assessmentDomain);

    @Query("SELECT gar FROM GrowthAssessmentRecord gar WHERE gar.studentId = :studentId AND gar.isPassed = true AND gar.isActive = true")
    List<GrowthAssessmentRecord> findPassedByStudent(@Param("studentId") String studentId);

    @Query("SELECT gar FROM GrowthAssessmentRecord gar WHERE gar.studentId = :studentId AND gar.assessmentDomain = :domain AND gar.isActive = true")
    List<GrowthAssessmentRecord> findByStudentAndDomain(@Param("studentId") String studentId, @Param("domain") String domain);

    @Query("SELECT gar FROM GrowthAssessmentRecord gar WHERE gar.validUntil < CURRENT_TIMESTAMP AND gar.assessmentStatus = 'COMPLETED' AND gar.isActive = true")
    List<GrowthAssessmentRecord> findExpiredAssessments();
}
