package com.edubridge.learningservices.repositories;

import com.edubridge.learningservices.entities.ResourceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRecordRepository extends JpaRepository<ResourceRecord, String> {

    List<ResourceRecord> findByCourseProfileIdAndIsActiveTrue(String courseProfileId);

    List<ResourceRecord> findByStudentIdAndIsActiveTrue(String studentId);

    List<ResourceRecord> findByResourceTypeAndIsActiveTrue(String resourceType);

    List<ResourceRecord> findByResourceStatusAndIsActiveTrue(String resourceStatus);

    @Query("SELECT rr FROM ResourceRecord rr WHERE rr.studentId = :studentId AND rr.resourceStatus = 'ACCESSIBLE' AND rr.isActive = true")
    List<ResourceRecord> findAccessibleByStudent(@Param("studentId") String studentId);

    @Query("SELECT rr FROM ResourceRecord rr WHERE rr.studentId = :studentId AND rr.resourceStatus = 'COMPLETED' AND rr.isActive = true")
    List<ResourceRecord> findCompletedByStudent(@Param("studentId") String studentId);
}
