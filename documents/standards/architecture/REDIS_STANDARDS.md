# Redis Standards

## Status

LOCKED - Sprint 0C, Step 16

## Principle

Performance only. Temporary storage. PostgreSQL is source of truth.

## Redis Responsibilities

```
Caching              -> Application, API, reference data, search
Session Management   -> User sessions, admin sessions
Rate Limiting        -> Authentication, payment, public, admin APIs
Temporary Tokens     -> Email verification, password reset, OTP
Distributed Locks    -> Payment processing, document uploads
Performance          -> Query optimization, response caching
```

## Cache Standards

```
Application Cache    -> TTL: 10 minutes
API Cache            -> TTL: 5 minutes
Reference Data Cache -> TTL: 1 hour
Search Cache         -> TTL: 2 minutes
Configuration Cache  -> TTL: 1 hour
Analytics Cache      -> TTL: 5 minutes
```

Rules:
- All caches must have TTL
- No permanent caches
- Cache invalidation on write
- Cache-aside pattern preferred

## Session Standards

```
User Sessions        -> TTL: 30 minutes
Admin Sessions       -> TTL: 30 minutes
Distributed Sessions -> TTL: 30 minutes
```

Features:
- Session expiration
- Session revocation
- Session isolation
- Session monitoring

## Rate Limiting Standards

```
Authentication APIs  -> 10 requests/minute
Payment APIs         -> 20 requests/minute
Public APIs          -> 100 requests/minute
Administrative APIs  -> 50 requests/minute
Learning APIs        -> 100 requests/minute
AI APIs              -> 10 requests/minute
```

Every public endpoint must support rate limiting.

## Temporary Token Standards

```
Email Verification   -> TTL: 24 hours
Password Reset       -> TTL: 15 minutes
OTP Verification     -> TTL: 5 minutes
Invitation Tokens    -> TTL: 7 days
```

Requirements:
- Expiration policies
- Revocation policies
- Audit support
- Security standards

## Distributed Lock Standards

```
Payment Processing   -> Lease: 30 seconds
Document Uploads     -> Lease: 60 seconds
Learning Progress    -> Lease: 30 seconds
Admin Operations     -> Lease: 30 seconds
```

Prevents race conditions across services.

## Key Naming Standards

```
edubridge:cache:{domain}:{key}
edubridge:session:{sessionId}
edubridge:ratelimit:{endpoint}:{identifier}
edubridge:token:{purpose}:{token}
edubridge:lock:{resource}:{key}
```

## Monitoring Standards

```
Cache Hit Rate       -> Target: >80%
Cache Miss Rate      -> Target: <20%
Memory Usage         -> Alert: >80%
Session Statistics   -> Active, expired, revoked
Rate Limit Stats     -> Allowed, rejected
Performance Metrics  -> Latency, throughput
Health Checks        -> Every 30 seconds
```

## Testing Standards

```
Cache Tests          -> Put, get, evict, TTL
Session Tests        -> Create, validate, revoke
Rate Limit Tests     -> Allow, reject, reset
Performance Tests    -> Latency, throughput
Concurrency Tests    -> Lock acquisition, release
Integration Tests    -> Full workflow
```

## Never

```
- Permanent business storage
- Unlimited cache expiration
- Public session access
- Cross-domain cache ownership
- Hardcoded rate limits
- Manual cache invalidation
- Redis as source of truth
```
