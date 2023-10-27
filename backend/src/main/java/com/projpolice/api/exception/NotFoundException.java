package com.projpolice.api.exception;

import com.projpolice.common.model.ResponseCode;

/**
 * The NotFoundException class is a custom exception class that extends the BaseException class.
 * It is used to indicate that a resource or object is not found in the system.
 */
public class NotFoundException extends BaseException {
    public NotFoundException(ResponseCode code, String message) {
        super(code, message);
    }

    public NotFoundException(ResponseCode code, String message, String log){
        super(code, message, log);
    }
}
