package com.p2pagent.order.payload;

public record QuotePayload(
        String message,
        String priceEth
) implements HasMessage {}
