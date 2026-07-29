package com.edubridge.financial.repositories;

import com.edubridge.financial.entities.CostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CostCategoryRepository extends JpaRepository<CostCategory, String> {

    Optional<CostCategory> findByCode(String code);

    List<CostCategory> findByParentCode(String parentCode);

    List<CostCategory> findByIsActiveTrueOrderBySortOrder();

    @Query("SELECT c FROM CostCategory c WHERE c.isActive = true AND c.parentCode IS NULL ORDER BY c.sortOrder")
    List<CostCategory> findRootCategories();

    @Query("SELECT c FROM CostCategory c WHERE c.code = :code AND c.isActive = true")
    Optional<CostCategory> findActiveByCode(@Param("code") String code);

    boolean existsByCode(String code);
}
