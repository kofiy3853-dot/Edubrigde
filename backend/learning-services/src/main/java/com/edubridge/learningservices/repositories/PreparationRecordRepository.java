package com.edubridge.learningservices.repositories;

import com.edubridge.learningservices.entities.PreparationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreparationRecordRepository extends JpaRepository<PreparationRecord, String> {

    List<PreparationRecord> findByCourseProfileIdAndIsActiveTrue(String courseProfileId);

    List<PreparationRecord> findByStudentIdAndIsActiveTrue(String studentId);

    List<PreparationRecord> findByPreparationTypeAndIsActiveTrue(String preparationType);

    List<PreparationRecord> findByPreparationStatusAndIsActiveTrue(String preparationStatus);

    @Query("SELECT pr FROM PreparationRecord pr WHERE pr.studentId = :studentId AND pr.preparationStatus = 'IN_PROGRESS' AND pr.isActive = true")
    List<PreparationRecord> findInProgressByStudent(@Param("studentId") String studentId);

    @Query("SELECT pr FROM PreparationRecord pr WHERE pr.studentId = :studentId AND pr.preparationStatus = 'PLANNING' AND pr.isActive = true")
    List<PreparationRecord> findPlanningByStudent(@Param("studentId") String studentId);
}
