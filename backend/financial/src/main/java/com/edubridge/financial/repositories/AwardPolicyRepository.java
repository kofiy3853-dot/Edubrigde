package com.edubridge.financial.repositories;

import com.edubridge.financial.entities.AwardPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AwardPolicyRepository extends JpaRepository<AwardPolicy, String> {

    List<AwardPolicy> findByScholarshipIdAndIsActiveTrue(String scholarshipId);

    List<AwardPolicy> findByPolicyTypeAndIsActiveTrue(String policyType);

    List<AwardPolicy> findByCoverageTypeAndIsActiveTrue(String coverageType);

    List<AwardPolicy> findByRenewalRequiredAndIsActiveTrue(Boolean renewalRequired);

    @Query("SELECT ap FROM AwardPolicy ap WHERE ap.scholarshipId = :scholarshipId AND ap.policyType = :policyType AND ap.isActive = true")
    List<AwardPolicy> findByScholarshipAndType(@Param("scholarshipId") String scholarshipId, @Param("policyType") String policyType);

    @Query("SELECT ap FROM AwardPolicy ap WHERE ap.scholarshipId = :scholarshipId AND ap.coverageType = :coverageType AND ap.isActive = true")
    List<AwardPolicy> findByScholarshipAndCoverage(@Param("scholarshipId") String scholarshipId, @Param("coverageType") String coverageType);

    @Query("SELECT ap FROM AwardPolicy ap WHERE ap.renewalRequired = true AND ap.isActive = true")
    List<AwardPolicy> findRenewalRequired();
}
