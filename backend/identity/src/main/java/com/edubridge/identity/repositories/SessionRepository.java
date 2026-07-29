package com.edubridge.identity.repositories;

import com.edubridge.identity.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SessionRepository extends JpaRepository<Session, UUID> {

    Optional<Session> findByTokenHash(String tokenHash);

    List<Session> findByUserIdAndIsActiveTrue(UUID userId);

    @Query("SELECT s FROM Session s WHERE s.user.id = :userId AND s.isActive = true AND s.expiresAt > :now")
    List<Session> findActiveSessionsByUserId(@Param("userId") UUID userId, @Param("now") Instant now);

    void deleteByExpiresAtBefore(Instant expiresAt);
}
