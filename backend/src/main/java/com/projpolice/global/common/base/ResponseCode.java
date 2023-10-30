package com.projpolice.global.common.base;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;

/**
 * This enum represents different response codes that can be returned from a server.
 **/
@Getter
public enum ResponseCode {
    /**
     * TODO: Please add response codes used in {@link BaseResponse}
     *
     * This code represents result of request.
     */
    OK(0),
    InternalServerError(1);

    @JsonValue
    private final int code;

    ResponseCode(int code) {
        this.code = code;
    }

}
