package bcp.bootcamp.paymentservicesfavorites.routers;

import bcp.bootcamp.paymentservicesfavorites.core.exceptions.GlobalExceptionHandler;
import bcp.bootcamp.paymentservicesfavorites.entities.Favorite;
import bcp.bootcamp.paymentservicesfavorites.handlers.FavoriteHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;


@Configuration
public class RouterConfiguration {

    @Bean
    @RouterOperations(
            {
                    @RouterOperation(
                            path = "/payment-services-favorites/user/{id}",
                            produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.GET,
                            beanClass = FavoriteHandler.class,
                            beanMethod = "findById",
                            operation = @Operation(
                                    summary = "Listar favoritos",
                                    description = "Listar favoritos en general",
                                    operationId = "findById",
                                    responses = {
                                            @ApiResponse(responseCode = "200",
                                                    description = "successful operation",
                                                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Favorite.class)))),
                                            @ApiResponse(
                                                    responseCode = "404",
                                                    description = "No se encontró elementos",
                                                    content = @Content(schema = @Schema(implementation = GlobalExceptionHandler.HttpError.class))
                                            )
                                    },
                                    parameters = {
                                            @Parameter(in = ParameterIn.PATH, name = "id", required = false)})

                    ),
                    @RouterOperation(path = "/payment-services-favorites",
                            produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.POST,
                            beanClass = FavoriteHandler.class,
                            beanMethod = "registerFavorite",
                            operation = @Operation(
                                    operationId = "registerFavorite",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "successful operation",
                                                    content = @Content(schema = @Schema(implementation = Favorite.class)))},
                                    parameters = {},
                                    requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = Favorite.class))))
                    )

            })
    public RouterFunction<ServerResponse> blogRoutes(FavoriteHandler favoriteHandler) {
        return RouterFunctions.nest(RequestPredicates.path("/payment-services-favorites"),
                RouterFunctions
                        .route(GET("/user/{id}"), favoriteHandler::findById)
                        .andRoute(POST("").and(contentType(APPLICATION_JSON)), favoriteHandler::registerFavorite)
        );
    }

}
