package com.edubridge.learningservices.repositories;

import com.edubridge.learningservices.entities.EmployabilityRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployabilityRecordRepository extends JpaRepository<EmployabilityRecord, String> {

    List<EmployabilityRecord> findByStudentIdAndIsActiveTrue(String studentId);

    List<EmployabilityRecord> findByCareerProfileIdAndIsActiveTrue(String careerProfileId);

    List<EmployabilityRecord> findByEmployabilityTypeAndIsActiveTrue(String employabilityType);

    List<EmployabilityRecord> findByEmployabilityStatusAndIsActiveTrue(String employabilityStatus);

    List<EmployabilityRecord> findByCompetencyAreaAndIsActiveTrue(String competencyArea);

    @Query("SELECT er FROM EmployabilityRecord er WHERE er.studentId = :studentId AND er.employabilityStatus = 'ACHIEVED' AND er.isActive = true")
    List<EmployabilityRecord> findAchievedByStudent(@Param("studentId") String studentId);

    @Query("SELECT er FROM EmployabilityRecord er WHERE er.studentId = :studentId AND er.competencyLevel = :level AND er.isActive = true")
    List<EmployabilityRecord> findByStudentAndLevel(@Param("studentId") String studentId, @Param("level") String level);

    @Query("SELECT er FROM EmployabilityRecord er WHERE er.validUntil < CURRENT_TIMESTAMP AND er.employabilityStatus = 'ACHIEVED' AND er.isActive = true")
    List<EmployabilityRecord> findExpiredEmployabilityRecords();
}
