package com.projpolice.global.common.error.exception;

import com.projpolice.global.common.error.info.ExceptionInfo;

/**
 * The NotFoundException class is a custom exception class that extends the BaseException class.
 * It is used to indicate that a resource or object is not found in the system.
 */
public class TaskException extends BaseException {

    public TaskException(ExceptionInfo exceptionInfo) {
        super(exceptionInfo);
    }
}
