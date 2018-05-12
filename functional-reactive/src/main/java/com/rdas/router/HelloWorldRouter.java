package com.rdas.router;

import com.rdas.service.HelloWorldHandler;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

public class HelloWorldRouter {
    public static RouterFunction<?> doRoutes(final HelloWorldHandler helloWorldHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/helloWorld")
                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), helloWorldHandler::helloWorld);
    }
}
