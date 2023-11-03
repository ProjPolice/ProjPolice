package com.projpolice.global.common.error.info;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ExceptionInfo {

    // Common
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 1000, "서버 에러입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 1001, "권한이 존재하지 않습니다."),

    // User
    UNAUTHORIZED_ERROR(HttpStatus.UNAUTHORIZED, 2000, "권한이 부족합니다."),
    INVALID_USER(HttpStatus.BAD_REQUEST, 2001, "존재하지 않는 사용자 입니다."),
    LOGIN_FAIL(HttpStatus.OK, 1002, "사용자 로그인에 실패하였습니다."), // 로그인 실패는 OK에 code로 return

    // Meta
    INVALID_METADATA(HttpStatus.BAD_REQUEST, 3000, "유효하지 않은 메타데이터 입니다."),

    // Project
    INVALID_PROJECT(HttpStatus.BAD_REQUEST, 4000, "존재하지 않는 프로젝트입니다."),
    INVALID_PROJECT_INSERTION_PARAM(HttpStatus.BAD_REQUEST, 4001, "프로젝트 입력 실패 : 유효하지 않은 매개변수"),

    // User Project
    INVALID_USER_PROJECT(HttpStatus.BAD_REQUEST, 5000, "유효하지 않은 프로젝트 멤버입니다."),

    // Epic
    INVALID_EPIC(HttpStatus.BAD_REQUEST, 6000, "존재하지 않는 할 일입니다."),

    // Oracle
    FAILED_FILE_UPLOAD(HttpStatus.BAD_REQUEST, 7000, "파일 업로드를 실패했습니다."),
    FAILED_FILE_DOWNLOAD(HttpStatus.BAD_REQUEST, 7001, "파일 불러오기를 실패했습니다."),
    FAILED_FILE_DELETE(HttpStatus.BAD_REQUEST, 7001, "파일 삭제를 실패했습니다."),
    ;

    private final HttpStatus status;
    private final Integer code;
    private final String message;

    ExceptionInfo(HttpStatus status, Integer code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
