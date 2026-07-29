package com.edubridge.financial.repositories;

import com.edubridge.financial.entities.CostComparison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CostComparisonRepository extends JpaRepository<CostComparison, String> {

    List<CostComparison> findByStudentIdAndIsActiveTrue(String studentId);

    List<CostComparison> findByStudentIdAndIsCompletedAndIsActiveTrue(String studentId, Boolean isCompleted);

    @Query("SELECT cc FROM CostComparison cc WHERE cc.studentId = :studentId AND cc.isActive = true ORDER BY cc.createdAt DESC")
    List<CostComparison> findByStudentOrdered(@Param("studentId") String studentId);

    @Query("SELECT cc FROM CostComparison cc WHERE cc.studentId = :studentId AND cc.isCompleted = false AND cc.isActive = true")
    List<CostComparison> findIncompleteByStudent(@Param("studentId") String studentId);
}
