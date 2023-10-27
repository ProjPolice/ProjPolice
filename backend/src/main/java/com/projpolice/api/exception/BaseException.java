package com.projpolice.api.exception;

import com.projpolice.common.model.BaseResponse;
import com.projpolice.common.model.ResponseCode;

import lombok.Builder;
import lombok.Getter;

/**
 * {@link BaseException} is a custom exception class that extends the {@link RuntimeException} class.
 * It is used to handle application-specific exceptions.
 */
@Builder
@Getter
public class BaseException extends RuntimeException {
    ResponseCode code;
    String message;
    String log;

    BaseException(ResponseCode code, String message) {
        this.code = code;
        this.message = message;
    }

    BaseException(ResponseCode code, String message, String log) {
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
