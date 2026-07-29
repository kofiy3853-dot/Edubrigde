package com.edubridge.shared.tokens;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;

@Service
public class TempTokenService {

    private static final String TOKEN_PREFIX = "temptoken:";

    private final RedisTemplate<String, Object> redisTemplate;

    public TempTokenService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String createToken(String purpose, int ttlMinutes) {
        String token = UUID.randomUUID().toString();
        String key = TOKEN_PREFIX + purpose + ":" + token;
        redisTemplate.opsForValue().set(key, purpose, Duration.ofMinutes(ttlMinutes));
        return token;
    }

    public Optional<String> validateToken(String purpose, String token) {
        String key = TOKEN_PREFIX + purpose + ":" + token;
        Object value = redisTemplate.opsForValue().get(key);
        if (value != null) {
            redisTemplate.delete(key);
            return Optional.of(purpose);
        }
        return Optional.empty();
    }

    public void revokeToken(String purpose, String token) {
        String key = TOKEN_PREFIX + purpose + ":" + token;
        redisTemplate.delete(key);
    }

    public void revokeAllForPurpose(String purpose) {
        var keys = redisTemplate.keys(TOKEN_PREFIX + purpose + ":*");
        if (keys != null) {
            redisTemplate.delete(keys);
        }
    }
}
