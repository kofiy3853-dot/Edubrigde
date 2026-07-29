# Frontend Standards

## Status

LOCKED - Sprint 0C, Step 13

## Principle

Design system-first. Shared components. No duplication.

## Technology Stack

```
Next.js         16.x    App Router
TypeScript      5.x     Strict mode
TailwindCSS     4.x     Utility-first CSS
Shadcn/UI               Component library
React Hook Form 7.x     Form management
Zod             3.x     Schema validation
TanStack Query  5.x     Server state
Zustand         5.x     Client state
Motion          12.x    Animations
Playwright              E2E testing
Vitest          3.x     Unit testing
```

## Design System Structure

```
components/
  ui/           -> Shadcn/UI components
  forms/        -> Shared form components
  layouts/      -> Shared layouts
  feedback/     -> Notifications, alerts, loading
  navigation/   -> Menus, breadcrumbs, headers
  data-display/ -> Cards, tables, statistics
```

## Shared Packages

```
@edubridge/ui          -> cn() utility
@edubridge/types       -> BaseEntity, ApiResponse, Pagination
@edubridge/validators  -> email, password, name, login, register
@edubridge/constants   -> APP_NAME, ROUTES, HTTP_STATUS
@edubridge/config      -> API config, app config
@edubridge/themes      -> Theme management
@edubridge/utils       -> formatCurrency, formatDate, debounce
```

## Component Rules

```
1. All components use TypeScript
2. All components are accessible (WCAG 2.1 AA)
3. All components use TailwindCSS
4. All components use Shadcn/UI patterns
5. No feature-specific components
6. No duplicated components
7. No API calls inside components
8. No business logic inside UI
```

## Form Rules

```
1. All forms use React Hook Form
2. All validation uses Zod
3. All validators use shared schemas
4. All forms have loading states
5. All forms have error states
6. All forms have success states
7. All forms are accessible
```

## State Management Rules

```
Zustand          -> Client state (UI state, preferences)
TanStack Query   -> Server state (API data)
Services         -> API communications
Modules          -> Business features
```

## Theme Rules

```
1. Light theme by default
2. Dark theme supported
3. System theme supported
4. All colors use CSS variables
5. All spacing follows TailwindCSS defaults
6. All typography follows TailwindCSS defaults
```

## Testing Rules

```
1. Component tests for all shared components
2. Accessibility tests for all components
3. Form tests for all forms
4. Validation tests for all validators
5. Integration tests for feature modules
6. E2E tests for critical paths
```

## Never

```
- Build pages before design system
- Create feature-specific components
- Duplicate existing components
- Skip accessibility testing
- Use inline styles
- Use CSS modules
- Skip type safety
```
