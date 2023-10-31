package com.projpolice.global.common.error.exception;

import com.projpolice.global.common.error.info.ExceptionInfo;

/**
 * Represents an exception thrown when an unauthorized access is detected.
 * This exception is a subclass of the BaseException class.
 */
public class UnAuthorizedException extends BaseException {
    public UnAuthorizedException(ExceptionInfo exceptionInfo) {
        super(exceptionInfo.getStatus(), exceptionInfo.getCode(), exceptionInfo.getMessage());
    }
}
