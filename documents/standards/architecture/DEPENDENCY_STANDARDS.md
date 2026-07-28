# Dependency Standards

## Status

LOCKED - Sprint 0B, Step 10

## Principle

Dependencies flow downward only. No circular dependencies.

## Dependency Rules

### Applications (apps/)

```
MAY USE:
  - Shared packages (packages/*)
  - Backend APIs (via HTTP)
  - Infrastructure services (via config)

MUST NOT USE:
  - Domain internals (backend/*)
  - Direct database access
  - Direct cache access
```

### Backend Domains (backend/*)

```
MAY USE:
  - Shared contracts (packages/types)
  - Shared validators (packages/validators)
  - Shared constants (packages/constants)
  - Shared utilities (packages/utils)

MUST NOT USE:
  - Other domain internals
  - Application code (apps/*)
  - Infrastructure code
```

### Shared Packages (packages/*)

```
MAY USE:
  - External npm packages
  - External Maven dependencies

MUST NOT USE:
  - Backend domain code
  - Application code
  - Infrastructure code
```

## Dependency Direction

```
apps/
  -> packages/ (shared)
  -> backend/ (API calls)

backend/
  -> packages/ (shared contracts)
  -> infrastructure/ (config)

packages/
  -> external dependencies only

infrastructure/
  -> external dependencies only
```

## Forbidden Patterns

```
NO Circular Dependencies:
  A -> B -> C -> A (FORBIDDEN)

NO Cross-Domain Dependencies:
  identity/ -> academic/ (FORBIDDEN)

NO Upward Dependencies:
  packages/ -> backend/ (FORBIDDEN)

NO Lateral Dependencies:
  identity/ -> financial/ (FORBIDDEN)
```

## Shared Packages

### packages/types/

```
Purpose:    Shared type definitions
Contents:   DTOs, interfaces, enums
Used by:    All domains
Rules:      No business logic, no dependencies
```

### packages/validators/

```
Purpose:    Shared validation schemas
Contents:   Zod schemas, validation rules
Used by:    Frontend and backend
Rules:      No business logic, no side effects
```

### packages/constants/

```
Purpose:    Shared constants
Contents:   Enums, config values, magic numbers
Used by:    All modules
Rules:      No business logic, no state
```

### packages/utils/

```
Purpose:    Shared utility functions
Contents:   Pure functions, helpers
Used by:    All modules
Rules:      No side effects, no state
```

### packages/config/

```
Purpose:    Shared configuration
Contents:   Environment schemas, config types
Used by:    All modules
Rules:      No secrets, no business logic
```

## Version Management

```
All packages use semantic versioning:
  MAJOR.MINOR.PATCH

Breaking changes -> MAJOR bump
New features -> MINOR bump
Bug fixes -> PATCH bump
```

## Never

```
- Create circular dependencies
- Import across domain boundaries
- Import upward from packages
- Use wildcard imports
- Skip dependency review
- Use deprecated dependencies
- Skip security audit
```
