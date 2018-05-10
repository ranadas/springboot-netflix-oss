package com.rdas.service;

import com.rdas.model.Message;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

public class MessageHandler {
    private final MessageService messageService;

    public MessageHandler(MessageService messageService) {
        this.messageService = messageService;
    }

    public Mono<ServerResponse> handleMessage(ServerRequest request) {
        Mono<Message> message = messageService.getMessage();
        return ok().body(fromPublisher(message, Message.class));
    }

    public Mono<ServerResponse> handleMessages(ServerRequest request) {
        Flux<Message> messages = messageService.getMessages();
        return ok().body(fromPublisher(messages, Message.class));
    }
}
