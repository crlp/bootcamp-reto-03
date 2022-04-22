package bcp.bootcamp.paymentservices.routers;

import bcp.bootcamp.paymentservices.entities.Service;
import bcp.bootcamp.paymentservices.exceptions.GlobalExceptionHandler;
import bcp.bootcamp.paymentservices.handlers.OperationHandler;
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
                            path = "/payment-services/channel/{id}",
                            produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.GET,
                            beanClass = OperationHandler.class,
                            beanMethod = "findByChannel",
                            operation = @Operation(
                                    summary = "Listar favoritos",
                                    description = "Listar favoritos en general",
                                    operationId = "findByChannel",
                                    responses = {
                                            @ApiResponse(responseCode = "200",
                                                    description = "successful operation",
                                                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Service.class)))),
                                            @ApiResponse(
                                                    responseCode = "404",
                                                    description = "No se encontró elementos",
                                                    content = @Content(schema = @Schema(implementation = GlobalExceptionHandler.HttpError.class))
                                            )
                                    },
                                    parameters = {
                                            @Parameter(in = ParameterIn.PATH, name = "id", required = false)})

                    ),
                    @RouterOperation(path = "/payment-services",
                            produces = {MediaType.APPLICATION_JSON_VALUE},
                            method = RequestMethod.POST,
                            beanClass = OperationHandler.class,
                            beanMethod = "save",
                            operation = @Operation(
                                    operationId = "save",
                                    responses = {
                                            @ApiResponse(responseCode = "200", description = "successful operation",
                                                    content = @Content(schema = @Schema(implementation = bcp.bootcamp.paymentservices.entities.Operation.class)))},
                                    parameters = {},
                                    requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = bcp.bootcamp.paymentservices.entities.Operation.class))))
                    )

            })
    public RouterFunction<ServerResponse> blogRoutes(OperationHandler operationHandler) {
        return RouterFunctions.nest(RequestPredicates.path("/payment-services"),
                RouterFunctions
                        .route(GET("/channel/{channel}"), operationHandler::findByChannel)
                        .andRoute(POST("").and(contentType(APPLICATION_JSON)), operationHandler::save)
        );
    }

}
