package com.edubridge.identity.repositories;

import com.edubridge.identity.entities.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, UUID> {

    Optional<Policy> findByName(String name);

    Boolean existsByName(String name);

    @Query("SELECT p FROM Policy p WHERE p.isActive = true AND p.resource = :resource AND p.action = :action ORDER BY p.priority DESC")
    List<Policy> findActivePoliciesForResource(@Param("resource") String resource, @Param("action") String action);

    @Query("SELECT p FROM Policy p JOIN FETCH p.roles r WHERE p.isActive = true AND r.id IN :roleIds AND p.resource = :resource AND p.action = :action ORDER BY p.priority DESC")
    List<Policy> findActivePoliciesForRoles(@Param("roleIds") List<UUID> roleIds, @Param("resource") String resource, @Param("action") String action);
}
