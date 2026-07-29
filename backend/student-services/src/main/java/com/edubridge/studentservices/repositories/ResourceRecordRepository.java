package com.edubridge.studentservices.repositories;

import com.edubridge.studentservices.entities.ResourceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRecordRepository extends JpaRepository<ResourceRecord, String> {

    List<ResourceRecord> findBySupportProfileIdAndIsActiveTrue(String supportProfileId);

    List<ResourceRecord> findByStudentIdAndIsActiveTrue(String studentId);

    List<ResourceRecord> findByResourceTypeAndIsActiveTrue(String resourceType);

    List<ResourceRecord> findByResourceStatusAndIsActiveTrue(String resourceStatus);

    @Query("SELECT rr FROM ResourceRecord rr WHERE rr.studentId = :studentId AND rr.resourceStatus = 'ACTIVE' AND rr.isActive = true")
    List<ResourceRecord> findActiveByStudent(@Param("studentId") String studentId);

    @Query("SELECT rr FROM ResourceRecord rr WHERE rr.studentId = :studentId AND rr.resourceStatus = 'REQUESTED' AND rr.isActive = true")
    List<ResourceRecord> findRequestedByStudent(@Param("studentId") String studentId);
}
