package com.edubridge.academic.repositories;

import com.edubridge.academic.entities.AcademicValidation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcademicValidationRepository extends JpaRepository<AcademicValidation, String> {

    List<AcademicValidation> findByUniversityId(String universityId);

    List<AcademicValidation> findByProgramId(String programId);

    List<AcademicValidation> findByValidationType(String validationType);

    List<AcademicValidation> findByUniversityIdAndProgramId(String universityId, String programId);

    List<AcademicValidation> findByIsMandatoryTrue();

    List<AcademicValidation> findByIsActiveTrue();

    @Query("SELECT av FROM AcademicValidation av WHERE av.isActive = true AND av.universityId = :universityId")
    List<AcademicValidation> findActiveByUniversity(@Param("universityId") String universityId);

    @Query("SELECT av FROM AcademicValidation av WHERE av.isActive = true AND av.programId = :programId")
    List<AcademicValidation> findActiveByProgram(@Param("programId") String programId);

    @Query("SELECT av FROM AcademicValidation av WHERE av.isActive = true AND av.validationType = :type")
    List<AcademicValidation> findActiveByType(@Param("type") String type);
}
