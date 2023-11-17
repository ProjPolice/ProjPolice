package com.projpolice.global.common.config;

import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.projpolice.global.common.error.info.ExceptionInfo;

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

    @Value("${springdoc.api-docs.version}")
    String springDocVersion;

    @Value("${springdoc.swagger-ui.with-credentials}")
    boolean withCredentials = false;

    /**
     * This method creates and returns an instance of OpenAPI.
     * It sets the title, version, and description of the API using the provided values.
     * It also adds security schemes and requirements to the API.
     *
     * @return An instance of OpenAPI with the specified settings and security requirements.
     */
    @Bean
    public OpenAPI openAPI() {
        StringBuilder descriptionBuilder = new StringBuilder();
        descriptionBuilder.append("[프로폴리스 API]\n\n");
        for (ExceptionInfo exception : ExceptionInfo.values()) {
            descriptionBuilder.append(
                String.format("%s : [HTTP : %d, Code : %d, Msg : %s]\n\n",
                    exception.name(),
                    exception.getStatus().value(),
                    exception.getCode(),
                    exception.getMessage()
                )
            );
        }

        Info info = new Info()
            .title("프로폴리스")
            .version(springDocVersion)
            .description(descriptionBuilder.toString());

        OpenAPI api = new OpenAPI().info(info);

        Components components = new Components();

        if (withCredentials) {
            components.addSecuritySchemes("Authorization", getJwtSecurityScheme());
            SecurityRequirement securityRequirement = new SecurityRequirement().addList("Authorization");
            api.addSecurityItem(securityRequirement);
        }

        api.components(components);
        return api;
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
