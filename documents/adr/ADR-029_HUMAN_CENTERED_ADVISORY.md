# ADR-029: Human-Centered Advisory Architecture

## Status

**LOCKED** - This decision is permanent and cannot be changed.

## Context

EduBridge OS requires comprehensive consultation management capabilities for educational advisory services. Educational consultation is not simply booking appointments with consultants. The Consultation Management Module becomes the permanent owner of all educational advisory standards throughout EduBridge OS.

## Decision

**Consultation Management Module is the permanent owner of all educational advisory standards across EduBridge OS.**

### Architecture

```
Consultation Management Module
    │
    ├── Session Management
    │   ├── Consultation Sessions
    │   ├── Session Planning
    │   ├── Session Assessments
    │   ├── Session Standards
    │   └── Session Validation
    │
    ├── Advisory Management
    │   ├── Academic Guidance
    │   ├── University Guidance
    │   ├── Scholarship Guidance
    │   ├── Financial Guidance
    │   └── Success Planning
    │
    ├── Consultant Management
    │   ├── Consultant Profiles
    │   ├── Academic Advisors
    │   ├── Financial Advisors
    │   ├── Student Success Advisors
    │   └── International Advisors
    │
    ├── Policy Validation
    │   ├── Scheduling Policies
    │   ├── Validation Policies
    │   ├── Session Policies
    │   └── Integration Policies
    │
    ├── Audit Layer
    │   ├── Consultation Audit
    │   ├── Session Audit
    │   ├── Advisor Audit
    │   └── Policy Audit
    │
    ├── Monitoring Layer
    │   ├── Session Monitoring
    │   ├── Policy Monitoring
    │   ├── Lifecycle Monitoring
    │   └── Integration Monitoring
    │
    └── Future Domains
        ├── Financial → uses Consultation Management
        ├── Learning → uses Consultation Management
        ├── AI Services → uses Consultation Management
        └── Student Services → uses Consultation Management
```

### Consultation Types

| Type | Description |
|------|-------------|
| ACADEMIC | Academic advising |
| UNIVERSITY | University selection guidance |
| FINANCIAL | Financial planning consultations |
| SCHOLARSHIP | Scholarship advisory services |
| SUCCESS | Student success planning |
| INTERNATIONAL | International education guidance |

### Session States

| State | Description |
|-------|-------------|
| PENDING | Session initiated |
| VALIDATING | Validation in progress |
| SCHEDULED | Session scheduled |
| ACTIVE | Session in progress |
| COMPLETED | Session completed |
| RESTRICTED | Session restricted |
| CANCELLED | Session cancelled |
| ARCHIVED | Archived |

### Advisor Types

| Type | Description |
|------|-------------|
| ACADEMIC | Academic advisors |
| FINANCIAL | Financial advisors |
| SUCCESS | Student success advisors |
| INTERNATIONAL | International education advisors |
| GENERAL | General advisors |

## Consequences

### Positive
- Single source of truth for all consultation data
- Consistent consultation management across platform
- Human-centered advisory intelligence
- Policy-driven consultation operations

### Negative
- All consultation operations must go through Consultation Management
- No bypassing consultation validation
- No cross-domain consultation ownership

## Integration Points

```
Future Domains → Consultation Management Module
├── Financial → use Consultation Standards
├── Learning → use Session Management
├── AI Services → use Advisory Management
└── Student Services → use Consultant Standards
```

## Forbidden Patterns

```
NOT ALLOWED:
✗ Duplicate consultation records
✗ Cross-domain consultation ownership
✗ Consultation validation bypass mechanisms
✗ Unauthorized consultation modifications
✗ Hardcoded consultation policies
✗ Future domains managing consultations
✗ Consultation assessments without validation
```

## Required Patterns

```
REQUIRED:
✓ Human-centered advisory architecture
✓ Repository-wide consultation standards
✓ Security-first consultation validation
✓ Monitoring-first consultation operations
✓ Policy-aware consultation management
✓ Extensible international educational advisory support
✓ Audit logging for all operations
✓ Error handling and retry logic
✓ Idempotent operations
```
