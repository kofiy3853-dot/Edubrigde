package com.edubridge.shared.ratelimiting;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RateLimitService {

    private static final String RATE_LIMIT_PREFIX = "ratelimit:";

    private final RedisTemplate<String, Object> redisTemplate;

    public RateLimitService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean isAllowed(String key, int maxRequests, int windowSeconds) {
        String redisKey = RATE_LIMIT_PREFIX + key;
        Long current = redisTemplate.opsForValue().increment(redisKey);
        if (current != null && current == 1) {
            redisTemplate.expire(redisKey, Duration.ofSeconds(windowSeconds));
        }
        return current != null && current <= maxRequests;
    }

    public long getRemainingRequests(String key, int maxRequests) {
        String redisKey = RATE_LIMIT_PREFIX + key;
        Object value = redisTemplate.opsForValue().get(redisKey);
        if (value == null) {
            return maxRequests;
        }
        long current = Long.parseLong(value.toString());
        return Math.max(0, maxRequests - current);
    }

    public void reset(String key) {
        redisTemplate.delete(RATE_LIMIT_PREFIX + key);
    }
}
