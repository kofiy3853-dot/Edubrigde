package com.edubridge.learningservices.repositories;

import com.edubridge.learningservices.entities.ProfessionalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessionalRecordRepository extends JpaRepository<ProfessionalRecord, String> {

    List<ProfessionalRecord> findByStudentIdAndIsActiveTrue(String studentId);

    List<ProfessionalRecord> findByCareerProfileIdAndIsActiveTrue(String careerProfileId);

    List<ProfessionalRecord> findByProfessionalTypeAndIsActiveTrue(String professionalType);

    List<ProfessionalRecord> findByProfessionalStatusAndIsActiveTrue(String professionalStatus);

    List<ProfessionalRecord> findBySkillAreaAndIsActiveTrue(String skillArea);

    @Query("SELECT pr FROM ProfessionalRecord pr WHERE pr.studentId = :studentId AND pr.professionalStatus = 'COMPLETED' AND pr.isActive = true")
    List<ProfessionalRecord> findCompletedByStudent(@Param("studentId") String studentId);

    @Query("SELECT pr FROM ProfessionalRecord pr WHERE pr.studentId = :studentId AND pr.professionalType = :type AND pr.isActive = true")
    List<ProfessionalRecord> findByStudentAndType(@Param("studentId") String studentId, @Param("type") String type);

    @Query("SELECT pr FROM ProfessionalRecord pr WHERE pr.certificationExpiry < CURRENT_TIMESTAMP AND pr.professionalStatus = 'COMPLETED' AND pr.isActive = true")
    List<ProfessionalRecord> findExpiredCertifications();
}
