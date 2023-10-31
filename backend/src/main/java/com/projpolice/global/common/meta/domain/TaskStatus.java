package com.projpolice.global.common.meta.domain;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;

/**
 * 세부 작업의 상태 ENUM
 */
@Getter
public enum TaskStatus {
    TODO(0),
    PROCEEDING(1),
    DONE(2);

    @JsonValue
    private final int code;

    TaskStatus(int code) {
        this.code = code;
    }
}
