# Accessibility Standards

## Status

LOCKED - Sprint 0A, Step 04

## Rule

Accessibility is mandatory. Every UI component must be usable by everyone.

## WCAG 2.1 Compliance

EduBridge OS targets WCAG 2.1 Level AA compliance.

## Requirements

### 1. Keyboard Navigation

All interactive elements must be keyboard accessible.

```
Requirements:
  - Tab order follows logical sequence
  - All interactive elements focusable
  - Focus visible on all elements
  - No keyboard traps
  - Skip navigation links provided
  - Escape closes modals/dropdowns
  - Enter/Space activates buttons
  - Arrow keys navigate within components
```

### 2. Screen Readers

Content must be accessible to screen readers.

```
Requirements:
  - Semantic HTML elements used
  - ARIA labels on interactive elements
  - ARIA landmarks for page structure
  - Alt text on all images
  - Hidden decorative elements
  - Live regions for dynamic content
  - Proper heading hierarchy (h1 -> h2 -> h3)
  - Form labels associated with inputs
```

### 3. Semantic Elements

Use correct HTML elements for content.

```
GOOD:
  <nav> for navigation
  <main> for main content
  <article> for articles
  <button> for actions
  <a> for links
  <input> with <label> for forms

BAD:
  <div> for everything
  <span> for buttons
  <div onClick> for links
```

### 4. Responsive Design

Content must work on all screen sizes.

```
Breakpoints:
  - Mobile: 320px - 768px
  - Tablet: 768px - 1024px
  - Desktop: 1024px+

Requirements:
  - Content reflows at all breakpoints
  - No horizontal scrolling on mobile
  - Touch targets minimum 44x44px
  - Text readable without zoom
```

### 5. Focus Management

Focus must be managed correctly.

```
Requirements:
  - Focus moves to new content
  - Focus returns after modal closes
  - Focus trapped in modals
  - Focus visible at all times
  - Focus order logical
```

### 6. Color Contrast

Text must be readable against backgrounds.

```
Requirements:
  - Normal text: 4.5:1 contrast ratio
  - Large text: 3:1 contrast ratio
  - UI components: 3:1 contrast ratio
  - Never use color alone to convey information
```

### 7. Error Accessibility

Errors must be accessible to all users.

```
Requirements:
  - Error messages associated with fields
  - Errors announced to screen readers
  - Errors visible without color alone
  - Errors provide guidance on fix
  - Form submissions announce results
```

### 8. Loading Accessibility

Loading states must be accessible.

```
Requirements:
  - Loading indicators have aria-busy
  - Loading text provided
  - Skeleton screens announced
  - Progress bars have aria-valuenow
```

## Testing

```
Manual:
  - Keyboard-only navigation test
  - Screen reader test (NVDA/VoiceOver)
  - Color contrast check

Automated:
  - axe-core integration
  - Lighthouse accessibility audit
  - ESLint jsx-a11y plugin
```

## Accessibility is Not Optional

Every component must pass accessibility review before approval.
