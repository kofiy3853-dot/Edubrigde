package com.edubridge.studentservices.repositories;

import com.edubridge.studentservices.entities.WellbeingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WellbeingRecordRepository extends JpaRepository<WellbeingRecord, String> {

    List<WellbeingRecord> findBySupportProfileIdAndIsActiveTrue(String supportProfileId);

    List<WellbeingRecord> findByStudentIdAndIsActiveTrue(String studentId);

    List<WellbeingRecord> findByWellbeingTypeAndIsActiveTrue(String wellbeingType);

    List<WellbeingRecord> findByWellbeingStatusAndIsActiveTrue(String wellbeingStatus);

    List<WellbeingRecord> findBySeverityLevelAndIsActiveTrue(String severityLevel);

    @Query("SELECT wr FROM WellbeingRecord wr WHERE wr.studentId = :studentId AND wr.wellbeingStatus = 'IN_TREATMENT' AND wr.isActive = true")
    List<WellbeingRecord> findInTreatmentByStudent(@Param("studentId") String studentId);

    @Query("SELECT wr FROM WellbeingRecord wr WHERE wr.severityLevel = 'CRITICAL' AND wr.wellbeingStatus != 'RESOLVED' AND wr.isActive = true")
    List<WellbeingRecord> findCriticalCases();
}
