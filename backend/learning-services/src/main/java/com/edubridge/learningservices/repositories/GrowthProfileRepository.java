package com.edubridge.learningservices.repositories;

import com.edubridge.learningservices.entities.GrowthProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrowthProfileRepository extends JpaRepository<GrowthProfile, String> {

    List<GrowthProfile> findByStudentIdAndIsActiveTrue(String studentId);

    List<GrowthProfile> findByGrowthStatusAndIsActiveTrue(String growthStatus);

    List<GrowthProfile> findByAcademicLevelAndIsActiveTrue(String academicLevel);

    List<GrowthProfile> findByAcademicStandingAndIsActiveTrue(String academicStanding);

    @Query("SELECT gp FROM GrowthProfile gp WHERE gp.studentId = :studentId AND gp.growthStatus = 'EXCELLING' AND gp.isActive = true")
    List<GrowthProfile> findExcellingStudents(@Param("studentId") String studentId);

    @Query("SELECT gp FROM GrowthProfile gp WHERE gp.readinessScore >= :minScore AND gp.isActive = true")
    List<GrowthProfile> findHighReadinessStudents(@Param("minScore") Double minScore);

    @Query("SELECT gp FROM GrowthProfile gp WHERE gp.currentGpa < :threshold AND gp.isActive = true")
    List<GrowthProfile> findStudentsBelowGpaThreshold(@Param("threshold") Double threshold);
}
