# Sentry Java SDK Configuration (Backend)
# EduBridge OS Observability Foundation

import io.sentry.Sentry;
import io.sentry.SentryOptions;

public class SentryConfig {

    public static void init() {
        Sentry.init(options -> {
            options.setDsn(System.getenv("SENTRY_DSN"));
            options.setEnvironment(System.getenv().getOrDefault("SENTRY_ENVIRONMENT", "development"));
            options.setRelease("edubridge@" + getVersion());
            options.setTracesSampleRate(1.0);
            options.setSampleRate(1.0);
            options.setMaxBreadcrumbs(100);
            options.setAttachStacktrace(true);
            options.setSendDefaultPii(false);

            // Filter sensitive data
            options.beforeSend((event, hint) -> {
                if (event.getRequest() != null && event.getRequest().getCookies() != null) {
                    event.getRequest().setCookies(null);
                }
                return event;
            });
        });
    }

    private static String getVersion() {
        return SentryConfig.class.getPackage().getImplementationVersion();
    }
}
