# ADR-012: Identity Domain as Guardian Domain

## Status

**LOCKED** - Permanent architectural decision

## Context

EduBridge OS requires a centralized identity management system for authentication, authorization, sessions, and access control. Multiple domains (Academic, Financial, Institution, etc.) need user information but should not implement their own user management.

## Decision

**The Identity Domain will become the Guardian Domain of EduBridge OS.** Every future domain will trust the Identity Domain for authentication, authorization, permissions, roles, sessions, and account security. No other domain is permitted to implement its own user management system.

### Architecture

```
Identity Domain (Guardian)
    |
+---+---+---+---+
|   |   |   |   |
Users  Roles  Permissions  Sessions  Authentication
    |
+---+---+---+---+
|   |   |   |   |
Academic  Financial  Institution  AI  (all domains trust Identity)
```

### Identity Domain Responsibilities

| Responsibility | Description |
|----------------|-------------|
| Users | User accounts, profiles, verification |
| Roles | Role definitions, system roles |
| Permissions | Permission definitions, granular access |
| Authentication | Login, JWT, refresh tokens |
| Authorization | RBAC, permission checking |
| Sessions | Session management, revocation |
| Security | Password policies, account lock |

### Prohibited Actions

No other domain may:
- Create user tables
- Implement authentication logic
- Manage roles or permissions
- Handle session management
- Implement authorization logic

### Identity Domain Structure

```
identity/
  controllers/
  services/
  repositories/
  entities/
  dto/
  validators/
  events/
  security/
  sessions/
  permissions/
  roles/
  configurations/
  tests/
```

### API Standards

```
/api/v1/identity/
  /authentication (login, logout, refresh)
  /authorization (permissions, roles)
  /users (CRUD)
  /roles (CRUD)
  /permissions (CRUD)
  /sessions (management)
```

## Consequences

### Benefits

1. Single source of truth for identity
2. Consistent authentication across all domains
3. Centralized authorization policies
4. Easier security auditing
5. Reduced code duplication
6. Clear domain boundaries

### Trade-offs

1. Single point of failure (mitigated by redundancy)
2. All domains depend on Identity
3. Identity changes affect all domains
4. Higher initial complexity

## Scope

**Permanent** - This decision applies to all domains in EduBridge OS.

## Related Decisions

- ADR-006: PostgreSQL Schema-Based Modular Database Architecture
- ADR-010: Infrastructure-as-Code and Container-First Architecture
- ADR-011: Observability-First Architecture
