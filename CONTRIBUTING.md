# Contributing to EduBridge OS

Welcome to EduBridge OS! This document provides guidelines for contributing to the project.

## Table of Contents

- [Code of Conduct](#code-of-conduct)
- [Getting Started](#getting-started)
- [Development Process](#development-process)
- [Coding Standards](#coding-standards)
- [Commit Messages](#commit-messages)
- [Pull Request Process](#pull-request-process)
- [Review Process](#review-process)

## Code of Conduct

- Be respectful and professional
- Be constructive and collaborative
- Focus on the code, not the person
- Help others learn and grow

## Getting Started

1. Read the [Getting Started Guide](documents/onboarding/GETTING_STARTED.md)
2. Review the [Architecture](documents/architecture/)
3. Understand the [Standards](documents/standards/)
4. Find an issue with `good-first-issue` label

## Development Process

### 1. Create an Issue

Before starting work, create or find an existing issue.

### 2. Fork and Clone

```bash
git clone https://github.com/kofiy3853-dot/Edubrigde.git
cd edubridge-os
```

### 3. Create a Branch

```bash
git checkout -b feature/EDU-123-your-feature
```

Branch naming:
- `feature/{ticket-id}-{description}`
- `bugfix/{ticket-id}-{description}`
- `hotfix/{ticket-id}-{description}`

### 4. Implement

Follow the [Module Structure Standards](documents/standards/architecture/MODULE_STRUCTURE_STANDARDS.md).

### 5. Test

```bash
# Run all tests
mvn test

# Run specific tests
mvn test -Dtest=StudentServiceTest
```

### 6. Document

Update relevant documentation.

### 7. Submit PR

Use the PR template.

## Coding Standards

Follow all standards in `documents/standards/`:

- [Coding Standards](documents/standards/coding/)
- [API Standards](documents/standards/api/)
- [Database Standards](documents/standards/database/)
- [Testing Standards](documents/standards/testing/)
- [Security Standards](documents/standards/security/)

## Commit Messages

Follow the format:

```
type(scope): description

Types:
  feat     New feature
  fix      Bug fix
  docs     Documentation
  test     Tests
  refactor Code refactoring
  style    Formatting
  chore    Maintenance
  build    Build system
  ci       CI/CD
  perf     Performance
  security Security

Examples:
  feat(institution): add university search endpoint
  fix(identity): resolve login timeout
  docs(api): update OpenAPI specification
```

## Pull Request Process

1. Fill out the PR template completely
2. Link the related issue
3. Ensure all tests pass
4. Ensure documentation is updated
5. Request review from team members
6. Address all feedback
7. Get approval before merge

## Review Process

### What Reviewers Check

- Code quality and standards
- Test coverage
- Documentation completeness
- Security considerations
- Performance implications

### Responding to Feedback

- Respond to all comments
- Make requested changes
- Re-request review after changes
- Discuss disagreements constructively

## Questions?

- Check the documentation
- Search existing issues
- Ask in team chat
- Contact the team lead

## Thank You

Thank you for contributing to EduBridge OS!
