# Developer Experience Standards

## Status

LOCKED - Sprint 0A, Step 07

## Rule

Every contributor should understand the project within hours, not weeks.

## Developer Questions

Every contributor must be able to answer:

```
1. What is EduBridge?
2. How is it structured?
3. How do I contribute?
4. Where should files be placed?
5. How are APIs implemented?
6. How are modules organized?
7. How are tests written?
8. How are releases managed?
```

## Documentation Hierarchy

```
README.md                    -> Project overview
documents/onboarding/        -> Developer onboarding
documents/governance/        -> Project policies
documents/adr/               -> Architecture decisions
documents/standards/          -> All standards
documents/architecture/      -> System architecture
documents/apis/              -> API specifications
documents/examples/          -> Implementation examples
documents/roadmaps/          -> Project roadmaps
```

## Onboarding Flow

```
1. Read README.md
2. Read documents/onboarding/GETTING_STARTED.md
3. Read documents/architecture/ARCHITECTURE.md
4. Read documents/standards/ (skim all)
5. Read documents/adr/ADR-001.md
6. Read documents/governance/CONTRIBUTION_STANDARDS.md
7. Explore documents/examples/
8. Start contributing
```

## Code Examples

Every standard must include examples:

```
GOOD:
  - How to create a controller
  - How to create a service
  - How to write a test
  - How to create a migration
  - How to add an API endpoint

BAD:
  - Standards without examples
  - Abstract rules without context
  - Documentation that requires interpretation
```

## File Placement Rules

```
Backend Controller:     backend/{domain}/controllers/{Entity}Controller.java
Backend Service:        backend/{domain}/services/{Entity}Service.java
Backend Repository:     backend/{domain}/repositories/{Entity}Repository.java
Backend Entity:         backend/{domain}/entities/{Entity}.java
Backend DTO:            backend/{domain}/dto/{Entity}Request.java
Frontend Component:     apps/platform/src/components/{Entity}/{Entity}Card.tsx
Frontend Page:          apps/platform/src/pages/{Feature}/{Feature}Page.tsx
API Spec:               documents/apis/{domain}/openapi.yaml
Database Migration:     database/migrations/{timestamp}_{description}.sql
Test:                   tests/{type}/{domain}/{Entity}Test.java
```

## Naming Consistency

```
Classes:    PascalCase (StudentController, UniversityService)
Methods:    camelCase (getStudent, createUniversity)
Files:      PascalCase for classes, kebab-case for config
Tables:     snake_case (student_profiles, universities)
Columns:    snake_case (first_name, created_at)
Routes:     kebab-case (/student-profiles, /universities)
```

## Quick Reference

```
Add new API endpoint:
  1. Define in OpenAPI spec
  2. Create DTO
  3. Create Controller
  4. Create Service
  5. Create Repository
  6. Write tests
  7. Update documentation

Add new database table:
  1. Create migration file
  2. Create entity
  3. Create repository
  4. Write tests
  5. Update documentation
```

## Never

```
- Require developers to guess standards
- Skip examples in documentation
- Use inconsistent naming
- Leave documentation incomplete
- Skip onboarding documentation
```
