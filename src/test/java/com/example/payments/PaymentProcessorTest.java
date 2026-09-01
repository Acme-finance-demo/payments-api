package com.example.payments;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class PaymentProcessorTest {

    private final PaymentProcessor processor = new PaymentProcessor();

    @Test
    void authorizesAnAmountUnderTheLimit() {
        assertTrue(processor.authorize("acct-1001", new BigDecimal("12500")));
    }

    @Test
    void rejectsANonPositiveAmount() {
        assertFalse(processor.authorize("acct-1001", BigDecimal.ZERO));
    }

    @Test
    void rejectsAnAmountOverTheLimit() {
        assertFalse(processor.authorize("acct-1001", new BigDecimal("500001")));
    }
}
