package com.edubridge.studentservices.repositories;

import com.edubridge.studentservices.entities.GrowthRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrowthRecordRepository extends JpaRepository<GrowthRecord, String> {

    List<GrowthRecord> findByMentorshipIdAndIsActiveTrue(String mentorshipId);

    List<GrowthRecord> findByStudentIdAndIsActiveTrue(String studentId);

    List<GrowthRecord> findByGrowthTypeAndIsActiveTrue(String growthType);

    List<GrowthRecord> findByGrowthStatusAndIsActiveTrue(String growthStatus);

    @Query("SELECT gr FROM GrowthRecord gr WHERE gr.studentId = :studentId AND gr.growthStatus = 'TRACKING' AND gr.isActive = true")
    List<GrowthRecord> findTrackingByStudent(@Param("studentId") String studentId);

    @Query("SELECT gr FROM GrowthRecord gr WHERE gr.studentId = :studentId AND gr.growthStatus = 'ACHIEVED' AND gr.isActive = true")
    List<GrowthRecord> findAchievedByStudent(@Param("studentId") String studentId);
}
