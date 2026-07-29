# ADR-034: Academic Growth and Learning Intelligence Architecture

## Status

ACCEPTED (LOCKED — PERMANENT)

## Context

EduBridge OS requires a Learning Services Domain that serves as the permanent owner of learning intelligence throughout the platform. Learning Services is not merely an e-learning platform. Every future learning capability must support academic growth, digital education, career readiness, study planning, international education preparation, and lifelong learning standards. Learning intelligence must remain reusable across all future domains without duplicating educational information.

## Decision

The Learning Services Domain is the permanent owner of all learning intelligence in EduBridge OS.

### Locked Architecture

```
                         Learning Services

                                  |

                         Learning Intelligence

                                  |

                        Academic Growth Engine

                                  |

                       Digital Education Engine

                                  |

                        Career Readiness Engine

                                  |

                           Policy Validation

                                  |

                              Audit Layer

                                  |

                           Monitoring Layer

                                  |

                             Future Domains
```

No future domain is permitted to duplicate learning intelligence. Nothing bypasses learning validation policies.

### Core Responsibilities

1. **Learning Intelligence**: Central hub for all learning data, analytics, and insights
2. **Academic Growth Engine**: Study skills development, performance planning, learning progress, success planning
3. **Digital Education Engine**: Digital courses, learning resources, academic tutorials, study planning, educational guidance
4. **Career Readiness Engine**: Career planning, professional development, leadership development, international career planning, digital skills development
5. **Policy Validation**: Learning, academic, validation, integration, and assessment policies
6. **Audit Layer**: Complete audit trail for all learning operations
7. **Monitoring Layer**: Lifecycle, assessment, learning, and policy monitoring

### Architecture Principles

1. **Student-Centric**: All learning intelligence serves student success
2. **Growth-Oriented**: Academic growth remains the primary objective
3. **Policy-Driven**: Nothing bypasses learning validation policies
4. **Repository-Wide**: Learning standards become repository-wide policies
5. **Integration-First**: Future domains consume learning through defined APIs

### Domain Boundaries

- **Owns**: Learning Intelligence, Digital Education, Academic Growth, Career Readiness, Policy, Audit, Integration standards
- **Reads**: Student profiles from Identity Domain, academic records from Academic Domain, student services from Student Services Domain
- **Publishes**: Learning events to Kafka
- **Provides APIs**: All learning capabilities through defined REST endpoints

### Cross-Domain Inheritance

```
Academic Domain      -->  Learning Standards
Student Services     -->  Learning Standards
AI Services          -->  Learning Standards
Financial Domain     -->  Learning Standards
```

### Permanently NOT ALLOWED

- Duplicate learning intelligence
- Cross-domain learning ownership
- Learning validation bypass mechanisms
- Unauthorized learning modifications
- Hardcoded learning policies
- Future domains managing learning concerns
- Learning assessments without validation

## Consequences

- All learning intelligence is centralized in the Learning Services Domain
- Future domains consume learning through defined APIs only
- Complete audit trail for all learning operations
- Cross-domain communication follows defined standards
- Every learning operation must remain independently testable, observable, auditable, secure, maintainable, recoverable, and scalable
