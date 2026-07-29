package com.edubridge.studentservices.repositories;

import com.edubridge.studentservices.entities.AssessmentRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssessmentRecordRepository extends JpaRepository<AssessmentRecord, String> {

    List<AssessmentRecord> findByConsultationIdAndIsActiveTrue(String consultationId);

    List<AssessmentRecord> findByStudentIdAndIsActiveTrue(String studentId);

    List<AssessmentRecord> findByAdvisorIdAndIsActiveTrue(String advisorId);

    List<AssessmentRecord> findByAssessmentTypeAndIsActiveTrue(String assessmentType);

    List<AssessmentRecord> findByAssessmentStatusAndIsActiveTrue(String assessmentStatus);

    @Query("SELECT ar FROM AssessmentRecord ar WHERE ar.studentId = :studentId AND ar.assessmentType = :type AND ar.isActive = true")
    List<AssessmentRecord> findByStudentAndType(@Param("studentId") String studentId, @Param("type") String type);

    @Query("SELECT ar FROM AssessmentRecord ar WHERE ar.advisorId = :advisorId AND ar.assessmentType = :type AND ar.isActive = true")
    List<AssessmentRecord> findByAdvisorAndType(@Param("advisorId") String advisorId, @Param("type") String type);
}
