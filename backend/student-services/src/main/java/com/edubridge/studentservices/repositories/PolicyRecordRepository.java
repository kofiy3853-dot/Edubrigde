package com.edubridge.studentservices.repositories;

import com.edubridge.studentservices.entities.PolicyRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolicyRecordRepository extends JpaRepository<PolicyRecord, String> {

    List<PolicyRecord> findByPolicyTypeAndIsActiveTrue(String policyType);

    List<PolicyRecord> findByEffectAndIsActiveTrue(String effect);

    @Query("SELECT pr FROM PolicyRecord pr WHERE pr.policyType = :type AND pr.effect = :effect AND pr.isActive = true")
    List<PolicyRecord> findByTypeAndEffect(@Param("type") String type, @Param("effect") String effect);

    @Query("SELECT pr FROM PolicyRecord pr WHERE pr.effect = 'DENY' AND pr.isActive = true")
    List<PolicyRecord> findDenyPolicies();

    @Query("SELECT pr FROM PolicyRecord pr WHERE pr.isActive = true ORDER BY pr.priority DESC")
    List<PolicyRecord> findAllActiveOrderedByPriority();
}
