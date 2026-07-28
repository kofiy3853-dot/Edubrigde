# Audit Standards

## Status

LOCKED - Sprint 0A, Step 06

## Rule

Every critical action produces an immutable audit record.

## What Requires Audit Logging

```
Authentication Events:
  - Login success
  - Login failure
  - Logout
  - Password change
  - Password reset
  - Account lockout
  - MFA enable/disable

Authorization Events:
  - Permission denied
  - Role changed
  - Access revoked

Data Events:
  - Record created
  - Record updated
  - Record deleted
  - Bulk operations

Financial Events:
  - Payment processed
  - Refund issued
  - Invoice generated

Administrative Events:
  - User created
  - User deactivated
  - Configuration changed
  - System settings modified
```

## Audit Record Structure

```json
{
  "auditId": "uuid",
  "timestamp": "2026-01-01T00:00:00.000Z",
  "userId": "uuid",
  "userEmail": "user@example.com",
  "userRole": "ADMIN",
  "action": "USER_CREATED",
  "resource": "identity.users",
  "resourceId": "uuid",
  "status": "SUCCESS",
  "ipAddress": "192.168.1.1",
  "userAgent": "Mozilla/5.0...",
  "traceId": "abc-123",
  "metadata": {
    "previousValue": {},
    "newValue": {}
  }
}
```

## Audit Record Fields

| Field | Required | Description |
|-------|----------|-------------|
| auditId | Yes | Unique audit identifier |
| timestamp | Yes | When the action occurred |
| userId | Yes | Who performed the action |
| action | Yes | What action was performed |
| resource | Yes | What resource was affected |
| resourceId | Yes | Which specific resource |
| status | Yes | SUCCESS or FAILURE |
| ipAddress | Yes | Client IP address |
| traceId | Yes | Request trace identifier |
| metadata | No | Additional context |

## Audit Actions

```
AUTH_LOGIN              -> User login
AUTH_LOGIN_FAILED       -> Failed login attempt
AUTH_LOGOUT             -> User logout
AUTH_PASSWORD_CHANGE    -> Password changed
AUTH_PASSWORD_RESET     -> Password reset
AUTH_MFA_ENABLE         -> MFA enabled
AUTH_MFA_DISABLE        -> MFA disabled

USER_CREATED            -> User account created
USER_UPDATED            -> User profile updated
USER_DEACTIVATED        -> User account deactivated
USER_ROLE_CHANGED       -> User role modified

DATA_CREATED            -> Record created
DATA_UPDATED            -> Record deleted
DATA_DELETED            -> Record soft-deleted

PAYMENT_PROCESSED       -> Payment completed
PAYMENT_FAILED          -> Payment failed
REFUND_ISSUED           -> Refund processed

ADMIN_ACTION            -> Administrative action
CONFIG_CHANGED          -> Configuration modified
```

## Audit Storage

```
1. Audit records stored in dedicated audit schema
2. Audit records are append-only (no updates, no deletes)
3. Audit records retained for 1 year minimum
4. Audit records queryable for investigation
5. Audit records exportable for compliance
```

## Audit Query API

```
GET /api/v1/analytics/audit?userId=uuid&action=AUTH_LOGIN
GET /api/v1/analytics/audit?resource=identity.users&resourceId=uuid
GET /api/v1/analytics/audit?startDate=2026-01-01&endDate=2026-12-31
```

## Never

```
- Skip audit logging for critical actions
- Allow audit record modification
- Allow audit record deletion
- Log sensitive data in audit records
- Store audit records in same schema as business data
- Skip audit logging in development
```
