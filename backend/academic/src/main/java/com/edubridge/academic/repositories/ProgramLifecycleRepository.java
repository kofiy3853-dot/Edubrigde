package com.edubridge.academic.repositories;

import com.edubridge.academic.entities.ProgramLifecycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProgramLifecycleRepository extends JpaRepository<ProgramLifecycle, String> {

    List<ProgramLifecycle> findByProgramId(String programId);

    List<ProgramLifecycle> findByStatus(String status);

    List<ProgramLifecycle> findByProgramIdAndStatus(String programId, String status);

    List<ProgramLifecycle> findByIsCurrentTrue();

    Optional<ProgramLifecycle> findByProgramIdAndIsCurrentTrue(String programId);

    List<ProgramLifecycle> findByIsActiveTrue();

    @Query("SELECT pl FROM ProgramLifecycle pl WHERE pl.isActive = true AND pl.programId = :programId")
    List<ProgramLifecycle> findActiveByProgram(@Param("programId") String programId);

    @Query("SELECT pl FROM ProgramLifecycle pl WHERE pl.isActive = true AND pl.status = :status")
    List<ProgramLifecycle> findActiveByStatus(@Param("status") String status);

    @Query("SELECT pl FROM ProgramLifecycle pl WHERE pl.isCurrent = true AND pl.programId = :programId")
    Optional<ProgramLifecycle> findCurrentByProgram(@Param("programId") String programId);
}
