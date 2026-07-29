package com.edubridge.academic.repositories;

import com.edubridge.academic.entities.UniversityPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UniversityPolicyRepository extends JpaRepository<UniversityPolicy, String> {

    List<UniversityPolicy> findByUniversityId(String universityId);

    List<UniversityPolicy> findByPolicyType(String policyType);

    List<UniversityPolicy> findByEffect(String effect);

    List<UniversityPolicy> findByUniversityIdAndPolicyType(String universityId, String policyType);

    List<UniversityPolicy> findByIsMandatoryTrue();

    List<UniversityPolicy> findByIsActiveTrue();

    @Query("SELECT up FROM UniversityPolicy up WHERE up.isActive = true AND up.universityId = :universityId")
    List<UniversityPolicy> findActiveByUniversity(@Param("universityId") String universityId);

    @Query("SELECT up FROM UniversityPolicy up WHERE up.isActive = true AND up.policyType = :type")
    List<UniversityPolicy> findActiveByType(@Param("type") String type);

    @Query("SELECT up FROM UniversityPolicy up WHERE up.isActive = true AND up.effect = :effect")
    List<UniversityPolicy> findActiveByEffect(@Param("effect") String effect);
}
