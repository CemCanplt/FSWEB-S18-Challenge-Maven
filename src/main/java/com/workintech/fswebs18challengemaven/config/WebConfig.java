package com.workintech.fswebs18challengemaven.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Spring'e bunun bir yapılandırma sınıfı olduğunu belirtir
public class WebConfig {

    // CORS yapılandırması için bir Bean tanımlar
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/workintech/**") // CORS'un uygulanacağı URL desenleri
                        .allowedOrigins("http://localhost:3000", "https://codepen.io") // İzin verilen originler (frontend adresleri)
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // İzin verilen HTTP metotları
                        .allowedHeaders("*"); // İzin verilen HTTP başlıkları
                // .allowCredentials(true); // Eğer çerez (cookie) kullanıyorsanız bu satırı aktif edin
            }
        };
    }
}