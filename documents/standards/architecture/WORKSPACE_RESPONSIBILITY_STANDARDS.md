# Workspace Responsibility Standards

## Status

LOCKED - Sprint 0C, Step 11

## Principle

Each zone has a clear responsibility. Responsibilities never overlap.

## Zones

### Workspace

```
RESPONSIBILITY:    Developer Experience
OWNERS:            All developers
includes:
  - IDE configuration
  - Code formatting
  - Linting rules
  - Git hooks
  - Build scripts
  - Documentation
```

### Applications

```
RESPONSIBILITY:    User Experience
OWNERS:            Frontend developers
includes:
  - UI components
  - Routing
  - Client state
  - User interaction
  - Accessibility
  - Performance
```

### Infrastructure

```
RESPONSIBILITY:    Production Experience
OWNERS:            DevOps / Platform engineers
includes:
  - Containerization
  - Networking
  - Monitoring
  - Deployment
  - Security
  - Scaling
```

### Tests

```
RESPONSIBILITY:    Quality Assurance
OWNERS:            QA / All developers
includes:
  - Unit tests
  - Integration tests
  - E2E tests
  - Performance tests
  - Security tests
  - Test utilities
```

## Boundaries

```
Workspace     -> Does NOT deploy to production
Applications  -> Does NOT manage infrastructure
Infrastructure-> Does NOT implement business logic
Tests         -> Does NOT contain production code
```

## Cross-Zone Communication

```
Applications  -> Use packages/ for shared types
Backend       -> Use packages/ for shared contracts
Infrastructure-> Use configurations/ for environment config
Tests         -> Use packages/ for shared test utilities
```

## Never

```
- Mix workspace and application responsibilities
- Put infrastructure code in applications
- Put business logic in infrastructure
- Put production code in tests
- Skip responsibility boundaries
```
