package com.p2pagent.ens;

import com.p2pagent.ens.contract.EnsRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.web3j.ens.NameHash;
import org.web3j.ens.contracts.generated.PublicResolver;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

@Service
public class EnsService {


    private final EnsRegistry ensRegistry;
    private final PublicResolver resolver;

    private final String rootDomain;
    private final String resolverAddress;

    public EnsService(EnsRegistry ensRegistry,
                      PublicResolver resolver,
                      @Value("${ens.root-domain}") String rootDomain,
                      @Value("${ens.resolver.address}") String resolverAddress) {

        this.ensRegistry = ensRegistry;
        this.resolver = resolver;
        this.rootDomain = rootDomain;
        this.resolverAddress = resolverAddress;
    }

    public String createSubdomain(String role, String peerId, String ownerAddress) {

        String label = role + "-" + peerId.substring(0, 6);
        String fullName = label + "." + rootDomain;

        System.out.println("[ENS] Creating subdomain: " + fullName);

        try {
            ensRegistry.setSubnodeRecord(
                    nameHash(rootDomain),
                    labelHash(label),
                    ownerAddress,
                    resolverAddress,
                    BigInteger.ZERO
            ).send();

            return fullName;

        } catch (Exception e) {
            throw new RuntimeException("Failed to create subdomain: " + fullName, e);
        }
    }

    public void setText(String name, String key, String value) {

        byte[] node = nameHash(name);

        try {
            resolver.setText(node, key, value).send();

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

    public String resolveService(String ensName) {
        return getText(ensName, "services");
    }

    public String resolveRole(String ensName) {
        return getText(ensName, "role");
    }

    public void ensureTextRecord(String name, String key, String expectedValue) {

        String current = getText(name, key);

        if (current == null || !current.equals(expectedValue)) {
            setText(name, key, expectedValue);
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
