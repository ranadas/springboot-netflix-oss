package com.rdas.config;

import com.rdas.router.MessageRouter;
import com.rdas.service.MessageHandler;
import com.rdas.service.MessageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;

@Configuration
public class ApplicationConfig {
    @Bean
    MessageService messageService() {
        return new MessageService();
    }

    @Bean
    RouterFunction<?> mainRouterFunction(final MessageHandler messageHandler) {
        return MessageRouter.doRoutes(messageHandler);
    }

    @Bean
    MessageHandler messageHandler(final MessageService messageService) {
        return new MessageHandler(messageService);
    }
}
