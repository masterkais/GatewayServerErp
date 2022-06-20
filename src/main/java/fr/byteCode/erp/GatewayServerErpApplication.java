package fr.byteCode.erp;



import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableHystrix
public class GatewayServerErpApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerErpApplication.class, args);
    }

    @Bean
    RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/api/user**")
                        .filters(
                                f->f
                                        //cercuit breaker
                                        .hystrix(h->h.setName("api")
                                        )
                        )
                        .uri("lb://erp-monolithique")
                        .id("r1"))
                .route(r -> r.path("/api/group**")
                        .filters(
                                f->f
                                        //cercuit breaker
                                        .hystrix(h->h.setName("api")
                                        )
                        )
                        .uri("lb://erp-monolithique")
                        .id("r1"))
                .build();
    }
    }


