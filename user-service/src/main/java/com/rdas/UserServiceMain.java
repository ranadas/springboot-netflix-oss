package com.rdas;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@Slf4j
@EnableEurekaClient
@SpringBootApplication
public class UserServiceMain {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(UserServiceMain.class, args);
    }
}

