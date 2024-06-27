package com.example.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CorsConfigTest {

    @InjectMocks
    private CorsConfig corsConfig;

    @Mock
    private CorsRegistry corsRegistry;

    @Test
    public void testCorsConfigurerBean() {
        // Obtain the WebMvcConfigurer from the corsConfig
        WebMvcConfigurer configurer = corsConfig.corsConfigurer();

        // Ensure the returned object is not null
        assertNotNull(configurer, "The WebMvcConfigurer bean should not be null.");

        // Use the configurer to call addCorsMappings
        configurer.addCorsMappings(corsRegistry);

        // Verify the interactions with corsRegistry
        verify(corsRegistry).addMapping("/**");
        verify(corsRegistry.addMapping("/**")).allowedOrigins("http://localhost:3000");
        verify(corsRegistry.addMapping("/**")).allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}
