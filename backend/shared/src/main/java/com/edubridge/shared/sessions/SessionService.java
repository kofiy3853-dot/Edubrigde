package com.edubridge.shared.sessions;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;

@Service
public class SessionService {

    private static final String SESSION_PREFIX = "session:";
    private static final Duration DEFAULT_TTL = Duration.ofMinutes(30);

    private final RedisTemplate<String, Object> redisTemplate;

    public SessionService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Session createSession(UUID userId, String ipAddress, String userAgent) {
        UUID sessionId = UUID.randomUUID();
        String tokenHash = UUID.randomUUID().toString();
        Session session = new Session(
            sessionId,
            userId,
            tokenHash,
            ipAddress,
            userAgent,
            java.time.Instant.now().plus(DEFAULT_TTL),
            java.time.Instant.now()
        );
        redisTemplate.opsForValue().set(
            SESSION_PREFIX + sessionId,
            session,
            DEFAULT_TTL
        );
        return session;
    }

    public Optional<Session> getSession(UUID sessionId) {
        Object session = redisTemplate.opsForValue().get(SESSION_PREFIX + sessionId);
        if (session == null) {
            return Optional.empty();
        }
        Session s = (Session) session;
        if (s.isExpired()) {
            evictSession(sessionId);
            return Optional.empty();
        }
        return Optional.of(s);
    }

    public void evictSession(UUID sessionId) {
        redisTemplate.delete(SESSION_PREFIX + sessionId);
    }

    public void evictAllUserSessions(UUID userId) {
        var keys = redisTemplate.keys(SESSION_PREFIX + "*");
        if (keys != null) {
            keys.forEach(key -> {
                Object session = redisTemplate.opsForValue().get(key);
                if (session instanceof Session s && s.userId().equals(userId)) {
                    redisTemplate.delete(key);
                }
            });
        }
    }
}
