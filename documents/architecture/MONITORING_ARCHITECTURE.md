# Monitoring Architecture

## Status

LOCKED - Sprint 0B, Step 08

## Principle

If it cannot be monitored, it cannot be deployed.

## Monitoring Stack

```
OpenTelemetry
  -> Collects traces, metrics, logs
  -> Exports to backends

Prometheus
  -> Scrapes metrics from services
  -> Stores time-series data
  -> Evaluates alert rules

Grafana
  -> Visualizes metrics
  -> Creates dashboards
  -> Manages alerts
```

## Three Pillars

### 1. Logging

```
Standard:    Structured JSON
Collection:   FluentBit -> Loki
Retention:    30 days (application), 1 year (audit)
Access:       Grafana
```

### 2. Metrics

```
Standard:    OpenTelemetry metrics
Collection:   Prometheus scraper
Retention:    30 days
Access:       Grafana
```

### 3. Tracing

```
Standard:    OpenTelemetry traces
Collection:   Jaeger or Tempo
Retention:    7 days
Access:       Grafana
```

## Required Metrics Per Service

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

Message Queue:
  - kafka_messages_produced_total
  - kafka_messages_consumed_total
  - kafka_consumer_lag

Storage:
  - minio_operations_total
  - minio_operation_duration_seconds
```

## Health Check Endpoints

Every service must expose:

```
GET /health         -> Overall health
GET /health/ready   -> Readiness probe
GET /health/live    -> Liveness probe
GET /health/startup -> Startup probe
```

## Alert Rules

```
Critical (Page):
  - Service down
  - Error rate > 5%
  - P99 latency > 5s
  - Database connection pool exhausted

Warning (Notify):
  - Error rate > 1%
  - P99 latency > 2s
  - Disk usage > 80%
  - Memory usage > 80%

Info (Log):
  - Deployment completed
  - Configuration changed
  - Scaling event
```

## Dashboard Requirements

Every service must have:

```
1. Request rate dashboard
2. Error rate dashboard
3. Latency dashboard
4. Resource usage dashboard
5. Business metrics dashboard
```

## Grafana Dashboards

```
Infrastructure:
  - Docker container metrics
  - PostgreSQL metrics
  - Redis metrics
  - Kafka metrics
  - MinIO metrics

Application:
  - Per-service metrics
  - API endpoint metrics
  - User activity metrics

Business:
  - Registration metrics
  - Search metrics
  - Payment metrics
  - Matching metrics
```

## Never

```
- Deploy without monitoring
- Skip health checks
- Skip alerting configuration
- Skip dashboard creation
- Log sensitive data
- Skip metric collection
- Expose monitoring publicly
```
