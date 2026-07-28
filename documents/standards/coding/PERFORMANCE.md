# Performance Standards

## Status

LOCKED - Sprint 0A, Step 04

## Rule

No implementation may knowingly introduce performance regressions.

## Frontend Performance

### Core Web Vitals

| Metric | Target |
|--------|--------|
| LCP (Largest Contentful Paint) | < 2.5s |
| FID (First Input Delay) | < 100ms |
| CLS (Cumulative Layout Shift) | < 0.1 |
| TTFB (Time to First Byte) | < 200ms |
| FCP (First Contentful Paint) | < 1.8s |

### Requirements

```
Loading:
  - Code splitting per route
  - Lazy loading for off-screen content
  - Image optimization (WebP, srcset)
  - Font optimization (preload, swap)
  - Critical CSS inlining

Caching:
  - Static assets: 1 year cache
  - API responses: appropriate cache headers
  - Service worker for offline support

Bundle:
  - Initial bundle < 200KB gzipped
  - Per-route chunks < 50KB gzipped
  - Tree shaking enabled
  - Dead code elimination
```

## Backend Performance

### Response Time Targets

| Operation | Target |
|-----------|--------|
| Simple GET | < 100ms |
| Complex query | < 500ms |
| Write operation | < 200ms |
| Search operation | < 300ms |

### Requirements

```
Database:
  - Proper indexing on frequently queried columns
  - Connection pooling
  - Query optimization
  - N+1 query prevention
  - Pagination for list endpoints

Caching:
  - Redis for frequently accessed data
  - Cache invalidation strategy
  - TTL for cached items

API:
  - Response compression (gzip/brotli)
  - Pagination for large datasets
  - Field selection (sparse fieldsets)
  - Rate limiting
```

## Monitoring

```
Required metrics:
  - Response time (p50, p95, p99)
  - Error rate
  - Throughput (requests/second)
  - Database query time
  - Cache hit rate
  - Memory usage
  - CPU usage
```

## Performance Testing

```
Required:
  - Load testing before production
  - Stress testing for critical paths
  - Baseline metrics established
  - Regression detection in CI/CD
```

## Performance Review

Every PR must confirm:
- No new slow queries
- No bundle size regression
- No memory leaks
- Cache strategy appropriate
