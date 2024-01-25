package com.ha.java.config;

import com.ha.java.constants.RoleConstants;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class ProjectSecurityConfig {

    private static final String EMPLOYEE_URL = "/employees/**";

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeyCloakRoleConverter());

        http.csrf(CsrfConfigurer::disable)
//              .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setExposedHeaders(Arrays.asList("Authorization"));
                config.setMaxAge(3600L);
                return config;
            }
        }))
//                .csrf(csrf -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/contact")
//                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
//                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> requests
                                .requestMatchers("/keycloak/**", "/verify-account/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/employees", "/testing").authenticated()

                        .requestMatchers(HttpMethod.POST,"/employees")
                            .hasAnyRole(String.valueOf(RoleConstants.USER), String.valueOf(RoleConstants.ADMIN))

                        .requestMatchers(HttpMethod.DELETE, EMPLOYEE_URL)
                            .hasRole(String.valueOf(RoleConstants.ADMIN))
                        .requestMatchers(HttpMethod.GET, EMPLOYEE_URL)
                        .hasAnyRole(String.valueOf(RoleConstants.ADMIN), String.valueOf(RoleConstants.USER))

                        .requestMatchers(HttpMethod.PUT, EMPLOYEE_URL)
                            .hasRole(String.valueOf(RoleConstants.ADMIN))

                        )

                .oauth2ResourceServer(oauth2 -> oauth2.jwt(j -> j.jwtAuthenticationConverter(jwtAuthenticationConverter)));
        return http.build();
    }

}
