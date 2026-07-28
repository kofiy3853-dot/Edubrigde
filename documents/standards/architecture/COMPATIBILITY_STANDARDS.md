# Compatibility Standards

## Status

LOCKED - Sprint 0B, Step 10

## Principle

All technologies must be compatible before initialization.

## Compatibility Requirements

### 1. Language Compatibility

```
Java:
  - JDK 21 LTS (Eclipse Temurin)
  - Spring Boot 3.4.x compatible
  - All libraries JDK 21 compatible

TypeScript:
  - TypeScript 5.x
  - Node.js 22.x LTS compatible
  - All packages ESM compatible
```

### 2. Framework Compatibility

```
Spring Boot:
  - Version 3.4.x
  - All starters compatible
  - All auto-configurations compatible

Next.js:
  - Version 15.x
  - React 19.x compatible
  - All plugins compatible
```

### 3. Database Compatibility

```
PostgreSQL:
  - Version 16
  - JDBC driver compatible
  - Flyway compatible
  - All extensions compatible

Redis:
  - Version 7.x
  - Lettuce driver compatible
  - All features compatible
```

### 4. Infrastructure Compatibility

```
Docker:
  - Version 27.x
  - Docker Compose 2.x compatible
  - All images compatible

NGINX:
  - Version 1.27.x
  - All modules compatible
  - All configurations compatible
```

## Compatibility Checks

### Before Adding Dependency

```
1. Check version compatibility
2. Check license compatibility
3. Check security vulnerabilities
4. Check maintenance status
5. Check documentation quality
6. Check community support
```

### Before Upgrading Dependency

```
1. Check changelog for breaking changes
2. Run full test suite
3. Verify all features work
4. Update documentation
5. Create migration guide
```

## Version Matrix

```
Java 21 + Spring Boot 3.4.x + PostgreSQL 16
Node.js 22.x + Next.js 15.x + TypeScript 5.x
Docker 27.x + Docker Compose 2.x + NGINX 1.27.x
```

## Known Incompatibilities

```
Document any known incompatibilities here.
Update as new incompatibilities are discovered.
```

## Never

```
- Add incompatible dependencies
- Skip compatibility testing
- Use deprecated combinations
- Skip version matrix validation
- Use RC/Beta in production
- Skip security compatibility check
```
