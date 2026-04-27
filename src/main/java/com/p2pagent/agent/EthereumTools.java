package com.p2pagent.agent;

import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Component;

@Component
public class EthereumTools {

    private final EthereumService ethereumService;

    public EthereumTools(EthereumService ethereumService) {
        this.ethereumService = ethereumService;
    }

    @Tool("Returns ETH balance in wei for a given Ethereum address")
    public String getBalance(String address) {
        try {
            return ethereumService.getBalance(address).toString();
        } catch (Exception e) {
            e.printStackTrace(); // 👈 IMPORTANT
            return "ERROR: " + e.getClass().getSimpleName() + " - " + e.getMessage();
        }
    }
}