package com.example.payments;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class RefundServiceTest {

    private final RefundService refunds = new RefundService();

    @Test
    void refusesABlankTransactionId() {
        assertThrows(IllegalArgumentException.class,
            () -> refunds.refund("  ", new BigDecimal("100")));
    }
}
