package com.p2pagent.payment;

import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Payment {

    private final String orderId;
    private final String fromAddress;
    private final String toAddress;
    private final BigInteger amountWei;

    private String txHash;
    private Status status;

    public Payment(String orderId,
                   String fromAddress,
                   String toAddress,
                   BigInteger amountWei) {

        this.orderId = orderId;
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.amountWei = amountWei;
        this.status = Status.CREATED;
    }

    public BigDecimal getAmountEth() {
        return Convert.fromWei(new BigDecimal(amountWei), Convert.Unit.ETHER);
    }

    public void markSubmitted(String txHash) {
        this.txHash = txHash;
        this.status = Status.SUBMITTED;
    }

    public void markFailed() {
        this.status = Status.FAILED;
    }

    public String getOrderId() { return orderId; }
    public String getFromAddress() { return fromAddress; }
    public String getToAddress() { return toAddress; }
    public BigInteger getAmountWei() { return amountWei; }
    public String getTxHash() { return txHash; }
    public Status getStatus() { return status; }

    public enum Status {
        CREATED,
        SUBMITTED,
        CONFIRMED,
        FAILED
    }
}