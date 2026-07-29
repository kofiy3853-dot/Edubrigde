package com.edubridge.financial.repositories;

import com.edubridge.financial.entities.CostItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CostItemRepository extends JpaRepository<CostItem, String> {

    List<CostItem> findByUniversityIdAndIsActiveTrue(String universityId);

    List<CostItem> findByProgramIdAndIsActiveTrue(String programId);

    List<CostItem> findByCategoryCodeAndIsActiveTrue(String categoryCode);

    List<CostItem> findByUniversityIdAndCategoryCodeAndIsActiveTrue(String universityId, String categoryCode);

    List<CostItem> findByUniversityIdAndAcademicYearAndIsActiveTrue(String universityId, Integer academicYear);

    @Query("SELECT ci FROM CostItem ci WHERE ci.universityId = :universityId AND ci.programId = :programId AND ci.isActive = true ORDER BY ci.categoryCode, ci.name")
    List<CostItem> findByUniversityAndProgram(@Param("universityId") String universityId, @Param("programId") String programId);

    @Query("SELECT ci FROM CostItem ci WHERE ci.universityId = :universityId AND ci.isMandatory = true AND ci.isActive = true ORDER BY ci.categoryCode")
    List<CostItem> findMandatoryByUniversity(@Param("universityId") String universityId);

    @Query("SELECT ci FROM CostItem ci WHERE ci.universityId = :universityId AND ci.isMandatory = false AND ci.isActive = true ORDER BY ci.categoryCode")
    List<CostItem> findOptionalByUniversity(@Param("universityId") String universityId);
}
