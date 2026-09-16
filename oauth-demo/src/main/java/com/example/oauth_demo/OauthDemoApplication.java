package com.example.oauth_demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OauthDemoApplication {

	private static final Logger logger = LoggerFactory.getLogger(OauthDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OauthDemoApplication.class, args);
		logger.info("Application started successfully on port 8080.");
	}
}
