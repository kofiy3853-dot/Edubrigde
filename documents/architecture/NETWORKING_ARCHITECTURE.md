# Networking Standards

## Status

LOCKED - Sprint 0B, Step 08

## Principle

All external access goes through NGINX. No direct database access.

## Network Architecture

```
Internet
  -> Cloudflare (CDN, DDoS, SSL)
    -> NGINX (reverse proxy)
      -> Platform (Next.js :3000)
      -> Admin (Next.js :3001)
      -> API Gateway (Spring Boot :4000)
        -> Identity Service
        -> Academic Service
        -> Career Service
        -> Financial Service
        -> Institution Service
        -> Communication Service
        -> Analytics Service
        -> AI Service
      -> Infrastructure (internal only)
        -> PostgreSQL :5432
        -> Redis :6379
        -> Kafka :9092
        -> MinIO :9000
```

## NGINX Configuration

### Routing Rules

```
/                     -> Platform (Next.js)
/admin                 -> Admin (Next.js)
/api/*                 -> API Gateway (Spring Boot)
/health                -> API Gateway health check
```

### Security Headers

```
Strict-Transport-Security: max-age=31536000; includeSubDomains
X-Content-Type-Options: nosniff
X-Frame-Options: DENY
X-XSS-Protection: 1; mode=block
Content-Security-Policy: default-src 'self'
Referrer-Policy: strict-origin-when-cross-origin
Permissions-Policy: camera=(), microphone=(), geolocation=()
```

### Rate Limiting

```
Global:         100 requests/second
Per IP:         50 requests/minute
API endpoints:  Configurable per route
Auth endpoints: 20 requests/15 minutes
```

### SSL/TLS

```
Development:    Self-signed certificates
Production:     Cloudflare SSL
Internal:       Mutual TLS (future)
```

## Internal Network

```
Docker Network: edubridge-internal

Services:
  - postgres:5432 (internal only)
  - redis:6379 (internal only)
  - kafka:9092 (internal only)
  - minio:9000 (internal only)
  - grafana:3000 (internal only, optional external)
  - prometheus:9090 (internal only)
```

## Access Rules

```
External -> NGINX:           Allowed
External -> PostgreSQL:      Blocked
External -> Redis:           Blocked
External -> Kafka:           Blocked
External -> MinIO:           Blocked
Internal -> PostgreSQL:      Allowed (application only)
Internal -> Redis:           Allowed (application only)
Internal -> Kafka:           Allowed (application only)
Internal -> MinIO:           Allowed (application only)
```

## CORS Configuration

```
Development:
  - Origin: http://localhost:3000
  - Methods: GET, POST, PUT, DELETE
  - Credentials: true

Production:
  - Origin: https://edubridge.com
  - Methods: GET, POST, PUT, DELETE
  - Credentials: true
```

## DNS Configuration

```
Development:    localhost
Staging:        staging.edubridge.com
Production:     edubridge.com
Admin:          admin.edubridge.com
```

## Never

```
- Expose databases publicly
- Skip SSL/TLS
- Skip rate limiting
- Skip security headers
- Allow direct database access
- Skip CORS configuration
- Expose internal services
- Skip network monitoring
```
