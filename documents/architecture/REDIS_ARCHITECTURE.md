# Redis Responsibilities

## Status

LOCKED - Sprint 0B, Step 08

## Principle

Redis is a cache and temporary store, never a primary database.

## Responsibilities

### 1. Caching

```
Purpose:    Cache frequently accessed data
Examples:   University listings, user profiles, search results
Strategy:   Cache-aside with TTL
TTL:        5-60 minutes depending on data volatility
Invalidation: On data update, event-driven
```

### 2. Rate Limiting

```
Purpose:    Protect API from abuse
Examples:   Login attempts, API calls, search requests
Strategy:   Sliding window counter
Limits:     Configurable per endpoint and user role
```

### 3. Sessions

```
Purpose:    Store active user sessions
Examples:   JWT refresh tokens, session state
Strategy:   Session ID as key, user data as value
TTL:        Match token expiry (7 days for refresh tokens)
```

### 4. Temporary Tokens

```
Purpose:    Store short-lived tokens
Examples:   Password reset tokens, email verification, MFA codes
Strategy:   Token as key, user ID as value
TTL:        15 minutes for reset, 24 hours for verification
```

### 5. Performance Optimization

```
Purpose:    Reduce database load
Examples:   Query result caching, aggregation caching
Strategy:   Write-through or cache-aside
TTL:        Based on data freshness requirements
```

### 6. Temporary Storage

```
Purpose:    Short-lived operational data
Examples:   Rate limit counters, lock mechanisms, job queues
Strategy:   Atomic operations, TTL-based cleanup
```

## Redis Data Patterns

```
Cache:          cache:{domain}:{id} -> JSON
Rate Limit:     ratelimit:{ip}:{endpoint} -> counter
Session:        session:{sessionId} -> JSON
Token:          token:{tokenType}:{token} -> userId
Lock:           lock:{resource} -> timestamp
Queue:          queue:{queueName} -> list
```

## What Redis Must Never Do

```
NEVER:
  - Store primary business data
  - Replace PostgreSQL
  - Store permanent records
  - Store file metadata
  - Store audit logs
  - Store financial transactions
  - Store user credentials
```

## Redis Configuration

```
Max Memory:     256MB (development), 2GB (production)
Eviction:       allkeys-lru
Persistence:    RDB snapshots (for session recovery)
Replication:    None (development), Sentinel (production)
```

## Monitoring

```
Required Metrics:
  - Memory usage
  - Connection count
  - Hit/miss ratio
  - Operations per second
  - Latency
```

## Never

```
- Use as primary database
- Store sensitive data without encryption
- Skip TTL on temporary data
- Skip monitoring
- Expose Redis publicly
- Skip authentication
```
