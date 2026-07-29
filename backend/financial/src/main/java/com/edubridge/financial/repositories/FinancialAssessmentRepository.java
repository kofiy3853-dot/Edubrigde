package com.edubridge.financial.repositories;

import com.edubridge.financial.entities.FinancialAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FinancialAssessmentRepository extends JpaRepository<FinancialAssessment, String> {

    List<FinancialAssessment> findByStudentId(String studentId);

    List<FinancialAssessment> findByUniversityId(String universityId);

    List<FinancialAssessment> findByProgramId(String programId);

    List<FinancialAssessment> findByStatus(String status);

    List<FinancialAssessment> findByAssessmentType(String assessmentType);

    List<FinancialAssessment> findByStudentIdAndStatus(String studentId, String status);

    List<FinancialAssessment> findByIsActiveTrue();

    @Query("SELECT fa FROM FinancialAssessment fa WHERE fa.isActive = true AND fa.studentId = :studentId")
    List<FinancialAssessment> findActiveByStudent(@Param("studentId") String studentId);

    @Query("SELECT fa FROM FinancialAssessment fa WHERE fa.isActive = true AND fa.status = :status")
    List<FinancialAssessment> findActiveByStatus(@Param("status") String status);

    @Query("SELECT fa FROM FinancialAssessment fa WHERE fa.isActive = true AND fa.countryCode = :countryCode")
    List<FinancialAssessment> findActiveByCountry(@Param("countryCode") String countryCode);
}
