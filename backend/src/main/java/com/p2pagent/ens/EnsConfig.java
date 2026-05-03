package com.p2pagent.ens;

import com.p2pagent.ens.contract.EnsRegistry;
import com.p2pagent.ens.contract.EnsResolver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.DefaultGasProvider;

@Configuration
public class EnsConfig {

    @Bean
    public EnsRegistry ensRegistry(@Qualifier("ensWeb3j") Web3j web3j,
                                   Credentials rootCredentials,
                                   @Value("${ens.registry.address}") String address) {

        return EnsRegistry.load(
                address,
                web3j,
                rootCredentials,
                new DefaultGasProvider()
        );
    }

    @Bean
    public EnsResolver ensResolver(@Qualifier("ensWeb3j") Web3j web3j,
                                   Credentials rootCredentials,
                                   @Value("${ens.resolver.address}") String address) {

        return EnsResolver.load(
                address,
                web3j,
                rootCredentials,
                new DefaultGasProvider()
        );
    }
}