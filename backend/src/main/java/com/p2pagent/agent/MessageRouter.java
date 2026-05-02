package com.p2pagent.agent;

import com.p2pagent.order.OrderEvent;
import com.p2pagent.order.OrderService;
import org.springframework.stereotype.Component;

@Component
public class MessageRouter {

    private final EventMapper eventMapper;
    private final OrderService orderService;
    private final ChatService chatService;

    public MessageRouter(EventMapper eventMapper,
                         OrderService orderService,
                         ChatService chatService) {
        this.eventMapper = eventMapper;
        this.orderService = orderService;
        this.chatService = chatService;
    }

    public void route(AgentMessage<?> msg) {

        switch (msg.type()) {

            case SERVICE_REQUEST,
                 PAYMENT_SENT,
                 PAYMENT_CONFIRMED,
                 QUOTE,
                 ORDER_ACCEPTED,
                 ORDER_COMPLETED -> {

                OrderEvent event = eventMapper.toEvent(msg);
                orderService.handleEvent(event);
            }

            case CHAT -> {
                chatService.handle(msg);
            }

            default -> {
                System.out.println("Unknown message type: " + msg.type());
            }
        }
    }
}