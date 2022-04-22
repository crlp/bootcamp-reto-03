package bcp.bootcamp.uxpaymentservice.services.impl;

import bcp.bootcamp.uxpaymentservice.entities.Operation;
import bcp.bootcamp.uxpaymentservice.entities.Service;
import bcp.bootcamp.uxpaymentservice.exceptions.ServiceBaseException;
import bcp.bootcamp.uxpaymentservice.repositories.OperationRepository;
import bcp.bootcamp.uxpaymentservice.repositories.ServiceRepository;
import bcp.bootcamp.uxpaymentservice.services.OperationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@org.springframework.stereotype.Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private OperationRepository operationRepository;

    @Override
    public Flux<Service> findByChannel(String channel) {
        return serviceRepository
                .findByChannel(channel)
                .onErrorResume(e -> Mono.empty())
                .switchIfEmpty(Mono.error(new ServiceBaseException(HttpStatus.NOT_FOUND,"Canal no encontrado")))
                .map(service -> {
                    Service s = new Service();
                    s.setChannel(service.getChannel());
                    s.setCode(service.getCode());
                    s.setId(service.getId());
                    s.setName(service.getName());
                    return s;
                });

    }

    @Override
    public Mono<Operation> save(Operation operation) {
        return operationRepository
                .save(operation)
                .onErrorResume(e -> Mono.empty())
                .switchIfEmpty(Mono.error(new ServiceBaseException(HttpStatus.NOT_FOUND,"Operacion no encontrado")))
                .map(operationResponse -> {
                    Operation op = new Operation();
                    op.setId(operationResponse.getId());
                    op.setSupply(op.getSupply());
                    op.setCode(operationResponse.getCode());
                    op.setChannel(operationResponse.getChannel());
                    op.setAmount(operationResponse.getAmount());
                    op.setFavorite(operationResponse.getFavorite());
                    op.setDate(operationResponse.getDate());
                    return op;
                });
    }

}
