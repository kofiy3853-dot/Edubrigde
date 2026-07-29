package com.edubridge.identity.repositories;

import com.edubridge.identity.entities.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, UUID> {

    Optional<VerificationToken> findByTokenHash(String tokenHash);

    @Query("SELECT vt FROM VerificationToken vt WHERE vt.tokenHash = :tokenHash AND vt.used = false AND vt.expiresAt > CURRENT_TIMESTAMP")
    Optional<VerificationToken> findValidToken(@Param("tokenHash") String tokenHash);
}
