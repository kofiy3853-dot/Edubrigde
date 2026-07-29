package com.edubridge.academic.repositories;

import com.edubridge.academic.entities.AccreditationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccreditationRecordRepository extends JpaRepository<AccreditationRecord, String> {

    List<AccreditationRecord> findByUniversityId(String universityId);

    List<AccreditationRecord> findByAccreditationType(String accreditationType);

    List<AccreditationRecord> findByStatus(String status);

    List<AccreditationRecord> findByAccreditationBody(String accreditationBody);

    List<AccreditationRecord> findByUniversityIdAndStatus(String universityId, String status);

    List<AccreditationRecord> findByIsActiveTrue();

    @Query("SELECT ar FROM AccreditationRecord ar WHERE ar.isActive = true AND ar.universityId = :universityId")
    List<AccreditationRecord> findActiveByUniversity(@Param("universityId") String universityId);

    @Query("SELECT ar FROM AccreditationRecord ar WHERE ar.isActive = true AND ar.status = :status")
    List<AccreditationRecord> findActiveByStatus(@Param("status") String status);

    @Query("SELECT ar FROM AccreditationRecord ar WHERE ar.isActive = true AND ar.accreditationType = :type")
    List<AccreditationRecord> findActiveByType(@Param("type") String type);
}
