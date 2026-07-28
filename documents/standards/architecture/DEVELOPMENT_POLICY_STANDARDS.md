# Development Policy Standards

## Status

LOCKED - Sprint 0C, Step 11

## Principle

Every change follows a controlled pipeline. No shortcuts.

## Required Pipeline

```
Build -> Validate -> Test -> Review -> Document -> Approve -> Deploy
```

### 1. Build

```
- Code compiles without errors
- All dependencies resolved
- No build warnings without justification
```

### 2. Validate

```
- Linting passes
- Formatting passes
- Type checking passes
- Security scanning passes
```

### 3. Test

```
- Unit tests pass
- Integration tests pass
- E2E tests pass (when applicable)
- Code coverage meets threshold
```

### 4. Review

```
- Peer code review completed
- Architecture review (if applicable)
- Security review (if applicable)
- Performance review (if applicable)
```

### 5. Document

```
- Code comments where needed
- API documentation updated
- README updated (if applicable)
- Changelog updated
```

### 6. Approve

```
- All reviewers approved
- All checks passing
- No unresolved discussions
- Merge conflict free
```

### 7. Deploy

```
- Deployment pipeline succeeds
- Health checks pass
- Monitoring confirms stability
- Rollback plan ready
```

## Forbidden Pattern

```
Implement -> Push -> Deploy (NEVER)

This skips:
  - Validation
  - Testing
  - Review
  - Documentation
  - Approval
```

## Exceptions

```
Hotfixes may skip non-critical steps but MUST:
  - Be reviewed by at least one reviewer
  - Have tests added within 24 hours
  - Be documented within 24 hours
  - Follow up with full pipeline compliance
```

## Never

```
- Skip the pipeline without exception approval
- Deploy without review
- Push directly to main
- Skip testing for convenience
- Merge with failing CI
```
