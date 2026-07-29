package com.edubridge.learningservices.repositories;

import com.edubridge.learningservices.entities.CourseProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseProfileRepository extends JpaRepository<CourseProfile, String> {

    List<CourseProfile> findByStudentIdAndIsActiveTrue(String studentId);

    List<CourseProfile> findByCourseTypeAndIsActiveTrue(String courseType);

    List<CourseProfile> findByCourseStatusAndIsActiveTrue(String courseStatus);

    List<CourseProfile> findByDifficultyLevelAndIsActiveTrue(String difficultyLevel);

    @Query("SELECT cp FROM CourseProfile cp WHERE cp.studentId = :studentId AND cp.courseStatus = 'IN_PROGRESS' AND cp.isActive = true")
    List<CourseProfile> findInProgressByStudent(@Param("studentId") String studentId);

    @Query("SELECT cp FROM CourseProfile cp WHERE cp.courseStatus = 'ENROLLED' AND cp.isActive = true")
    List<CourseProfile> findEnrolledCourses();
}
