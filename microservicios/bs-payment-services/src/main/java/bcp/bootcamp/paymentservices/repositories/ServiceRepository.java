package bcp.bootcamp.paymentservices.repositories;

import bcp.bootcamp.paymentservices.entities.Service;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ServiceRepository extends ReactiveCrudRepository<Service,Integer> {

    Flux<Service> findByChannel(String channel);
}
