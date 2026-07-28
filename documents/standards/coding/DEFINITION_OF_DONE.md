# Definition of Done

## Status

LOCKED - Sprint 0A, Step 04

## Rule

A feature is complete only when ALL criteria are satisfied. No exceptions.

## Checklist

A feature is READY FOR APPROVAL when:

```
Implementation Complete
  - Code follows naming standards
  - Code follows coding principles
  - Code is DRY (no duplication)
  - Code is self-documenting
  - No TODO/FIXME in production code

Validation Complete
  - Input validation implemented
  - Output validation implemented
  - Business rule validation implemented
  - Error messages are clear

Testing Complete
  - Unit tests written
  - Integration tests written
  - Edge cases covered
  - Error cases covered
  - Test coverage meets threshold

Documentation Complete
  - Module README updated
  - API documentation updated
  - Database documentation updated
  - Architecture docs updated if needed

Security Complete
  - Authentication required where needed
  - Authorization enforced
  - Input sanitized
  - No secrets in code
  - Security headers set
  - Rate limiting applied

Accessibility Complete
  - Keyboard navigable
  - Screen reader accessible
  - Semantic HTML used
  - Color contrast adequate
  - Focus management correct

Performance Complete
  - No performance regression
  - Caching strategy implemented
  - Pagination for lists
  - Bundle size acceptable
  - Response times meet targets

Review Complete
  - Code review approved
  - Security review passed
  - Architecture review passed
  - Testing review passed
  - Documentation review passed

Verification Complete
  - All tests passing
  - CI/CD pipeline green
  - No linting errors
  - No type errors
  - Manual testing completed
```

## Approval Process

```
1. Developer completes implementation
2. Developer runs full test suite
3. Developer completes documentation
4. Developer self-reviews against this checklist
5. Developer submits PR
6. Automated checks run (CI/CD)
7. Code reviewer reviews
8. Security reviewer reviews (if applicable)
9. All feedback addressed
10. PR approved and merged
```

## Rejecting a PR

A PR must be rejected if any of the following:

```
- Implementation incomplete
- Tests missing or failing
- Documentation missing
- Security concerns unresolved
- Performance regression
- Accessibility issues
- Code review feedback unresolved
```

## No Shortcuts

There is no "we'll fix it later." Every item must be complete before approval.
