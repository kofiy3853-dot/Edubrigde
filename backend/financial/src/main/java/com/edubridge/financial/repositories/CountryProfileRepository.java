package com.edubridge.financial.repositories;

import com.edubridge.financial.entities.CountryProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryProfileRepository extends JpaRepository<CountryProfile, String> {

    Optional<CountryProfile> findByCountryCode(String countryCode);

    Optional<CountryProfile> findByCountryName(String countryName);

    List<CountryProfile> findByIsActiveTrue();

    @Query("SELECT cp FROM CountryProfile cp WHERE cp.isActive = true ORDER BY cp.countryName")
    List<CountryProfile> findActiveOrderedByName();

    boolean existsByCountryCode(String countryCode);

    boolean existsByCountryName(String countryName);
}
