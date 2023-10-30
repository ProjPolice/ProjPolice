package com.projpolice.global.common.meta.domain;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonValue;
import com.projpolice.global.common.error.exception.MetaException;
import com.projpolice.global.common.error.info.ExceptionInfo;

import lombok.Getter;

/**
 * 세부 작업의 상태 ENUM
 */
@Getter
public enum TaskStatus {
	TODO(0, "TODO"),
	PROCEEDING(1, "PROCEEDING"),
	DONE(2, "DONE");

	@JsonValue
	private final int code;
	@JsonValue
	private final String name;

	TaskStatus(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static TaskStatus ofName(String name) {
		return Arrays.stream(TaskStatus.values())
			.filter(value -> value.getName().equals(name))
			.findAny()
			.orElseThrow(() -> new MetaException(ExceptionInfo.INVALID_METADATA));
	}
}
