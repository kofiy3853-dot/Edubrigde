# Directory Protection Standards

## Status

LOCKED - Sprint 0C, Step 11

## Principle

Each directory has a single responsibility. Violations are caught at code review.

## Rules

### apps/

```
RESPONSIBILITY:    User Experience
PROTECTION:        NO business logic duplication
ALLOWED:           UI components, routing, client state, user interaction
```

### backend/

```
RESPONSIBILITY:    Business Logic
PROTECTION:        NO cross-domain coupling
ALLOWED:           Domain services, API endpoints, data access within domain
```

### packages/

```
RESPONSIBILITY:    Shared Code
PROTECTION:        NO application-specific code
ALLOWED:           Types, validators, constants, utilities, shared config
```

### database/

```
RESPONSIBILITY:    Data Schema
PROTECTION:        NO manual production changes
ALLOWED:           Migrations, seeds, schemas, indexes, constraints
```

### infrastructure/

```
RESPONSIBILITY:    Production Environment
PROTECTION:        NO business logic
ALLOWED:           Docker, networking, monitoring, deployment, security
```

### tests/

```
RESPONSIBILITY:    Quality Assurance
PROTECTION:        NO production implementations
ALLOWED:           Test files, test utilities, test data, fixtures
```

## Enforcement

```
1. Code review checks directory placement
2. Linting rules enforce import boundaries
3. CI validates directory structure
4. Architecture review catches violations
```

## Never

```
- Put business logic in apps/
- Put UI code in backend/
- Put application code in packages/
- Manually edit production database
- Put business logic in infrastructure/
- Put production code in tests/
```
