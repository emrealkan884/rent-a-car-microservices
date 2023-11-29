package com.turkcell.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
  @Bean
  public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
    serverHttpSecurity
        .csrf(ServerHttpSecurity.CsrfSpec::disable)
        .authorizeExchange(
            exchange ->
                exchange
                    // .pathMatchers("/eureka/**")
                    .pathMatchers("/eureka/**")
                    .permitAll()
                    .pathMatchers("/api/cars/**")
                    .authenticated()
                    .anyExchange()
                    .authenticated())
        .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtDecoder(customJwtDecoder())));

    return serverHttpSecurity.build();
  }

  @Bean
  public ReactiveJwtDecoder customJwtDecoder() {
    // Configure and return your JwtDecoder bean here
    return NimbusReactiveJwtDecoder.withIssuerLocation(
            "http://keycloak:8080/realms/rent-a-car-microservice")
        .build();
  }
}
