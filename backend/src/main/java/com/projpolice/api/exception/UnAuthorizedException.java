package com.projpolice.api.exception;

import com.projpolice.common.model.ResponseCode;

/**
 * Represents an exception thrown when an unauthorized access is detected.
 * This exception is a subclass of the BaseException class.
 */
public class UnAuthorizedException extends BaseException {
    public UnAuthorizedException(ResponseCode code, String message) {
        super(code, message);
    }

    public UnAuthorizedException(ResponseCode code, String message, String log){
        super(code, message, log);
    }
}
