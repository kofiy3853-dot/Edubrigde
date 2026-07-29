package com.edubridge.academic.repositories;

import com.edubridge.academic.entities.InstitutionClassification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstitutionClassificationRepository extends JpaRepository<InstitutionClassification, String> {

    List<InstitutionClassification> findByUniversityId(String universityId);

    List<InstitutionClassification> findByClassificationType(String classificationType);

    List<InstitutionClassification> findByClassificationValue(String classificationValue);

    List<InstitutionClassification> findByUniversityIdAndClassificationType(String universityId, String classificationType);

    List<InstitutionClassification> findByIsPrimaryTrue();

    List<InstitutionClassification> findByIsActiveTrue();

    @Query("SELECT ic FROM InstitutionClassification ic WHERE ic.isActive = true AND ic.universityId = :universityId")
    List<InstitutionClassification> findActiveByUniversity(@Param("universityId") String universityId);

    @Query("SELECT ic FROM InstitutionClassification ic WHERE ic.isActive = true AND ic.classificationType = :type")
    List<InstitutionClassification> findActiveByType(@Param("type") String type);
}
