package com.projpolice.global.common.error.exception;

import com.projpolice.global.common.error.info.ExceptionInfo;

/**
 * Exception representing a bad request error.
 *
 * This exception is thrown when a client's request is invalid or cannot be processed successfully due to a bad request.
 *
 * It extends the {@link BaseException} class and provides constructors to set the response code, error message, and optional log.
 *
 * @since 1.0
 */
public class BadRequestException extends BaseException {
    public BadRequestException(ExceptionInfo exceptionInfo) {
        super(exceptionInfo.getStatus(), exceptionInfo.getCode(), exceptionInfo.getMessage());
    }
}
