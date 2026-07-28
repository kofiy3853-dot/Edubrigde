# Networking Responsibilities

## Status

LOCKED - Sprint 0B, Step 09

## Principle

All external access goes through NGINX. No direct infrastructure access.

## Network Architecture

```
Internet
  -> Cloudflare (CDN, DDoS, SSL termination)
    -> NGINX (reverse proxy, load balancer)
      -> Applications
        -> Infrastructure (internal network)
```

## Internal Network

```
Network: edubridge-internal
Type: bridge
Driver: bridge

Subnets:
  - 172.20.0.0/16 (application network)
  - 172.21.0.0/16 (infrastructure network)
```

## NGINX Configuration

### Routing Rules

```
/                     -> platform:3000
/admin                 -> admin:3001
/api/v1/*              -> api-gateway:4000
/health                -> api-gateway:4000/health
```

### Security Headers

```
Strict-Transport-Security: max-age=31536000; includeSubDomains; preload
X-Content-Type-Options: nosniff
X-Frame-Options: DENY
X-XSS-Protection: 1; mode=block
Content-Security-Policy: default-src 'self'; script-src 'self'; style-src 'self' 'unsafe-inline'
Referrer-Policy: strict-origin-when-cross-origin
Permissions-Policy: camera=(), microphone=(), geolocation=()
X-Permitted-Cross-Domain-Policies: none
```

### Rate Limiting

```
Zone:           api_limit
Size:           10m
Rate:           10r/s

Auth endpoints: 5r/m per IP
API endpoints:  50r/m per IP
Global:         100r/s total
```

### SSL/TLS

```
Development:    Self-signed certificates
Staging:        Let's Encrypt
Production:     Cloudflare SSL + Origin certificates
Internal:       Mutual TLS (future)
```

## Service Routing

### External Access

```
Users -> NGINX -> Platform (3000)
Users -> NGINX -> Admin (3001)
Users -> NGINX -> API Gateway (4000)
```

### Internal Access

```
API Gateway -> Identity Service (4001)
API Gateway -> Academic Service (4002)
API Gateway -> Career Service (4003)
API Gateway -> Financial Service (4004)
API Gateway -> Institution Service (4005)
API Gateway -> Communication Service (4006)
API Gateway -> Analytics Service (4007)
API Gateway -> AI Service (4008)
```

### Infrastructure Access

```
Application -> PostgreSQL (5432)
Application -> Redis (6379)
Application -> Kafka (9092)
Application -> MinIO (9000)
```

## Firewall Rules

```
External:
  - Allow: 80, 443 (HTTP, HTTPS)
  - Block: All other ports

Internal:
  - Allow: All internal communication
  - Block: External access to infrastructure ports
```

## DNS Configuration

```
Development:    localhost
Staging:        staging.edubridge.com
Production:     edubridge.com
Admin:          admin.edubridge.com
API:            api.edubridge.com
```

## Never

```
- Expose databases externally
- Skip SSL/TLS
- Skip rate limiting
- Skip security headers
- Allow direct infrastructure access
- Skip CORS configuration
- Use default NGINX configuration
- Skip network monitoring
```
