package com.edubridge.financial.repositories;

import com.edubridge.financial.entities.BudgetLifecycleRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetLifecycleRecordRepository extends JpaRepository<BudgetLifecycleRecord, String> {

    List<BudgetLifecycleRecord> findByBudgetProfileIdAndIsActiveTrue(String budgetProfileId);

    List<BudgetLifecycleRecord> findByStudentIdAndIsActiveTrue(String studentId);

    List<BudgetLifecycleRecord> findByNewStatusAndIsActiveTrue(String newStatus);

    @Query("SELECT blr FROM BudgetLifecycleRecord blr WHERE blr.budgetProfileId = :budgetProfileId AND blr.studentId = :studentId AND blr.isActive = true ORDER BY blr.changedAt DESC")
    List<BudgetLifecycleRecord> findByBudgetAndStudent(@Param("budgetProfileId") String budgetProfileId, @Param("studentId") String studentId);

    @Query("SELECT blr FROM BudgetLifecycleRecord blr WHERE blr.budgetProfileId = :budgetProfileId ORDER BY blr.changedAt DESC LIMIT 1")
    BudgetLifecycleRecord findLatestByBudget(@Param("budgetProfileId") String budgetProfileId);

    @Query("SELECT blr FROM BudgetLifecycleRecord blr WHERE blr.studentId = :studentId ORDER BY blr.changedAt DESC")
    List<BudgetLifecycleRecord> findLatestByStudent(@Param("studentId") String studentId);
}
