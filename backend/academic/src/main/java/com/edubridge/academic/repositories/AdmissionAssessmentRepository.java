package com.edubridge.academic.repositories;

import com.edubridge.academic.entities.AdmissionAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdmissionAssessmentRepository extends JpaRepository<AdmissionAssessment, String> {

    List<AdmissionAssessment> findByUniversityId(String universityId);

    List<AdmissionAssessment> findByProgramId(String programId);

    List<AdmissionAssessment> findByStudentId(String studentId);

    List<AdmissionAssessment> findByStatus(String status);

    List<AdmissionAssessment> findByAssessmentType(String assessmentType);

    List<AdmissionAssessment> findByUniversityIdAndProgramId(String universityId, String programId);

    List<AdmissionAssessment> findByStudentIdAndStatus(String studentId, String status);

    List<AdmissionAssessment> findByIsActiveTrue();

    @Query("SELECT aa FROM AdmissionAssessment aa WHERE aa.isActive = true AND aa.universityId = :universityId")
    List<AdmissionAssessment> findActiveByUniversity(@Param("universityId") String universityId);

    @Query("SELECT aa FROM AdmissionAssessment aa WHERE aa.isActive = true AND aa.programId = :programId")
    List<AdmissionAssessment> findActiveByProgram(@Param("programId") String programId);

    @Query("SELECT aa FROM AdmissionAssessment aa WHERE aa.isActive = true AND aa.studentId = :studentId")
    List<AdmissionAssessment> findActiveByStudent(@Param("studentId") String studentId);

    @Query("SELECT aa FROM AdmissionAssessment aa WHERE aa.isActive = true AND aa.status = :status")
    List<AdmissionAssessment> findActiveByStatus(@Param("status") String status);
}
