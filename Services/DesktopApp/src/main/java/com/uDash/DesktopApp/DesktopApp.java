package com.uDash.DesktopApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class DesktopApp {

    public static void main(String[] args) {
        // Will configure using web-server.yml
        System.setProperty("spring.config.name", "web-server");
        SpringApplication.run(DesktopApp.class, args);
    }

    @LoadBalanced    // Make sure to create the load-balanced template
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * Account service calls microservice internally using provided URL.
     */
//    @Bean
//    public WidgetComponentsService accountsService() {
//        return new WidgetComponentsService(ACCOUNTS_SERVICE_URL);
//    }

//
//    @Bean
//    public WebAccountsController accountsController() {
//        return new WebAccountsController
//                (accountsService());  // plug in account-service
//    }
}
