# Observability Standards

## Status

LOCKED - Sprint 0A, Step 06

## Rule

If it cannot be monitored, it cannot be deployed.

## Three Pillars

### 1. Logging

Structured, contextual logs.

```
Requirements:
  - Structured JSON format
  - Consistent fields across services
  - Log levels enforced
  - Sensitive data masked
  - Correlation IDs propagated
  - Centralized aggregation
```

### 2. Metrics

Quantitative measurements.

```
Requirements:
  - Application metrics (request count, latency, errors)
  - Infrastructure metrics (CPU, memory, disk)
  - Business metrics (signups, payments, matches)
  - Custom metrics per service
  - Aggregation and alerting
```

### 3. Tracing

Request flow across services.

```
Requirements:
  - Distributed tracing enabled
  - Trace ID propagated across services
  - Span creation for each operation
  - Trace sampling configured
  - Trace storage and query
```

## OpenTelemetry Integration

```
All services must export:
  - Traces -> OpenTelemetry Collector
  - Metrics -> Prometheus
  - Logs -> Loki
```

## Metric Types

```
Counter:      Requests processed, errors occurred
Gauge:        Active connections, queue size
Histogram:    Request duration, response size
Summary:      Latency percentiles (p50, p95, p99)
```

## Required Metrics

```
Application:
  - http_requests_total
  - http_request_duration_seconds
  - http_request_errors_total
  - app_info

Database:
  - db_connections_active
  - db_query_duration_seconds
  - db_queries_total

Cache:
  - cache_hits_total
  - cache_misses_total
  - cache_operation_duration_seconds

Business:
  - user_registrations_total
  - payments_processed_total
  - universities_searched_total
```

## Alerting Rules

```
Critical (Page immediately):
  - Service down
  - Error rate > 5%
  - P99 latency > 5s
  - Database connection pool exhausted

Warning (Notify team):
  - Error rate > 1%
  - P99 latency > 2s
  - Disk usage > 80%
  - Memory usage > 80%

Info (Log only):
  - Deployment completed
  - Configuration changed
  - Scaling event
```

## Dashboard Requirements

```
Every service must have:
  - Request rate dashboard
  - Error rate dashboard
  - Latency dashboard
  - Resource usage dashboard
  - Business metrics dashboard
```

## Log Retention

| Log Type | Retention |
|----------|-----------|
| Application | 30 days |
| Audit | 1 year |
| Security | 1 year |
| Performance | 30 days |

## Never

```
- Deploy without monitoring
- Skip distributed tracing
- Skip alerting configuration
- Skip dashboard creation
- Log sensitive data
- Skip metric collection
- Ignore performance regressions
```
