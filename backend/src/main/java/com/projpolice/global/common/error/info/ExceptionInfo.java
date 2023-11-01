package com.projpolice.global.common.error.info;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ExceptionInfo {
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 9999, "서버 에러입니다."),
    UNAUTHORIZED_ERROR(HttpStatus.UNAUTHORIZED, 9998, "권한이 부족합니다."),

    // Meta
    INVALID_METADATA(HttpStatus.BAD_REQUEST, 1900, "유효하지 않은 메타데이터 입니다."),

    // Project
    INVALID_PROJECT(HttpStatus.BAD_REQUEST, 1901, "존재하지 않는 프로젝트입니다."),
    INVALID_PROJECT_INSERTION_PARAM(HttpStatus.BAD_REQUEST, 1905, "프로젝트 입력 실패 : 유효하지 않은 매개변수"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 1902, "권한이 존재하지 않습니다."),

    // User Project
    INVALID_USER_PROJECT(HttpStatus.BAD_REQUEST, 2500, "유효하지 않은 프로젝트 멤버입니다."),

    // Epic
    INVALID_EPIC(HttpStatus.BAD_REQUEST, 1800, "존재하지 않는 할 일입니다."),

    // User
    INVALID_USER(HttpStatus.BAD_REQUEST, 3000, "존재하지 않는 사용자 입니다.")
    ,;

    private final HttpStatus status;
    private final Integer code;
    private final String message;

    ExceptionInfo(HttpStatus status, Integer code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
