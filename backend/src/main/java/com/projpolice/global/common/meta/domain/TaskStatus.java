package com.projpolice.global.common.meta.domain;

import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.projpolice.global.common.error.exception.TaskException;
import com.projpolice.global.common.error.info.ExceptionInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 세부 작업의 상태 ENUM
 */
@Getter
@NoArgsConstructor
@Slf4j
public enum TaskStatus {
    TODO,
    PROCEEDING,
    DONE;

    @JsonCreator()
    public static TaskStatus ofName(String name) {
        return Stream.of(TaskStatus.values())
            .filter(t -> t.name().equals(name.toUpperCase()))
            .findFirst()
            .orElseThrow(() -> new TaskException(ExceptionInfo.INVALID_METADATA));
    }
}
