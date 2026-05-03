package com.p2pagent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class P2PAgentApplication {

	public static void main(String[] args) {
		SpringApplication.run(P2PAgentApplication.class, args);
	}

}
