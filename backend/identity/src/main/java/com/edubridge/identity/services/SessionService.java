package com.edubridge.identity.services;

import com.edubridge.identity.entities.Session;
import com.edubridge.identity.repositories.SessionRepository;
import com.edubridge.shared.exceptions.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SessionService {

    private final SessionRepository sessionRepository;

    @Transactional(readOnly = true)
    public Optional<Session> getSessionByTokenHash(String tokenHash) {
        return sessionRepository.findByTokenHash(tokenHash);
    }

    @Transactional(readOnly = true)
    public List<Session> getActiveSessions(UUID userId) {
        return sessionRepository.findActiveSessionsByUserId(userId, Instant.now());
    }

    @Transactional
    public void revokeSession(String tokenHash, String reason) {
        sessionRepository.findByTokenHash(tokenHash).ifPresent(session -> {
            session.revoke(reason);
            sessionRepository.save(session);
            log.info("Session revoked: tokenHash={}, reason={}", tokenHash, reason);
        });
    }

    @Transactional
    public void revokeAllUserSessions(UUID userId, String reason) {
        List<Session> activeSessions = sessionRepository.findByUserIdAndIsActiveTrue(userId);
        for (Session session : activeSessions) {
            session.revoke(reason);
            sessionRepository.save(session);
        }
        log.info("All sessions revoked for user: userId={}, count={}, reason={}", userId, activeSessions.size(), reason);
    }

    @Transactional
    public void updateLastActivity(String tokenHash) {
        sessionRepository.findByTokenHash(tokenHash).ifPresent(session -> {
            session.setLastActivityAt(Instant.now());
            sessionRepository.save(session);
        });
    }

    @Scheduled(fixedRate = 3600000)
    @Transactional
    public void cleanupExpiredSessions() {
        sessionRepository.deleteByExpiresAtBefore(Instant.now());
        log.info("Expired sessions cleaned up");
    }
}
