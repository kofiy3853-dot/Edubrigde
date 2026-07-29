package com.edubridge.financial.repositories;

import com.edubridge.financial.entities.BudgetProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetProfileRepository extends JpaRepository<BudgetProfile, String> {

    List<BudgetProfile> findByStudentIdAndIsActiveTrue(String studentId);

    List<BudgetProfile> findByUniversityIdAndIsActiveTrue(String universityId);

    List<BudgetProfile> findByCountryCodeAndIsActiveTrue(String countryCode);

    List<BudgetProfile> findByAffordabilityStatusAndIsActiveTrue(String affordabilityStatus);

    List<BudgetProfile> findByRiskLevelAndIsActiveTrue(String riskLevel);

    @Query("SELECT bp FROM BudgetProfile bp WHERE bp.studentId = :studentId AND bp.isFinalized = false AND bp.isActive = true")
    List<BudgetProfile> findDraftByStudent(@Param("studentId") String studentId);

    @Query("SELECT bp FROM BudgetProfile bp WHERE bp.studentId = :studentId AND bp.isFinalized = true AND bp.isActive = true")
    List<BudgetProfile> findFinalizedByStudent(@Param("studentId") String studentId);

    @Query("SELECT bp FROM BudgetProfile bp WHERE bp.riskLevel = 'HIGH' OR bp.riskLevel = 'CRITICAL' AND bp.isActive = true")
    List<BudgetProfile> findHighRiskBudgets();
}
