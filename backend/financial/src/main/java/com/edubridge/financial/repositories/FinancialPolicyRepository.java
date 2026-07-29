package com.edubridge.financial.repositories;

import com.edubridge.financial.entities.FinancialPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinancialPolicyRepository extends JpaRepository<FinancialPolicy, String> {

    List<FinancialPolicy> findByUniversityId(String universityId);

    List<FinancialPolicy> findByCountryCode(String countryCode);

    List<FinancialPolicy> findByPolicyType(String policyType);

    List<FinancialPolicy> findByEffect(String effect);

    List<FinancialPolicy> findByUniversityIdAndPolicyType(String universityId, String policyType);

    List<FinancialPolicy> findByIsMandatoryTrue();

    List<FinancialPolicy> findByIsActiveTrue();

    @Query("SELECT fp FROM FinancialPolicy fp WHERE fp.isActive = true AND fp.universityId = :universityId")
    List<FinancialPolicy> findActiveByUniversity(@Param("universityId") String universityId);

    @Query("SELECT fp FROM FinancialPolicy fp WHERE fp.isActive = true AND fp.countryCode = :countryCode")
    List<FinancialPolicy> findActiveByCountry(@Param("countryCode") String countryCode);

    @Query("SELECT fp FROM FinancialPolicy fp WHERE fp.isActive = true AND fp.policyType = :type")
    List<FinancialPolicy> findActiveByType(@Param("type") String type);
}
