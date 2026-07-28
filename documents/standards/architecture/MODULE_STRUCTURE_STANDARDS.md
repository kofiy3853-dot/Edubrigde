# Module Structure Standards

## Status

LOCKED - Sprint 0A, Step 05

## Rule

Every domain module follows the exact same internal structure. No exceptions.

## Required Directory Structure

```
backend/{domain}/
  controllers/       -> HTTP request handlers
  services/          -> Business logic
  repositories/      -> Data access layer
  entities/          -> Domain models and value objects
  dto/               -> Request/response data transfer objects
  mappers/           -> Entity <-> DTO mapping
  validators/        -> Input validation schemas
  events/            -> Domain events (publish and subscribe)
  exceptions/        -> Custom error classes
  tests/             -> Unit and integration tests
  config/            -> Module configuration
  README.md          -> Module documentation
```

## Directory Responsibilities

### controllers/

HTTP request handling only.

```
Responsibilities:
  - Parse request (path, query, body)
  - Validate input against schema
  - Call service method
  - Format response
  - Handle errors

Must NOT contain:
  - Business logic
  - Database queries
  - Direct entity manipulation
```

### services/

Business logic and orchestration.

```
Responsibilities:
  - Business rule validation
  - Domain logic execution
  - Orchestrate repository calls
  - Publish domain events
  - Transaction management

Must NOT contain:
  - HTTP request/response handling
  - Direct database queries
  - Infrastructure concerns
```

### repositories/

Data access and persistence.

```
Responsibilities:
  - Database queries
  - Entity persistence
  - Data retrieval
  - Transaction participation

Must NOT contain:
  - Business logic
  - HTTP concerns
  - Infrastructure clients
```

### entities/

Core domain models.

```
Responsibilities:
  - Define domain properties
  - Define domain behavior
  - Define value objects
  - Define domain events

Must NOT contain:
  - Framework decorators
  - Database mapping
  - HTTP serialization
```

### dto/

Data transfer objects.

```
Responsibilities:
  - Request schemas
  - Response schemas
  - Inter-layer data transfer

Must NOT contain:
  - Business logic
  - Database mapping
```

### mappers/

Object transformation.

```
Responsibilities:
  - Entity to DTO conversion
  - DTO to Entity conversion
  - Nested object mapping

Must NOT contain:
  - Business logic
  - Database operations
```

### validators/

Input validation.

```
Responsibilities:
  - Request body validation
  - Query parameter validation
  - Path parameter validation
  - Business rule validation

Must NOT contain:
  - Business logic
  - Database operations
```

### events/

Domain events.

```
Responsibilities:
  - Define event types
  - Publish events
  - Subscribe to events
  - Event handlers

Must NOT contain:
  - Business logic outside event handling
```

### exceptions/

Custom error types.

```
Responsibilities:
  - Domain-specific errors
  - Error codes
  - Error messages

Must NOT contain:
  - Infrastructure errors
```

### tests/

Module tests.

```
Responsibilities:
  - Unit tests for services
  - Unit tests for repositories
  - Integration tests
  - Controller tests

Must NOT contain:
  - Production code
```

### config/

Module configuration.

```
Responsibilities:
  - Dependency injection
  - Module setup
  - Configuration loading

Must NOT contain:
  - Business logic
```

## File Naming

```
Controllers:  {Entity}Controller.java
Services:     {Entity}Service.java
Repositories: {Entity}Repository.java
Entities:     {Entity}.java
DTOs:         {Entity}Request.java, {Entity}Response.java
Mappers:      {Entity}Mapper.java
Validators:   {Entity}Validator.java
Events:       {EventName}Event.java
Exceptions:   {Entity}Exception.java
Tests:        {Entity}Test.java
```

## Never

```
- Skip any directory
- Add directories not in this standard
- Mix responsibilities across directories
- Use different naming conventions
- Skip tests directory
- Skip documentation
```
