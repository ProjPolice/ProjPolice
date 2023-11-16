package com.projpolice.global.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * An implementation of WebMvcConfigurer that adds Cross-Origin Resource Sharing (CORS) mappings
 * to the provided CorsRegistry.
 *
 * This class is responsible for defining the CORS configuration for the Spring MVC application.
 * It allows the application to handle cross-origin requests by specifying the allowed origins,
 * methods, headers, max age, and whether credentials should be allowed or not.
 *
 * The CORS configuration is provided through a property called "spring.projpolice.cors". This property
 * is expected to be a string array containing the allowed origins.
 *
 * Usage:
 * 1. Create an instance of CorsConfig.
 * 2. Inject the allowed origins using the `corsPaths` property.
 * 3. Register the CorsConfig instance as a bean in the Spring application context.
 *
 * Example:
 *
 *     // Application properties
 *     spring.projpolice.cors=https://example.com,http://localhost:8080
 *
 *     // CorsConfig class
 *     @Configuration
 *     public class CorsConfig implements WebMvcConfigurer {
 *
 *         @Value("${spring.projpolice.cors}")
 *         private String[] corsPaths;
 *
 *         @Override
 *         public void addCorsMappings(CorsRegistry registry) {
 *             registry.addMapping("/**")
 *                 .allowedOrigins(corsPaths)
 *                 .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
 *                 .allowedHeaders("*")
 *                 .maxAge(3000)
 *                 .allowCredentials(true);
 *         }
 *     }
 *
 * Note: This class is typically used in a Spring MVC application to configure CORS. The CorsConfig class
 * can be registered as a bean in the application's configuration to enable CORS support.
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Value("${spring.projpolice.cors}")
    private String[] corsPaths;

    /**
     * Adds Cross-Origin Resource Sharing (CORS) mappings to the provided CorsRegistry.
     *
     * @param registry the CorsRegistry instance to which the CORS mappings will be added
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins(corsPaths)
            .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
            .allowedHeaders("*")
            .exposedHeaders("Content-Disposition")
            .maxAge(3000)
            .allowCredentials(true);
    }
}
