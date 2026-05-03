package com.p2pagent.ens;

import com.p2pagent.ens.contract.EnsRegistry;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.ens.NameHash;
import com.p2pagent.ens.contract.EnsResolver;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

@Service
public class EnsService {


    private final EnsRegistry ensRegistry;
    private final EnsResolver resolver;
    private final Web3j web3j;

    private final String rootDomain;
    private final String resolverAddress;

    public EnsService(EnsRegistry ensRegistry,
                      EnsResolver resolver,
                      @Qualifier("ensWeb3j") Web3j web3j,
                      @Value("${ens.root-domain}") String rootDomain,
                      @Value("${ens.resolver.address}") String resolverAddress) {

        this.ensRegistry = ensRegistry;
        this.resolver = resolver;
        this.web3j = web3j;
        this.rootDomain = rootDomain;
        this.resolverAddress = resolverAddress;
    }

    public String createOrGetSubdomain(String role, String peerId, String ownerAddress) {

        String fullName = role + "." + rootDomain;

        byte[] node = nameHash(fullName);

        try {
            boolean exists = ensRegistry.recordExists(node).send();

            if (exists) {
                String currentOwner = ensRegistry.owner(node).send();

                System.out.println("[ENS] Subdomain already exists: " + fullName);
                System.out.println("[ENS] Owner: " + currentOwner);

                if (!currentOwner.equalsIgnoreCase(ownerAddress)) {
                    System.out.println("[ENS] WARNING: owner mismatch!");
                }

                return fullName;
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to check ENS record", e);
        }

        try {
            ensRegistry.setSubnodeRecord(
                    nameHash(rootDomain),
                    labelHash(role),
                    ownerAddress,
                    resolverAddress,
                    BigInteger.ZERO
            ).send();

            System.out.println("[ENS] Created subdomain: " + fullName);

            return fullName;

        } catch (Exception e) {
            throw new RuntimeException("Failed to create subdomain", e);
        }
    }

    public void setText(String name, String key, String value, Credentials agentCredentials) {

        byte[] node = nameHash(name);

        EnsResolver resolverWithAgent = EnsResolver.load(
                resolverAddress,
                web3j,
                agentCredentials,
                new DefaultGasProvider()
        );

        try {
            resolverWithAgent.setText(node, key, value).send();

            System.out.println("[ENS] setText " + name + " → " + key + "=" + value);

        } catch (Exception e) {
            throw new RuntimeException("Failed to set text record", e);
        }
    }

    public String getText(String name, String key) {

        byte[] node = nameHash(name);

        try {
            return resolver.text(node, key).send();

        } catch (Exception e) {
            throw new RuntimeException("Failed to get text record", e);
        }
    }

    public String resolvePeerId(String ensName) {
        return getText(ensName, "peerId");
    }

    public String resolveRole(String ensName) {
        return getText(ensName, "role");
    }

    public void ensureTextRecord(String name, String key, String expectedValue, Credentials credentials) {

        String current = getText(name, key);

        if (current == null || !current.equals(expectedValue)) {
            setText(name, key, expectedValue, credentials);
        } else {
            System.out.println("[ENS] " + name + " already has " + key);
        }
    }


    private byte[] nameHash(String name) {
        return NameHash.nameHashAsBytes(name);
    }

    private byte[] labelHash(String label) {
        return org.web3j.crypto.Hash.sha3(label.getBytes(StandardCharsets.UTF_8));
    }
}
