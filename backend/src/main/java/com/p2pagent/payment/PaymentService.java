package com.p2pagent.payment;

import com.p2pagent.web3.EthereumService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {

    private final EthereumService ethereumService;

    public PaymentService(EthereumService ethereumService) {
        this.ethereumService = ethereumService;
    }

    public String send(Payment payment, String toAddress, BigDecimal amountEth) throws Exception {
        String txHash = ethereumService.sendEth(toAddress, amountEth);
        payment.markSubmitted(txHash);

        return txHash;
    }
}