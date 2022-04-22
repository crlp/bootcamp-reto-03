package bcp.bootcamp.uxpaymentservice.repositories;


import bcp.bootcamp.uxpaymentservice.entities.Service;
import reactor.core.publisher.Flux;

public interface ServiceRepository {

    Flux<Service> findByChannel(String channel);

}
