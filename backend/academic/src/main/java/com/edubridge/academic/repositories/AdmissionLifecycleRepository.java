package com.edubridge.academic.repositories;

import com.edubridge.academic.entities.AdmissionLifecycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdmissionLifecycleRepository extends JpaRepository<AdmissionLifecycle, String> {

    List<AdmissionLifecycle> findByAssessmentId(String assessmentId);

    List<AdmissionLifecycle> findByStatus(String status);

    List<AdmissionLifecycle> findByAssessmentIdAndStatus(String assessmentId, String status);

    List<AdmissionLifecycle> findByIsCurrentTrue();

    Optional<AdmissionLifecycle> findByAssessmentIdAndIsCurrentTrue(String assessmentId);

    List<AdmissionLifecycle> findByIsActiveTrue();

    @Query("SELECT al FROM AdmissionLifecycle al WHERE al.isActive = true AND al.assessmentId = :assessmentId")
    List<AdmissionLifecycle> findActiveByAssessment(@Param("assessmentId") String assessmentId);

    @Query("SELECT al FROM AdmissionLifecycle al WHERE al.isActive = true AND al.status = :status")
    List<AdmissionLifecycle> findActiveByStatus(@Param("status") String status);

    @Query("SELECT al FROM AdmissionLifecycle al WHERE al.isCurrent = true AND al.assessmentId = :assessmentId")
    Optional<AdmissionLifecycle> findCurrentByAssessment(@Param("assessmentId") String assessmentId);
}
