package com.projpolice.api.exception;

import com.projpolice.common.model.ResponseCode;

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
    public BadRequestException(ResponseCode code, String message) {
        super(code, message);
    }

    public BadRequestException(ResponseCode code, String message, String log){
        super(code, message, log);
    }
}
