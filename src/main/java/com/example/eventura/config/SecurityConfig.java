package com.example.eventura.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable) // ✅ disable CSRF for APIs
                .authorizeExchange(exchange -> exchange
                        .anyExchange().permitAll() // ✅ let AuthGlobalFilter handle JWT logic
                )
                .build();
    }
}
