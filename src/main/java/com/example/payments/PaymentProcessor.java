package com.example.payments;

import java.math.BigDecimal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** Authorizes a payment and logs the decision. */
public class PaymentProcessor {

    private static final Logger LOGGER = LogManager.getLogger(PaymentProcessor.class);
    private static final BigDecimal LIMIT = new BigDecimal("500000");

    public boolean authorize(String accountId, BigDecimal amount) {
        LOGGER.debug("authorizing account={} amount={}", accountId, amount);

        if (amount.signum() <= 0) {
            LOGGER.warn("rejected non-positive amount for account={}: {}", accountId, amount);
            AuditLog.recordRejected("authorize", accountId, "non-positive amount");
            return false;
        }
        if (amount.compareTo(LIMIT) > 0) {
            LOGGER.warn("rejected account={} amount={} over limit {}", accountId, amount, LIMIT);
            AuditLog.recordRejected("authorize", accountId, "over limit");
            return false;
        }

        LOGGER.info("authorized account={} amount={}", accountId, amount);
        AuditLog.record("authorize", accountId, "approved");
        return true;
    }
}
