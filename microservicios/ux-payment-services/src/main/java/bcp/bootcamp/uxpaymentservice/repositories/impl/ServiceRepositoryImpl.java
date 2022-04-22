package bcp.bootcamp.uxpaymentservice.repositories.impl;

import bcp.bootcamp.uxpaymentservice.entities.Service;
import bcp.bootcamp.uxpaymentservice.exceptions.ServiceBaseException;
import bcp.bootcamp.uxpaymentservice.repositories.ServiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.util.retry.Retry;

import java.time.Duration;

@Slf4j
@Repository
public class ServiceRepositoryImpl implements ServiceRepository {

    private final WebClient client;

    public ServiceRepositoryImpl(WebClient.Builder builder,
                                @Value( "${application.urlBsPaymentService}" ) String urlApiAuthors){

        // Configurar Response timeout
        HttpClient client = HttpClient.create()
                .responseTimeout(Duration.ofSeconds(5));
        this.client = builder.baseUrl(urlApiAuthors)
                .clientConnector(new ReactorClientHttpConnector(client))
                .build();
    }

    @Override
    public Flux<Service> findByChannel(String channel) {
        return this.client.get().uri("/channel/{channel}", channel).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, response-> Mono.error(new ServiceBaseException("Server error")))
                .bodyToFlux(Service.class)
                .retryWhen(
                        Retry.fixedDelay(2, Duration.ofSeconds(2))
                                .doBeforeRetry(x->  log.info("LOG BEFORE RETRY=" + x))
                                .doAfterRetry(x->  log.info("LOG AFTER RETRY=" + x))
                )
                .doOnError(x-> log.info("LOG ERROR"));
    }
}
