package com.p2pagent.agent;

import com.p2pagent.axl.AxlProperties;
import com.p2pagent.ens.EnsService;
import com.p2pagent.web3.WalletService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import org.web3j.crypto.Credentials;

@Service
public class AgentStartupService {

    private final EnsService ensService;
    private final WalletService walletService;
    private final AxlProperties axlProperties;

    @Value("${agent.role}")
    private String role;

    private String ensName;

    public AgentStartupService(EnsService ensService,
                               WalletService walletService,
                               AxlProperties axlProperties) {
        this.ensService = ensService;
        this.walletService = walletService;
        this.axlProperties = axlProperties;
    }

    @PostConstruct
    public void init() {

        Credentials agentCredentials = walletService.getCredentials();

        String peerId = axlProperties.getPeerId();
        String walletAddress = walletService.getAddress();

        System.out.println("[Startup] Initializing agent...");
        System.out.println("[Startup] Role: " + role);
        System.out.println("[Startup] PeerId: " + peerId);
        System.out.println("[Startup] Wallet: " + walletAddress);

        ensName = ensService.createOrGetSubdomain(
                role,
                peerId,
                walletAddress
        );

        System.out.println("[ENS] Assigned name: " + ensName);

        ensService.ensureTextRecord(ensName, "peerId", peerId, agentCredentials);
        ensService.ensureTextRecord(ensName, "role", role, agentCredentials);
        ensService.ensureTextRecord(ensName, "wallet", walletAddress, agentCredentials);
        ensService.ensureTextRecord(ensName, "services", resolveServices(role), agentCredentials);

        System.out.println("[ENS] Metadata published");
    }

    private String resolveServices(String role) {
        return switch (role) {
            case "baker" -> "bread";
            case "farmer" -> "flour,wheat";
            case "guard" -> "protection";
            default -> "unknown";
        };
    }

    public String getEnsName() {
        return ensName;
    }
}