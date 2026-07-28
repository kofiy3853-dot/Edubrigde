# Backup Strategy

## Status

LOCKED - Sprint 0B, Step 09

## Principle

Every backup strategy must define What, When, How, Where, and Recovery Objectives.

## Backup Categories

### Database Backups

```
What:       PostgreSQL schemas, data, configurations
When:       Daily at 02:00 UTC, before deployments
How:        pg_dump with compression
Where:      Local volume + remote storage (S3-compatible)
Retention:  30 days daily, 12 months weekly, indefinite monthly
Recovery:   RPO 24 hours, RTO 1 hour
```

### Storage Backups

```
What:       MinIO buckets and objects
When:       Daily at 03:00 UTC
How:        mc mirror to backup bucket
Where:      Secondary MinIO or S3-compatible storage
Retention:  30 days
Recovery:   RPO 24 hours, RTO 2 hours
```

### Configuration Backups

```
What:       Docker Compose files, environment configs, NGINX configs
When:       On every change (version controlled)
How:        Git repository
Where:      GitHub repository
Retention:  Indefinite (git history)
Recovery:   RPO 0, RTO 15 minutes
```

### Monitoring Backups

```
What:       Grafana dashboards, Prometheus rules, alert configurations
When:       Weekly
How:        Grafana export, Prometheus rules export
Where:      Git repository + remote storage
Retention:  Indefinite
Recovery:   RPO 7 days, RTO 30 minutes
```

## Backup Schedule

```
Daily (02:00 UTC):
  - PostgreSQL full backup
  - MinIO incremental backup

Weekly (Sunday 03:00 UTC):
  - PostgreSQL weekly backup (retained longer)
  - Grafana dashboard export
  - Configuration export

Monthly (1st 04:00 UTC):
  - PostgreSQL monthly backup (retained indefinitely)
  - Full infrastructure backup
```

## Recovery Procedures

### Database Recovery

```
1. Stop application services
2. Restore PostgreSQL from backup
3. Verify data integrity
4. Run migrations if needed
5. Restart services
6. Verify health checks
7. Resume traffic
```

### Storage Recovery

```
1. Stop application writes
2. Restore MinIO from backup
3. Verify file integrity
4. Resume application writes
5. Verify health checks
```

### Full Recovery

```
1. Provision infrastructure
2. Restore database
3. Restore storage
4. Restore configurations
5. Deploy applications
6. Verify health checks
7. Resume traffic
```

## Backup Verification

```
Weekly:
  - Restore database to test environment
  - Verify data integrity
  - Document results

Monthly:
  - Full disaster recovery drill
  - Measure RTO achievement
  - Document findings
```

## Backup Security

```
1. Backups encrypted at rest
2. Backups encrypted in transit
3. Access to backups restricted
4. Backup storage access logged
5. Backup retention policy enforced
```

## Recovery Time Objectives

```
RPO (Recovery Point Objective):
  - Database: 24 hours
  - Storage: 24 hours
  - Configuration: 0 (git)

RTO (Recovery Time Objective):
  - Database: 1 hour
  - Storage: 2 hours
  - Full system: 4 hours
```

## Never

```
- Skip backup verification
- Store backups in same location as primary
- Skip encryption for backups
- Skip backup logging
- Skip recovery testing
- Keep backups indefinitely without rotation
- Store backup credentials in repository
```
