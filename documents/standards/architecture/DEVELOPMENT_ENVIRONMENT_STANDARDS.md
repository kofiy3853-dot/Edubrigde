# Development Environment Standards

## Status

LOCKED - Sprint 0B, Step 10

## Principle

Every developer has the same environment. No "works on my machine."

## Required Tools

### Core

```
JDK:            Eclipse Temurin 21 LTS
Node.js:        22.x LTS
npm:            10.x
Docker:         27.x
Docker Compose: 2.x
```

### IDE

```
Backend:        IntelliJ IDEA (latest stable)
Frontend:       VS Code (latest stable)
Database:       DBeaver or pgAdmin
```

### CLI Tools

```
Git:            Latest
Java:           sdkman or jabba
Node:           nvm or fnm
Docker:         Docker Desktop
```

## Environment Setup

### 1. Clone Repository

```bash
git clone https://github.com/kofiy3853-dot/Edubrigde.git
cd edubridge-os
```

### 2. Install Prerequisites

```bash
# Java
sdk install java 21-tem

# Node
nvm install 22
nvm use 22

# Docker
# Install Docker Desktop
```

### 3. Start Infrastructure

```bash
cd infrastructure/environments/local
docker compose up -d
```

### 4. Initialize Database

```bash
cd backend
./gradlew flywayMigrate
```

### 5. Start Applications

```bash
# Backend
cd backend
./gradlew bootRun

# Frontend
cd apps/platform
npm install
npm run dev
```

## IDE Configuration

### IntelliJ IDEA

```
1. Import Gradle project
2. Set JDK to Temurin 21
3. Enable Lombok plugin
4. Configure code style
5. Set up run configurations
```

### VS Code

```
1. Install extensions:
   - TypeScript and JavaScript
   - Tailwind CSS
   - ESLint
   - Prettier
   - GitLens
   - Docker
2. Configure settings
3. Set up workspace
```

## Port Allocation

```
Application:
  Platform:           3000
  Admin:              3001
  API Gateway:        4000

Services:
  Identity:           4001
  Academic:           4002
  Career:             4003
  Financial:          4004
  Institution:        4005
  Communication:      4006
  Analytics:          4007
  AI:                 4008

Infrastructure:
  PostgreSQL:         5432
  Redis:              6379
  Kafka:              9092
  MinIO:              9000
  MinIO Console:      9001
  Grafana:            3000 (dev only)
  Prometheus:         9090 (dev only)
```

## Environment Variables

### Local Development

```bash
# Database
DATABASE_URL=postgresql://postgres:password@localhost:5432/edubridge

# Redis
REDIS_URL=redis://localhost:6379

# Kafka
KAFKA_BROKERS=localhost:9092

# MinIO
MINIO_ENDPOINT=localhost:9000
MINIO_ACCESS_KEY=minioadmin
MINIO_SECRET_KEY=minioadmin

# Authentication
JWT_SECRET=dev-secret-min-32-chars-long!!
JWT_EXPIRY=7d

# Application
APP_ENV=development
APP_PORT=4000
APP_URL=http://localhost:4000
```

## Validation

```bash
# Run all checks
./scripts/validation/validate-all.sh

# Run tests
./gradlew test
npm test

# Run linting
./gradlew check
npm run lint
```

## Never

```
- Use different JDK versions
- Skip Docker setup
- Use production credentials locally
- Skip environment validation
- Commit .env files
- Skip IDE configuration
```
