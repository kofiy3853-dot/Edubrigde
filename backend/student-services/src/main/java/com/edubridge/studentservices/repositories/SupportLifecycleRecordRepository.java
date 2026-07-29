package com.edubridge.studentservices.repositories;

import com.edubridge.studentservices.entities.SupportLifecycleRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupportLifecycleRecordRepository extends JpaRepository<SupportLifecycleRecord, String> {

    List<SupportLifecycleRecord> findBySupportProfileIdAndIsActiveTrue(String supportProfileId);

    List<SupportLifecycleRecord> findByStudentIdAndIsActiveTrue(String studentId);

    @Query("SELECT slr FROM SupportLifecycleRecord slr WHERE slr.supportProfileId = :supportProfileId ORDER BY slr.changedAt DESC")
    List<SupportLifecycleRecord> findBySupportProfileIdOrderedByDate(@Param("supportProfileId") String supportProfileId);
}
