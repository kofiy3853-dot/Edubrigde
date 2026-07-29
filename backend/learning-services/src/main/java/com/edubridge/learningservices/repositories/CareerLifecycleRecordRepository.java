package com.edubridge.learningservices.repositories;

import com.edubridge.learningservices.entities.CareerLifecycleRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CareerLifecycleRecordRepository extends JpaRepository<CareerLifecycleRecord, String> {

    List<CareerLifecycleRecord> findByCareerProfileIdAndIsActiveTrue(String careerProfileId);

    List<CareerLifecycleRecord> findByStudentIdAndIsActiveTrue(String studentId);

    List<CareerLifecycleRecord> findByEntityTypeAndIsActiveTrue(String entityType);

    List<CareerLifecycleRecord> findByNewStatusAndIsActiveTrue(String newStatus);

    @Query("SELECT clr FROM CareerLifecycleRecord clr WHERE clr.entityType = :entityType AND clr.entityId = :entityId AND clr.isActive = true ORDER BY clr.changedAt DESC")
    List<CareerLifecycleRecord> findByEntityTypeAndEntityId(@Param("entityType") String entityType, @Param("entityId") String entityId);

    @Query("SELECT clr FROM CareerLifecycleRecord clr WHERE clr.studentId = :studentId AND clr.isActive = true ORDER BY clr.changedAt DESC")
    List<CareerLifecycleRecord> findRecentByStudent(@Param("studentId") String studentId);
}
