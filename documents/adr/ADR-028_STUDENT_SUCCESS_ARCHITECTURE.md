# ADR-028: Student Success Architecture

## Status

**LOCKED** - This decision is permanent and cannot be changed.

## Context

EduBridge OS requires comprehensive student services capabilities for the entire student journey. Student Services is not merely a support system. It becomes the permanent owner of the entire student journey—from university exploration and admission preparation to academic success, mentoring, consultations, wellbeing support, and long-term educational outcomes.

## Decision

**Student Services Domain is the permanent owner of all student success intelligence across EduBridge OS.**

### Architecture

```
Student Services Domain
    │
    ├── Student Journey Engine
    │   ├── Student Registration Journey
    │   ├── Academic Journey
    │   ├── Financial Journey
    │   ├── University Journey
    │   └── Success Planning
    │
    ├── Student Success Engine
    │   ├── Academic Success Planning
    │   ├── Career Success Planning
    │   ├── Educational Success Planning
    │   ├── Student Goal Planning
    │   └── International Success Planning
    │
    ├── Consultation Management
    │   ├── Consultation Standards
    │   ├── Advisory Services
    │   ├── Consultant Management
    │   └── Educational Consultation
    │
    ├── Mentorship Services
    │   ├── Mentorship Standards
    │   ├── Mentoring Programs
    │   ├── Student Guidance
    │   └── Career Mentorship
    │
    ├── Student Support Services
    │   ├── Student Support Standards
    │   ├── Academic Support Services
    │   ├── Wellbeing Support Standards
    │   ├── Resource Management Standards
    │   └── Student Guidance Standards
    │
    ├── Policy Validation
    │   ├── Success Policies
    │   ├── Support Policies
    │   ├── Validation Policies
    │   └── Integration Policies
    │
    ├── Audit Layer
    │   ├── Journey Audit
    │   ├── Success Audit
    │   ├── Support Audit
    │   └── Policy Audit
    │
    ├── Monitoring Layer
    │   ├── Journey Monitoring
    │   ├── Success Monitoring
    │   ├── Support Monitoring
    │   └── Integration Monitoring
    │
    └── Future Domains
        ├── Learning → uses Student Services
        ├── AI Services → uses Student Services
        ├── Applications → uses Student Services
        └── International Education → uses Student Services
```

### Student Journey States

| State | Description |
|-------|-------------|
| PENDING | Journey initiated |
| VALIDATING | Validation in progress |
| ACTIVE | Journey active |
| SUPPORTED | Student supported |
| RESTRICTED | Journey restricted |
| INACTIVE | No longer active |
| ARCHIVED | Archived |

### Student Success Categories

| Category | Description |
|----------|-------------|
| ACADEMIC | Academic success planning |
| CAREER | Career success planning |
| EDUCATIONAL | Educational success planning |
| PERSONAL | Personal goal planning |
| INTERNATIONAL | International success planning |

## Consequences

### Positive
- Single source of truth for all student data
- Consistent student services across platform
- Student-centric success intelligence
- Policy-driven student operations

### Negative
- All student operations must go through Student Services
- No bypassing student validation
- No cross-domain student ownership

## Integration Points

```
Future Domains → Student Services Domain
├── Learning → use Student Journeys
├── AI Services → use Student Success
├── Applications → use Student Support
└── International Education → use Student Standards
```

## Forbidden Patterns

```
NOT ALLOWED:
✗ Duplicate student service records
✗ Cross-domain student ownership
✗ Student validation bypass mechanisms
✗ Hardcoded student policies
✗ Unauthorized student modifications
✗ Future domains managing student concerns
✗ Student assessments without validation
```

## Required Patterns

```
REQUIRED:
✓ Student success architecture
✓ Repository-wide student service standards
✓ Security-first student validation
✓ Monitoring-first student service operations
✓ Policy-aware student success management
✓ Extensible international student support
✓ Audit logging for all operations
✓ Error handling and retry logic
✓ Idempotent operations
```
