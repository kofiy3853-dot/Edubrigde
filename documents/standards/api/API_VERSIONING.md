# API Versioning Standards

## Status

LOCKED - Sprint 0A, Step 05

## Rule

All APIs are versioned from day one. Breaking changes never happen silently.

## Versioning Strategy

Path-based versioning:

```
/api/v1/{domain}/{resource}
/api/v2/{domain}/{resource}
```

## Version Lifecycle

```
v1  -> Initial release, stable
v2  -> Breaking changes introduced
v3  -> Future major changes
```

## What Constitutes a Breaking Change

```
Breaking:
  - Removing a field from response
  - Renaming a field
  - Changing field type
  - Changing URL path structure
  - Changing authentication method
  - Removing an endpoint
  - Changing error response format
  - Changing validation rules (stricter)
```

## What is NOT a Breaking Change

```
Not Breaking:
  - Adding a new optional field to response
  - Adding a new endpoint
  - Adding new query parameter (optional)
  - Adding new request body field (optional)
  - Adding new error code
  - Relaxing validation rules
  - Adding new enum value (if client ignores unknown)
```

## Version Deprecation

When deprecating a version:

```
1. Add Deprecation header to responses
2. Add Sunset header with removal date
3. Document migration guide
4. Minimum 6 months notice
5. Monitor usage before removal
```

Deprecation header:

```
Deprecation: true
Sunset: Sat, 01 Jan 2027 00:00:00 GMT
Link: </api/v2/docs>; rel="successor-version"
```

## Migration Guide

Every version bump must include:

```
1. List of breaking changes
2. Migration steps for each change
3. Timeline for old version removal
4. Support contact for issues
```

## Version Documentation

Location: `documents/apis/{domain}/CHANGELOG.md`

```markdown
# Changelog

## v1.0.0 (2026-01-01)
- Initial release

## v1.1.0 (2026-03-01)
- Added search endpoint
- Added pagination

## v2.0.0 (2026-06-01)
- BREAKING: Changed user response format
- BREAKING: Removed deprecated endpoints
- Migration guide: /docs/migration/v1-to-v2
```

## Never

```
- Change endpoints without version bump
- Remove fields without version bump
- Skip deprecation period
- Deploy breaking changes without approval
- Use header versioning (use path versioning)
- Use query param versioning (use path versioning)
```
