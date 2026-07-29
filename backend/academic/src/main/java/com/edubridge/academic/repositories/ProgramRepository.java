package com.edubridge.academic.repositories;

import com.edubridge.academic.entities.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgramRepository extends JpaRepository<Program, String> {

    Optional<Program> findByCode(String code);

    List<Program> findByUniversityId(String universityId);

    List<Program> findByLevel(String level);

    List<Program> findByFieldOfStudy(String fieldOfStudy);

    List<Program> findByUniversityIdAndLevel(String universityId, String level);

    List<Program> findByIsActiveTrue();

    List<Program> findByIsOnlineTrue();

    @Query("SELECT p FROM Program p WHERE p.isActive = true AND p.universityId = :universityId")
    List<Program> findActiveByUniversity(@Param("universityId") String universityId);

    @Query("SELECT p FROM Program p WHERE p.isActive = true AND p.level = :level")
    List<Program> findActiveByLevel(@Param("level") String level);

    @Query("SELECT p FROM Program p WHERE p.isActive = true AND p.languageOfInstruction = :language")
    List<Program> findActiveByLanguage(@Param("language") String language);

    boolean existsByCode(String code);

    boolean existsByUniversityIdAndName(String universityId, String name);
}
