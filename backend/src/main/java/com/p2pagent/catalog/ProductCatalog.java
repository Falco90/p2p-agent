package com.p2pagent.catalog;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "catalog")
public class ProductCatalog {

    private Map<String, BigDecimal> items;

    public Map<String, BigDecimal> getItems() {
        return items;
    }

    public void setItems(Map<String, BigDecimal> items) {
        this.items = items;
    }

    public BigDecimal getPrice(String item) {
        return items.get(item.toLowerCase());
    }

    public boolean hasItem(String item) {
        return items.containsKey(item.toLowerCase());
    }
}