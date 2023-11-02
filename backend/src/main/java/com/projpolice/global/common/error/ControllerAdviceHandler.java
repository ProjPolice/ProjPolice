package com.projpolice.global.common.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.projpolice.global.common.base.BaseResponse;
import com.projpolice.global.common.error.exception.BaseException;
import com.projpolice.global.common.error.info.ExceptionInfo;

import lombok.extern.slf4j.Slf4j;

/**
 * This class is a controller advice handler that handles general exceptions and returns a ResponseEntity with a BaseResponse object.
 * It is responsible for logging the exception, building the stack trace, and returning an internal server error response.
 *
 * <p>
 * This class is annotated with {@code @Slf4j} to enable logging using the SLF4J logging framework.
 * It is also annotated with {@code @RestControllerAdvice} to indicate that it is a controller advice class.
 * </p>
 *
 * <p>
 * The handler method in this class is annotated with {@code @ExceptionHandler(Exception.class)} to specify that it handles exceptions of type Exception.
 * It takes the exception as a parameter and returns a ResponseEntity with a BaseResponse object representing an internal server error.
 * </p>
 *
 * <p>
 * The handleGeneralException method logs the exception message and builds the stack trace.
 * It then creates a BaseResponse object with the appropriate code, message, and no data.
 * Finally, it returns a ResponseEntity with the BaseResponse object and the HTTP status code of Internal Server Error.
 * </p>
 */
@Slf4j
@RestControllerAdvice
public class ControllerAdviceHandler {

    /**
     * Handles general exceptions and returns a ResponseEntity with a BaseResponse object representing an internal server error.
     *
     * @param exception The exception that is being handled.
     * @return A ResponseEntity containing a BaseResponse object with the status, code, and message of the internal error.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Void>> handleGeneralException(Exception exception) {
        log.error("Uncaught Exception : {}", exception.getMessage());
        StringBuilder stackTrage = new StringBuilder();
        for (StackTraceElement elem : exception.getStackTrace()) {
            stackTrage.append(elem.toString()).append("\n");
        }
        log.error("{}", stackTrage);

        ExceptionInfo exceptionInfo = ExceptionInfo.INTERNAL_ERROR;
        return ResponseEntity.status(exceptionInfo.getStatus())
            .body(
                BaseResponse.<Void>builder()
                    .code(exceptionInfo.getCode())
                    .message(exceptionInfo.getMessage())
                    .build()
            );
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseResponse<Void>> handleBaseException(BaseException exception) {
        log.error("[ {} exception occurred ]", exception.getClass().getName());

        return ResponseEntity.status(exception.getStatus())
            .body(
                BaseResponse.<Void>builder()
                    .code(exception.getCode())
                    .message(exception.getMessage())
                    .build()
            );
    }
}
