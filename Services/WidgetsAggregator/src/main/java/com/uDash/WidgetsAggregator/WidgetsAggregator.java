package com.uDash.WidgetsAggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@ComponentScan
@EnableDiscoveryClient
@SpringBootApplication
public class WidgetsAggregator extends SpringBootServletInitializer {

    public static void main(String args[]) {
        SpringApplication.run(WidgetsAggregator.class, args);
    }

    @Override
    protected final SpringApplicationBuilder configure (SpringApplicationBuilder application){
        return application.sources(WidgetsAggregator.class);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
