package com.edubridge.financial.repositories;

import com.edubridge.financial.entities.FinancialLifecycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FinancialLifecycleRepository extends JpaRepository<FinancialLifecycle, String> {

    List<FinancialLifecycle> findByAssessmentId(String assessmentId);

    List<FinancialLifecycle> findByStatus(String status);

    List<FinancialLifecycle> findByAssessmentIdAndStatus(String assessmentId, String status);

    List<FinancialLifecycle> findByIsCurrentTrue();

    Optional<FinancialLifecycle> findByAssessmentIdAndIsCurrentTrue(String assessmentId);

    List<FinancialLifecycle> findByIsActiveTrue();

    @Query("SELECT fl FROM FinancialLifecycle fl WHERE fl.isActive = true AND fl.assessmentId = :assessmentId")
    List<FinancialLifecycle> findActiveByAssessment(@Param("assessmentId") String assessmentId);

    @Query("SELECT fl FROM FinancialLifecycle fl WHERE fl.isActive = true AND fl.status = :status")
    List<FinancialLifecycle> findActiveByStatus(@Param("status") String status);

    @Query("SELECT fl FROM FinancialLifecycle fl WHERE fl.isCurrent = true AND fl.assessmentId = :assessmentId")
    Optional<FinancialLifecycle> findCurrentByAssessment(@Param("assessmentId") String assessmentId);
}
