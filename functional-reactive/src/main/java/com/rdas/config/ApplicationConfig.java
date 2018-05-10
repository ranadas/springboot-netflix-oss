package com.rdas.config;

import com.rdas.router.MessageRouter;
import com.rdas.service.MessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

@Configuration
public class ApplicationConfig {
    @Bean
    RouterFunction<?> mainRouterFunction(final MessageHandler messageHandler) {
        return MessageRouter.doRoutes(messageHandler);
    }
}
