package com.edubridge.learningservices.repositories;

import com.edubridge.learningservices.entities.LeadershipRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeadershipRecordRepository extends JpaRepository<LeadershipRecord, String> {

    List<LeadershipRecord> findByStudentIdAndIsActiveTrue(String studentId);

    List<LeadershipRecord> findByCareerProfileIdAndIsActiveTrue(String careerProfileId);

    List<LeadershipRecord> findByLeadershipTypeAndIsActiveTrue(String leadershipType);

    List<LeadershipRecord> findByLeadershipStatusAndIsActiveTrue(String leadershipStatus);

    List<LeadershipRecord> findByLeadershipLevelAndIsActiveTrue(String leadershipLevel);

    @Query("SELECT lr FROM LeadershipRecord lr WHERE lr.studentId = :studentId AND lr.leadershipStatus = 'ACHIEVED' AND lr.isActive = true")
    List<LeadershipRecord> findAchievedByStudent(@Param("studentId") String studentId);

    @Query("SELECT lr FROM LeadershipRecord lr WHERE lr.studentId = :studentId AND lr.leadershipType = :type AND lr.isActive = true")
    List<LeadershipRecord> findByStudentAndType(@Param("studentId") String studentId, @Param("type") String type);

    @Query("SELECT SUM(lr.projectCount) FROM LeadershipRecord lr WHERE lr.studentId = :studentId AND lr.isActive = true")
    Integer countTotalProjectsByStudent(@Param("studentId") String studentId);
}
