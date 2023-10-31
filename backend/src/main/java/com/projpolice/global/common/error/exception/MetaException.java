package com.projpolice.global.common.error.exception;

import com.projpolice.global.common.error.info.ExceptionInfo;

/**
 * The NotFoundException class is a custom exception class that extends the BaseException class.
 * It is used to indicate that a resource or object is not found in the system.
 */
public class MetaException extends BaseException {

	public MetaException(ExceptionInfo exceptionInfo) {
		super(exceptionInfo.getStatus(), exceptionInfo.getCode(), exceptionInfo.getMessage());
	}
}
