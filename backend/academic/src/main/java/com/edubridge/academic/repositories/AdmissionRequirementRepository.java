package com.edubridge.academic.repositories;

import com.edubridge.academic.entities.AdmissionRequirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdmissionRequirementRepository extends JpaRepository<AdmissionRequirement, String> {

    List<AdmissionRequirement> findByUniversityId(String universityId);

    List<AdmissionRequirement> findByProgramId(String programId);

    List<AdmissionRequirement> findByRequirementType(String requirementType);

    List<AdmissionRequirement> findByUniversityIdAndProgramId(String universityId, String programId);

    List<AdmissionRequirement> findByIsMandatoryTrue();

    List<AdmissionRequirement> findByIsActiveTrue();

    @Query("SELECT ar FROM AdmissionRequirement ar WHERE ar.isActive = true AND ar.universityId = :universityId")
    List<AdmissionRequirement> findActiveByUniversity(@Param("universityId") String universityId);

    @Query("SELECT ar FROM AdmissionRequirement ar WHERE ar.isActive = true AND ar.programId = :programId")
    List<AdmissionRequirement> findActiveByProgram(@Param("programId") String programId);

    @Query("SELECT ar FROM AdmissionRequirement ar WHERE ar.isActive = true AND ar.universityId = :universityId AND ar.programId IS NULL")
    List<AdmissionRequirement> findUniversityWideRequirements(@Param("universityId") String universityId);
}
