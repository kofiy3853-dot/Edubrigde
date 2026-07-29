# Sentry JavaScript SDK Configuration (Frontend)
# EduBridge OS Observability Foundation

import * as Sentry from "@sentry/nextjs";

Sentry.init({
  dsn: process.env.NEXT_PUBLIC_SENTRY_DSN,

  environment: process.env.NODE_ENV || "development",
  release: "edubridge@" + process.env.npm_package_version,

  tracesSampleRate: 1.0,
  replaysSessionSampleRate: 0.1,
  replaysOnErrorSampleRate: 1.0,

  integrations: [
    new Sentry.BrowserTracing(),
    new Sentry.Replay(),
  ],

  beforeSend(event) {
    // Filter sensitive data
    if (event.request?.cookies) {
      delete event.request.cookies;
    }
    return event;
  },
});
