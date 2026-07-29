package com.edubridge.financial.repositories;

import com.edubridge.financial.entities.CostBreakdown;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CostBreakdownRepository extends JpaRepository<CostBreakdown, String> {

    List<CostBreakdown> findByUniversityIdAndIsActiveTrue(String universityId);

    List<CostBreakdown> findByProgramIdAndIsActiveTrue(String programId);

    List<CostBreakdown> findByCountryCodeAndIsActiveTrue(String countryCode);

    Optional<CostBreakdown> findByUniversityIdAndProgramIdAndAcademicYearAndIsActiveTrue(String universityId, String programId, Integer academicYear);

    @Query("SELECT cb FROM CostBreakdown cb WHERE cb.universityId = :universityId AND cb.programId = :programId AND cb.isActive = true ORDER BY cb.academicYear DESC")
    List<CostBreakdown> findByUniversityAndProgramOrdered(@Param("universityId") String universityId, @Param("programId") String programId);

    @Query("SELECT cb FROM CostBreakdown cb WHERE cb.countryCode = :countryCode AND cb.academicYear = :academicYear AND cb.isActive = true ORDER BY cb.grandTotal ASC")
    List<CostBreakdown> findByCountryAndYearOrdered(@Param("countryCode") String countryCode, @Param("academicYear") Integer academicYear);
}
