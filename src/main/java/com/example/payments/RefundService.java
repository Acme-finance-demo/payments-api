package com.example.payments;

import java.math.BigDecimal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** Issues refunds. Failures are logged with the throwable so the stack trace is kept. */
public class RefundService {

    private static final Logger LOGGER = LogManager.getLogger(RefundService.class);

    public void refund(String transactionId, BigDecimal amount) {
        try {
            if (transactionId == null || transactionId.isBlank()) {
                throw new IllegalArgumentException("transactionId is required");
            }
            LOGGER.info("refunding transaction={} amount={}", transactionId, amount);
            AuditLog.record("refund", transactionId, "issued");
        } catch (RuntimeException err) {
            LOGGER.error("refund failed for transaction=" + transactionId, err);
            AuditLog.recordRejected("refund", String.valueOf(transactionId), err.getMessage());
            throw err;
        }
    }
}
