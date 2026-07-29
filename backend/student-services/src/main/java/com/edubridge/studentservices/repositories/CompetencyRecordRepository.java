package com.edubridge.studentservices.repositories;

import com.edubridge.studentservices.entities.CompetencyRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetencyRecordRepository extends JpaRepository<CompetencyRecord, String> {

    List<CompetencyRecord> findByAssessmentProfileIdAndIsActiveTrue(String assessmentProfileId);

    List<CompetencyRecord> findByStudentIdAndIsActiveTrue(String studentId);

    List<CompetencyRecord> findByCompetencyCategoryAndIsActiveTrue(String competencyCategory);

    List<CompetencyRecord> findByProficiencyLevelAndIsActiveTrue(String proficiencyLevel);

    @Query("SELECT cr FROM CompetencyRecord cr WHERE cr.studentId = :studentId AND cr.proficiencyLevel = 'EXPERT' AND cr.isActive = true")
    List<CompetencyRecord> findExpertByStudent(@Param("studentId") String studentId);

    @Query("SELECT cr FROM CompetencyRecord cr WHERE cr.studentId = :studentId AND cr.proficiencyLevel = 'BEGINNER' AND cr.isActive = true")
    List<CompetencyRecord> findBeginnerByStudent(@Param("studentId") String studentId);
}
