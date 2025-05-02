package com.tms.gateway.configurations;
import com.tms.gateway.core.constants.ApiConstants;
import com.tms.gateway.core.enums.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import java.util.stream.Stream;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final String[] freeResourceUrls = {"/eureka/**","/swagger-ui.html",
            "/swagger-ui/**", "/swagger-resources/**",
            "/v3/api-docs/**", "/webjars/**",
            "/api-docs/**", "/aggregate/**"};
    @Bean
    public SecurityFilterChain customFilterChain(HttpSecurity http) throws Exception {
        http.cors(c->c.disable())
                .csrf(c->c.disable())
                .authorizeHttpRequests(r->
                        r.requestMatchers(freeResourceUrls).permitAll()
                                .requestMatchers(ApiConstants.freeUrls).permitAll()
                                .requestMatchers(ApiConstants.onlyAdmin).hasRole(Role.ADMIN.name())
                                .requestMatchers(ApiConstants.onlyAdminAndPubliher).hasAnyRole(Role.ADMIN.name(),Role.PUBLISHER.name())
                                .requestMatchers(ApiConstants.onlyAdminPubliherReader).hasAnyRole(Role.ADMIN.name(),Role.PUBLISHER.name(), Role.READER.name())
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oa->oa.jwt(Customizer.withDefaults()))
                .sessionManagement(ss->ss.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }


    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        var converter = new JwtAuthenticationConverter();
        var jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        converter.setPrincipalClaimName("preferred_username");
        converter.setJwtGrantedAuthoritiesConverter(jwt->{
            var authorities = jwtGrantedAuthoritiesConverter.convert(jwt);
            var roles = jwt.getClaimAsStringList("app_news_roles");
            return Stream.concat(authorities.stream(),
                    roles.stream()
                            .filter(r->r.startsWith("ROLE_"))
                            .map(SimpleGrantedAuthority::new)
                            .map(GrantedAuthority.class::cast)).toList();

        });
        return converter;
    }
}
