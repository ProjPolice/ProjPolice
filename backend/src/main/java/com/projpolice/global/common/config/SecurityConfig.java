package com.projpolice.global.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * This class provides configuration for the security filter chain.
 * It enables web security and configures the security filter chain for the HttpSecurity object.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configures the security filter chain for the given HttpSecurity object.
     *
     * @param http the HttpSecurity object to configure the security filter chain for
     * @return the configured SecurityFilterChain object
     * @throws Exception if an error occurs while configuring the security filter chain
     */
    @Bean
    protected SecurityFilterChain config(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
            .cors(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.OPTIONS).permitAll()
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/projpolice-api/**").permitAll()
                .requestMatchers("/v3/**").permitAll()
            )
            .headers(headers ->
                headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
            // .addFilterBefore() // jwt
            // .addFilterBefore() // exception Handler
            .getOrBuild();
    }


}
