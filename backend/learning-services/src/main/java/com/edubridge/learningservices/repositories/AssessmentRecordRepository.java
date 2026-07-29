package com.edubridge.learningservices.repositories;

import com.edubridge.learningservices.entities.AssessmentRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssessmentRecordRepository extends JpaRepository<AssessmentRecord, String> {

    List<AssessmentRecord> findByCourseProfileIdAndIsActiveTrue(String courseProfileId);

    List<AssessmentRecord> findByStudentIdAndIsActiveTrue(String studentId);

    List<AssessmentRecord> findByAssessmentTypeAndIsActiveTrue(String assessmentType);

    List<AssessmentRecord> findByAssessmentStatusAndIsActiveTrue(String assessmentStatus);

    @Query("SELECT ar FROM AssessmentRecord ar WHERE ar.studentId = :studentId AND ar.assessmentStatus = 'PENDING' AND ar.isActive = true")
    List<AssessmentRecord> findPendingByStudent(@Param("studentId") String studentId);

    @Query("SELECT ar FROM AssessmentRecord ar WHERE ar.studentId = :studentId AND ar.assessmentStatus = 'COMPLETED' AND ar.isActive = true")
    List<AssessmentRecord> findCompletedByStudent(@Param("studentId") String studentId);
}
