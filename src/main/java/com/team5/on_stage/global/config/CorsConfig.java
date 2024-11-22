package com.team5.on_stage.global.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;
import java.util.List;

public class CorsConfig implements CorsConfigurationSource {

    @Override
    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

        CorsConfiguration config = new CorsConfiguration();

        //config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
        config.setAllowedOrigins(List.of("http://localhost:3000","http://localhost:8080"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        config.setExposedHeaders(Collections.singletonList("Authorization"));
        config.setAllowCredentials(true);

        return config;
    }
}
