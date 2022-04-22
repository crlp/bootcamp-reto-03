package bcp.bootcamp.paymentservicesfavorites.services.impl;

import bcp.bootcamp.paymentservicesfavorites.entities.Favorite;
import bcp.bootcamp.paymentservicesfavorites.repositories.FavoriteRepository;
import bcp.bootcamp.paymentservicesfavorites.services.FavoriteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Override
    public Flux<Favorite> findById(String userId) {
        return favoriteRepository.findAllByUser(userId);
    }

    @Override
    public Mono<Favorite> registerFavorite(Favorite favorite) {
        return favoriteRepository.save(favorite);
    }
}
