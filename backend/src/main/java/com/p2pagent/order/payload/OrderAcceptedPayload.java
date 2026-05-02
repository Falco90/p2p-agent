package com.p2pagent.order.payload;

public record OrderAcceptedPayload(
        String message,
        String paymentAddress,
        String priceEth
) implements HasMessage {}