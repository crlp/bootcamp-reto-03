package bcp.bootcamp.uxpaymentservice.services;
import bcp.bootcamp.uxpaymentservice.entities.Operation;
import bcp.bootcamp.uxpaymentservice.entities.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OperationService {
    Flux<Service> findByChannel(String channel);
    Mono<Operation> save(Operation operation);
}
