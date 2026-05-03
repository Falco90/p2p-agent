package com.p2pagent.discovery;

import com.p2pagent.ens.EnsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AgentDiscoveryService {

    private final EnsService ensService;
    private final String rootDomain;

    private final Map<String, DiscoveredAgent> byPeerId =
            new ConcurrentHashMap<>();

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

        DiscoveredAgent agent = new DiscoveredAgent(
                ensName,
                peerId,
                resolvedRole != null ? resolvedRole : role,
                walletAddress,
                services
        );

        byPeerId.put(peerId, agent);

        return agent;
    }

    public DiscoveredAgent findByPeerId(String peerId) {

        DiscoveredAgent agent = byPeerId.get(peerId);

        if (agent != null) {
            return agent;
        }

        throw new RuntimeException("No agent found for peerId: " + peerId);
    }

    private List<String> parseServices(String raw) {
        if (raw == null || raw.isBlank()) return List.of();
        return Arrays.stream(raw.split(","))
                .map(String::trim)
                .toList();
    }

    public List<DiscoveredAgent> getAllKnownAgents() {

        return List.of(
                findByRole("baker"),
                findByRole("farmer"),
                findByRole("guard")
        );
    }
}