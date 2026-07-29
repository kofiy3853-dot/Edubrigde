# Database Architecture Standards

## Status

LOCKED - Sprint 0C, Step 15

## Principle

Schema-first. Domain isolation. No coupling.

## Schema Architecture

```
PostgreSQL
  |
  +-- identity     (users, roles, permissions, sessions)
  +-- academic     (students, courses, learning_progress)
  +-- career       (job_listings, job_applications)
  +-- financial    (payments, budgets, scholarships)
  +-- institution  (universities, programs, applications)
  +-- communication (notifications, messages)
  +-- analytics    (events, reports)
  +-- ai           (recommendations, model_versions)
  +-- shared       (audit_log, countries, currencies, configurations)
```

## Table Standards

Every table includes:

```sql
id          UUID PRIMARY KEY DEFAULT gen_random_uuid()
created_at  TIMESTAMP WITH TIME ZONE DEFAULT NOW()
updated_at  TIMESTAMP WITH TIME ZONE DEFAULT NOW()
deleted_at  TIMESTAMP WITH TIME ZONE
version     INTEGER DEFAULT 1
```

## Migration Standards

```
V1__initialize_{schema}_schema.sql
V2__create_{table}_table.sql
V3__add_{feature}.sql
```

Rules:
- Versioned migrations only
- No unversioned changes
- Rollback strategies required
- Documentation required
- Testing required

## Soft Delete Standards

```
deleted_at = NULL     -> Active record
deleted_at = timestamp -> Deleted record
```

Rules:
- No hard deletes of business data
- No removing audit records
- No manual production changes

## Constraint Standards

Every table supports:
- NOT NULL constraints
- UNIQUE constraints
- CHECK constraints
- FOREIGN KEY constraints
- VERSION constraints
- VALIDATION constraints

## Indexing Standards

Required indexes:
- Primary keys (automatic)
- Foreign keys
- Frequently queried columns
- Search fields
- Analytics fields
- Audit fields

Indexes must be justified and documented.

## Seed Standards

Permanent seed categories:
- Roles
- Permissions
- Countries
- Currencies
- Configurations
- Reference Data

NOT:
- Production data
- User data
- Sensitive information

## Testing Standards

Required testing:
- Schema tests
- Migration tests
- Constraint tests
- Index tests
- Performance tests
- Rollback tests
- Integration tests

## Never

```
- Domains sharing tables
- Cross-schema business logic
- Manual production migrations
- Hard deleting critical data
- Unversioned database changes
- Production seed data
```
