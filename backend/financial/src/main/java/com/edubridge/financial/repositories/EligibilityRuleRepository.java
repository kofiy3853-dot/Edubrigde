package com.edubridge.financial.repositories;

import com.edubridge.financial.entities.EligibilityRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EligibilityRuleRepository extends JpaRepository<EligibilityRule, String> {

    List<EligibilityRule> findByScholarshipIdAndIsActiveTrue(String scholarshipId);

    List<EligibilityRule> findByRuleTypeAndIsActiveTrue(String ruleType);

    List<EligibilityRule> findByRuleCategoryAndIsActiveTrue(String ruleCategory);

    List<EligibilityRule> findByIsMandatoryAndIsActiveTrue(Boolean isMandatory);

    @Query("SELECT er FROM EligibilityRule er WHERE er.scholarshipId = :scholarshipId AND er.isActive = true ORDER BY er.priority ASC")
    List<EligibilityRule> findByScholarshipOrdered(@Param("scholarshipId") String scholarshipId);

    @Query("SELECT er FROM EligibilityRule er WHERE er.scholarshipId = :scholarshipId AND er.isMandatory = true AND er.isActive = true")
    List<EligibilityRule> findMandatoryByScholarship(@Param("scholarshipId") String scholarshipId);

    @Query("SELECT er FROM EligibilityRule er WHERE er.scholarshipId = :scholarshipId AND er.ruleType = :ruleType AND er.isActive = true")
    List<EligibilityRule> findByScholarshipAndType(@Param("scholarshipId") String scholarshipId, @Param("ruleType") String ruleType);
}
