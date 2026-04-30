package com.p2pagent.axl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AxlClient {

    private final RestTemplate restTemplate;

    @Value("${axl.base.url}")
    private String baseUrl;

    public AxlClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String sendUrl() {
        return baseUrl + "/send";
    }

    private String recvUrl() {
        return baseUrl + "/recv";
    }

    public AxlMessage recv() {
        try {
            ResponseEntity<String> response =
                    restTemplate.getForEntity(recvUrl(), String.class);

            String body = response.getBody();
            String fromPeerId = response.getHeaders()
                    .getFirst("X-From-Peer-Id");

            if (body == null) {
                return null;
            }

            return new AxlMessage(body, fromPeerId);

        } catch (Exception e) {
            // queue empty or temporary error → return null
            return null;
        }
    }

    public void send(String destinationPeerId, String message) {
        try {
            org.springframework.http.HttpHeaders headers =
                    new org.springframework.http.HttpHeaders();
            headers.add("X-Destination-Peer-Id", destinationPeerId);

            org.springframework.http.HttpEntity<String> request =
                    new org.springframework.http.HttpEntity<>(message, headers);

            restTemplate.postForEntity(sendUrl(), request, String.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}