package bcp.bootcamp.paymentservices.handlers;

import bcp.bootcamp.paymentservices.entities.Operation;
import bcp.bootcamp.paymentservices.entities.Service;
import bcp.bootcamp.paymentservices.exceptions.ServiceBaseException;
import bcp.bootcamp.paymentservices.services.OperationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class OperationHandler {

    @Autowired
    private OperationService operationService;

    public Mono<ServerResponse> findByChannel(ServerRequest serverRequest){
        String channel = serverRequest.pathVariable("channel").toUpperCase();
        return this.operationService.findByChannel(channel)
                .switchIfEmpty(Mono.error(new ServiceBaseException("No se encontró elementos")))
                .collectList()
                .flatMap(list-> ServerResponse.ok().body(Mono.just(list), Service.class));
    }

    public Mono<ServerResponse> save(ServerRequest serverRequest){
        return serverRequest.bodyToMono(Operation.class)
                .flatMap(operation -> this.operationService.save(operation))
                .flatMap(operation -> ServerResponse.ok().body(Mono.just(operation), Operation.class));
    }
}
