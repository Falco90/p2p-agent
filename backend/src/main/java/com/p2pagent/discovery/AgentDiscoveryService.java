package com.p2pagent.discovery;

import com.p2pagent.ens.EnsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AgentDiscoveryService {

    private final EnsService ensService;
    private final String rootDomain;

    public AgentDiscoveryService(EnsService ensService,
                                 @Value("${ens.root-domain}") String rootDomain) {
        this.ensService = ensService;
        this.rootDomain = rootDomain;
    }

    public DiscoveredAgent findByRole(String role) {

        String ensName = role + "." + rootDomain;

        String peerId = ensService.resolvePeerId(ensName);
        String resolvedRole = ensService.resolveRole(ensName);
        String walletAddress = ensService.getText(ensName, "walletAddress");
        List<String> services = parseServices(
                ensService.getText(ensName, "services")
        );

        if (peerId == null || peerId.isBlank()) {
            throw new RuntimeException("No peerId found for " + ensName);
        }

        return new DiscoveredAgent(
                ensName,
                peerId,
                resolvedRole != null ? resolvedRole : role,
                walletAddress,
                services
        );
    }

    private List<String> parseServices(String raw) {
        if (raw == null || raw.isBlank()) return List.of();
        return Arrays.stream(raw.split(","))
                .map(String::trim)
                .toList();
    }
}