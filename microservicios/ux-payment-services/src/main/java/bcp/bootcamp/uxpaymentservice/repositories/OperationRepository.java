package bcp.bootcamp.uxpaymentservice.repositories;


import bcp.bootcamp.uxpaymentservice.entities.Operation;
import reactor.core.publisher.Mono;

public interface OperationRepository {
     Mono<Operation> save(Operation operation);
}
