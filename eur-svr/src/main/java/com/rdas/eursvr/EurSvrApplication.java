package com.rdas.eursvr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurSvrApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurSvrApplication.class, args);
	}
}
