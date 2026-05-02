package com.p2pagent.order.payload;

public record CancelledPayload(
        String message,
        String reason
) implements HasMessage {}