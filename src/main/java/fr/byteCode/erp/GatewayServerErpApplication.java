package fr.byteCode.erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableHystrix
@EnableDiscoveryClient
public class GatewayServerErpApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerErpApplication.class, args);
    }
    @Bean
    RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route((r)->r.path("/api/user/**").uri("lb://erp-monolithique"))
                .build();
    }
    // un bean qui s'occupe de faire les routes d'une maniére dynamique
    @Bean
    DiscoveryClientRouteDefinitionLocator dynamicRoutes (ReactiveDiscoveryClient
                                                                 rdc, DiscoveryLocatorProperties dlp) {
        return new DiscoveryClientRouteDefinitionLocator(rdc, dlp);
    }
}
