package com.edubridge.financial.repositories;

import com.edubridge.financial.entities.RiskAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RiskAssessmentRepository extends JpaRepository<RiskAssessment, String> {

    List<RiskAssessment> findByBudgetProfileIdAndIsActiveTrue(String budgetProfileId);

    List<RiskAssessment> findByStudentIdAndIsActiveTrue(String studentId);

    List<RiskAssessment> findByRiskTypeAndIsActiveTrue(String riskType);

    List<RiskAssessment> findByRiskLevelAndIsActiveTrue(String riskLevel);

    @Query("SELECT ra FROM RiskAssessment ra WHERE ra.studentId = :studentId AND ra.riskLevel = 'LOW' AND ra.isActive = true")
    List<RiskAssessment> findLowRiskByStudent(@Param("studentId") String studentId);

    @Query("SELECT ra FROM RiskAssessment ra WHERE ra.studentId = :studentId AND ra.riskLevel = 'HIGH' OR ra.riskLevel = 'CRITICAL' AND ra.isActive = true")
    List<RiskAssessment> findHighRiskByStudent(@Param("studentId") String studentId);

    @Query("SELECT ra FROM RiskAssessment ra WHERE ra.budgetProfileId = :budgetProfileId AND ra.riskType = :type AND ra.isActive = true")
    List<RiskAssessment> findByBudgetAndType(@Param("budgetProfileId") String budgetProfileId, @Param("type") String type);
}
