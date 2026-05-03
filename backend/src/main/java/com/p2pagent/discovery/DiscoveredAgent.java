package com.p2pagent.discovery;

import java.util.List;

public record DiscoveredAgent(
        String ensName,
        String peerId,
        String role,
        String walletAddress,
        List<String> services
) {}