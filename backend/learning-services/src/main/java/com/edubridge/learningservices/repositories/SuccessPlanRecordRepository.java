package com.edubridge.learningservices.repositories;

import com.edubridge.learningservices.entities.SuccessPlanRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuccessPlanRecordRepository extends JpaRepository<SuccessPlanRecord, String> {

    List<SuccessPlanRecord> findByStudentIdAndIsActiveTrue(String studentId);

    List<SuccessPlanRecord> findByGrowthProfileIdAndIsActiveTrue(String growthProfileId);

    List<SuccessPlanRecord> findByPlanTypeAndIsActiveTrue(String planType);

    List<SuccessPlanRecord> findByPlanStatusAndIsActiveTrue(String planStatus);

    List<SuccessPlanRecord> findByPriorityLevelAndIsActiveTrue(String priorityLevel);

    @Query("SELECT spr FROM SuccessPlanRecord spr WHERE spr.studentId = :studentId AND spr.planStatus = 'ACTIVE' AND spr.isActive = true")
    List<SuccessPlanRecord> findActivePlansByStudent(@Param("studentId") String studentId);

    @Query("SELECT spr FROM SuccessPlanRecord spr WHERE spr.studentId = :studentId AND spr.planType = :planType AND spr.isActive = true")
    List<SuccessPlanRecord> findByStudentAndPlanType(@Param("studentId") String studentId, @Param("planType") String planType);

    @Query("SELECT spr FROM SuccessPlanRecord spr WHERE spr.targetCompletionDate < CURRENT_TIMESTAMP AND spr.planStatus = 'IN_PROGRESS' AND spr.isActive = true")
    List<SuccessPlanRecord> findOverduePlans();
}
