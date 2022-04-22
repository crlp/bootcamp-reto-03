package bcp.bootcamp.paymentservices.services.impl;

import bcp.bootcamp.paymentservices.entities.Operation;
import bcp.bootcamp.paymentservices.repositories.OperationRepository;
import bcp.bootcamp.paymentservices.repositories.ServiceRepository;
import bcp.bootcamp.paymentservices.services.OperationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private OperationRepository operationRepository;

    @Override
    public Flux<bcp.bootcamp.paymentservices.entities.Service> findByChannel(String channel) {
        return serviceRepository.findByChannel(channel);
    }

    @Override
    public Mono<Operation> save(Operation operation) {
        return operationRepository.save(operation);
    }
}
