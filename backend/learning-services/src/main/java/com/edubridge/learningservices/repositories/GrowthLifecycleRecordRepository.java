package com.edubridge.learningservices.repositories;

import com.edubridge.learningservices.entities.GrowthLifecycleRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrowthLifecycleRecordRepository extends JpaRepository<GrowthLifecycleRecord, String> {

    List<GrowthLifecycleRecord> findByGrowthProfileIdAndIsActiveTrue(String growthProfileId);

    List<GrowthLifecycleRecord> findByStudentIdAndIsActiveTrue(String studentId);

    List<GrowthLifecycleRecord> findByEntityTypeAndIsActiveTrue(String entityType);

    List<GrowthLifecycleRecord> findByNewStatusAndIsActiveTrue(String newStatus);

    @Query("SELECT glr FROM GrowthLifecycleRecord glr WHERE glr.entityType = :entityType AND glr.entityId = :entityId AND glr.isActive = true ORDER BY glr.changedAt DESC")
    List<GrowthLifecycleRecord> findByEntityTypeAndEntityId(@Param("entityType") String entityType, @Param("entityId") String entityId);

    @Query("SELECT glr FROM GrowthLifecycleRecord glr WHERE glr.studentId = :studentId AND glr.isActive = true ORDER BY glr.changedAt DESC")
    List<GrowthLifecycleRecord> findRecentByStudent(@Param("studentId") String studentId);
}
