package com.edubridge.financial.repositories;

import com.edubridge.financial.entities.SustainabilityRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SustainabilityRecordRepository extends JpaRepository<SustainabilityRecord, String> {

    List<SustainabilityRecord> findByBudgetProfileIdAndIsActiveTrue(String budgetProfileId);

    List<SustainabilityRecord> findByStudentIdAndIsActiveTrue(String studentId);

    List<SustainabilityRecord> findBySustainabilityTypeAndIsActiveTrue(String sustainabilityType);

    List<SustainabilityRecord> findBySustainabilityStatusAndIsActiveTrue(String sustainabilityStatus);

    @Query("SELECT sr FROM SustainabilityRecord sr WHERE sr.studentId = :studentId AND sr.sustainabilityStatus = 'SUSTAINABLE' AND sr.isActive = true")
    List<SustainabilityRecord> findSustainableByStudent(@Param("studentId") String studentId);

    @Query("SELECT sr FROM SustainabilityRecord sr WHERE sr.studentId = :studentId AND sr.sustainabilityStatus = 'NOT_SUSTAINABLE' AND sr.isActive = true")
    List<SustainabilityRecord> findNotSustainableByStudent(@Param("studentId") String studentId);

    @Query("SELECT sr FROM SustainabilityRecord sr WHERE sr.budgetProfileId = :budgetProfileId AND sr.sustainabilityType = :type AND sr.isActive = true")
    List<SustainabilityRecord> findByBudgetAndType(@Param("budgetProfileId") String budgetProfileId, @Param("type") String type);
}
