package com.edubridge.studentservices.repositories;

import com.edubridge.studentservices.entities.ConsultationProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultationProfileRepository extends JpaRepository<ConsultationProfile, String> {

    List<ConsultationProfile> findByStudentIdAndIsActiveTrue(String studentId);

    List<ConsultationProfile> findByAdvisorIdAndIsActiveTrue(String advisorId);

    List<ConsultationProfile> findByConsultationTypeAndIsActiveTrue(String consultationType);

    List<ConsultationProfile> findByConsultationStatusAndIsActiveTrue(String consultationStatus);

    @Query("SELECT cp FROM ConsultationProfile cp WHERE cp.studentId = :studentId AND cp.consultationStatus = 'SCHEDULED' AND cp.isActive = true")
    List<ConsultationProfile> findScheduledByStudent(@Param("studentId") String studentId);

    @Query("SELECT cp FROM ConsultationProfile cp WHERE cp.advisorId = :advisorId AND cp.consultationStatus = 'SCHEDULED' AND cp.isActive = true")
    List<ConsultationProfile> findScheduledByAdvisor(@Param("advisorId") String advisorId);

    @Query("SELECT cp FROM ConsultationProfile cp WHERE cp.consultationStatus = 'PENDING' AND cp.isActive = true")
    List<ConsultationProfile> findPendingConsultations();
}
