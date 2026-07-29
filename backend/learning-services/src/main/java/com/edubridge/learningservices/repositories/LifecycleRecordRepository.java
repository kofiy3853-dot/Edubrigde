package com.edubridge.learningservices.repositories;

import com.edubridge.learningservices.entities.LifecycleRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LifecycleRecordRepository extends JpaRepository<LifecycleRecord, String> {

    List<LifecycleRecord> findByCourseProfileIdAndIsActiveTrue(String courseProfileId);

    List<LifecycleRecord> findByStudentIdAndIsActiveTrue(String studentId);

    @Query("SELECT lr FROM LifecycleRecord lr WHERE lr.courseProfileId = :courseProfileId ORDER BY lr.changedAt DESC")
    List<LifecycleRecord> findByCourseProfileIdOrderedByDate(@Param("courseProfileId") String courseProfileId);
}
