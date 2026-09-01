package com.example.payments;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

/**
 * Every audited action carries a correlation id. The id is put on Log4j's
 * ThreadContext so both the console pattern layout (%X{correlationId}) and the
 * JSON audit appender pick it up without the call sites having to pass it around.
 */
public final class AuditLog {

    private static final Logger LOGGER = LogManager.getLogger(AuditLog.class);

    private AuditLog() {
    }

    public static void withCorrelationId(String correlationId, Runnable action) {
        ThreadContext.put("correlationId", correlationId);
        try {
            action.run();
        } finally {
            ThreadContext.remove("correlationId");
        }
    }

    public static void record(String action, String subject, String outcome) {
        LOGGER.info("action={} subject={} outcome={}", action, subject, outcome);
    }

    public static void recordRejected(String action, String subject, String reason) {
        LOGGER.warn("action={} subject={} outcome=rejected reason={}", action, subject, reason);
    }
}
