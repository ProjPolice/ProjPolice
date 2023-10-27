package com.projpolice.api.exception;

import javax.ws.rs.core.MediaType;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projpolice.common.model.ResponseCode;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * An exception handling filter that handles various types of exceptions and returns appropriate error responses.
 * It extends OncePerRequestFilter and overrides the doFilterInternal method to provide the exception handling logic.
 */
@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    /**
     * This method is used to filter the incoming requests and responses.
     * It catches specific exceptions and handles them accordingly.
     *
     * @param request The HttpServletRequest object representing the incoming request.
     * @param response The HttpServletResponse object representing the response to be sent.
     * @param filterChain The FilterChain object used to continue the request/response process.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) {
        try {
            filterChain.doFilter(request, response);
        } catch (BadRequestException exception) {
            responseError(request, response, HttpStatus.BAD_REQUEST.value(), exception);
        } catch (NotFoundException exception) {
            responseError(request, response, HttpStatus.NOT_FOUND.value(), exception);
        } catch (UnAuthorizedException exception) {
            responseError(request, response, HttpStatus.UNAUTHORIZED.value(), exception);
        } catch (Exception exception) {
            responseError(request, response, 500,
                BaseException.builder()
                    .code(ResponseCode.InternalServerError)
                    .message("Internal Server Error").build());
        }
    }

    /**
     * This method is called to construct and send an error response.
     *
     * @param request The HttpServletRequest object representing the incoming request.
     * @param response The HttpServletResponse object representing the response to be sent.
     * @param statusCode The HTTP status code for the error response.
     * @param exception The BaseException object representing the specific exception that occurred.
     */
    private void responseError(HttpServletRequest request, HttpServletResponse response, int statusCode,
        BaseException exception) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            response.setStatus(statusCode);
            response.setContentType(MediaType.APPLICATION_JSON);
            response.setHeader("Access-Control", request.getHeader("Origin"));
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setHeader("Access-Control-Max-Age", "0");
            response.setHeader("Access-Control-Allow-Headers",
                "Origin, X-Requested-With, Content-Type, Accept, Key, Authorization");
            response.getWriter().write(objectMapper.writeValueAsString(exception.toResponse()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
