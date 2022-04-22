package bcp.bootcamp.paymentservices.repositories;

import bcp.bootcamp.paymentservices.entities.Operation;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface OperationRepository extends ReactiveCrudRepository<Operation,Integer> {
}
