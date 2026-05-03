package com.p2pagent.agent;

import com.p2pagent.axl.AxlProperties;
import com.p2pagent.ens.EnsService;
import com.p2pagent.web3.WalletService;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import org.web3j.crypto.Credentials;

import java.util.List;

@Service
public class AgentStartupService {

    private final EnsService ensService;
    private final WalletService walletService;
    private final AxlProperties axlProperties;
    private final AgentProperties agentProperties;

    private String ensName;

    public AgentStartupService(EnsService ensService,
                               WalletService walletService,
                               AxlProperties axlProperties,
                               AgentProperties agentProperties) {
        this.ensService = ensService;
        this.walletService = walletService;
        this.axlProperties = axlProperties;
        this.agentProperties = agentProperties;
    }

    @PostConstruct
    public void init() {

        String role = agentProperties.getRole();
        List<String> services = agentProperties.getServices();

        String servicesString = String.join(",", services);

        Credentials agentCredentials = walletService.getCredentials();

        String peerId = axlProperties.getPeerId();
        String walletAddress = walletService.getAddress();

        System.out.println("[Startup] Initializing agent...");
        System.out.println("[Startup] Role: " + role);
        System.out.println("[Startup] PeerId: " + peerId);
        System.out.println("[Startup] Wallet address: " + walletAddress);

        ensName = ensService.createOrGetSubdomain(
                role,
                peerId,
                walletAddress
        );

        System.out.println("[ENS] Assigned name: " + ensName);

        ensService.ensureTextRecord(ensName, "peerId", peerId, agentCredentials);
        ensService.ensureTextRecord(ensName, "role", role, agentCredentials);
        ensService.ensureTextRecord(ensName, "walletAddress", walletAddress, agentCredentials);
        ensService.ensureTextRecord(ensName, "services", servicesString, agentCredentials);

        System.out.println("[ENS] Metadata published");
    }

}