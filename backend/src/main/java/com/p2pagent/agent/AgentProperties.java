package com.p2pagent.agent;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "agent")
public class AgentProperties {

    private String role;
    private List<String> services;
    private String personality;

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public List<String> getServices() { return services; }
    public void setServices(List<String> services) { this.services = services; }

    public String getPersonality() { return personality; }
    public void setPersonality(String personality) { this.personality = personality; }
}