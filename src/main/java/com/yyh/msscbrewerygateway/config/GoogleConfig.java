package com.yyh.msscbrewerygateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

// @Profile("google")
// @Configuration
public class GoogleConfig {

    @Bean
    public RouteLocator googleRouteConfig(RouteLocatorBuilder builder) {
        return builder.routes()
                // initially will hit google.com/googlesearch2
                .route(r -> r.path("/googlesearch2")
                        // rewrite the path: /googlesearch2<segment> to /<segment>
                        // which is google.com/ when enter localhost:9090/googlesearch2
                        .filters(f -> f.rewritePath("/googlesearch2(?<segment>/?.*)", "/${segment}"))
                        .uri("https://google.com"))
                .build();
    }
}
