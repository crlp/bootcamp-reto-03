package bcp.bootcamp.paymentservicesfavorites;

import bcp.bootcamp.paymentservicesfavorites.entities.Favorite;
import bcp.bootcamp.paymentservicesfavorites.repositories.FavoriteRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Pago de servicios favoritos", version = "1.0", description = "Documentation APIs v1.0"))
public class PaymentServicesFavoritesApplication {

	@Autowired
	FavoriteRepository favoriteRepository;

	public static void main(String[] args) {
		SpringApplication.run(PaymentServicesFavoritesApplication.class, args);
	}

}
