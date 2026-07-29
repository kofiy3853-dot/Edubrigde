package com.edubridge.academic.repositories;

import com.edubridge.academic.entities.EligibilityRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EligibilityRuleRepository extends JpaRepository<EligibilityRule, String> {

    List<EligibilityRule> findByUniversityId(String universityId);

    List<EligibilityRule> findByProgramId(String programId);

    List<EligibilityRule> findByRuleType(String ruleType);

    List<EligibilityRule> findByEffect(String effect);

    List<EligibilityRule> findByUniversityIdAndProgramId(String universityId, String programId);

    List<EligibilityRule> findByIsMandatoryTrue();

    List<EligibilityRule> findByIsActiveTrue();

    @Query("SELECT er FROM EligibilityRule er WHERE er.isActive = true AND er.universityId = :universityId")
    List<EligibilityRule> findActiveByUniversity(@Param("universityId") String universityId);

    @Query("SELECT er FROM EligibilityRule er WHERE er.isActive = true AND er.programId = :programId")
    List<EligibilityRule> findActiveByProgram(@Param("programId") String programId);

    @Query("SELECT er FROM EligibilityRule er WHERE er.isActive = true AND er.ruleType = :type")
    List<EligibilityRule> findActiveByType(@Param("type") String type);
}
