package com.ha.java.Bmicroservice.config;

//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

//@Configuration
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http.csrf(Customizer.withDefaults())
////                .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
////                    @Override
////                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
////                        CorsConfiguration config = new CorsConfiguration();
////                        config.setAllowedOrigins(Collections.singletonList("http://localhost:8090"));
////                        config.setAllowedMethods(Collections.singletonList("*"));
////                        config.setAllowCredentials(true);
////                        config.setAllowedHeaders(Collections.singletonList("*"));
////                        config.setExposedHeaders(Arrays.asList("Authorization"));
////                        config.setMaxAge(3600L);
////                        return config;
////                    }}))
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/role/**").permitAll()
//                        )
//
//                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
//
//                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        );
//
//        return http.build();
//    }

}
