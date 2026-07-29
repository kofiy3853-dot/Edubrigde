package com.edubridge.academic.repositories;

import com.edubridge.academic.entities.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UniversityRepository extends JpaRepository<University, String> {

    Optional<University> findByCode(String code);

    Optional<University> findByName(String name);

    List<University> findByCountryCode(String countryCode);

    List<University> findByInstitutionType(String institutionType);

    List<University> findByAccreditationStatus(String accreditationStatus);

    List<University> findByIsActiveTrue();

    List<University> findByIsPartnerTrue();

    @Query("SELECT u FROM University u WHERE u.isActive = true AND u.countryCode = :countryCode")
    List<University> findActiveByCountry(@Param("countryCode") String countryCode);

    @Query("SELECT u FROM University u WHERE u.isActive = true AND u.institutionType = :type")
    List<University> findActiveByType(@Param("type") String type);

    boolean existsByCode(String code);

    boolean existsByName(String name);
}
