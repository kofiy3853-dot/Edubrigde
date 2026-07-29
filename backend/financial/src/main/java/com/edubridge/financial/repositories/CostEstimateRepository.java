package com.edubridge.financial.repositories;

import com.edubridge.financial.entities.CostEstimate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CostEstimateRepository extends JpaRepository<CostEstimate, String> {

    List<CostEstimate> findByStudentIdAndIsActiveTrue(String studentId);

    List<CostEstimate> findByUniversityIdAndIsActiveTrue(String universityId);

    List<CostEstimate> findByProgramIdAndIsActiveTrue(String programId);

    Optional<CostEstimate> findByStudentIdAndUniversityIdAndProgramIdAndAcademicYearAndIsActiveTrue(String studentId, String universityId, String programId, Integer academicYear);

    @Query("SELECT ce FROM CostEstimate ce WHERE ce.studentId = :studentId AND ce.isFinalized = false AND ce.isActive = true")
    List<CostEstimate> findDraftByStudent(@Param("studentId") String studentId);

    @Query("SELECT ce FROM CostEstimate ce WHERE ce.studentId = :studentId AND ce.isFinalized = true AND ce.isActive = true ORDER BY ce.finalizedAt DESC")
    List<CostEstimate> findFinalizedByStudent(@Param("studentId") String studentId);

    @Query("SELECT ce FROM CostEstimate ce WHERE ce.riskLevel = :riskLevel AND ce.isActive = true")
    List<CostEstimate> findByRiskLevel(@Param("riskLevel") String riskLevel);
}
