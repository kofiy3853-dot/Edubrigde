# Technology Standards

## Status

LOCKED - Sprint 0B, Step 10

## Principle

All technologies are stable, supported, compatible, documented, and production-ready.

## Frontend Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Next.js | 15.x | React framework |
| TypeScript | 5.x | Type safety |
| TailwindCSS | 4.x | Utility-first CSS |
| Shadcn/UI | Latest | Component library |
| TanStack Query | 5.x | Server state management |
| Zustand | 5.x | Client state management |
| Zod | 3.x | Schema validation |
| React Hook Form | 7.x | Form management |
| Motion | 12.x | Animations |
| Playwright | Latest | E2E testing |
| Vitest | 3.x | Unit testing |

### Frontend Rules

```
1. TypeScript required for all files
2. TailwindCSS for all styling
3. Shadcn/UI for all components
4. Zod for all validation
5. TanStack Query for all API calls
6. Zustand for client state only
```

## Backend Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 21 LTS | Language |
| Spring Boot | 3.4.x | Framework |
| Spring Security | 6.x | Authentication |
| Spring Data JPA | 3.x | Data access |
| MapStruct | 1.6.x | Object mapping |
| Flyway | 10.x | Database migrations |
| Lombok | Latest | Boilerplate reduction |
| Bucket4j | 8.x | Rate limiting |
| SpringDoc | 2.x | OpenAPI documentation |

### Backend Rules

```
1. Java 21 LTS (stable, not latest)
2. Spring Boot 3.4.x (current stable)
3. All entities use UUID primary keys
4. All DTOs validated with Jakarta validation
5. All services transactional
6. All controllers return ResponseEntity
```

## Database Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| PostgreSQL | 16 | Primary database |
| Redis | 7.x | Cache, sessions, rate limiting |
| Elasticsearch | 8.x | Full-text search (future) |

### Database Rules

```
1. PostgreSQL for all persistent data
2. Redis for caching and temporary data only
3. Elasticsearch only when search complexity requires it
4. Schema-per-domain isolation
5. All tables have audit columns
6. All data soft-deleted
```

## Messaging Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Apache Kafka | 3.8.x | Event messaging |

### Messaging Rules

```
1. Kafka for all async communication
2. Events are immutable
3. Events include correlation IDs
4. Events are versioned
5. Producers don't know consumers
```

## Storage Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| MinIO | Latest | Object storage |

### Storage Rules

```
1. MinIO for all file storage
2. Files accessed via presigned URLs
3. Metadata stored in PostgreSQL
4. Buckets organized by domain
5. Files never in database
```

## Infrastructure Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Docker | 27.x | Containerization |
| Docker Compose | 2.x | Local orchestration |
| NGINX | 1.27.x | Reverse proxy |
| GitHub Actions | Latest | CI/CD |
| Cloudflare | Latest | CDN, DDoS protection |

## Observability Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| Grafana | 11.x | Dashboards |
| Prometheus | 3.x | Metrics |
| OpenTelemetry | 1.x | Traces, metrics |
| Loki | 3.x | Logs |
| Tempo | 2.x | Distributed tracing |

## Testing Stack

| Technology | Version | Purpose |
|------------|---------|---------|
| JUnit | 5.x | Unit testing (Java) |
| Mockito | 5.x | Mocking (Java) |
| Vitest | 3.x | Unit testing (TypeScript) |
| Playwright | Latest | E2E testing |
| SonarQube | Latest | Code quality |

## Technology Selection Criteria

```
1. Stable release (not RC, not beta)
2. Active maintenance
3. Strong community
4. Good documentation
5. Production-ready
6. Compatible with stack
7. Security-track-record
```

## Never

```
- Use unstable versions in production
- Use technologies without community support
- Skip compatibility validation
- Use deprecated technologies
- Skip security review for new technologies
```
