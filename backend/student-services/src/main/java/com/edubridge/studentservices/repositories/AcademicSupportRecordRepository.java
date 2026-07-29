package com.edubridge.studentservices.repositories;

import com.edubridge.studentservices.entities.AcademicSupportRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcademicSupportRecordRepository extends JpaRepository<AcademicSupportRecord, String> {

    List<AcademicSupportRecord> findBySupportProfileIdAndIsActiveTrue(String supportProfileId);

    List<AcademicSupportRecord> findByStudentIdAndIsActiveTrue(String studentId);

    List<AcademicSupportRecord> findBySupportTypeAndIsActiveTrue(String supportType);

    List<AcademicSupportRecord> findBySupportStatusAndIsActiveTrue(String supportStatus);

    @Query("SELECT asr FROM AcademicSupportRecord asr WHERE asr.studentId = :studentId AND asr.supportStatus = 'IN_PROGRESS' AND asr.isActive = true")
    List<AcademicSupportRecord> findInProgressByStudent(@Param("studentId") String studentId);

    @Query("SELECT asr FROM AcademicSupportRecord asr WHERE asr.studentId = :studentId AND asr.supportStatus = 'REQUESTED' AND asr.isActive = true")
    List<AcademicSupportRecord> findRequestedByStudent(@Param("studentId") String studentId);
}
