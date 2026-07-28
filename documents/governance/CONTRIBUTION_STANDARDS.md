# Contribution Standards

## Status

LOCKED - Sprint 0A, Step 07

## Rule

Every contribution follows a defined process. No undocumented contributions.

## Contribution Workflow

```
1. Find or create issue
2. Fork repository
3. Create feature branch
4. Implement changes
5. Write tests
6. Update documentation
7. Submit PR
8. Address review feedback
9. Merge
```

## Branch Naming

```
feature/{ticket-id}-{brief-description}
bugfix/{ticket-id}-{brief-description}
hotfix/{ticket-id}-{brief-description}
release/v{version}

Examples:
  feature/EDU-123-add-university-search
  bugfix/EDU-456-fix-login-error
  hotfix/EDU-789-fix-payment-crash
  release/v1.0.0
```

## Commit Messages

```
type(scope): description

Types:
  feat     -> New feature
  fix      -> Bug fix
  docs     -> Documentation
  test     -> Tests
  refactor -> Code refactoring
  style    -> Formatting
  chore    -> Maintenance
  build    -> Build system
  ci       -> CI/CD
  perf     -> Performance
  security -> Security

Examples:
  feat(institution): add university search endpoint
  fix(identity): resolve login timeout issue
  docs(api): update OpenAPI specification
  test(academic): add course enrollment tests
```

## PR Requirements

Every PR must include:

```
1. Issue reference
2. Summary of changes
3. Implementation details
4. Testing results
5. Documentation updates
6. Screenshots (if UI changes)
7. Breaking changes (if any)
8. Checklist completion
```

## PR Template

```markdown
## Summary
Brief description of changes.

## Related Issue
Closes #issue-number

## Changes Made
- Change 1
- Change 2

## Testing
- [ ] Unit tests added/updated
- [ ] Integration tests added/updated
- [ ] Manual testing completed

## Documentation
- [ ] README updated
- [ ] API docs updated
- [ ] Changelog updated

## Screenshots
(If applicable)

## Breaking Changes
(None / Description)

## Checklist
- [ ] Code follows standards
- [ ] Tests passing
- [ ] Documentation complete
- [ ] Security review complete
- [ ] No secrets in code
```

## Code Review Standards

```
Reviewer Responsibilities:
  - Check code quality
  - Check test coverage
  - Check documentation
  - Check security
  - Check performance
  - Provide constructive feedback

Author Responsibilities:
  - Respond to feedback
  - Make requested changes
  - Re-request review after changes
```

## Review Checklist

```
Code Quality:
  - [ ] Follows naming conventions
  - [ ] No code duplication
  - [ ] Clear and readable
  - [ ] Proper error handling

Testing:
  - [ ] Tests present
  - [ ] Tests meaningful
  - [ ] Edge cases covered

Documentation:
  - [ ] Code documented
  - [ ] API docs updated
  - [ ] README updated

Security:
  - [ ] No secrets in code
  - [ ] Input validated
  - [ ] Auth enforced
```

## Never

```
- Submit PR without issue reference
- Skip tests
- Skip documentation
- Merge without review
- Force push to develop/main
- Skip CI/CD checks
```
