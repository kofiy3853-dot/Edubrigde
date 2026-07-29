package com.edubridge.learningservices.repositories;

import com.edubridge.learningservices.entities.CareerAssessmentRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CareerAssessmentRecordRepository extends JpaRepository<CareerAssessmentRecord, String> {

    List<CareerAssessmentRecord> findByStudentIdAndIsActiveTrue(String studentId);

    List<CareerAssessmentRecord> findByCareerProfileIdAndIsActiveTrue(String careerProfileId);

    List<CareerAssessmentRecord> findByAssessmentTypeAndIsActiveTrue(String assessmentType);

    List<CareerAssessmentRecord> findByAssessmentStatusAndIsActiveTrue(String assessmentStatus);

    List<CareerAssessmentRecord> findByAssessmentDomainAndIsActiveTrue(String assessmentDomain);

    @Query("SELECT car FROM CareerAssessmentRecord car WHERE car.studentId = :studentId AND car.isPassed = true AND car.isActive = true")
    List<CareerAssessmentRecord> findPassedByStudent(@Param("studentId") String studentId);

    @Query("SELECT car FROM CareerAssessmentRecord car WHERE car.studentId = :studentId AND car.assessmentDomain = :domain AND car.isActive = true")
    List<CareerAssessmentRecord> findByStudentAndDomain(@Param("studentId") String studentId, @Param("domain") String domain);

    @Query("SELECT car FROM CareerAssessmentRecord car WHERE car.validUntil < CURRENT_TIMESTAMP AND car.assessmentStatus = 'COMPLETED' AND car.isActive = true")
    List<CareerAssessmentRecord> findExpiredAssessments();
}
