# Environment Standards

## Status

LOCKED - Sprint 0B, Step 08

## Principle

Every environment is isolated. Configuration never leaks between environments.

## Environments

### Local Development

```
Purpose:    Developer workstation
Stack:      Docker Compose
Data:       Seed data
Access:     Localhost only
Monitoring: Optional
```

### Development

```
Purpose:    Feature development and testing
Stack:      Docker containers
Data:       Test data
Access:     Team only
Monitoring: Basic
```

### Testing

```
Purpose:    Automated test execution
Stack:      Docker containers
Data:       Test fixtures
Access:     CI/CD pipeline
Monitoring: Basic
```

### Staging

```
Purpose:    Pre-production validation
Stack:      Production mirror
Data:       Anonymized production data
Access:     Team + QA
Monitoring: Full
```

### Production

```
Purpose:    Live user traffic
Stack:      Production infrastructure
Data:       Real data
Access:     Public
Monitoring: Full + Alerting
```

## Environment Configuration

### Per-Environment Variables

```
DATABASE_URL         PostgreSQL connection string
REDIS_URL            Redis connection string
KAFKA_BROKERS        Kafka broker list
MINIO_ENDPOINT       MinIO endpoint
JWT_SECRET           Authentication secret
CORS_ORIGIN          Allowed origins
APP_URL              Application URL
API_URL              API URL
LOG_LEVEL            Logging level
```

### Configuration Isolation

```
Local:              .env.local
Development:        .env.development
Testing:            .env.testing
Staging:            .env.staging
Production:         .env.production (never committed)
```

## Environment Rules

```
1. Each environment has its own database
2. Each environment has its own Redis
3. Each environment has its own Kafka topics
4. Each environment has its own MinIO buckets
5. Production data never reaches non-production
6. Non-production credentials never reach production
7. Each environment is independently deployable
```

## Data Isolation

```
Local:          Local Docker volumes
Development:    Docker volumes
Testing:        Temporary containers
Staging:        Persistent volumes
Production:     Managed services or persistent volumes
```

## Secrets Management

```
Local:          .env file (gitignored)
Development:    Docker secrets
Testing:        Environment variables
Staging:        Docker secrets or vault
Production:     Vault or cloud secrets manager
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
```
