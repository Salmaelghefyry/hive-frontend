package com.hive.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // User Service Routes
                .route("user-service", r -> r.path("/api/v1/auth/**", "/api/v1/users/**")
                        .uri("lb://user-service"))
                
                // Project Service Routes
                .route("project-service", r -> r.path("/api/v1/project/**")
                        .uri("lb://project-service"))
                
                // Task Service Routes
                .route("task-service", r -> r.path("/api/v1/task/**")
                        .uri("lb://task-service"))
                
                // Comment Service Routes
                .route("comment-service", r -> r.path("/api/v1/comment/**")
                        .uri("lb://comment-service"))
                
                .build();
    }
}
