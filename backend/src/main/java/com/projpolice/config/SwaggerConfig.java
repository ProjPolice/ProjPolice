package com.projpolice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    @Value("${springdoc.version")
    String springDocVersion;

    @Bean
    public OpenAPI openAPI(){
        Info info = new Info()
            .title("프로폴리스")
            .version(springDocVersion)
            .description("프로폴리스 API");

        Components components = new Components()
            .addSecuritySchemes("Authroization", getJwtSecurityScheme());

        SecurityRequirement securityRequirement = new SecurityRequirement()
            .addList("Authorization");

        return new OpenAPI()
            .info(info)
            .components(components)
            .addSecurityItem(securityRequirement);
    }

    private SecurityScheme getJwtSecurityScheme() {
        return new SecurityScheme()
            .type(SecurityScheme.Type.APIKEY)
            .in(SecurityScheme.In.HEADER)
            .name("Authroization");
    }
}
