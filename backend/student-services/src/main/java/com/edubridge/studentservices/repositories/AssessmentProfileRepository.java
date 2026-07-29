package com.edubridge.studentservices.repositories;

import com.edubridge.studentservices.entities.AssessmentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssessmentProfileRepository extends JpaRepository<AssessmentProfile, String> {

    List<AssessmentProfile> findByStudentIdAndIsActiveTrue(String studentId);

    List<AssessmentProfile> findByAssessmentTypeAndIsActiveTrue(String assessmentType);

    List<AssessmentProfile> findByAssessmentStatusAndIsActiveTrue(String assessmentStatus);

    List<AssessmentProfile> findByAssessmentPeriodAndIsActiveTrue(String assessmentPeriod);

    @Query("SELECT ap FROM AssessmentProfile ap WHERE ap.studentId = :studentId AND ap.assessmentStatus = 'IN_PROGRESS' AND ap.isActive = true")
    List<AssessmentProfile> findInProgressByStudent(@Param("studentId") String studentId);

    @Query("SELECT ap FROM AssessmentProfile ap WHERE ap.assessmentStatus = 'PLANNING' AND ap.isActive = true")
    List<AssessmentProfile> findPlanningProfiles();
}
