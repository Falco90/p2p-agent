package com.p2pagent.order.payload;

public record PaymentConfirmedPayload(
        String message,
        String txHash
) implements HasMessage {}