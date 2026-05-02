package com.p2pagent.order.payload;

public record OrderAcceptedPayload(
        String message,
        String sellerWalletAddress,
        String priceEth
) implements HasMessage {}