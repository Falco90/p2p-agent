package com.p2pagent.order;

public record OrderRequest(
        String targetPeerId,
        String item,
        int quantity
) {}