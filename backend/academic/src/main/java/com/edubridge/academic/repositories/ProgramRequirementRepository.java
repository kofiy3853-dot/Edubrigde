package com.edubridge.academic.repositories;

import com.edubridge.academic.entities.ProgramRequirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramRequirementRepository extends JpaRepository<ProgramRequirement, String> {

    List<ProgramRequirement> findByProgramId(String programId);

    List<ProgramRequirement> findByRequirementType(String requirementType);

    List<ProgramRequirement> findByProgramIdAndRequirementType(String programId, String requirementType);

    List<ProgramRequirement> findByIsMandatoryTrue();

    List<ProgramRequirement> findByIsActiveTrue();

    @Query("SELECT pr FROM ProgramRequirement pr WHERE pr.isActive = true AND pr.programId = :programId")
    List<ProgramRequirement> findActiveByProgram(@Param("programId") String programId);

    @Query("SELECT pr FROM ProgramRequirement pr WHERE pr.isActive = true AND pr.requirementType = :type")
    List<ProgramRequirement> findActiveByType(@Param("type") String type);

    @Query("SELECT pr FROM ProgramRequirement pr WHERE pr.isActive = true AND pr.isMandatory = true")
    List<ProgramRequirement> findActiveMandatory();
}
