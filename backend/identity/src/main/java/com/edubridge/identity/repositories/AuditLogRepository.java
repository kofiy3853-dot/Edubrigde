package com.edubridge.identity.repositories;

import com.edubridge.identity.entities.AuditLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, UUID> {

    Page<AuditLog> findByUserIdOrderByTimestampDesc(UUID userId, Pageable pageable);

    List<AuditLog> findByUserIdAndActionOrderByTimestampDesc(UUID userId, String action);

    List<AuditLog> findByServiceNameAndTimestampBetween(String serviceName, Instant start, Instant end);

    @Query("SELECT a FROM AuditLog a WHERE a.timestamp BETWEEN :start AND :end ORDER BY a.timestamp DESC")
    Page<AuditLog> findByDateRange(@Param("start") Instant start, @Param("end") Instant end, Pageable pageable);

    @Query("SELECT a FROM AuditLog a WHERE a.user.id = :userId AND a.result = :result ORDER BY a.timestamp DESC")
    List<AuditLog> findByUserIdAndResult(@Param("userId") UUID userId, @Param("result") String result);

    long countByUserIdAndResult(UUID userId, String result);

    @Query("SELECT a FROM AuditLog a WHERE a.serviceName = :service AND a.action = :action ORDER BY a.timestamp DESC")
    List<AuditLog> findByServiceAndAction(@Param("service") String service, @Param("action") String action);
}
