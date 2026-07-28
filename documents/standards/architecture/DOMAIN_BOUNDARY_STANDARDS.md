# Domain Boundary Standards

## Status

LOCKED - Sprint 0A, Step 05

## Rule

Each domain is independent. Boundaries are enforced at the database, code, and deployment levels.

## Domain Definitions

```
identity     -> Authentication, authorization, users, roles, profiles
academic     -> Students, courses, enrollments, grades, transcripts
career       -> Career assessments, matching algorithms, recommendations
financial    -> Payments, invoices, scholarships, refunds
institution  -> Universities, programs, admissions, rankings
communication -> Notifications, emails, messages, templates
analytics    -> Reports, metrics, dashboards, aggregations
ai           -> ML models, predictions, recommendations, training
```

## Boundary Rules

### Database Isolation

Each domain owns its database schema:

```
identity.users        -> Only identity domain accesses
academic.students     -> Only academic domain accesses
institution.universities -> Only institution domain accesses
```

### Code Isolation

Each domain is a separate module:

```
backend/
  identity/     -> Self-contained module
  academic/     -> Self-contained module
  institution/  -> Self-contained module
```

No domain imports from another domain directly.

### Deployment Isolation

Each domain can be deployed independently:

```
1. Deploy identity changes without academic changes
2. Deploy institution changes without financial changes
3. Each domain has its own migration cycle
```

## Cross-Domain Communication

### Via API Contracts

Domain A calls Domain B's API:

```
academic service -> identity API -> get user info
financial service -> institution API -> get university info
```

### Via Events

Domain A publishes event, Domain B subscribes:

```
identity publishes: UserRegistered
communication subscribes: sends welcome email
academic subscribes: creates student profile
```

### Via Shared Contracts

Shared types in packages/:

```
packages/types/
  user.ts        -> User type shared across domains
  university.ts  -> University type shared across domains
```

## What is Forbidden

```
NO Direct database access across domains
  -> academic cannot SELECT FROM identity.users

NO Shared business logic
  -> payment logic cannot live in academic module

NO Cross-domain repositories
  -> no shared repository classes

NO Duplicated implementations
  -> same logic cannot exist in two domains
```

## Domain Events

Every significant business event must be published:

```
UserRegistered        -> identity publishes
CourseEnrolled        -> academic publishes
PaymentCompleted      -> financial publishes
UniversityAdded       -> institution publishes
NotificationSent      -> communication publishes
```

Event naming convention:

```
{Noun}{PastTenseVerb}

Examples:
  UserRegistered
  CourseCompleted
  PaymentProcessed
  UniversityMatched
```

## Shared Resources

Shared resources live in packages/:

```
packages/
  types/          -> Shared type definitions
  validators/     -> Shared validation schemas
  constants/      -> Shared constants
  utils/          -> Shared utilities
  config/         -> Shared configuration
```

Domains import from packages, not from each other.

## Dependency Direction

```
packages/ (shared)
    ^
    |
domain A  domain B  domain C
    |         |         |
    v         v         v
  database  database  database
```

Domains depend on shared packages. Domains do not depend on each other.

## Never

```
- Access another domain's database
- Import from another domain's module
- Share business logic between domains
- Create cross-domain repositories
- Duplicate implementations across domains
- Skip domain boundaries for convenience
```
