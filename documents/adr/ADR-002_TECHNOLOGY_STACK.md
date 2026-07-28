# ADR-002: Technology Stack

## Date

2026-01-01

## Status

Accepted

## Context

EduBridge OS needs a stable, maintainable, and scalable technology stack that supports enterprise requirements while remaining contributor-friendly.

## Decision

Adopt the following technology stack:

### Frontend

```
Next.js 15.x        -> React framework
TypeScript 5.x      -> Type safety
TailwindCSS 4.x     -> Styling
Shadcn/UI           -> Components
TanStack Query 5.x  -> Server state
Zustand 5.x         -> Client state
Zod 3.x             -> Validation
Playwright           -> E2E testing
Vitest 3.x          -> Unit testing
```

### Backend

```
Java 21 LTS         -> Language
Spring Boot 3.4.x   -> Framework
Spring Security 6.x -> Authentication
Spring Data JPA 3.x -> Data access
MapStruct 1.6.x     -> Object mapping
Flyway 10.x         -> Migrations
Lombok              -> Boilerplate
Bucket4j 8.x        -> Rate limiting
SpringDoc 2.x       -> OpenAPI
JUnit 5.x           -> Testing
Mockito 5.x         -> Mocking
```

### Data Layer

```
PostgreSQL 16        -> Primary database
Redis 7.x           -> Cache, sessions
```

### Messaging

```
Apache Kafka 3.8.x  -> Event messaging
```

### Storage

```
MinIO               -> Object storage
```

### Infrastructure

```
Docker 27.x         -> Containerization
Docker Compose 2.x  -> Orchestration
NGINX 1.27.x        -> Reverse proxy
GitHub Actions       -> CI/CD
Cloudflare           -> CDN, DDoS
```

### Observability

```
Grafana 11.x        -> Dashboards
Prometheus 3.x      -> Metrics
OpenTelemetry 1.x   -> Traces, metrics
Loki 3.x            -> Logs
Tempo 2.x           -> Distributed tracing
```

## Alternatives Considered

### Option 1: Python + Django

Benefits:
- Faster development
- Rich ecosystem
- Good for AI/ML

Trade-offs:
- Less enterprise adoption
- Performance limitations
- Weaker typing

### Option 2: Go + Gin

Benefits:
- High performance
- Simple deployment
- Strong concurrency

Trade-offs:
- Smaller ecosystem
- Less developer-friendly
- More boilerplate

### Option 3: Node.js Full Stack

Benefits:
- Single language
- Shared code
- Fast development

Trade-offs:
- Less enterprise adoption
- Weaker typing
- Performance limitations

## Rationale

Java + Spring Boot provides:
- Enterprise adoption and maturity
- Strong typing and safety
- Rich ecosystem
- Excellent tooling
- Large developer community
- Long-term support

Next.js + TypeScript provides:
- Modern React development
- Strong typing
- Excellent DX
- Great performance
- Large ecosystem

## Consequences

### Positive

- Enterprise-grade stability
- Strong community support
- Excellent documentation
- Long-term viability
- Easy hiring

### Negative

- More boilerplate (Java)
- Steeper learning curve
- More configuration

### Neutral

- Requires Java knowledge
- Requires TypeScript knowledge

## Implementation Impact

- All backend services use Java 21 + Spring Boot 3.4.x
- All frontend applications use Next.js 15 + TypeScript 5.x
- All infrastructure uses Docker + Docker Compose
- All monitoring uses OpenTelemetry + Grafana

## Approval

| Role | Name | Date |
|------|------|------|
| Tech Lead | | 2026-01-01 |
| Security Architect | | 2026-01-01 |
| Project Owner | | 2026-01-01 |

## References

- documents/standards/architecture/TECHNOLOGY_STANDARDS.md
- documents/architecture/INFRASTRUCTURE_ARCHITECTURE.md
