package bcp.bootcamp.paymentservicesfavorites.repositories;

import bcp.bootcamp.paymentservicesfavorites.entities.Favorite;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface FavoriteRepository extends ReactiveMongoRepository<Favorite,String> {
    Flux<Favorite> findAllByUser(String userId);
}
