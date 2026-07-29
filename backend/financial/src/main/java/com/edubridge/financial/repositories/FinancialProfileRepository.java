package com.edubridge.financial.repositories;

import com.edubridge.financial.entities.FinancialProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FinancialProfileRepository extends JpaRepository<FinancialProfile, String> {

    List<FinancialProfile> findByStudentId(String studentId);

    List<FinancialProfile> findByUniversityId(String universityId);

    List<FinancialProfile> findByProgramId(String programId);

    List<FinancialProfile> findByCountryCode(String countryCode);

    List<FinancialProfile> findByStudentIdAndIsActiveTrue(String studentId);

    Optional<FinancialProfile> findByStudentIdAndUniversityIdAndIsActiveTrue(String studentId, String universityId);

    List<FinancialProfile> findByIsActiveTrue();

    @Query("SELECT fp FROM FinancialProfile fp WHERE fp.isActive = true AND fp.studentId = :studentId")
    List<FinancialProfile> findActiveByStudent(@Param("studentId") String studentId);

    @Query("SELECT fp FROM FinancialProfile fp WHERE fp.isActive = true AND fp.countryCode = :countryCode")
    List<FinancialProfile> findActiveByCountry(@Param("countryCode") String countryCode);

    @Query("SELECT fp FROM FinancialProfile fp WHERE fp.isActive = true AND fp.riskLevel = :riskLevel")
    List<FinancialProfile> findActiveByRiskLevel(@Param("riskLevel") String riskLevel);
}
