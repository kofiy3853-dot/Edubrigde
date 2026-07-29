package com.edubridge.studentservices.repositories;

import com.edubridge.studentservices.entities.StudentSupportProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentSupportProfileRepository extends JpaRepository<StudentSupportProfile, String> {

    List<StudentSupportProfile> findByStudentIdAndIsActiveTrue(String studentId);

    List<StudentSupportProfile> findBySupportTypeAndIsActiveTrue(String supportType);

    List<StudentSupportProfile> findBySupportStatusAndIsActiveTrue(String supportStatus);

    List<StudentSupportProfile> findBySupportLevelAndIsActiveTrue(String supportLevel);

    @Query("SELECT ssp FROM StudentSupportProfile ssp WHERE ssp.studentId = :studentId AND ssp.supportStatus = 'ACTIVE' AND ssp.isActive = true")
    List<StudentSupportProfile> findActiveByStudent(@Param("studentId") String studentId);

    @Query("SELECT ssp FROM StudentSupportProfile ssp WHERE ssp.supportStatus = 'IDENTIFIED' AND ssp.isActive = true")
    List<StudentSupportProfile> findIdentifiedProfiles();
}
