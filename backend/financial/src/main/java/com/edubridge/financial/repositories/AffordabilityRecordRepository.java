package com.edubridge.financial.repositories;

import com.edubridge.financial.entities.AffordabilityRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AffordabilityRecordRepository extends JpaRepository<AffordabilityRecord, String> {

    List<AffordabilityRecord> findByBudgetProfileIdAndIsActiveTrue(String budgetProfileId);

    List<AffordabilityRecord> findByStudentIdAndIsActiveTrue(String studentId);

    List<AffordabilityRecord> findByAffordabilityTypeAndIsActiveTrue(String affordabilityType);

    List<AffordabilityRecord> findByAffordabilityStatusAndIsActiveTrue(String affordabilityStatus);

    @Query("SELECT ar FROM AffordabilityRecord ar WHERE ar.studentId = :studentId AND ar.affordabilityStatus = 'FULLY_AFFORDABLE' AND ar.isActive = true")
    List<AffordabilityRecord> findFullyAffordableByStudent(@Param("studentId") String studentId);

    @Query("SELECT ar FROM AffordabilityRecord ar WHERE ar.studentId = :studentId AND ar.affordabilityStatus = 'NOT_AFFORDABLE' AND ar.isActive = true")
    List<AffordabilityRecord> findNotAffordableByStudent(@Param("studentId") String studentId);

    @Query("SELECT ar FROM AffordabilityRecord ar WHERE ar.budgetProfileId = :budgetProfileId AND ar.affordabilityType = :type AND ar.isActive = true")
    List<AffordabilityRecord> findByBudgetAndType(@Param("budgetProfileId") String budgetProfileId, @Param("type") String type);
}
