package com.tms.apigateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.CorsDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebSecurity
//@EnableWebFluxSecurity
public class SecurityConfiguration {
    private final String[] freeResourceUrls = {"/eureka/**","/swagger-ui.html",
            "/swagger-ui/**", "/swagger-resources/**",
            "/v3/api-docs/**", "/webjars/**",
            "/api-docs/**", "/aggregate/**"};
    @Bean
    public SecurityFilterChain springSecurityFilterChain(HttpSecurity http) throws Exception {
        http.cors(c->c.disable())
                .csrf(x->x.disable())
                .authorizeHttpRequests(
                        x->x.anyRequest().authenticated());

        http.oauth2ResourceServer(rs->
                        rs.jwt(jwtConfigurer -> {}))
                .sessionManagement(x->x.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        return http.build();
    }
}
