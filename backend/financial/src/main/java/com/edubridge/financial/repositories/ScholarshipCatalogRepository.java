package com.edubridge.financial.repositories;

import com.edubridge.financial.entities.ScholarshipCatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScholarshipCatalogRepository extends JpaRepository<ScholarshipCatalog, String> {

    Optional<ScholarshipCatalog> findByCode(String code);

    List<ScholarshipCatalog> findByScholarshipTypeAndIsActiveTrue(String scholarshipType);

    List<ScholarshipCatalog> findByCountryCodeAndIsActiveTrue(String countryCode);

    List<ScholarshipCatalog> findByFundingOrganizationAndIsActiveTrue(String fundingOrganization);

    List<ScholarshipCatalog> findByIsRenewableAndIsActiveTrue(Boolean isRenewable);

    @Query("SELECT sc FROM ScholarshipCatalog sc WHERE sc.isActive = true AND (sc.maxRecipients IS NULL OR sc.currentRecipients < sc.maxRecipients) ORDER BY sc.name")
    List<ScholarshipCatalog> findAvailableScholarships();

    @Query("SELECT sc FROM ScholarshipCatalog sc WHERE sc.countryCode = :countryCode AND sc.scholarshipType = :type AND sc.isActive = true")
    List<ScholarshipCatalog> findByCountryAndType(@Param("countryCode") String countryCode, @Param("type") String type);

    @Query("SELECT sc FROM ScholarshipCatalog sc WHERE sc.applicationDeadline >= CURRENT_DATE AND sc.isActive = true ORDER BY sc.applicationDeadline ASC")
    List<ScholarshipCatalog> findOpenDeadlines();

    boolean existsByCode(String code);
}
