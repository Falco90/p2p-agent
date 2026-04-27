package com.p2pagent.agent;

import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;

import java.math.BigInteger;

@Service
public class EthereumService {

    private final Web3j web3j;

    public EthereumService(Web3j web3j) {
        this.web3j = web3j;
    }

    public BigInteger getBalance(String address) throws Exception {
        return web3j.ethGetBalance(
                address,
                DefaultBlockParameterName.LATEST
        ).send().getBalance();
    }
}