package com.projpolice.global.common.config;

import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;

/**
 * This class is responsible for configuring Swagger in the application.
 */
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    @Value("${springdoc.version")
    String springDocVersion;

    /**
     * This method creates and returns an instance of OpenAPI.
     * It sets the title, version, and description of the API using the provided values.
     * It also adds security schemes and requirements to the API.
     *
     * @return An instance of OpenAPI with the specified settings and security requirements.
     */
    @Bean
    public OpenAPI openAPI(){
        Info info = new Info()
            .title("프로폴리스")
            .version(springDocVersion)
            .description("프로폴리스 API");

        Components components = new Components()
            .addSecuritySchemes("Authorization", getJwtSecurityScheme());

        SecurityRequirement securityRequirement = new SecurityRequirement()
            .addList("Authorization");

        return new OpenAPI()
            .info(info)
            .components(components)
            .addSecurityItem(securityRequirement);
    }

    private SecurityScheme getJwtSecurityScheme() {
        return new SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .scheme("Bearer")
            .bearerFormat("Authorization")
            .in(SecurityScheme.In.HEADER)
            .name(HttpHeaders.AUTHORIZATION);
    }
}
