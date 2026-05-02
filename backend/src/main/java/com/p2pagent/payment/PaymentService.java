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

    public String send(Payment payment) {

        try {
            String txHash = ethereumService.sendEth(
                    payment.getToAddress(),
                    payment.getAmountEth()
            );

            payment.markSubmitted(txHash);

            return txHash;

        } catch (Exception e) {
            payment.markFailed();
            throw new RuntimeException("Payment failed", e);
        }
    }
}