package com.p2pagent.order;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrderService {

    private final Map<String, Order> orders = new ConcurrentHashMap<>();

    public void handleEvent(OrderEvent event) {

        Order order = orders.computeIfAbsent(
                event.orderId(),
                id -> new Order(id, event.fromPeerId(), event.toPeerId())
        );

        order.apply(event);

        System.out.println("Order updated: " + order.getState());
    }

    public Order getOrder(String orderId) {
        return orders.get(orderId);
    }
}