package com.example.eventura.gateway.config;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayDebugConfig {
    @Bean
    CommandLineRunner logRoutes(RouteLocator routeLocator) {
        return args -> routeLocator.getRoutes().subscribe(
                route -> System.out.println("🛣 Loaded route -> " + route.getId() + " : " + route.getUri() + " : " )
        );
    }
}

