package com.edubridge.learningservices.repositories;

import com.edubridge.learningservices.entities.CareerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CareerProfileRepository extends JpaRepository<CareerProfile, String> {

    List<CareerProfile> findByStudentIdAndIsActiveTrue(String studentId);

    List<CareerProfile> findByCareerStatusAndIsActiveTrue(String careerStatus);

    List<CareerProfile> findByCareerFieldAndIsActiveTrue(String careerField);

    List<CareerProfile> findByIndustryPreferenceAndIsActiveTrue(String industryPreference);

    @Query("SELECT cp FROM CareerProfile cp WHERE cp.studentId = :studentId AND cp.careerStatus = 'EXPLORING' AND cp.isActive = true")
    List<CareerProfile> findExploringStudents(@Param("studentId") String studentId);

    @Query("SELECT cp FROM CareerProfile cp WHERE cp.careerReadinessScore >= :minScore AND cp.isActive = true")
    List<CareerProfile> findHighReadinessStudents(@Param("minScore") Double minScore);

    @Query("SELECT cp FROM CareerProfile cp WHERE cp.careerReadinessScore < :threshold AND cp.isActive = true")
    List<CareerProfile> findStudentsBelowReadinessThreshold(@Param("threshold") Double threshold);
}
