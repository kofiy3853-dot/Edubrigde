# ADR-006: Database Architecture

## Date

2026-01-01

## Status

Accepted

## Context

EduBridge OS needs a database architecture that supports domain isolation, independent maintainability, and future scalability. A schema-based approach provides logical separation without the overhead of multiple databases.

## Decision

Adopt a Schema-Based Modular Database Architecture with PostgreSQL.

### Structure

```
PostgreSQL
  |
  +-- public (default)
  +-- identity (users, roles, permissions, sessions)
  +-- academic (students, courses, learning_progress)
  +-- career (job_listings, job_applications)
  +-- financial (payments, budgets, scholarships)
  +-- institution (universities, programs, applications)
  +-- communication (notifications, messages)
  +-- analytics (events, reports)
  +-- ai (recommendations, model_versions)
  +-- shared (audit_log, countries, currencies, configurations)
```

### Table Standards

Every table includes:
- `id` (UUID, primary key)
- `created_at` (timestamp)
- `updated_at` (timestamp)
- `deleted_at` (timestamp, nullable)
- `version` (integer, optimistic locking)

### Migration Standards

```
V1__initialize_{schema}_schema.sql
V2__create_{table}_table.sql
V3__add_{feature}.sql
```

## Rules

```
1. Schema-first design
2. UUID primary keys everywhere
3. Soft deletes for all business data
4. Optimistic locking with version column
5. Versioned migrations only
6. No hard deletes of critical data
7. No manual production changes
8. No cross-schema business logic
```

## Alternatives Considered

### Option 1: Single Schema (public)

Benefits:
- Simpler setup
- No schema management

Trade-offs:
- No domain isolation
- Table name conflicts
- Hard to maintain at scale

### Option 2: Multiple Databases

Benefits:
- Complete isolation
- Independent scaling

Trade-offs:
- Complex deployment
- Cross-database queries
- Connection pool management

### Option 3: Schema-per-Tenant (Multi-tenancy)

Benefits:
- Tenant isolation
- Data separation

Trade-offs:
- Schema proliferation
- Migration complexity
- Connection overhead

## Rationale

Schema-Based Modular Database provides:
- Domain isolation (schemas)
- Simple deployment (single database)
- Clear ownership (schema per domain)
- Future microservices migration path
- Enterprise-grade maintainability

## Consequences

### Positive

- Domain isolation
- Clear ownership
- Simple deployment
- Easy testing
- Enterprise maintainability

### Negative

- Schema management
- Cross-schema queries
- Migration coordination

### Neutral

- Requires discipline
- Requires code review
- Requires architecture governance

## Approval

| Role | Name | Date |
|------|------|------|
| Tech Lead | | 2026-01-01 |
| Database Lead | | 2026-01-01 |
| Project Owner | | 2026-01-01 |

## References

- documents/standards/architecture/TECHNOLOGY_STANDARDS.md
- documents/standards/database/DATABASE_STANDARDS.md
- documents/standards/database/MIGRATION_STANDARDS.md
