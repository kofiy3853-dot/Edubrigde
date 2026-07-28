# Container Responsibilities

## Status

LOCKED - Sprint 0B, Step 09

## Principle

Each container category has a single, well-defined responsibility.

## Container Categories

### applications/

```
Purpose:    User-facing and API services
Services:
  - platform     (Next.js :3000)
  - admin        (Next.js :3001)
  - api-gateway  (Spring Boot :4000)
  - identity     (Spring Boot :4001)
  - academic     (Spring Boot :4002)
  - career       (Spring Boot :4003)
  - financial    (Spring Boot :4004)
  - institution  (Spring Boot :4005)
  - communication (Spring Boot :4006)
  - analytics    (Spring Boot :4007)
  - ai           (Spring Boot :4008)

Rules:
  - Each service has its own Dockerfile
  - Each service is independently deployable
  - Services communicate via API or events
```

### databases/

```
Purpose:    Persistent data storage
Services:
  - postgresql   (PostgreSQL 16 :5432)
  - redis        (Redis 7 :6379)

Rules:
  - Databases are internal only
  - No external access
  - Persistent volumes required
  - Backup strategy required
```

### messaging/

```
Purpose:    Asynchronous event communication
Services:
  - kafka        (Apache Kafka :9092)
  - zookeeper    (Zookeeper :2181)

Rules:
  - Kafka is internal only
  - Topics organized by domain
  - Events are immutable
  - Schema registry for event evolution
```

### storage/

```
Purpose:    Object storage for files
Services:
  - minio        (MinIO :9000, console :9001)

Rules:
  - MinIO is internal only
  - Files accessed via presigned URLs
  - Metadata stored in PostgreSQL
  - Buckets organized by domain
```

### monitoring/

```
Purpose:    Observability and alerting
Services:
  - grafana      (Grafana :3000)
  - prometheus   (Prometheus :9090)
  - loki         (Loki :3100)
  - tempo        (Tempo :3200)

Rules:
  - Monitoring services may be externally accessible (dev only)
  - Production monitoring via VPN only
  - Dashboards required for all services
  - Alerts required for all critical metrics
```

## Container Naming Convention

```
edubridge-{category}-{service}

Examples:
  edubridge-app-platform
  edubridge-app-api-gateway
  edubridge-db-postgresql
  edubridge-db-redis
  edubridge-mq-kafka
  edubridge-storage-minio
  edubridge-monitor-grafana
```

## Volume Mounts

```
Databases:
  - postgresql-data -> /var/lib/postgresql/data
  - redis-data -> /data

Storage:
  - minio-data -> /data

Monitoring:
  - prometheus-data -> /prometheus
  - grafana-data -> /grafana
```

## Health Checks

Every container must define:

```yaml
healthcheck:
  test: ["CMD", "health-check-script"]
  interval: 30s
  timeout: 10s
  retries: 3
  start_period: 30s
```

## Resource Limits

```
Development:
  - No strict limits
  - Monitor usage

Production:
  - CPU limits defined
  - Memory limits defined
  - Disk limits defined
```

## Never

```
- Mix application and database containers
- Allow external database access
- Skip health checks
- Skip resource limits in production
- Use latest tag in production
- Skip container logging
```
