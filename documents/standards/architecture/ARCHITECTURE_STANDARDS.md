# Architecture Standards

## Status

LOCKED - Sprint 0A, Step 05

## Rule

Every implementation follows layered architecture. Boundaries are enforced, not suggested.

## Layered Architecture

```
Client Layer (Frontend)
  -> Next.js applications
  -> React components
  -> API calls

API Layer (Controllers)
  -> HTTP request handling
  -> Input validation
  -> Response formatting
  -> Authentication/Authorization

Service Layer (Business Logic)
  -> Business rules
  -> Domain logic
  -> Orchestration
  -> Validation

Domain Layer (Entities)
  -> Core business entities
  -> Value objects
  -> Domain events
  -> Domain rules

Repository Layer (Data Access)
  -> Database queries
  -> Data mapping
  -> Transaction management

Database Layer (Storage)
  -> PostgreSQL schemas
  -> Tables and indexes
  -> Constraints
  -> Migrations
```

## Layer Rules

```
1. Client Layer can only call API Layer
2. API Layer can only call Service Layer
3. Service Layer can call Domain and Repository Layers
4. Domain Layer has no external dependencies
5. Repository Layer can only access Database Layer
```

## Forbidden Patterns

```
NO Controllers containing business logic
  -> Business logic belongs in Services

NO Cross-domain database access
  -> Each domain owns its data

NO Circular dependencies
  -> Dependencies flow downward only

NO Hardcoded configurations
  -> Use environment variables

NO Direct infrastructure dependencies in domain
  -> Domain layer is pure business logic
```

## Dependency Flow

```
Controller -> Service -> Repository -> Database
    |            |
    |            v
    |         Domain (entities, events)
    |
    v
  DTO (request/response)
```

## Module Boundaries

Each domain is a self-contained module:

```
backend/
  identity/
    controllers/    -> HTTP handlers
    services/       -> Business logic
    repositories/   -> Data access
    entities/       -> Domain models
    dto/            -> Data transfer objects
    mappers/        -> Object mapping
    validators/     -> Input validation
    events/         -> Domain events
    exceptions/     -> Custom errors
    tests/          -> Unit tests
    config/         -> Module configuration
```

## Cross-Domain Communication

Domains communicate through:

```
API Contracts
  -> REST APIs between services
  -> Formal request/response contracts

Events
  -> Async event publishing
  -> Event-driven choreography

Shared Contracts
  -> Shared types in packages/
  -> Common interfaces
```

Never through:

```
NO Direct database access across domains
NO Shared business logic
NO Cross-domain repositories
NO Duplicated implementations
```

## Technology Independence

Domain layer must not depend on:

```
NO Frameworks (Express, Spring, etc.)
NO Database libraries (Prisma, Hibernate, etc.)
NO HTTP libraries
NO Cache libraries
NO Message queue libraries
NO External service clients
```

Domain layer contains only:

```
PURE business logic
PURE entities
PURE value objects
PURE domain events
PURE domain rules
```

## Event-Driven Architecture

Cross-domain events flow through message broker:

```
Domain A -> Event -> Kafka -> Domain B
```

Event structure:

```json
{
  "eventId": "uuid",
  "eventType": "UserRegistered",
  "aggregateId": "user-uuid",
  "timestamp": "2026-01-01T00:00:00Z",
  "data": { ... },
  "metadata": { ... }
}
```

## Scalability Considerations

```
1. Services can be deployed independently
2. Databases can be split per domain
3. Caching can be added per layer
4. Load balancing per service
5. Horizontal scaling for stateless services
```

## Never

```
- Put business logic in controllers
- Access other domains' databases directly
- Create circular dependencies
- Hardcode configuration values
- Skip layer boundaries
- Mix domain and infrastructure concerns
- Skip event-driven communication
```
