package com.edubridge.financial.repositories;

import com.edubridge.financial.entities.AuditRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditRecordRepository extends JpaRepository<AuditRecord, String> {

    List<AuditRecord> findByScholarshipIdAndIsActiveTrue(String scholarshipId);

    List<AuditRecord> findByStudentIdAndIsActiveTrue(String studentId);

    List<AuditRecord> findByActionAndIsActiveTrue(String action);

    List<AuditRecord> findByEntityTypeAndIsActiveTrue(String entityType);

    @Query("SELECT ar FROM AuditRecord ar WHERE ar.scholarshipId = :scholarshipId AND ar.action = :action AND ar.isActive = true")
    List<AuditRecord> findByScholarshipAndAction(@Param("scholarshipId") String scholarshipId, @Param("action") String action);

    @Query("SELECT ar FROM AuditRecord ar WHERE ar.studentId = :studentId AND ar.action = :action AND ar.isActive = true")
    List<AuditRecord> findByStudentAndAction(@Param("studentId") String studentId, @Param("action") String action);

    @Query("SELECT ar FROM AuditRecord ar WHERE ar.entityType = :entityType AND ar.entityId = :entityId AND ar.isActive = true ORDER BY ar.performedAt DESC")
    List<AuditRecord> findByEntityTypeAndEntityId(@Param("entityType") String entityType, @Param("entityId") String entityId);
}
