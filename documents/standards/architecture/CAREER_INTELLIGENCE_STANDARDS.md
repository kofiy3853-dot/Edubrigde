# Career Intelligence Management Standards (LOCKED — PERMANENT)

## Overview

Standards for the AI Services Domain — Career Intelligence Management Module. This module is the permanent owner of all career pathway intelligence throughout EduBridge OS. Career Intelligence assists students in planning their professional future. It never autonomously determines employment outcomes, guarantees internships, guarantees job placements, or replaces human career decision-making.

## Locked Architecture

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
```

## Module Structure

```
ai-services/
  careers/
    intelligence/
    career-pathways/
    employability/
    professional-development/
    assessments/
    lifecycle/
    policies/
    validators/
    services/
    dto/
    events/
    testing/

  security/

  configurations/
```

## Permanent Responsibilities

### Career Intelligence Standards

- Career Intelligence
- Career Pathway Intelligence
- Employability Intelligence
- Professional Development Intelligence
- Industry Intelligence
- Future Career Services

### Career Pathway Standards

- Academic-to-Career Planning
- Career Pathway Planning
- Graduate Planning Services
- Industry Readiness Planning
- Educational Transition Planning
- Future Career Services

### Employability Intelligence Standards

- Professional Skills Intelligence
- Employability Planning
- Internship Readiness Planning
- Career Development Planning
- Professional Growth Planning
- Future Employability Services

### Professional Development Standards

- Professional Readiness Standards
- Career Readiness Standards
- Leadership Development Standards
- Lifelong Learning Standards
- Professional Success Standards
- Future Development Services

### Career Lifecycle Standards

- Career Profile Created
- Educational Validation
- Career Validation
- Governance Validation
- Policy Validation
- Monitoring
- Archival Policies

### Career Policy Standards

- Career Policies
- Employability Policies
- Validation Policies
- Professional Development Policies
- Transparency Policies
- Future Policies

## Entity Standards

Every career profile MUST permanently support:

- Educational Information
- Career Information
- Professional Information
- Planning Information
- Lifecycle Information
- Audit Information

## Assessment Standards

### Permanent Workflow

```
Student Request

    |

Educational Validation

    |

Career Validation

    |

Professional Validation

    |

Policy Validation

    |

Governance Validation

    |

Career Assessment

    |

Future Domains
```

### Required Assessment States

- PENDING
- VALIDATING
- ACTIVE
- SUPPORTED
- RESTRICTED
- INACTIVE
- ARCHIVED

Every career assessment must possess a valid lifecycle state.

## API Standards

```
/api/v1/ai-services/careers/

    intelligence/

    career-pathways/

    employability/

    professional-development/

    assessments/

    policies/
```

### Examples

GOOD:
- `/api/v1/ai-services/careers/intelligence`
- `/api/v1/ai-services/careers/employability`
- `/api/v1/ai-services/careers/assessments`

BAD:
- `/hire-student`
- `/guarantee-job`
- `/everything`

## Database Standards

```
ai-services/
  careers/
    intelligence_records
    pathway_records
    employability_records
    professional_records
    assessment_records
    policy_records
    lifecycle_records
    audit_records
```

## Cross-Domain Standards

Future domains inherit:

```
Learning Services     -->  Career Standards
Admissions Intelligence  -->  Career Standards
International Education  -->  Career Standards
Scholarship Intelligence  -->  Career Standards
```

Career intelligence standards become repository-wide policies.

## Security Standards

### Permanent Requirements

- Career Validation
- Professional Validation
- Policy Validation
- Access Validation
- Audit Logging
- Repository Validation
- Privacy Validation

### Required Support

- Career Monitoring
- Professional Monitoring
- Lifecycle Monitoring
- Assessment Monitoring
- Audit Policies

Nothing should bypass Career Intelligence validation policies.

## Testing Standards

### Required Support

- Career Intelligence Tests
- Career Pathway Tests
- Employability Tests
- Professional Development Tests
- Policy Tests
- Validation Tests
- Integration Tests
- Performance Tests

### Every Implementation Inherits

- Security
- Validation
- Performance
- Observability
- Maintainability
- Scalability

## Foundation Verification Standards

The Career Intelligence Management Module MUST permanently provide:

- Career Intelligence Standards ✓
- Career Pathway Standards ✓
- Employability Intelligence Standards ✓
- Professional Development Standards ✓
- Lifecycle Standards ✓
- Cross-Domain Integration ✓

## Initialization Standards

During initialization we may initialize ONLY:

- Career Intelligence Standards
- Career Pathway Standards
- Employability Intelligence Standards
- Professional Development Standards
- Lifecycle Standards
- Repository Standards

Nothing else.

## Implementation Rules

1. No duplicated career intelligence
2. No cross-domain ownership violations
3. No career validation bypass mechanisms
4. Do not implement business workflows
5. Do not initialize feature implementations
6. No production employment services or career data
7. Nothing bypasses career validation policies
8. Career intelligence never guarantees employment outcomes or professional placements

## Technical Standards

### Entities

- Extend `AuditableEntity`
- Use schema `ai_services`
- Use `@Entity`, `@Table`, `@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor`, `@Builder`
- Status enums as String columns with comments
- Soft delete via `isActive` field

### Repositories

- Extend `JpaRepository<Entity, String>`
- Use `@Repository` annotation
- Custom query methods for filtered lookups
- Use `@Query` for complex queries

### Services

- Use `@Service` and `@RequiredArgsConstructor`
- Use `@Slf4j` for logging
- Use `@Transactional` for write operations
- Use `@Transactional(readOnly = true)` for read operations

### DTOs

- Use `@Data`, `@NoArgsConstructor`, `@AllArgsConstructor`, `@Builder`
- Response DTOs match entity fields

### Controllers

- Use `@RestController` and `@RequestMapping`
- Use `@RequiredArgsConstructor` for dependency injection
- Use `@Slf4j` for logging
- RESTful endpoints with proper HTTP methods
- Return `ResponseEntity` with proper status codes
