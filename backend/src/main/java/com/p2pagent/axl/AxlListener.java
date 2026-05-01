package com.p2pagent.axl;

import com.p2pagent.agent.AgentMessage;
import com.p2pagent.agent.MessageParser;
import com.p2pagent.agent.MessageRouter;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class AxlListener {

    private final AxlClient axlClient;
    private final MessageParser messageParser;
    private final MessageRouter messageRouter;
    private volatile boolean running = true;

    public AxlListener(AxlClient axlClient, MessageParser messageParser, MessageRouter messageRouter) {
        this.axlClient = axlClient;
        this.messageParser = messageParser;
        this.messageRouter = messageRouter;
    }

    @PostConstruct
    public void start() {
        Thread.startVirtualThread(this::pollLoop);
    }

    private void pollLoop() {
        while (running) {
            try {
                AxlMessage msg = axlClient.recv();

                if (msg != null) {
                    handle(msg);
                } else {
                    Thread.sleep(200);
                }

            } catch (Exception e) {
                e.printStackTrace();
                sleep(1000);
            }
        }
    }

    private void handle(AxlMessage axlMessage) throws Exception {
        System.out.println("Message received");
        System.out.println("From: " + axlMessage.fromPeerId());
        System.out.println("Body: " + axlMessage.body());

        AgentMessage agentMessage = messageParser.parse(
                axlMessage.body(),
                axlMessage.fromPeerId()
        );

        messageRouter.route(agentMessage);

    }

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }

    @PreDestroy
    public void stop() {
        running = false;
    }
}