package com.projpolice.global.common.error.info;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ExceptionInfo {
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 9999, "서버 에러입니다."),
    // Meta
    INVALID_METADATA(HttpStatus.BAD_REQUEST, 1900, "유효하지 않은 메타데이터 입니다."),
    INVALID_PROJECT(HttpStatus.BAD_REQUEST, 1901, "존재하지 않는 프로젝트입니다.");


    private final HttpStatus status;
    private final Integer code;
    private final String message;

    ExceptionInfo(HttpStatus status, Integer code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
