package com.edubridge.financial.repositories;

import com.edubridge.financial.entities.BudgetPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetPolicyRepository extends JpaRepository<BudgetPolicy, String> {

    List<BudgetPolicy> findByUniversityIdAndIsActiveTrue(String universityId);

    List<BudgetPolicy> findByCountryCodeAndIsActiveTrue(String countryCode);

    List<BudgetPolicy> findByPolicyTypeAndIsActiveTrue(String policyType);

    List<BudgetPolicy> findByEffectAndIsActiveTrue(String effect);

    @Query("SELECT bp FROM BudgetPolicy bp WHERE bp.universityId = :universityId AND bp.policyType = :policyType AND bp.isActive = true")
    List<BudgetPolicy> findByUniversityAndType(@Param("universityId") String universityId, @Param("policyType") String policyType);

    @Query("SELECT bp FROM BudgetPolicy bp WHERE bp.countryCode = :countryCode AND bp.policyType = :policyType AND bp.isActive = true")
    List<BudgetPolicy> findByCountryAndType(@Param("countryCode") String countryCode, @Param("policyType") String policyType);

    @Query("SELECT bp FROM BudgetPolicy bp WHERE bp.effect = 'DENY' AND bp.isActive = true")
    List<BudgetPolicy> findDenyPolicies();
}
