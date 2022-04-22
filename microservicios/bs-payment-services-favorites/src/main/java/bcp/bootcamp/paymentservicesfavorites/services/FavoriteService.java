package bcp.bootcamp.paymentservicesfavorites.services;

import bcp.bootcamp.paymentservicesfavorites.entities.Favorite;
import bcp.bootcamp.paymentservicesfavorites.handlers.FavoriteHandler;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FavoriteService {
    Flux<Favorite> findById(String userId);
    Mono<Favorite> registerFavorite(Favorite favorite);
}
