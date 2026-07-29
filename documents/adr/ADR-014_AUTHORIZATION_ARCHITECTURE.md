# ADR-014: Policy-Based RBAC Architecture

## Status

**LOCKED** - Permanent architectural decision

## Context

EduBridge OS requires a flexible authorization system that goes beyond simple role-based access control. Roles alone cannot handle complex scenarios like resource ownership, time-based access, or domain-specific policies. We need a policy engine that combines RBAC with resource-level permissions and domain policies.

## Decision

**Policy-Based RBAC Architecture (RBAC + Resource-Level Permissions + Domain Policies).** Roles determine who a user is. Permissions determine what a user can do. Policies determine when, where, and under which conditions access should be granted. No future domain is permitted to implement its own authorization mechanisms outside the Identity Domain.

### Authorization Flow

```
User
  |
Authentication
  |
Session Layer
  |
RBAC Layer (roles)
  |
Permission Validation (permissions)
  |
Policy Engine (policies)
  |
Resource Validation (ownership)
  |
Domain Validation (domain rules)
  |
Access Decision
  |
Audit Logging
  |
Monitoring
```

### Policy Types

| Type | Purpose |
|------|---------|
| ROLE_BASED | Access based on user roles |
| RESOURCE_BASED | Access based on resource ownership |
| SESSION_BASED | Access based on session state |
| TIME_BASED | Access based on time conditions |
| COMPOSITE | Combined policy rules |

### Policy Effects

| Effect | Description |
|--------|-------------|
| ALLOW | Grants access if conditions match |
| DENY | Denies access if conditions match (overrides ALLOW) |

### Policy Evaluation

1. Find all active policies for user's roles
2. Filter by resource and action
3. Sort by priority (highest first)
4. Evaluate conditions
5. First DENY wins, otherwise first ALLOW wins
6. Log the decision

### Resource Authorization

| Resource | Owner Access | Admin Access |
|----------|--------------|--------------|
| User Profile | OWN_RESOURCE | SYSTEM_RESOURCE |
| Academic Records | OWN_RESOURCE | SYSTEM_RESOURCE |
| Financial Data | OWN_RESOURCE | SYSTEM_RESOURCE |
| System Settings | N/A | SYSTEM_RESOURCE |

### Access Logging

Every authorization decision is logged:
- User ID
- Resource
- Action
- Access granted/denied
- Matched policy
- Denial reason
- IP address, user agent, request ID

## Consequences

### Benefits

1. Fine-grained access control
2. Flexible policy management
3. Resource ownership support
4. Auditable decisions
5. Domain-aware authorization
6. Easy policy changes

### Trade-offs

1. Complex policy evaluation
2. Performance overhead
3. More entities to manage
4. Policy testing complexity

## Scope

**Permanent** - This decision applies to all authorization in EduBridge OS.

## Related Decisions

- ADR-012: Identity Domain as Guardian Domain
- ADR-013: Zero-Trust Authentication Architecture
