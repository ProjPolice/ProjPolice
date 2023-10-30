package com.projpolice.global.common.meta.converter;

import com.projpolice.global.common.meta.domain.TaskStatus;

import jakarta.persistence.AttributeConverter;

public class TaskStatusConverter implements AttributeConverter<TaskStatus, String> {
	@Override
	public String convertToDatabaseColumn(TaskStatus attribute) {
		return attribute.getName();
	}

	@Override
	public TaskStatus convertToEntityAttribute(String dbData) {
		return TaskStatus.ofName(dbData);
	}
}
