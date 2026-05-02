package com.p2pagent.web3;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

import java.io.File;
import java.nio.file.Files;

@Component
public class WalletService {

    private final String basePath;
    private final String password;
    private final String peerId;
    private final String role;

    private Credentials credentials;

    public WalletService(
            @Value("${wallet.base-path}") String basePath,
            @Value("${wallet.password}") String password,
            @Value("${axl.peerId}") String peerId,
            @Value("${agent.role}") String role
    ) {
        this.basePath = basePath;
        this.password = password;
        this.peerId = peerId;
        this.role = role;
    }

    @PostConstruct
    public void init() {
        try {
            String walletPath = resolveWalletPath();

            File dir = new File(basePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File walletFile = new File(walletPath);

            if (!walletFile.exists()) {
                System.out.println("No wallet found for " + role + " with peerId " + peerId + ", creating one...");

                String generatedFile = WalletUtils.generateNewWalletFile(
                        password,
                        dir,
                        true
                );

                File generated = new File(dir, generatedFile);

                Files.move(
                        generated.toPath(),
                        walletFile.toPath()
                );

                System.out.println("Wallet created: " + walletPath);
            }

            this.credentials = WalletUtils.loadCredentials(password, walletPath);

            System.out.println("Wallet loaded for " + role + " with peerId " + peerId);
            System.out.println("Address: " + getAddress());

        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize wallet", e);
        }
    }

    private String resolveWalletPath() {
        return basePath + "/" + peerId + ".json";
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public String getAddress() {
        return credentials.getAddress();
    }
}