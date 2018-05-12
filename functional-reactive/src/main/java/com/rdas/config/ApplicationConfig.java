package com.rdas.config;

import com.rdas.router.HelloWorldRouter;
import com.rdas.router.MessageRouter;
import com.rdas.service.HelloWorldHandler;
import com.rdas.service.MessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class ApplicationConfig {
    @Bean
    RouterFunction<?> mainRouterFunction(final MessageHandler messageHandler) {
        return MessageRouter.doRoutes(messageHandler);
    }

    @Bean
    public RouterFunction<?> helloWorldRouteFunction(HelloWorldHandler helloWorldHandler) {
        return HelloWorldRouter.doRoutes(helloWorldHandler);
    }
}
