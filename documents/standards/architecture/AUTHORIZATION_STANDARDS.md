# Authorization Standards

## LOCKED STANDARDS

These standards are permanently locked and cannot be modified.

---

## 1. Policy-Based RBAC Architecture

Roles determine who a user is. Permissions determine what a user can do. Policies determine when, where, and under which conditions access should be granted.

No future domain may implement its own authorization mechanisms.

---

## 2. RBAC Standards

### Role Hierarchy

```
ROLE_STUDENT
  |
ROLE_PARENT
  |
ROLE_MENTOR
  |
ROLE_CONSULTANT
  |
ROLE_SUPPORT
  |
ROLE_ADMIN
  |
ROLE_SUPER_ADMIN
```

### RBAC Responsibilities

- Role assignment
- Role validation
- Permission resolution
- Access policies
- Session validation
- Audit support

---

## 3. Permission Standards

### Structure

```
RESOURCE_ACTION_SCOPE
```

### Examples

| Permission | Description |
|------------|-------------|
| USER_VIEW | View user profiles |
| USER_UPDATE | Update user profiles |
| PROFILE_VIEW | View own profile |
| PROFILE_UPDATE | Update own profile |
| SESSION_MANAGE | Manage sessions |
| REPORT_DOWNLOAD | Download reports |
| SYSTEM_CONFIGURE | Configure system |

### Rules

- Permissions are globally unique
- Granular and reusable
- Assigned through roles

---

## 4. Policy Standards

### Policy Types

| Type | Purpose |
|------|---------|
| ROLE_BASED | Access based on roles |
| RESOURCE_BASED | Access based on ownership |
| SESSION_BASED | Access based on session |
| TIME_BASED | Access based on time |
| COMPOSITE | Combined rules |

### Policy Effects

| Effect | Description |
|--------|-------------|
| ALLOW | Grants access |
| DENY | Denies access (overrides ALLOW) |

### Policy Evaluation

1. Find policies for user's roles
2. Filter by resource and action
3. Sort by priority
4. Evaluate conditions
5. First DENY wins
6. Otherwise first ALLOW wins

---

## 5. Resource Authorization Standards

### Resource Types

| Resource | Owner Access | Admin Access |
|----------|--------------|--------------|
| User Profile | OWN_RESOURCE | SYSTEM_RESOURCE |
| Academic Records | OWN_RESOURCE | SYSTEM_RESOURCE |
| Financial Data | OWN_RESOURCE | SYSTEM_RESOURCE |
| System Settings | N/A | SYSTEM_RESOURCE |

### Rules

- Users can access their own resources
- Admins can access system resources
- Resource ownership is validated
- Cross-resource access requires explicit policy

---

## 6. API Authorization Standards

### Workflow

```
API Request
  |
JWT Validation
  |
Session Validation
  |
Permission Validation
  |
Policy Validation
  |
Resource Validation
  |
Access Decision
  |
API Response
```

### Rules

- Authentication never guarantees authorization
- Every request must be authorized
- Authorization decisions are logged
- No bypass mechanisms

---

## 7. Access Logging Standards

### Logged Data

- User ID
- Resource
- Action
- Access granted/denied
- Matched policy
- Denial reason
- IP address
- User agent
- Request ID
- Timestamp

### Rules

- Every authorization decision is logged
- Logs are immutable
- Logs are retained for audit
- Logs are monitored

---

## 8. Security Standards

### Required

- RBAC
- Policy enforcement
- Session validation
- Resource validation
- Rate limiting
- Audit logging
- Security monitoring
- Authorization monitoring

### Prohibited

- Role-only authorization
- Hardcoded permissions
- Authorization bypass
- Cross-domain authorization
- Resource access without validation
- Undocumented policies
- Implicit admin privileges

---

## 9. Authorization API Standards

### Endpoints

```
GET  /api/v1/identity/authorization/check-permission?permission=...
GET  /api/v1/identity/authorization/check-role?role=...
GET  /api/v1/identity/authorization/permissions
GET  /api/v1/identity/authorization/roles
POST /api/v1/identity/authorization/evaluate?resource=...&action=...
```

---

## 10. Authorization Directory Structure

```
identity/
  entities/
    Policy.java
    AccessLog.java
  repositories/
    PolicyRepository.java
    AccessLogRepository.java
  services/
    PolicyEngine.java
    AuthorizationContext.java
  controllers/
    AuthorizationController.java
```
