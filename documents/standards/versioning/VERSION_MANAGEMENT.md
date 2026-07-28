# Version Management

## Status

LOCKED - Sprint 0B, Step 10

## Principle

All versions follow semantic versioning. No exceptions.

## Semantic Versioning

```
MAJOR.MINOR.PATCH

MAJOR:   Breaking changes
MINOR:   New features (backward compatible)
PATCH:   Bug fixes (backward compatible)
```

## Examples

```
1.0.0 -> Initial production release
1.0.1 -> Bug fix
1.1.0 -> New feature
1.2.0 -> Another feature
2.0.0 -> Breaking changes
```

## Version Bumping Rules

### MAJOR Version

```
When:
  - API contract changes
  - Database schema breaking changes
  - Authentication method changes
  - Configuration format changes

Example:
  v1.x.x -> v2.0.0
```

### MINOR Version

```
When:
  - New API endpoint
  - New feature
  - New database column (optional)
  - New configuration option

Example:
  v1.0.x -> v1.1.0
```

### PATCH Version

```
When:
  - Bug fix
  - Security patch
  - Documentation update
  - Configuration fix

Example:
  v1.0.0 -> v1.0.1
```

## Release Process

```
1. Create release branch
2. Update version in all files
3. Update CHANGELOG.md
4. Create git tag
5. Push to GitHub
6. Create GitHub release
```

## Version Files

```
backend/
  build.gradle          -> version = "1.0.0"

apps/
  platform/
    package.json        -> "version": "1.0.0"
  admin/
    package.json        -> "version": "1.0.0"

Root:
  CHANGELOG.md          -> Version history
```

## Changelog Format

```markdown
# Changelog

## [1.0.0] - 2026-01-01

### Added
- Initial production release
- Identity domain
- Academic domain

### Changed
- None

### Fixed
- None

### Security
- None
```

## Version Tagging

```
Format: v{MAJOR}.{MINOR}.{PATCH}

Examples:
  v1.0.0
  v1.0.1
  v1.1.0
  v2.0.0
```

## Never

```
- Skip semantic versioning
- Make breaking changes in MINOR
- Make breaking changes in PATCH
- Skip CHANGELOG updates
- Skip git tags
- Skip release notes
- Use versions inconsistently across modules
```
