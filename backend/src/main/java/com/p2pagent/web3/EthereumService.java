package com.p2pagent.web3;

import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Transfer;
import org.web3j.crypto.Credentials;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

@Service
public class EthereumService {

    private final Web3j web3j;
    private final WalletService walletService;

    public EthereumService(Web3j web3j, WalletService walletService) {
        this.web3j = web3j;
        this.walletService = walletService;
    }

    public BigInteger getBalance(String address) throws Exception {
        return web3j.ethGetBalance(
                address,
                DefaultBlockParameterName.LATEST
        ).send().getBalance();
    }

    public String sendEth(String toAddress, BigDecimal amountEth) throws Exception {

        Credentials credentials = walletService.getCredentials();

        TransactionReceipt receipt = Transfer.sendFunds(
                web3j,
                credentials,
                toAddress,
                amountEth,
                Convert.Unit.ETHER
        ).send();

        return receipt.getTransactionHash();
    }

    public Optional<TransactionReceipt> getTransactionReceipt(String txHash) throws Exception {
        return web3j.ethGetTransactionReceipt(txHash)
                .send()
                .getTransactionReceipt();
    }

    public TransactionReceipt waitForConfirmation(String txHash,
                                                  int maxAttempts,
                                                  long sleepMs) throws Exception {

        for (int i = 0; i < maxAttempts; i++) {

            Optional<TransactionReceipt> receipt = getTransactionReceipt(txHash);

            if (receipt.isPresent()) {
                return receipt.get();
            }

            Thread.sleep(sleepMs);
        }

        throw new RuntimeException("Transaction not confirmed: " + txHash);
    }
}