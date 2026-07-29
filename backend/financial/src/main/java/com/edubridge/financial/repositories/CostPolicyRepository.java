package com.edubridge.financial.repositories;

import com.edubridge.financial.entities.CostPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CostPolicyRepository extends JpaRepository<CostPolicy, String> {

    List<CostPolicy> findByUniversityIdAndIsActiveTrue(String universityId);

    List<CostPolicy> findByCountryCodeAndIsActiveTrue(String countryCode);

    List<CostPolicy> findByPolicyTypeAndIsActiveTrue(String policyType);

    List<CostPolicy> findByCategoryCodeAndIsActiveTrue(String categoryCode);

    @Query("SELECT cp FROM CostPolicy cp WHERE cp.universityId = :universityId AND cp.policyType = :policyType AND cp.isActive = true")
    List<CostPolicy> findByUniversityAndType(@Param("universityId") String universityId, @Param("policyType") String policyType);

    @Query("SELECT cp FROM CostPolicy cp WHERE cp.countryCode = :countryCode AND cp.categoryCode = :categoryCode AND cp.isActive = true")
    List<CostPolicy> findByCountryAndCategory(@Param("countryCode") String countryCode, @Param("categoryCode") String categoryCode);

    @Query("SELECT cp FROM CostPolicy cp WHERE cp.requiresApproval = true AND cp.isActive = true")
    List<CostPolicy> findRequiringApproval();
}
