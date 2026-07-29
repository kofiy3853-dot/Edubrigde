package com.edubridge.academic.repositories;

import com.edubridge.academic.entities.InternationalPartnership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternationalPartnershipRepository extends JpaRepository<InternationalPartnership, String> {

    List<InternationalPartnership> findByPartnerUniversityId(String partnerUniversityId);

    List<InternationalPartnership> findByPartnershipType(String partnershipType);

    List<InternationalPartnership> findByStatus(String status);

    List<InternationalPartnership> findByPartnerInstitutionCountry(String partnerInstitutionCountry);

    List<InternationalPartnership> findByPartnerUniversityIdAndStatus(String partnerUniversityId, String status);

    List<InternationalPartnership> findByIsActiveTrue();

    @Query("SELECT ip FROM InternationalPartnership ip WHERE ip.isActive = true AND ip.partnerUniversityId = :universityId")
    List<InternationalPartnership> findActiveByUniversity(@Param("universityId") String universityId);

    @Query("SELECT ip FROM InternationalPartnership ip WHERE ip.isActive = true AND ip.status = :status")
    List<InternationalPartnership> findActiveByStatus(@Param("status") String status);

    @Query("SELECT ip FROM InternationalPartnership ip WHERE ip.isActive = true AND ip.partnershipType = :type")
    List<InternationalPartnership> findActiveByType(@Param("type") String type);
}
