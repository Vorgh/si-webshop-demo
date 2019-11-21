package com.epam.siwebshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication
@EnableIntegration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
