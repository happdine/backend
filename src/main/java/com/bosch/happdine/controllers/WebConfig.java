package com.seuprojeto.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Habilita CORS para todos os endpoints
                .allowedOrigins("http://localhost:5173")  // Permite o frontend específico
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Métodos permitidos
                .allowedHeaders("*")  // Permitir todos os cabeçalhos
                .allowCredentials(true);  // Permitir cookies/sessões se necessário
    }
}
