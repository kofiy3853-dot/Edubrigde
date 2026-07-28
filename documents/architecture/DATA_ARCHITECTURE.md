# Data Architecture

## Status

LOCKED - Sprint 0B, Step 08

## Principle

One PostgreSQL instance, multiple schemas.

## Schema Structure

```
PostgreSQL
  |
  +-- identity        -> Users, roles, permissions, sessions
  +-- academic        -> Students, courses, enrollments, grades
  +-- career          -> Assessments, matches, recommendations
  +-- financial       -> Payments, invoices, scholarships
  +-- institution     -> Universities, programs, admissions
  +-- communication   -> Notifications, templates, history
  +-- analytics       -> Metrics, reports, aggregations
  +-- ai              -> Models, predictions, training data
```

## Schema Isolation Rules

```
1. Each domain owns its schema
2. Cross-schema queries require explicit permission
3. Foreign keys never cross schema boundaries
4. Each schema has its own migrations
5. Each schema can be independently backed up
```

## Table Standards

Every table must include:

```sql
id              UUID PRIMARY KEY DEFAULT gen_random_uuid()
created_at      TIMESTAMP NOT NULL DEFAULT NOW()
updated_at      TIMESTAMP NOT NULL DEFAULT NOW()
created_by      UUID
updated_by      UUID
deleted_at      TIMESTAMP NULL
version         INTEGER NOT NULL DEFAULT 1
```

## Naming Conventions

```
Schemas:    snake_case, singular (identity, academic)
Tables:     snake_case, plural (users, courses)
Columns:    snake_case (first_name, created_at)
Indexes:    idx_{table}_{column} (idx_users_email)
Constraints: chk_{table}_{rule} (chk_users_role)
Foreign Keys: fk_{table}_{references} (fk_courses_users)
```

## Data Types

| Purpose | PostgreSQL Type |
|---------|----------------|
| UUID | UUID |
| Email | VARCHAR(255) |
| Name | VARCHAR(100) |
| Text | TEXT |
| Number | INTEGER |
| Decimal | DECIMAL(10,2) |
| Boolean | BOOLEAN |
| Date | DATE |
| Timestamp | TIMESTAMP |
| JSON | JSONB |
| Array | TEXT[] |

## Soft Deletes

All data is soft-deleted:

```sql
-- Delete
UPDATE users SET deleted_at = NOW() WHERE id = $1;

-- Query active
SELECT * FROM users WHERE deleted_at IS NULL;

-- Restore
UPDATE users SET deleted_at = NULL WHERE id = $1;
```

## Migration Standards

```
1. Every change is a migration
2. Every migration has a rollback
3. Every migration is tested
4. Migrations run in order
5. No manual production changes
```

## Scaling Strategy

```
Phase 1: Single instance, multiple schemas
Phase 2: Read replicas for analytics
Phase 3: Schema-level sharding if needed
Phase 4: Separate instances per domain if needed
```

## Never

```
- Create separate databases per domain (unless scaling requires)
- Skip schema isolation
- Hard delete records
- Skip migrations
- Allow cross-schema foreign keys
- Store files in database
- Skip indexes on foreign keys
```
