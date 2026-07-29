package com.edubridge.studentservices.repositories;

import com.edubridge.studentservices.entities.AdvisorProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvisorProfileRepository extends JpaRepository<AdvisorProfile, String> {

    List<AdvisorProfile> findByUserIdAndIsActiveTrue(String userId);

    List<AdvisorProfile> findByAdvisorTypeAndIsActiveTrue(String advisorType);

    List<AdvisorProfile> findByAvailabilityStatusAndIsActiveTrue(String availabilityStatus);

    List<AdvisorProfile> findBySpecializationAndIsActiveTrue(String specialization);

    @Query("SELECT ap FROM AdvisorProfile ap WHERE ap.availabilityStatus = 'AVAILABLE' AND ap.currentConsultations < ap.maxConsultationsPerDay AND ap.isActive = true")
    List<AdvisorProfile> findAvailableAdvisors();

    @Query("SELECT ap FROM AdvisorProfile ap WHERE ap.advisorType = :type AND ap.availabilityStatus = 'AVAILABLE' AND ap.isActive = true")
    List<AdvisorProfile> findAvailableByType(@Param("type") String type);

    @Query("SELECT ap FROM AdvisorProfile ap WHERE ap.rating >= :minRating AND ap.isActive = true ORDER BY ap.rating DESC")
    List<AdvisorProfile> findTopRated(@Param("minRating") java.math.BigDecimal minRating);
}
