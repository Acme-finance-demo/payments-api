package com.example.payments;

import java.math.BigDecimal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class App {

    private static final Logger LOGGER = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        LOGGER.info("payments-api starting");
        PaymentProcessor processor = new PaymentProcessor();
        RefundService refunds = new RefundService();

        AuditLog.withCorrelationId("demo-0001", () -> {
            processor.authorize("acct-1001", new BigDecimal("12500"));
            refunds.refund("txn-9001", new BigDecimal("2500"));
        });

        LOGGER.info("payments-api done");
    }
}
