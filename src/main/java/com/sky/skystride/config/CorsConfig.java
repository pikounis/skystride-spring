package com.sky.skystride.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // Allow your frontend origin
                .allowedMethods("*")                     // Allow all HTTP methods
                .allowedHeaders("*")                     // Allow all headers
                .allowCredentials(true);                 // Allow credentials (e.g., cookies)
    }
}