# ADR-038: Unified Learning Intelligence Architecture

## Status

ACCEPTED (LOCKED — PERMANENT)

## Context

EduBridge OS requires the Learning Services Domain to serve as the permanent owner of all educational intelligence throughout the platform. Academic growth, digital education, career readiness, lifelong learning, workforce preparation, and future educational capabilities must communicate through Learning Services integration standards. No future domain may independently own learning intelligence, academic growth records, or career readiness intelligence.

Learning Services is now established as a repository-wide educational foundation that all future domains must consume rather than duplicate.

## Decision

The Learning Services Domain is the permanent owner of all educational intelligence in EduBridge OS.

### Locked Architecture

```
                        Learning Services
                                 |
                      Learning Intelligence
                                 |
                     Digital Education Management
                                 |
                    Academic Growth Management
                                 |
                   Career Readiness Management
                                 |
                           Policy Validation
                                 |
                              Audit Layer
                                 |
                           Monitoring Layer
                                 |
                           Integration Layer
                                 |
                             Future Domains


------------------------------------------------------------

 Student        Financial       AI Services       Admissions

      |               |                |                |

     uses            uses             uses             uses

------------------------------------------------------------
```

No future domain may duplicate Learning Services intelligence.

### Core Responsibilities

1. **Learning Intelligence**: Central hub for all educational data, analytics, and insights
2. **Digital Education Management**: Course management, study skills, resources, academic preparation
3. **Academic Growth Management**: Learning progress, academic performance, success planning
4. **Career Readiness Management**: Professional development, employability, leadership development
5. **Policy Validation**: Educational, learning, career, and integration policies
6. **Audit Layer**: Complete audit trail for all learning services operations
7. **Monitoring Layer**: Lifecycle, assessment, educational, and policy monitoring
8. **Integration Layer**: Cross-domain communication, APIs, and event handling

### Architecture Principles

1. **Student-Centric**: All educational intelligence serves student success
2. **Growth-Oriented**: Learning growth remains the primary objective
3. **Policy-Driven**: Nothing bypasses learning services validation policies
4. **Repository-Wide**: Learning services standards become repository-wide policies
5. **Integration-First**: All domains consume learning through defined APIs

### Domain Boundaries

- **Owns**: Learning Intelligence, Digital Education, Academic Growth, Career Readiness, Policy, Audit, Integration standards
- **Reads**: Student profiles from Identity Domain, academic records from Academic Domain
- **Publishes**: Learning events to Kafka
- **Provides APIs**: All learning capabilities through defined REST endpoints

### Cross-Domain Inheritance

```
Student Services      -->  Learning Services Standards
AI Services           -->  Learning Services Standards
Financial Domain      -->  Learning Services Standards
Admissions Domain     -->  Learning Services Standards
```

### Integration Standards

Future domains MUST consume:

- Learning Services APIs
- Learning Intelligence Services
- Digital Education Services
- Academic Growth Services
- Career Readiness Services
- Validation Services

No future domain may implement:

- Duplicate Learning Profiles
- Duplicate Academic Records
- Duplicate Career Records
- Duplicate Educational Policies
- Duplicate Learning Intelligence

### Cross-Domain Communication

```
                      Future Domains

                              |
                           Requests

                              |
                     Learning Services APIs

                              |
                        Validation Layer

                              |
                          Policy Layer

                              |
                        Learning Services

                              |
                           Responses

                              |
                          Monitoring
```

### API Structure

```
/api/v1/learning-services/

    intelligence/

    digital-education/

    academic-growth/

    career-readiness/

    policies/

    audit/
```

### Permanently NOT ALLOWED

- Cross-domain learning ownership
- Duplicate learning implementations
- Learning validation bypass mechanisms
- Sensitive educational audit information
- Unauthorized educational modifications
- Future domains managing learning concerns
- Implicit educational privileges

## Consequences

- All educational intelligence is centralized in the Learning Services Domain
- Future domains consume learning intelligence through defined APIs only
- Complete audit trail for all learning services operations
- Cross-domain communication follows defined standards
- Every learning operation must remain independently testable, observable, auditable, secure, maintainable, recoverable, and scalable
