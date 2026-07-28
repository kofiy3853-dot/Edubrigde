# Incident Standards

## Status

LOCKED - Sprint 0A, Step 06

## Rule

Every production incident follows a structured process.

## Severity Classification

### Critical (P1)

```
Impact:  Complete service outage or data breach
Response: Immediate (within 15 minutes)
Resolution: Within 1 hour
Escalation: CEO, CTO, all hands
Communication: Real-time updates every 15 minutes
```

### High (P2)

```
Impact:  Major feature unavailable or degraded
Response: Within 30 minutes
Resolution: Within 4 hours
Escalation: Engineering lead
Communication: Updates every hour
```

### Medium (P3)

```
Impact:  Minor feature affected, workaround exists
Response: Within 2 hours
Resolution: Within 24 hours
Escalation: Team lead
Communication: Daily updates
```

### Low (P4)

```
Impact:  Cosmetic issue or minor inconvenience
Response: Within 8 hours
Resolution: Next sprint
Escalation: None
Communication: Ticket updates
```

## Incident Response Process

### 1. Detection

```
Sources:
  - Monitoring alerts
  - Health check failures
  - User reports
  - Automated anomaly detection
```

### 2. Triage

```
Actions:
  - Assess severity
  - Assign incident commander
  - Create incident channel
  - Begin investigation
```

### 3. Investigation

```
Actions:
  - Check logs and metrics
  - Check recent deployments
  - Check configuration changes
  - Identify root cause
```

### 4. Recovery

```
Actions:
  - Implement fix or rollback
  - Verify service restored
  - Monitor for recurrence
  - Communicate resolution
```

### 5. Post-Incident

```
Actions:
  - Root cause analysis
  - Document timeline
  - Document impact
  - Create action items
  - Share learnings
```

## Incident Record

```json
{
  "incidentId": "INC-2026-001",
  "title": "Service outage due to database connection",
  "severity": "P1",
  "status": "resolved",
  "detectedAt": "2026-01-01T10:00:00Z",
  "resolvedAt": "2026-01-01T10:45:00Z",
  "duration": 45,
  "impact": "All users unable to login",
  "rootCause": "Database connection pool exhausted",
  "resolution": "Increased pool size, restarted service",
  "incidentCommander": "engineer@edubridge.com",
  "actionItems": [
    {
      "description": "Add connection pool monitoring",
      "assignee": "team-lead@edubridge.com",
      "dueDate": "2026-01-15"
    }
  ]
}
```

## Communication Template

```
[SEVERITY] Service Name - Brief Description

Impact: What users are affected
Status: Investigating / Identified / Monitoring / Resolved
Next Update: When we will update again

Timeline:
  - HH:MM - Issue detected
  - HH:MM - Investigation started
  - HH:MM - Root cause identified
  - HH:MM - Fix deployed
  - HH:MM - Service restored
```

## Post-Incident Review

Required for P1 and P2 incidents:

```
1. Timeline reconstruction
2. Root cause analysis (5 Whys)
3. Contributing factors
4. Action items with owners and due dates
5. Lessons learned
6. Prevention strategy
```

## Never

```
- Skip severity classification
- Skip incident commander assignment
- Skip communication during incident
- Skip root cause analysis
- Skip post-incident review
- Skip action item tracking
- Blame individuals
```
