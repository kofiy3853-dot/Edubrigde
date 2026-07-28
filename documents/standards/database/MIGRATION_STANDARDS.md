# Migration Standards

## Status

LOCKED - Sprint 0A, Step 05

## Rule

Every database change is a migration. Manual production changes are prohibited.

## Migration File Naming

```
{timestamp}_{description}.sql

Examples:
20260101_000001_create_users_table.sql
20260101_000002_create_universities_table.sql
20260102_000001_add_email_index.sql
```

## Migration File Structure

```sql
-- Migration: Create users table
-- Author: Developer Name
-- Date: 2026-01-01
-- Description: Creates the identity.users table

BEGIN;

-- Up Migration
CREATE TABLE identity.users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'STUDENT',
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
    deleted_at TIMESTAMP NULL,
    version INTEGER NOT NULL DEFAULT 1
);

CREATE INDEX idx_users_email ON identity.users(email);
CREATE INDEX idx_users_role ON identity.users(role);

COMMIT;
```

## Rollback File Structure

```sql
-- Rollback: Create users table
-- Author: Developer Name
-- Date: 2026-01-01

BEGIN;

DROP INDEX IF EXISTS identity.idx_users_role;
DROP INDEX IF EXISTS identity.idx_users_email;
DROP TABLE IF EXISTS identity.users;

COMMIT;
```

## Migration Rules

```
1. Every migration must have a rollback
2. Every migration must be idempotent
3. Every migration must be tested
4. Every migration must be documented
5. Migrations run in order of timestamp
6. No manual production changes
```

## Rollback Strategy

Every migration must include:

```
1. Forward migration (up)
2. Rollback migration (down)
3. Validation queries
4. Test cases
```

## Migration Testing

```
1. Run migration on test database
2. Verify schema matches expected
3. Run rollback
4. Verify schema restored
5. Run migration again
6. Verify data integrity
```

## Migration Documentation

Each migration must document:

```
1. Purpose of the change
2. Tables affected
3. Columns added/modified/removed
4. Indexes added/removed
5. Constraints added/removed
6. Data migration required
7. Rollback steps
```

## Deployment Process

```
1. Migration reviewed in PR
2. Migration tested in staging
3. Migration approved
4. Migration applied in production
5. Migration verified in production
```

## Never

```
- Modify production database manually
- Skip rollback files
- Skip testing
- Skip documentation
- Use DROP TABLE without backup
- Rename columns directly (create new, migrate, drop old)
- Change column types without migration
- Skip code review for migrations
```
