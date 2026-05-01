package com.p2pagent.agent;

public record ChatMessage(
        String fromPeerId,
        String message,
        long timestamp
) {}