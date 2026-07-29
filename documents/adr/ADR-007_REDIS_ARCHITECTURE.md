# ADR-007: Redis Architecture

## Date

2026-01-01

## Status

Accepted

## Context

EduBridge OS needs a high-performance caching and temporary storage layer that complements PostgreSQL without becoming a secondary source of truth.

## Decision

Use Redis exclusively for performance optimization, temporary storage, and distributed capabilities. PostgreSQL remains the sole source of truth for business data.

### Architecture

```
Application
    |
    +-- PostgreSQL (permanent business data)
    |
    +-- Redis (temporary/performance data)
        |
        +-- Caching
        +-- Sessions
        +-- Rate Limiting
        +-- Temporary Tokens
        +-- Distributed Locks
```

### Responsibilities

```
Redis Manages:
  - Application caching
  - User sessions
  - Rate limiting
  - Email verification tokens
  - Password reset tokens
  - Distributed locks
  - Temporary state

Redis Does NOT Manage:
  - Student records
  - Payment data
  - University information
  - Application data
  - Any permanent business data
```

## Rules

```
1. PostgreSQL is the only source of truth
2. Redis is for performance only
3. All cache entries must have TTL
4. No permanent caches allowed
5. Sessions expire after 30 minutes
6. OTP tokens expire after 5 minutes
7. Password resets expire after 15 minutes
8. Every public endpoint supports rate limiting
```

## Alternatives Considered

### Option 1: Redis as Primary Database

Benefits:
- High performance
- Simple architecture

Trade-offs:
- No ACID transactions
- Data loss risk
- No complex queries
- Not suitable for business data

### Option 2: No Caching Layer

Benefits:
- Simple architecture
- No cache invalidation

Trade-offs:
- Poor performance
- No session management
- No rate limiting
- Not scalable

### Option 3: Memcached

Benefits:
- Simple caching
- Fast

Trade-offs:
- No persistence
- No data structures
- No sessions support
- No rate limiting

## Rationale

Redis provides:
- High performance (sub-millisecond)
- Rich data structures
- Persistence option
- Pub/Sub for future events
- Cluster support for scaling

## Consequences

### Positive

- Improved performance
- Session management
- Rate limiting
- Distributed locking
- Temporary token management

### Negative

- Cache invalidation complexity
- Additional infrastructure
- Memory management

### Neutral

- Requires monitoring
- Requires TTL management
- Requires security hardening

## Approval

| Role | Name | Date |
|------|------|------|
| Tech Lead | | 2026-01-01 |
| Backend Lead | | 2026-01-01 |
| Project Owner | | 2026-01-01 |

## References

- documents/standards/architecture/TECHNOLOGY_STANDARDS.md
- documents/architecture/REDIS_ARCHITECTURE.md
