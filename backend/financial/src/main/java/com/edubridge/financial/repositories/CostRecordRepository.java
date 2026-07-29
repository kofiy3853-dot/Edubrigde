package com.edubridge.financial.repositories;

import com.edubridge.financial.entities.CostRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CostRecordRepository extends JpaRepository<CostRecord, String> {

    List<CostRecord> findByUniversityId(String universityId);

    List<CostRecord> findByProgramId(String programId);

    List<CostRecord> findByCountryCode(String countryCode);

    List<CostRecord> findByCostType(String costType);

    List<CostRecord> findByFrequency(String frequency);

    List<CostRecord> findByUniversityIdAndCostType(String universityId, String costType);

    List<CostRecord> findByIsActiveTrue();

    @Query("SELECT cr FROM CostRecord cr WHERE cr.isActive = true AND cr.universityId = :universityId")
    List<CostRecord> findActiveByUniversity(@Param("universityId") String universityId);

    @Query("SELECT cr FROM CostRecord cr WHERE cr.isActive = true AND cr.countryCode = :countryCode")
    List<CostRecord> findActiveByCountry(@Param("countryCode") String countryCode);

    @Query("SELECT cr FROM CostRecord cr WHERE cr.isActive = true AND cr.costType = :type")
    List<CostRecord> findActiveByType(@Param("type") String type);
}
