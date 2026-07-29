package com.edubridge.studentservices.repositories;

import com.edubridge.studentservices.entities.MentorshipProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentorshipProfileRepository extends JpaRepository<MentorshipProfile, String> {

    List<MentorshipProfile> findByStudentIdAndIsActiveTrue(String studentId);

    List<MentorshipProfile> findByMentorshipTypeAndIsActiveTrue(String mentorshipType);

    List<MentorshipProfile> findByMentorshipStatusAndIsActiveTrue(String mentorshipStatus);

    List<MentorshipProfile> findByExperienceLevelAndIsActiveTrue(String experienceLevel);

    @Query("SELECT mp FROM MentorshipProfile mp WHERE mp.studentId = :studentId AND mp.mentorshipStatus = 'ACTIVE' AND mp.isActive = true")
    List<MentorshipProfile> findActiveByStudent(@Param("studentId") String studentId);

    @Query("SELECT mp FROM MentorshipProfile mp WHERE mp.mentorshipStatus = 'PENDING' AND mp.isActive = true")
    List<MentorshipProfile> findPendingMentorships();
}
