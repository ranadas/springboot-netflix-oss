package com.rdas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

//https://github.com/vhoang55/spring5-functional-reactive-web
//https://dzone.com/articles/functional-amp-reactive-spring-along-with-netflix
@SpringBootApplication
@EnableWebFlux
public class ReactiveWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReactiveWebApplication.class, args);
    }
}