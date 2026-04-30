package com.p2pagent.web3;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;

@Component
public class EthereumTools {

    private final EthereumService ethereumService;

    public EthereumTools(EthereumService ethereumService) {
        this.ethereumService = ethereumService;
    }

    @Tool("Returns ETH balance for an Ethereum address")
    public String getBalance(String address) {
        try {
            BigInteger wei = ethereumService.getBalance(address);
            BigDecimal eth = Convert.fromWei(new BigDecimal(wei), Convert.Unit.ETHER);
            return eth + " ETH";
        } catch (Exception e) {
            e.printStackTrace();
            return "FAILED_TO_FETCH_BALANCE";
        }
    }
}