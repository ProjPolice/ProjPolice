package com.projpolice.global.common.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.projpolice.domain.user.service.JwtService;
import com.projpolice.global.common.error.exception.UnAuthorizedException;
import com.projpolice.global.common.error.info.ExceptionInfo;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;


/**
 * 매 호출마다 한 번씩 호출되는 'OncePerRequestFilter'를 상속받는다.
 * 'doFilterInternal'을 오버라이드하여 필터 로직을 구현한다.
 * JWT 토큰이 유효한지 확인하는 역할을 하고있는 필터이다.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    /**
     * Bearer 로 시작하는 Jwt token 을 찾는다.
     * 이후 토큰이 유효한지 검토한 뒤 유효하지 않다면 403 HTTP 응답을 한다.
     * 유효하다면 사용자 인증 절차를 진행한다.
     */
    @Override
    protected void doFilterInternal(
        @NotNull HttpServletRequest request,
        @NotNull HttpServletResponse response,
        @NotNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userId;

        if (authHeader == null || !authHeader.startsWith("Bearer ")
            || (request.getServletPath().equals("/users") && request.getMethod().equals("POST"))) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);

        userId = jwtService.extractUsername(jwt);

        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userId);
            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken (
                    userDetails,
                    null,
                    userDetails.getAuthorities()
                );
                authToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
