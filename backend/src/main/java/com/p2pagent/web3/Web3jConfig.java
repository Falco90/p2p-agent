package com.p2pagent.web3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

@Configuration
public class Web3jConfig {

    @Bean(name = "ensWeb3j")
    public Web3j ensWeb3j(@Value("${ens.rpc.url}") String rpc) {
        return Web3j.build(new HttpService(rpc));
    }

    @Bean(name = "paymentWeb3j")
    public Web3j paymentWeb3j(@Value("${payment.rpc.url}") String rpc) {
        return Web3j.build(new HttpService(rpc));
    }

    @Bean
    public Credentials rootCredentials(
            @Value("${ens.root-private-key}") String privateKey
    ) {
        return Credentials.create(privateKey);
    }
}