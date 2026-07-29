package com.edubridge.academic.repositories;

import com.edubridge.academic.entities.AdmissionPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdmissionPolicyRepository extends JpaRepository<AdmissionPolicy, String> {

    List<AdmissionPolicy> findByUniversityId(String universityId);

    List<AdmissionPolicy> findByProgramId(String programId);

    List<AdmissionPolicy> findByPolicyType(String policyType);

    List<AdmissionPolicy> findByEffect(String effect);

    List<AdmissionPolicy> findByUniversityIdAndProgramId(String universityId, String programId);

    List<AdmissionPolicy> findByIsMandatoryTrue();

    List<AdmissionPolicy> findByIsActiveTrue();

    @Query("SELECT ap FROM AdmissionPolicy ap WHERE ap.isActive = true AND ap.universityId = :universityId")
    List<AdmissionPolicy> findActiveByUniversity(@Param("universityId") String universityId);

    @Query("SELECT ap FROM AdmissionPolicy ap WHERE ap.isActive = true AND ap.programId = :programId")
    List<AdmissionPolicy> findActiveByProgram(@Param("programId") String programId);

    @Query("SELECT ap FROM AdmissionPolicy ap WHERE ap.isActive = true AND ap.policyType = :type")
    List<AdmissionPolicy> findActiveByType(@Param("type") String type);
}
