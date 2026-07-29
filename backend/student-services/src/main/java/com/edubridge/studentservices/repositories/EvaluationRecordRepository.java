package com.edubridge.studentservices.repositories;

import com.edubridge.studentservices.entities.EvaluationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationRecordRepository extends JpaRepository<EvaluationRecord, String> {

    List<EvaluationRecord> findByAssessmentProfileIdAndIsActiveTrue(String assessmentProfileId);

    List<EvaluationRecord> findByStudentIdAndIsActiveTrue(String studentId);

    List<EvaluationRecord> findByEvaluationTypeAndIsActiveTrue(String evaluationType);

    List<EvaluationRecord> findByEvaluationStatusAndIsActiveTrue(String evaluationStatus);

    @Query("SELECT er FROM EvaluationRecord er WHERE er.studentId = :studentId AND er.evaluationStatus = 'PENDING' AND er.isActive = true")
    List<EvaluationRecord> findPendingByStudent(@Param("studentId") String studentId);

    @Query("SELECT er FROM EvaluationRecord er WHERE er.studentId = :studentId AND er.evaluationStatus = 'COMPLETED' AND er.isActive = true")
    List<EvaluationRecord> findCompletedByStudent(@Param("studentId") String studentId);
}
