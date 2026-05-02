package com.p2pagent.pricing;

import com.p2pagent.catalog.ProductCatalog;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;

@Service
public class PricingService {

    private final ProductCatalog catalog;

    public PricingService(ProductCatalog catalog) {
        this.catalog = catalog;
    }

    public BigInteger calculatePriceWei(String item, int quantity) {

        if (!catalog.hasItem(item)) {
            throw new RuntimeException("Item not available: " + item);
        }

        BigDecimal unitPrice = catalog.getPrice(item);

        BigDecimal totalEth = unitPrice.multiply(BigDecimal.valueOf(quantity));

        return totalEth
                .multiply(new BigDecimal("1000000000000000000"))
                .toBigInteger();
    }
}