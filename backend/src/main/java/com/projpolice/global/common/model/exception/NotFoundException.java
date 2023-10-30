package com.projpolice.global.common.model.exception;

import com.projpolice.global.common.base.ResponseCode;

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
