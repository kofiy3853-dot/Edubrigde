package com.edubridge.academic.repositories;

import com.edubridge.academic.entities.AcademicLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AcademicLevelRepository extends JpaRepository<AcademicLevel, String> {

    Optional<AcademicLevel> findByCode(String code);

    Optional<AcademicLevel> findByName(String name);

    List<AcademicLevel> findByIsActiveTrue();

    List<AcademicLevel> findBySortOrder();

    @Query("SELECT al FROM AcademicLevel al WHERE al.isActive = true ORDER BY al.sortOrder")
    List<AcademicLevel> findActiveOrderedBySortOrder();

    boolean existsByCode(String code);

    boolean existsByName(String name);
}
