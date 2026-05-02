package com.p2pagent.order.payload;

public record OrderCompletedPayload(
        String message
) implements HasMessage {}