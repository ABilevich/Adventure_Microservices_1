package com.microservices.apigateway.routes;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.setPath;
import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class Routes {

    @Bean
    public RouterFunction<ServerResponse> productServiceRoute(){
        return route("product_service")
            .route(RequestPredicates.GET("/api/products"), HandlerFunctions.http())
            .before(uri("http://localhost:8080"))
            .build();
    }

    @Bean
    public RouterFunction<ServerResponse> productServiceSwaggerRoute(){
        return route("product_service_swagger")
            .route(RequestPredicates.GET("/aggregate/product-service/v3/api-docs"), HandlerFunctions.http())
            .before(uri("http://localhost:8080"))
            .before(setPath("/api-docs"))
            .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderServiceRoute(){
        return route("order_service")
            .route(RequestPredicates.POST("/api/orders"), HandlerFunctions.http())
            .before(uri("http://localhost:8081"))
            .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderServiceSwaggerRoute(){
        return route("order_service_swagger")
            .route(RequestPredicates.GET("/aggregate/order-service/v3/api-docs"), HandlerFunctions.http())
            .before(uri("http://localhost:8081"))
            .before(setPath("/api-docs"))
            .build();
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryServiceRoute(){
        return route("inventory_service")
            .route(RequestPredicates.GET("/api/inventory"), HandlerFunctions.http())
            .before(uri("http://localhost:8082"))
            .build();
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryServiceSwaggerRoute(){
        return route("inventory_service_swagger")
            .route(RequestPredicates.GET("/aggregate/inventory-service/v3/api-docs"), HandlerFunctions.http())
            .before(uri("http://localhost:8082"))
            .before(setPath("/api-docs"))
            .build();
    }
}
