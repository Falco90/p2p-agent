package com.p2pagent.order.payload;

public record ServiceRequestPayload(
        String message,
        String item,
        int quantity
) implements HasMessage {}