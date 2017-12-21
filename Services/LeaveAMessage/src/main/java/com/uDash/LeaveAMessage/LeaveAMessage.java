package com.uDash.LeaveAMessage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan
@EnableJpaAuditing
public class LeaveAMessage extends SpringBootServletInitializer {
    public static void main(String[] args)  {
        SpringApplication.run(LeaveAMessage.class, args);
    }

    @Override
    protected final SpringApplicationBuilder configure (SpringApplicationBuilder application){
        return application.sources(LeaveAMessage.class);
    }
}
