# ADR-046: Human-Centered Career Intelligence Architecture

## Status

ACCEPTED (LOCKED — PERMANENT)

## Context

EduBridge OS requires a Career Intelligence Management Module that serves as the permanent owner of all career pathway intelligence throughout the platform. Career Intelligence is not merely a job recommendation or resume management service. Every future capability involving career planning, employability intelligence, professional development, industry intelligence, internship opportunities, graduate pathway planning, and lifelong professional learning must consume repository-wide Career Intelligence standards.

Career Intelligence assists students in planning their professional future. It never autonomously determines employment outcomes, guarantees internships, guarantees job placements, or replaces human career decision-making.

## Decision

The Career Intelligence Management Module is the permanent owner of all career pathway intelligence in EduBridge OS.

### Locked Architecture

```
                            Student
                                |
                             AI Services
                                |
                    Career Intelligence Management
                                |
                      Career Pathway Intelligence
                                |
                       Employability Intelligence
                                |
                    Professional Development Engine
                                |
                      Industry Intelligence Engine
                                |
                         Policy Validation Layer
                                |
                             Audit Layer
                                |
                          Monitoring Layer
                                |
                           Governance Layer
                                |
                            Future Domains


----------------------------------------------------------------

 Learning        Scholarship      Admissions      International

      |                |                |                 |

     uses             uses             uses              uses

----------------------------------------------------------------
```

No future domain is permitted to duplicate Career Intelligence ownership.

### Core Responsibilities

1. **Career Intelligence**: Central hub for all career data, analytics, and insights
2. **Career Pathway Intelligence**: Academic-to-career planning, career pathway planning, graduate planning services, industry readiness planning, educational transition planning
3. **Employability Intelligence**: Professional skills intelligence, employability planning, internship readiness planning, career development planning, professional growth planning
4. **Professional Development Engine**: Professional readiness standards, career readiness standards, leadership development standards, lifelong learning standards, professional success standards
5. **Industry Intelligence Engine**: Industry trends, market intelligence, sector analysis, employer insights
6. **Policy Validation**: Career, employability, validation, professional development, governance, and integration policies
7. **Audit Layer**: Complete audit trail for all career operations
8. **Monitoring Layer**: Lifecycle, assessment, career, and policy monitoring
9. **Governance Layer**: Responsible career standards, human oversight, transparency

### Architecture Principles

1. **Student-Centric**: All career intelligence serves student success
2. **Human-Augmenting**: Career Intelligence assists students—it never autonomously determines employment outcomes
3. **Policy-Driven**: Nothing bypasses Career Intelligence validation policies
4. **Repository-Wide**: Career standards become repository-wide policies
5. **Integration-First**: All domains consume career intelligence through defined APIs
6. **Explainable**: All career recommendations must be explainable and auditable
7. **Governed**: Mandatory human oversight for career decisions

### Domain Boundaries

- **Owns**: Career Intelligence, Career Pathways, Employability, Professional Development, Industry Intelligence, Policy, Audit, Governance, Integration standards
- **Reads**: Student profiles from Identity Domain, academic records from Academic Domain, learning progress from Learning Services, admissions intelligence from AI Services
- **Publishes**: Career events to Kafka
- **Provides APIs**: All career capabilities through defined REST endpoints

### Cross-Domain Inheritance

```
Learning Services     -->  Career Standards
Admissions Intelligence  -->  Career Standards
International Education  -->  Career Standards
Scholarship Intelligence  -->  Career Standards
```

### Permanently NOT ALLOWED

- Autonomous employment decisions
- Cross-domain career ownership
- Career validation bypass mechanisms
- Unauthorized career modifications
- Hardcoded career policies
- Future domains managing career concerns
- Guaranteed employment outcomes
- Guaranteed internship placements
- Non-transparent career recommendations

## Consequences

- All career intelligence is centralized in the Career Intelligence Management Module
- Future domains consume career intelligence through defined APIs only
- Complete audit trail for all career operations
- All career recommendations are explainable and auditable
- Human oversight is mandatory for career decisions
- Cross-domain communication follows defined standards
- Every career operation must remain independently testable, observable, auditable, explainable, secure, maintainable, recoverable, scalable, and policy-compliant
