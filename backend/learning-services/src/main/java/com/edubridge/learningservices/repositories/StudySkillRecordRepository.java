package com.edubridge.learningservices.repositories;

import com.edubridge.learningservices.entities.StudySkillRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudySkillRecordRepository extends JpaRepository<StudySkillRecord, String> {

    List<StudySkillRecord> findByCourseProfileIdAndIsActiveTrue(String courseProfileId);

    List<StudySkillRecord> findByStudentIdAndIsActiveTrue(String studentId);

    List<StudySkillRecord> findBySkillCategoryAndIsActiveTrue(String skillCategory);

    List<StudySkillRecord> findBySkillStatusAndIsActiveTrue(String skillStatus);

    @Query("SELECT ssr FROM StudySkillRecord ssr WHERE ssr.studentId = :studentId AND ssr.skillStatus = 'MASTERED' AND ssr.isActive = true")
    List<StudySkillRecord> findMasteredByStudent(@Param("studentId") String studentId);

    @Query("SELECT ssr FROM StudySkillRecord ssr WHERE ssr.studentId = :studentId AND ssr.skillStatus = 'NEEDS_REVIEW' AND ssr.isActive = true")
    List<StudySkillRecord> findNeedsReviewByStudent(@Param("studentId") String studentId);
}
