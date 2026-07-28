# Developer Onboarding Guide

## Welcome

Welcome to EduBridge OS. This guide will help you understand the project and start contributing.

## What is EduBridge OS?

EduBridge OS is an enterprise-grade education platform connecting students with universities worldwide. It handles:

- Student profiles and matching
- University search and discovery
- Career assessments
- Financial transactions
- Communication and notifications
- Analytics and reporting

## Architecture Overview

```
EDUBRIDGE/
├── apps/                 # Frontend applications
│   ├── platform/         # Student-facing app
│   ├── admin/            # Admin dashboard
│   └── mobile/           # Mobile app
├── backend/              # Backend services
│   ├── identity/         # Auth, users, roles
│   ├── academic/         # Students, courses
│   ├── career/           # Assessments, matching
│   ├── financial/        # Payments, scholarships
│   ├── institution/      # Universities, programs
│   ├── communication/    # Notifications, emails
│   ├── analytics/        # Reports, metrics
│   ├── ai/               # ML, recommendations
│   └── shared/           # Cross-domain code
├── packages/             # Shared libraries
├── database/             # Schemas, migrations
├── infrastructure/       # Docker, CI/CD
├── documents/            # Documentation
└── tests/                # Test suites
```

## Tech Stack

| Layer | Technology |
|-------|-----------|
| Frontend | Next.js, TypeScript |
| Backend | Spring Boot, Java |
| Database | PostgreSQL, Redis |
| Messaging | Apache Kafka |
| Storage | MinIO |
| Container | Docker |
| CI/CD | GitHub Actions |
| Monitoring | OpenTelemetry, Grafana |

## Development Standards

All code must follow:

1. Coding standards (documents/standards/coding/)
2. API standards (documents/standards/api/)
3. Database standards (documents/standards/database/)
4. Testing standards (documents/standards/testing/)
5. Security standards (documents/standards/security/)

## Your First Contribution

### 1. Set Up Your Environment

```bash
# Clone the repository
git clone https://github.com/kofiy3853-dot/Edubrigde.git
cd edubridge-os

# Follow setup instructions in README.md
```

### 2. Find an Issue

Check GitHub issues for good first issues:
- `good-first-issue` label
- `documentation` label
- `bug` label

### 3. Create a Branch

```bash
git checkout -b feature/EDU-123-your-feature-name
```

### 4. Implement

Follow the module structure:
```
backend/{domain}/
  controllers/
  services/
  repositories/
  entities/
  dto/
  validators/
  tests/
```

### 5. Test

```bash
# Run all tests
mvn test

# Run specific test
mvn test -Dtest=StudentServiceTest
```

### 6. Document

Update relevant documentation:
- Module README.md
- API documentation
- Changelog

### 7. Submit PR

Use the PR template and fill in all sections.

## Key Resources

```
Standards:      documents/standards/
Architecture:   documents/architecture/
API Specs:      documents/apis/
ADRs:           documents/adr/
Governance:     documents/governance/
Examples:       documents/examples/
```

## Getting Help

- Check documentation first
- Search existing issues
- Ask in team chat
- Reach out to team lead

## Code of Conduct

- Be respectful
- Be constructive
- Be collaborative
- Be professional
