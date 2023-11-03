package com.projpolice.global.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.projpolice.global.common.error.exception.ExceptionHandlerFilter;

import lombok.RequiredArgsConstructor;

/**
 * This class provides configuration for the security filter chain.
 * It enables web security and configures the security filter chain for the HttpSecurity object.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final ExceptionHandlerFilter exceptionHandlerFilter;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    /**
     * Configures the security filter chain for the given HttpSecurity object.
     *
     * @param http the HttpSecurity object to configure the security filter chain for
     * @return the configured SecurityFilterChain object
     * @throws Exception if an error occurs while configuring the security filter chain
     */
    @Bean
    protected SecurityFilterChain config(HttpSecurity http) throws Exception {
        return http.httpBasic(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)
            .cors(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.OPTIONS).permitAll()
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/projpolice-api/**").permitAll()
                .requestMatchers("/v3/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/users").authenticated()
                .requestMatchers(HttpMethod.PATCH, "/users").authenticated()
                .requestMatchers("/users/**").permitAll()
                .anyRequest().authenticated()
            )
            .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .headers(headers ->
                headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(exceptionHandlerFilter, JwtAuthenticationFilter.class)
            .getOrBuild();
    }
}
