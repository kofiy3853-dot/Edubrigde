# Containerization Standards

## Status

LOCKED - Sprint 0B, Step 09

## Principle

Design for Docker now, ready for Kubernetes later.

## Containerization Strategy

```
Phase 1: Local Development
  -> Docker Compose
  -> All services local
  -> Fast iteration

Phase 2: Development Environment
  -> Docker containers
  -> Isolated environment
  -> Team collaboration

Phase 3: Production Deployment
  -> Docker Swarm or K3s
  -> Simple orchestration
  -> Production ready

Phase 4: Enterprise Deployment
  -> Kubernetes
  -> Full orchestration
  -> Auto-scaling
```

## Dockerfile Standards

### Multi-Stage Builds

```dockerfile
# Build stage
FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app
COPY . .
RUN ./gradlew bootJar

# Runtime stage
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
RUN addgroup --system appgroup && adduser --system --ingroup appgroup appuser
USER appuser
EXPOSE 8080
HEALTHCHECK --interval=30s --timeout=10s --retries=3 CMD curl -f http://localhost:8080/health || exit 1
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### Dockerfile Rules

```
1. Use multi-stage builds
2. Use official base images
3. Don't run as root
4. Use .dockerignore
5. Minimize layers
6. Use specific image tags (not latest)
7. Include health checks
8. Use non-root user
```

### .dockerignore

```
.git
.github
node_modules
build
.gradle
.idea
*.md
.env
.env.*
```

## Docker Compose Standards

### Service Definition

```yaml
services:
  service-name:
    build:
      context: .
      dockerfile: Dockerfile
    container_name: edubridge-{category}-{service}
    ports:
      - "internal:external"
    environment:
      - ENV_VAR=value
    volumes:
      - source:destination
    networks:
      - edubridge-internal
    depends_on:
      service:
        condition: service_healthy
    healthcheck:
      test: ["CMD", "health-check"]
      interval: 30s
      timeout: 10s
      retries: 3
    restart: unless-stopped
```

### Network Definition

```yaml
networks:
  edubridge-internal:
    driver: bridge
    ipam:
      config:
        - subnet: 172.20.0.0/16
```

### Volume Definition

```yaml
volumes:
  postgresql-data:
    driver: local
  redis-data:
    driver: local
  minio-data:
    driver: local
```

## Image Naming Convention

```
Registry/organization/image:tag

Examples:
  ghcr.io/edubridge/platform:1.0.0
  ghcr.io/edubridge/api-gateway:1.0.0
  ghcr.io/edubridge/identity:1.0.0
```

## Container Logging

```
Standard:    JSON structured logging
Destination: stdout/stderr
Collection:  Docker logs -> FluentBit -> Loki
Retention:   30 days
```

## Container Monitoring

```
Metrics:     cAdvisor -> Prometheus
Health:      Built-in health checks
Tracing:     OpenTelemetry agent
Logging:     Structured JSON to stdout
```

## Resource Guidelines

### Development

```
No strict limits
Monitor usage only
```

### Production

```
API Services:
  CPU: 0.5-2 cores
  Memory: 512MB-2GB

Database:
  CPU: 2-4 cores
  Memory: 2-8GB

Cache:
  CPU: 0.5-1 core
  Memory: 256MB-2GB

Monitoring:
  CPU: 1-2 cores
  Memory: 1-4GB
```

## Never

```
- Use latest tag in production
- Run containers as root
- Skip health checks
- Skip .dockerignore
- Use single-stage builds in production
- Skip resource limits in production
- Store secrets in images
- Skip container logging
```
