# Environment Responsibilities

## Status

LOCKED - Sprint 0B, Step 09

## Principle

Every environment is isolated. No configuration leakage.

## Environment Definitions

### local/

```
Purpose:    Developer workstation
Stack:      Docker Compose
Data:       Seed data only
Access:     Localhost only
Monitoring: Optional (console only)
Database:   Local PostgreSQL container
Cache:      Local Redis container
```

### development/

```
Purpose:    Feature development and testing
Stack:      Docker containers
Data:       Test data (seeded)
Access:     Team only (VPN)
Monitoring: Basic (Prometheus + Grafana)
Database:   PostgreSQL container (persistent)
Cache:      Redis container (persistent)
```

### testing/

```
Purpose:    Automated test execution
Stack:      Docker containers (ephemeral)
Data:       Test fixtures
Access:     CI/CD pipeline only
Monitoring: Basic
Database:   PostgreSQL container (ephemeral)
Cache:      Redis container (ephemeral)
```

### staging/

```
Purpose:    Pre-production validation
Stack:      Production mirror
Data:       Anonymized production data
Access:     Team + QA + Stakeholders
Monitoring: Full (all tools active)
Database:   PostgreSQL container or managed service
Cache:      Redis container or managed service
```

### production/

```
Purpose:    Live user traffic
Stack:      Production infrastructure
Data:       Real data
Access:     Public (via Cloudflare)
Monitoring: Full + Alerting + Incident Management
Database:   Managed PostgreSQL or container with replication
Cache:      Managed Redis or container with replication
```

## Configuration Isolation

### Per-Environment Files

```
environments/
  local/
    docker-compose.yml
    .env
    nginx.conf
  development/
    docker-compose.yml
    .env
    nginx.conf
  testing/
    docker-compose.yml
    .env
    nginx.conf
  staging/
    docker-compose.yml
    .env
    nginx.conf
  production/
    docker-compose.yml
    .env
    nginx.conf
```

### Environment Variables

```
DATABASE_URL         Different per environment
REDIS_URL            Different per environment
KAFKA_BROKERS        Different per environment
MINIO_ENDPOINT       Different per environment
JWT_SECRET           Different per environment (never shared)
CORS_ORIGIN          Different per environment
APP_URL              Different per environment
API_URL              Different per environment
LOG_LEVEL            Different per environment
```

## Data Isolation

```
Local:          Docker volumes (local)
Development:    Docker volumes (persistent)
Testing:        Temporary containers (destroyed after test)
Staging:        Persistent volumes or managed services
Production:     Managed services with replication
```

## Secrets Management

```
Local:          .env file (gitignored)
Development:    Docker secrets or .env (gitignored)
Testing:        Environment variables (CI/CD)
Staging:        Docker secrets or vault
Production:     Vault or cloud secrets manager
```

## Deployment Isolation

```
Each environment deploys independently:
  - Separate containers
  - Separate networks
  - Separate volumes
  - Separate configurations
```

## Never

```
- Share credentials between environments
- Use production data in non-production
- Commit secrets to repository
- Skip environment isolation
- Use same database for multiple environments
- Skip environment-specific configuration
- Expose non-production environments publicly
- Deploy production from development branch
```
