package com.edubridge.learningservices.repositories;

import com.edubridge.learningservices.entities.LearningProgressRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LearningProgressRecordRepository extends JpaRepository<LearningProgressRecord, String> {

    List<LearningProgressRecord> findByStudentIdAndIsActiveTrue(String studentId);

    List<LearningProgressRecord> findByGrowthProfileIdAndIsActiveTrue(String growthProfileId);

    List<LearningProgressRecord> findBySubjectAreaAndIsActiveTrue(String subjectArea);

    List<LearningProgressRecord> findByProgressStatusAndIsActiveTrue(String progressStatus);

    List<LearningProgressRecord> findByCompetencyLevelAndIsActiveTrue(String competencyLevel);

    @Query("SELECT lpr FROM LearningProgressRecord lpr WHERE lpr.studentId = :studentId AND lpr.progressStatus = 'IN_PROGRESS' AND lpr.isActive = true")
    List<LearningProgressRecord> findInProgressByStudent(@Param("studentId") String studentId);

    @Query("SELECT lpr FROM LearningProgressRecord lpr WHERE lpr.isMasteryAchieved = true AND lpr.isActive = true")
    List<LearningProgressRecord> findMasteryAchieved();

    @Query("SELECT lpr FROM LearningProgressRecord lpr WHERE lpr.studentId = :studentId AND lpr.subjectArea = :subjectArea AND lpr.isActive = true")
    List<LearningProgressRecord> findByStudentAndSubject(@Param("studentId") String studentId, @Param("subjectArea") String subjectArea);
}
