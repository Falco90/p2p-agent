package com.p2pagent.order.payload;

public record OrderStatusPayload(
        String message,
        String status
) implements HasMessage {}