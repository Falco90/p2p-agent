package com.p2pagent.axl;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class AxlListener {

    private final AxlClient axlClient;
    private volatile boolean running = true;

    public AxlListener(AxlClient axlClient) {
        this.axlClient = axlClient;
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
                    Thread.sleep(200); // queue empty → backoff
                }

            } catch (Exception e) {
                e.printStackTrace();
                sleep(1000);
            }
        }
    }

    private void handle(AxlMessage msg) {
        System.out.println("Message received");
        System.out.println("From: " + msg.getFromPeerId());
        System.out.println("Body: " + msg.getBody());

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