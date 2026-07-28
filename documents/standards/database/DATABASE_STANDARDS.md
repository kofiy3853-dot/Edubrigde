# Database Design Standards

## Status

LOCKED - Sprint 0A, Step 05

## Rule

Every database is schema-first. Design before implementation.

## Schema Structure

Database schemas map to domain boundaries:

```
schema: identity    -> Users, roles, permissions
schema: academic    -> Students, courses, grades
schema: career      -> Assessments, matches
schema: financial   -> Payments, scholarships
schema: institution -> Universities, programs
schema: communication -> Notifications, templates
schema: analytics   -> Metrics, reports
schema: ai          -> Models, predictions
```

## Required Table Columns

Every table must include these columns:

```sql
id              UUID PRIMARY KEY DEFAULT gen_random_uuid()
created_at      TIMESTAMP NOT NULL DEFAULT NOW()
updated_at      TIMESTAMP NOT NULL DEFAULT NOW()
created_by      UUID REFERENCES identity.users(id)
updated_by      UUID REFERENCES identity.users(id)
deleted_at      TIMESTAMP NULL
version         INTEGER NOT NULL DEFAULT 1
```

## Naming Conventions

```
Tables:       snake_case, plural (users, universities, student_profiles)
Columns:      snake_case (first_name, created_at)
Indexes:      idx_{table}_{column} (idx_users_email)
Constraints:  chk_{table}_{rule} (chk_users_email_format)
Foreign Keys: fk_{table}_{references} (fk_student_profiles_users)
```

## Data Types

| Purpose | Type |
|---------|------|
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

## Constraints

Every table must define:

```
1. Primary key (id)
2. NOT NULL on required fields
3. UNIQUE on natural keys (email)
4. CHECK on business rules
5. Foreign keys for relationships
6. Default values for optional fields
```

Example:

```sql
CREATE TABLE identity.users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'STUDENT'
        CHECK (role IN ('STUDENT', 'ADMIN')),
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
    created_by UUID,
    updated_by UUID,
    deleted_at TIMESTAMP NULL,
    version INTEGER NOT NULL DEFAULT 1
);
```

## Indexes

Required indexes:

```
1. Primary key (automatic)
2. Unique constraints (automatic)
3. Foreign keys
4. Frequently queried columns
5. Search columns (full-text)
6. Sort columns
7. Composite indexes for common queries
```

Index naming:

```sql
CREATE INDEX idx_users_email ON identity.users(email);
CREATE INDEX idx_users_role ON identity.users(role);
CREATE INDEX idx_universities_country ON institution.universities(country);
```

## Relationships

Define relationships explicitly:

```
1:1   -> Foreign key with UNIQUE constraint
1:N   -> Foreign key on child table
M:N   -> Junction table with foreign keys
```

Example (1:N):

```sql
CREATE TABLE academic.student_profiles (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL UNIQUE
        REFERENCES identity.users(id),
    academic_level VARCHAR(50),
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);
```

## Soft Deletes

All data is soft-deleted, never hard-deleted:

```sql
-- Soft delete
UPDATE users SET deleted_at = NOW() WHERE id = $1;

-- Query active records
SELECT * FROM users WHERE deleted_at IS NULL;

-- Restore
UPDATE users SET deleted_at = NULL WHERE id = $1;
```

## Audit Trails

Track all data changes:

```
1. created_by -> Who created the record
2. updated_by -> Who last modified the record
3. created_at -> When created
4. updated_at -> When last modified
5. version -> Optimistic locking
```

## Transactions

Use transactions for multi-step operations:

```
1. Start transaction
2. Perform operations
3. Validate state
4. Commit or rollback
5. Always handle errors
```

## Never

```
- Hard delete records
- Skip constraints
- Skip indexes on foreign keys
- Use reserved keywords as names
- Store sensitive data unencrypted
- Skip migrations
- Modify production database manually
- Use SELECT * in production code
```
