package com.edubridge.academic.repositories;

import com.edubridge.academic.entities.DurationPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DurationPolicyRepository extends JpaRepository<DurationPolicy, String> {

    List<DurationPolicy> findByProgramId(String programId);

    List<DurationPolicy> findByDurationType(String durationType);

    List<DurationPolicy> findByProgramIdAndDurationType(String programId, String durationType);

    List<DurationPolicy> findByIsActiveTrue();

    @Query("SELECT dp FROM DurationPolicy dp WHERE dp.isActive = true AND dp.programId = :programId")
    List<DurationPolicy> findActiveByProgram(@Param("programId") String programId);

    @Query("SELECT dp FROM DurationPolicy dp WHERE dp.isActive = true AND dp.durationType = :type")
    List<DurationPolicy> findActiveByType(@Param("type") String type);
}
