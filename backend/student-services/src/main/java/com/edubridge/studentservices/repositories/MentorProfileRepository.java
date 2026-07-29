package com.edubridge.studentservices.repositories;

import com.edubridge.studentservices.entities.MentorProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentorProfileRepository extends JpaRepository<MentorProfile, String> {

    List<MentorProfile> findByMentorTypeAndIsActiveTrue(String mentorType);

    List<MentorProfile> findByExpertiseAreasContainingAndIsActiveTrue(String expertise);

    List<MentorProfile> findByAvailabilityStatusAndIsActiveTrue(String availabilityStatus);

    @Query("SELECT mp FROM MentorProfile mp WHERE mp.availabilityStatus = 'AVAILABLE' AND mp.currentMentees < mp.maxMentees AND mp.isActive = true")
    List<MentorProfile> findAvailableMentors();

    @Query("SELECT mp FROM MentorProfile mp WHERE mp.userId = :userId AND mp.isActive = true")
    MentorProfile findByUserId(@Param("userId") String userId);
}
