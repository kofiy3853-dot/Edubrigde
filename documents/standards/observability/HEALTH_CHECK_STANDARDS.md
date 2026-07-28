# Health Check Standards

## Status

LOCKED - Sprint 0A, Step 06

## Rule

Every service must expose health checks. No service deploys without them.

## Health Check Endpoints

```
GET /health         -> Application health
GET /health/ready   -> Readiness probe
GET /health/live    -> Liveness probe
GET /health/startup -> Startup probe
```

## Health Check Response

### Healthy

```json
{
  "status": "healthy",
  "timestamp": "2026-01-01T00:00:00Z",
  "version": "1.0.0",
  "uptime": 86400,
  "checks": {
    "application": { "status": "healthy" },
    "database": { "status": "healthy", "latency": 5 },
    "cache": { "status": "healthy", "latency": 1 },
    "storage": { "status": "healthy", "latency": 10 }
  }
}
```

### Unhealthy

```json
{
  "status": "unhealthy",
  "timestamp": "2026-01-01T00:00:00Z",
  "version": "1.0.0",
  "checks": {
    "application": { "status": "healthy" },
    "database": { "status": "unhealthy", "error": "Connection refused" },
    "cache": { "status": "healthy", "latency": 1 },
    "storage": { "status": "healthy", "latency": 10 }
  }
}
```

## Required Health Checks

### Application Check

```
- Service is running
- Configuration is loaded
- Dependencies are initialized
```

### Database Check

```
- Connection pool is active
- Can execute query
- Response time acceptable
```

### Cache Check

```
- Connection is active
- Can read/write
- Response time acceptable
```

### Storage Check

```
- Connection is active
- Can read/write files
- Response time acceptable
```

### External Service Check

```
- Third-party APIs reachable
- Response time acceptable
- No circuit breaker open
```

## Health Check Rules

```
1. Health checks must complete in < 5 seconds
2. Health checks must not modify data
3. Health checks must be lightweight
4. Health checks must handle timeouts
5. Health checks must report latency
6. Health checks must report version
```

## Kubernetes Probes

```yaml
livenessProbe:
  httpGet:
    path: /health/live
    port: 8080
  initialDelaySeconds: 30
  periodSeconds: 10

readinessProbe:
  httpGet:
    path: /health/ready
    port: 8080
  initialDelaySeconds: 5
  periodSeconds: 5

startupProbe:
  httpGet:
    path: /health/startup
    port: 8080
  failureThreshold: 30
  periodSeconds: 10
```

## Monitoring Integration

```
1. Health check failures trigger alerts
2. Health check latency tracked
3. Health check history maintained
4. Health check dashboard available
```

## Never

```
- Skip health checks
- Make health checks modify data
- Make health checks slow
- Skip health checks in development
- Ignore health check failures
- Skip health check documentation
```
