package com.ha.java.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedMethods("*") // allow all methods
                .maxAge(3600L)
                .allowedHeaders("*") // allow all headers
                .allowedOrigins("http://localhost:3000");
    }
}