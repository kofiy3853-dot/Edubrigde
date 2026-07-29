# Docker & Container Standards

## LOCKED STANDARDS

These standards are permanently locked and cannot be modified.

---

## 1. Container Standards

### One Responsibility Per Container

Each container must have one responsibility, one purpose.

### Required Support

- Health checks
- Environment isolation
- Resource policies
- Logging
- Monitoring
- Security policies
- Restart policies

### Environments

- Development
- Testing
- Staging
- Production

Containers must remain independently deployable.

---

## 2. Docker Compose Standards

### Environment Isolation

| Environment | File |
|-------------|------|
| Local | compose.local.yaml |
| Development | compose.development.yaml |
| Testing | compose.testing.yaml |
| Staging | compose.staging.yaml |
| Production | compose.production.yaml |

No shared production configurations permitted.

---

## 3. NGINX Standards

### Responsibilities

- Reverse proxy
- Load balancing
- SSL management
- Security headers
- Routing
- Compression
- Caching policies
- Rate limiting support

### Prohibited

- Business logic
- Authentication logic
- Database logic
- Domain logic

### Future Routing

```
platform.edubridge.com -> Next.js
api.edubridge.com -> Spring Boot
admin.edubridge.com -> Admin Portal
```

---

## 4. Networking Standards

### Required Workflow

```
Users -> Cloudflare -> NGINX -> Applications -> API Layer -> Infrastructure Services -> Monitoring
```

### Forbidden

- Users -> PostgreSQL
- Users -> Redis
- Users -> Kafka
- Users -> MinIO

Infrastructure services must remain private.

---

## 5. Security Standards

### Required Support

- SSL/TLS
- Environment isolation
- Security headers
- Private networks
- Secrets management
- Container policies
- Access controls

### Inherited Qualities

- Security
- Validation
- Observability
- Scalability
- Maintainability

---

## 6. Health Check Standards

Every container must provide:

- Availability health status
- Performance metrics
- Security validation
- Recovery restart policies

No unhealthy service should silently fail.

---

## 7. Infrastructure Directory Structure

```
infrastructure/
  docker/
    local/
    development/
    testing/
    staging/
    production/
  nginx/
    configurations/
    policies/
    security/
    documentation/
  networking/
  monitoring/
  deployment/
  security/
  scripts/
  documentation/
```

---

## 8. Container Communication Standards

### Required Workflow

```
Platform -> NGINX -> Spring Boot -> Redis -> Kafka -> PostgreSQL -> MinIO -> Monitoring
```

### Communication Requirements

- Validated
- Observable
- Secure
- Documented

---

## 9. Infrastructure Testing Standards

### Required Tests

- Container tests
- Network tests
- Security tests
- Performance tests
- Health check tests
- Integration tests
- Recovery tests

No infrastructure implementation should bypass testing requirements.
