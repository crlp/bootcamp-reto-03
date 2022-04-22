package bcp.bootcamp.paymentservicesfavorites.handlers;

import bcp.bootcamp.paymentservicesfavorites.core.exceptions.FavoriteBaseException;
import bcp.bootcamp.paymentservicesfavorites.entities.Favorite;
import bcp.bootcamp.paymentservicesfavorites.services.FavoriteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class FavoriteHandler {

    @Autowired
    private FavoriteService favoriteService;

    public Mono<ServerResponse> findById(ServerRequest serverRequest){
        String id = serverRequest.pathVariable("id").toUpperCase();
        return this.favoriteService.findById(id)
                .switchIfEmpty(Mono.error(new FavoriteBaseException("No se encontró elementos")))
                .collectList()
                .flatMap(list-> ServerResponse.ok().body(Mono.just(list), Favorite.class));
    }


    public Mono<ServerResponse> registerFavorite(ServerRequest request) {
        return request.bodyToMono(Favorite.class)
                .flatMap(favorite -> this.favoriteService.registerFavorite(favorite))
                .flatMap(favorite -> ServerResponse.ok().body(Mono.just(favorite), Favorite.class));
    }

}
