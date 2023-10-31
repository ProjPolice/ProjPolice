package com.projpolice.global.common.error.exception;

import org.springframework.http.HttpStatus;

import com.projpolice.global.common.base.BaseResponse;

import lombok.Builder;
import lombok.Getter;

/**
 * {@link BaseException} is a custom exception class that extends the {@link RuntimeException} class.
 * It is used to handle application-specific exceptions.
 */
@Builder
@Getter
public class BaseException extends RuntimeException {
    HttpStatus status;
    Integer code;
    String message;
    String log;

    BaseException(HttpStatus status, Integer code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    BaseException(HttpStatus status, Integer code, String message, String log) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.log = log;
    }

    BaseResponse<Void> toResponse() {
        return BaseResponse.<Void>builder()
            .code(code)
            .message(message)
            .build();
    }
}
