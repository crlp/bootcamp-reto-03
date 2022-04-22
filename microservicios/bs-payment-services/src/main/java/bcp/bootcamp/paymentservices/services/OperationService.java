package bcp.bootcamp.paymentservices.services;

import bcp.bootcamp.paymentservices.entities.Operation;
import bcp.bootcamp.paymentservices.entities.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OperationService {
    Flux<Service> findByChannel(String channel);
    Mono<Operation> save(Operation operation);
}
