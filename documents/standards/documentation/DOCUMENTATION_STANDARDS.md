# Documentation Standards

## Status

LOCKED - Sprint 0A, Step 04

## Rule

Undocumented implementations are incomplete implementations.

## Required Documentation

### 1. Module Documentation

Every module must have a README.md containing:

```markdown
# Module Name

## Purpose
What this module does.

## Responsibilities
What this module is responsible for.

## Architecture
How this module is structured.

## Dependencies
What this module depends on.

## API Contracts
Endpoints, events, or interfaces exposed.

## Testing Strategy
How this module is tested.

## Security Requirements
Security considerations for this module.

## Examples
Usage examples.

## Future Improvements
Known limitations and planned enhancements.
```

### 2. API Documentation

Every API endpoint must document:

```
Required:
  - HTTP method
  - Path
  - Description
  - Request body schema
  - Response body schema
  - Error responses
  - Authentication required
  - Authorization required
  - Rate limits
  - Examples
```

Format: OpenAPI 3.0 specification.

### 3. Database Documentation

Every table must document:

```
Required:
  - Table purpose
  - Column descriptions
  - Relationships
  - Constraints
  - Indexes
  - Migration history
```

### 4. Architecture Documentation

System-wide documentation:

```
Required:
  - System overview
  - Domain boundaries
  - Data flow diagrams
  - Decision records (ADRs)
  - Integration patterns
```

### 5. Deployment Documentation

Deployment procedures:

```
Required:
  - Prerequisites
  - Step-by-step guide
  - Rollback procedures
  - Environment variables
  - Health checks
```

## Documentation Locations

| Type | Location |
|------|----------|
| Module README | Module root |
| API specs | documents/apis/ |
| Database docs | documents/database/ |
| Architecture | documents/architecture/ |
| Deployment | documents/deployment/ |
| Security | documents/security/ |
| Standards | documents/standards/ |

## Documentation Rules

```
1. Documentation must be kept up-to-date
2. Documentation must be reviewed in PRs
3. Documentation must be clear and concise
4. Documentation must include examples
5. Documentation must be versioned with code
```

## No Undocumented Code

Code without documentation will not be approved.
