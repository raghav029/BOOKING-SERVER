package com.rtt.luxurycar.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.rtt.luxurycar")
@EnableJpaRepositories(basePackages = "com.rtt.luxurycar")
@EntityScan(basePackages = "com.rtt.luxurycar")
public class LuxuryCarApplication {
    public static void main(String[] args) {
        SpringApplication.run(LuxuryCarApplication.class, args);
    }
}
